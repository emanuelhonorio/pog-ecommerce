package com.emanuelhonorio.pogecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emanuelhonorio.pogecommerce.model.PaymentIntentPendingTracker;

public interface PaymentIntentPendingTrackerRepository extends JpaRepository<PaymentIntentPendingTracker, String> {

}
