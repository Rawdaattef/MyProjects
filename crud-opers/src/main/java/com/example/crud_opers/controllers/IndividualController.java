package com.example.crud_opers.controllers;
import com.example.crud_opers.entities.IndividualDto;
import com.example.crud_opers.repositories.IndividualRepo;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.example.crud_opers.entities.Individual;
import com.example.crud_opers.services.IndividualService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@Controller
public class IndividualController {

    @Autowired
    private IndividualService service;

    @Autowired
    private IndividualRepo individualRepo;

    @GetMapping("/individuals")
    public String findAllIndividuals(Model model) {
        var individuals = service.getAllInds();
        model.addAttribute("individuals", individuals);
        return "individuals/indIndex";
    }

    @GetMapping("/create")
    public String createIndividual(Model model) {
        IndividualDto individualDto = new IndividualDto();
        model.addAttribute("individualDto", individualDto);
        return "individuals/create";
    }

    @PostMapping("addIndividual")
    public String addIndividual(@Valid @ModelAttribute("individualDto") IndividualDto individualDto, BindingResult result) {
        if(individualRepo.findById(individualDto.getNationalID()).isPresent()){
            result.addError(new FieldError("individualDto", "nationalID" ,
                    individualDto.getNationalID() , false, null , null ,
                    "This National ID is already used"));
        }

        if(result.hasErrors()) {
            return "individuals/create";
        }

        Individual individual = new Individual();
        individual.setNationalID(individualDto.getNationalID());
        individual.setName(individualDto.getName());
        individual.setBalance(individualDto.getBalance());
        individual.setLoan(individualDto.isLoan());

        individualRepo.save(individual);

        return ("redirect:/individuals");
    }

//    @PostMapping("/addIndividual")
//    public Individual addInd(@RequestBody Individual individual) {
//        return service.saveInd(individual);
//    }

    @GetMapping("/update/{nationalID}")
    public String updateIndividual(Model model ,@PathVariable int nationalID){
        System.out.println(nationalID);
        Individual individual = individualRepo.findById(nationalID).orElse(null);
        System.out.println(individual.getName());
        if(individual == null){
            return "redirect:/individuals";
        }
        IndividualDto individualDto = new IndividualDto();
        individualDto.setNationalID(individual.getNationalID());
        individualDto.setName(individual.getName());
        individualDto.setBalance(individual.getBalance());
        individualDto.setLoan(individual.isLoan());

        model.addAttribute("individualDto", individualDto);

        return "individuals/update";
    }

    @PostMapping("/edit/{nationalID}")
    public String edit(Model model , @PathVariable int nationalID ,
                         @Valid @ModelAttribute IndividualDto individualDto,
                         BindingResult result) {
        Individual individual = individualRepo.findById(nationalID).orElse(null);
        if(individual == null){
            return "redirect:/individuals";
        }

        model.addAttribute("individual", individual);

        if(result.hasErrors()) {
            return "individuals/update";
        }


        //Update Individual Details
        individual.setName(individualDto.getName());
        individual.setBalance(individualDto.getBalance());
        individual.setLoan(individualDto.isLoan());

        individualRepo.save(individual);

        return "redirect:/individuals";
    }

//    @PutMapping("/update")
//    public Individual updateIndividual(@RequestBody Individual individual) {
//        return service.updateInd(individual);
//    }


    @GetMapping("/delete")
    public String deleteIndividual(@RequestParam int nationalID){
        Individual individual = individualRepo.findById(nationalID).orElse(null);
        if(individual != null){
            individualRepo.delete(individual);
        }
        return ("redirect:/individuals");
    }


//    @DeleteMapping("/delete/{nationalID}")
//    public String deleteIndividual(@PathVariable int nationalID) {
//        return service.deleteInd(nationalID);
//    }

    @PostMapping("/addIndividuals")
    public List<Individual> addInds(@RequestBody List<Individual> individuals) {
        return service.saveInds(individuals);
    }



    @GetMapping("/individuals/{nationalID}")
    public Individual findIndividualById(@PathVariable int nationalID) {
        return service.getIndById(nationalID);
    }

    @GetMapping("/individuals/by-name/{name}")
    public Individual findIndividualByName(@PathVariable String name) {
        return service.getIndByName(name);
    }



}
