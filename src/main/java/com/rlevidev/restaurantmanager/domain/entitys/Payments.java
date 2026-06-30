package com.rlevidev.restaurantmanager.domain.entitys;

import com.rlevidev.restaurantmanager.domain.enums.payment.PaymentMethod;
import com.rlevidev.restaurantmanager.domain.enums.payment.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "payments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payments {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private UUID id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "order_id")
  private Orders order;

  private BigDecimal amount;

  @Enumerated(EnumType.STRING)
  private PaymentMethod paymentMethod;

  @Enumerated(EnumType.STRING)
  private PaymentStatus status = PaymentStatus.PENDING;

  @Column(name = "external_transaction_code")
  private String externalTransactionCode;

  @Column(name = "transaction_date")
  private LocalDateTime transactionDate;

  @Column(name = "created_at")
  private LocalDateTime createdAt;

  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  public void prePersist() {
    if (this.id == null) {
      this.id = UUID.randomUUID();
    }

    this.createdAt = LocalDateTime.now();
  }
}
