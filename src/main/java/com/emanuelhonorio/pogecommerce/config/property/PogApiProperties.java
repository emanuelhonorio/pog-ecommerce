package com.emanuelhonorio.pogecommerce.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("pog")
public class PogApiProperties {

	private final Stripe stripe = new Stripe();

	public Stripe getStripe() {
		return stripe;
	}

	public static class Stripe {
		private String apiKey;
		private String webhookSecret;

		public String getApiKey() {
			return apiKey;
		}

		public void setApiKey(String apiKey) {
			this.apiKey = apiKey;
		}

		public String getWebhookSecret() {
			return webhookSecret;
		}

		public void setWebhookSecret(String webhookSecret) {
			this.webhookSecret = webhookSecret;
		}

	}
}
