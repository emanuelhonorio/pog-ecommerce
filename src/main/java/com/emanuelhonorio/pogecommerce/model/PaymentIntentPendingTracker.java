package com.emanuelhonorio.pogecommerce.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "payment_intent_pending_tracker")
public class PaymentIntentPendingTracker {

	@Id
	@Column(name = "payment_intent_id")
	public String paymentIntentId;

	@Column(name = "compra_id")
	public Long compraId;

	@Column(name = "created_at")
	public LocalDateTime createdAt;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((paymentIntentId == null) ? 0 : paymentIntentId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PaymentIntentPendingTracker other = (PaymentIntentPendingTracker) obj;
		if (paymentIntentId == null) {
			if (other.paymentIntentId != null)
				return false;
		} else if (!paymentIntentId.equals(other.paymentIntentId))
			return false;
		return true;
	}

	public String getPaymentIntentId() {
		return paymentIntentId;
	}

	public void setPaymentIntentId(String paymentIntentId) {
		this.paymentIntentId = paymentIntentId;
	}

	public Long getCompraId() {
		return compraId;
	}

	public void setCompraId(Long compraId) {
		this.compraId = compraId;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

}
