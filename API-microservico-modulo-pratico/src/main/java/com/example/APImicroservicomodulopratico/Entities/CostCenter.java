package com.example.APImicroservicomodulopratico.Entities;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cost_center")
public class CostCenter {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cost_center_id", nullable = false)
    private UUID id;

    @Column(name = "cost_center_name", nullable = false)
    private String name;

}
