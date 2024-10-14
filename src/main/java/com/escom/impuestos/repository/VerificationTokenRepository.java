package com.escom.impuestos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.escom.impuestos.model.VerificationTokenEntity;

public interface VerificationTokenRepository extends JpaRepository<VerificationTokenEntity, Long> {
    VerificationTokenEntity findByToken(String token);
}
