package com.example.crud_opers.controllers;
import com.example.crud_opers.entities.Individual;
import com.example.crud_opers.services.IndividualService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
public class IndividualController {

    @Autowired
    private IndividualService service;

    @PostMapping("/addIndividual")
    public Individual addInd(@RequestBody Individual individual) {
        return service.saveInd(individual);
    }

    @PostMapping("/addIndividuals")
    public List<Individual> addInds(@RequestBody List<Individual> individuals) {
        return service.saveInds(individuals);
    }

    @GetMapping("/individuals")
    public List<Individual> findAllIndividuals() {
        return service.getAllInds();
    }

    @GetMapping("/individuals/{nationalID}")
    public Individual findIndividualById(@PathVariable int nationalID) {
        return service.getIndById(nationalID);
    }

    @GetMapping("/individuals/by-name/{name}")
    public Individual findIndividualByName(@PathVariable String name) {
        return service.getIndByName(name);
    }

    @DeleteMapping("/delete/{nationalID}")
    public String deleteIndividual(@PathVariable int nationalID) {
        return service.deleteInd(nationalID);
    }

    @PutMapping("/update")
    public Individual updateIndividual(@RequestBody Individual individual) {
        return service.updateInd(individual);
    }



}
