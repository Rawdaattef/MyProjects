package com.example.crud_opers.services;

import com.example.crud_opers.entities.Individual;
import com.example.crud_opers.repositories.IndividualRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndividualService {
    @Autowired
    private IndividualRepo repo;

    //saving only a new individual
    public Individual saveInd(Individual individual) {
        return repo.save(individual);
    }


    //saving bulk
    public List<Individual> saveInds(List<Individual> individuals) {
        return repo.saveAll(individuals);
    }

    //getting all Individuals
    public List<Individual> getAllInds() {
        return repo.findAll();
    }

    //getting a specific Individual by nationalID
    public Individual getIndById(int nationalID) {
        return repo.findById(nationalID).orElse(null);
    }


    //getting a specific Individual by name
    public Individual getIndByName(String name) {
        return repo.findByName(name);
    }


    //deleting an Individual
    public String deleteInd(int nationalID) {
        repo.deleteById(nationalID);
        return "Individual Account with national ID " + nationalID + " Deleted Successfully!";
    }

    //deleting an Individual
    public Individual updateInd(Individual individual) {
        Individual existingInd = repo.findById(individual.getNationalID()).orElse(null);
        existingInd.setName(individual.getName());
        existingInd.setBalance(individual.getBalance());
        existingInd.setLoan(individual.isLoan());
        return repo.save(existingInd);
    }

}

