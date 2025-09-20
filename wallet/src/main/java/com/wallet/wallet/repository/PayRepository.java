package com.wallet.wallet.repository;

import com.wallet.wallet.model.PayOperation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PayRepository extends JpaRepository<PayOperation, UUID> {
    Optional<PayOperation> findById(UUID uuid);
}
