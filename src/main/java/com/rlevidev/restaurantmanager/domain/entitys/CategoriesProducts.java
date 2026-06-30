package com.rlevidev.restaurantmanager.domain.entitys;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "categories_products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoriesProducts {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private UUID id;

  private String name;

  private Boolean active;

  @PrePersist
  public void prePersist() {
    this.active = true;
  }
}
