package com.example.crud_opers.repositories;
import com.example.crud_opers.entities.Individual;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IndividualRepo extends JpaRepository<Individual,Integer> {
    Individual findByName(String name);

}

