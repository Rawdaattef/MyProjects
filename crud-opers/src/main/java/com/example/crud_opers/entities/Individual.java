package com.example.crud_opers.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "INDIVIDUAL")
public class Individual {
    @Id
    private int nationalID;

    private String name;

    private int balance;

    private boolean loan;

}

