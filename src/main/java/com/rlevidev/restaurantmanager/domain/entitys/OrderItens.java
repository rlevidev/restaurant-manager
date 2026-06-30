package com.rlevidev.restaurantmanager.domain.entitys;

import com.rlevidev.restaurantmanager.domain.enums.order.OrderItensStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "order_itens")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItens {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private UUID id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "order_id")
  private Orders order;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "product_id")
  private Products product;

  private Integer quantity;

  @Column(name = "price_unit")
  private BigDecimal priceUnit;

  @Column(name = "total_price")
  private BigDecimal totalPrice;

  private String observations;

  @Enumerated(EnumType.STRING)
  private OrderItensStatus status = OrderItensStatus.PENDING;

  @PrePersist
  public void prePersist() {
    if (this.id == null) {
      this.id = UUID.randomUUID();
    }

    this.totalPrice = BigDecimal.valueOf(this.quantity).multiply(this.priceUnit);
  }
}
