package com.escom.impuestos.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "verification_tokens")
public class VerificationTokenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "token")
    private String token;

    @Column(name = "expiry_date")
    private LocalDateTime expiry_date;

    @OneToOne
    @JoinColumn(nullable = false, name = "id_usuario")
    private UserEntity user;

    public VerificationTokenEntity() {
    }

    public VerificationTokenEntity(String token, UserEntity user) {
        this.token = token;
        this.user = user;
        this.expiry_date = LocalDateTime.now().plusHours(24);  // Caduca en 24 horas
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(this.expiry_date);
    }

    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getToken() {
        return token;
    }
    
    public void setToken(String token) {
        this.token = token;
    }
    
    public LocalDateTime getExpiryDate() {
        return expiry_date;
    }
    
    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiry_date = expiryDate;
    }
    
    public UserEntity getUser() {
        return user;
    }
    
    public void setUser(UserEntity user) {
        this.user = user;
    }
    
}
