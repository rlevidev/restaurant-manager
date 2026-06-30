package com.rlevidev.restaurantmanager.domain.entitys;

import com.rlevidev.restaurantmanager.domain.enums.table.TableStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "tables")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tables {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private UUID id;

  private Integer number;

  private String description;

  private Integer capacity;

  @Enumerated(EnumType.STRING)
  private TableStatus status = TableStatus.AVAILABLE;
}
