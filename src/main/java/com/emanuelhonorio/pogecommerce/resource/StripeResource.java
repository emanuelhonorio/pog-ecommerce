package com.emanuelhonorio.pogecommerce.resource;

import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.emanuelhonorio.pogecommerce.config.property.PogApiProperties;
import com.emanuelhonorio.pogecommerce.dto.CompraDTO;
import com.emanuelhonorio.pogecommerce.model.Compra;
import com.emanuelhonorio.pogecommerce.model.ItemCompra;
import com.emanuelhonorio.pogecommerce.model.PaymentIntentPendingTracker;
import com.emanuelhonorio.pogecommerce.model.Usuario;
import com.emanuelhonorio.pogecommerce.model.enums.StatusCompraEnum;
import com.emanuelhonorio.pogecommerce.repository.PaymentIntentPendingTrackerRepository;
import com.emanuelhonorio.pogecommerce.repository.UsuarioRepository;
import com.emanuelhonorio.pogecommerce.service.CompraService;
import com.google.gson.JsonSyntaxException;
import com.stripe.Stripe;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.exception.StripeException;
import com.stripe.model.Event;
import com.stripe.model.EventDataObjectDeserializer;
import com.stripe.model.PaymentIntent;
import com.stripe.model.StripeObject;
import com.stripe.model.checkout.Session;
import com.stripe.net.Webhook;
import com.stripe.param.checkout.SessionCreateParams;
import com.stripe.param.checkout.SessionCreateParams.Builder;
import com.stripe.param.checkout.SessionCreateParams.Locale;

@RestController
public class StripeResource {

	@Autowired
	private CompraService compraService;

	@Autowired
	private PaymentIntentPendingTrackerRepository paymentIntentTrackerRepository;

	@Autowired
	private PogApiProperties pogProperties;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@PostConstruct
	public void init() {
		Stripe.apiKey = pogProperties.getStripe().getApiKey();

	}

	@PostMapping("/webhook")
	public ResponseEntity<?> handleStripeWebhook(@RequestBody String payload,
			@RequestHeader("Stripe-Signature") String sigHeader) {

		Event event = null;

		try {
			event = Webhook.constructEvent(payload, sigHeader, pogProperties.getStripe().getWebhookSecret());
		} catch (JsonSyntaxException e) {
			// Invalid payload
			return ResponseEntity.badRequest().build();
		} catch (SignatureVerificationException e) {
			// Invalid signature
			return ResponseEntity.badRequest().build();
		}

		// Deserialize the nested object inside the event
		EventDataObjectDeserializer dataObjectDeserializer = event.getDataObjectDeserializer();
		StripeObject stripeObject = null;
		if (dataObjectDeserializer.getObject().isPresent()) {
			stripeObject = dataObjectDeserializer.getObject().get();
		} else {

			System.out.println(dataObjectDeserializer.getObject().isPresent());
			// Deserialization failed, probably due to an API version mismatch.
			return ResponseEntity.badRequest().build();
		}

		// Handle the event
		PaymentIntent paymentIntent = null;
		switch (event.getType()) {
		case "payment_intent.succeeded": {
			paymentIntent = (PaymentIntent) stripeObject;
			handlePaymentIntentSucceeded(paymentIntent);
			break;
		}
		case "payment_intent.payment_failed": {
			paymentIntent = (PaymentIntent) stripeObject;
			// Then define and call a method to handle the successful attachment of a
			// PaymentMethod.
			handlePaymentIntentFailed(paymentIntent);
			break;
		}
		// ... handle other event types
		default:
			System.out.println("Unhandled event type: " + event.getType());
		}

		return ResponseEntity.ok(paymentIntent);
	}

	@PostMapping("/create-checkout-session")
	public HashMap<String, String> createCheckoutSession(@RequestBody @Valid CompraDTO compraDTO, Principal principal)
			throws StripeException {

		Optional<Usuario> usuarioOpt = usuarioRepository.findByEmailIgnoreCase(principal.getName());
		String myDomain = "http://localhost:4200";

		Builder builder = SessionCreateParams.builder().addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
				.setMode(SessionCreateParams.Mode.PAYMENT).setLocale(Locale.PT_BR)
				.setCustomerEmail(usuarioOpt.get().getEmail()).setSuccessUrl(myDomain + "/pedidos?success=true")
				.setCancelUrl(myDomain + "/checkout?canceled=true");

		// compra com status pending - reserva estoque
		Compra compra = compraService.comprar(usuarioOpt.get(), compraDTO, true);

		for (ItemCompra item : compra.getItems()) {
			Long unitAmount = item.getSubtotal().divide(new BigDecimal(item.getQuantidade()))
					.multiply(new BigDecimal(100)).longValue();
			String productImage = item.getProduto().getFotos().get(0).getImageUrl();

			builder.addLineItem(SessionCreateParams.LineItem.builder().setQuantity(item.getQuantidade())
					.setPriceData(SessionCreateParams.LineItem.PriceData.builder().setCurrency("brl")
							.setUnitAmount(unitAmount)
							.setProductData(SessionCreateParams.LineItem.PriceData.ProductData.builder()
									.setName(item.getProduto().getNome())
									.setDescription(item.getProduto().getDescricao()).addImage(productImage).build())
							.build())
					.build());
		}

		SessionCreateParams params = builder.build();
		Session session = Session.create(params);

		try {
			createPaymentIntentPendingTracker(compra.getId(), session.getPaymentIntent());
		} catch (Exception ex) {
		}

		HashMap<String, String> responseData = new HashMap<String, String>();
		responseData.put("id", session.getId());

		return responseData;
	}

	private PaymentIntentPendingTracker createPaymentIntentPendingTracker(Long compraId, String paymentIntentId) {
		// create payment intent tracker
		PaymentIntentPendingTracker tracker = new PaymentIntentPendingTracker();
		tracker.setCompraId(compraId);
		tracker.setCreatedAt(LocalDateTime.now());
		tracker.setPaymentIntentId(paymentIntentId);

		return paymentIntentTrackerRepository.save(tracker);
	}

	private void handlePaymentIntentFailed(PaymentIntent paymentIntent) {
		Optional<PaymentIntentPendingTracker> trackerOpt = this.paymentIntentTrackerRepository
				.findById(paymentIntent.getId());
		if (trackerOpt.isEmpty()) {
			return;
		}
		PaymentIntentPendingTracker tracker = trackerOpt.get();

		compraService.cancelarCompra(tracker.getCompraId());
		paymentIntentTrackerRepository.delete(tracker);
	}

	private void handlePaymentIntentSucceeded(PaymentIntent paymentIntent) {
		Optional<PaymentIntentPendingTracker> trackerOpt = this.paymentIntentTrackerRepository
				.findById(paymentIntent.getId());
		if (trackerOpt.isEmpty()) {
			return;
		}
		PaymentIntentPendingTracker tracker = trackerOpt.get();

		compraService.atualizarStatus(tracker.getCompraId(), StatusCompraEnum.DONE);
		paymentIntentTrackerRepository.delete(tracker);
	}
}
