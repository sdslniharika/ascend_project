package com.globalLogic.swimmers.controller;

import com.globalLogic.swimmers.entity.Swimmer;
import com.globalLogic.swimmers.service.ISwimmerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/swimmer")
public class SwimmerAPI {
    @Autowired
    private ISwimmerService service;

    @PostMapping("/addSwimmer")
    @ResponseStatus(HttpStatus.CREATED)
    Swimmer addSwimmer(@RequestBody Swimmer swimmer){
        return service.addSwimmer(swimmer);
    }

    @GetMapping("/byId/{id}")
    @ResponseStatus(HttpStatus.OK)
    Swimmer getSwimmerById(@PathVariable Integer id){
        return service.findById(id);
    }

    @GetMapping("/allSwimmers")
    @ResponseStatus(HttpStatus.OK)
    List<Swimmer> getAllSwimmers(){
        return service.findAll();
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    String deleteProductByID(@PathVariable Integer id){
        return service.deleteByID(id);
    }
}
