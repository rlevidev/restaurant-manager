package com.rlevidev.restaurantmanager.domain.entitys;

import com.rlevidev.restaurantmanager.domain.enums.order.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orders {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private UUID id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "table_id")
  private Tables table;

  @Column(name = "date_opening")
  private LocalDateTime dateOpening;

  @Column(name = "date_closing")
  private LocalDateTime dateClosing;

  @Enumerated(EnumType.STRING)
  private OrderStatus status = OrderStatus.OPEN;

  private String observations;

  @PrePersist
  public void prePersist() {
    if (this.id == null) {
      this.id = UUID.randomUUID();
    }

    this.dateOpening = LocalDateTime.now();
  }
}
