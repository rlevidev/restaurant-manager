package com.rlevidev.restaurantmanager.domain.entitys;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "order_closings")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderClosing {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private UUID id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "order_id")
  private Orders order;

  @Column(name = "sub_total")
  private BigDecimal subTotal;

  @Column(name = "service_charge")
  private BigDecimal serviceCharge;

  private BigDecimal discount;

  private BigDecimal total;

  @Column(name = "closed_at")
  private LocalDateTime closedAt;

  @PrePersist
  public void prePersist() {
    if (this.id == null) {
      this.id = UUID.randomUUID();
    }

    this.closedAt = LocalDateTime.now();
  }
}
