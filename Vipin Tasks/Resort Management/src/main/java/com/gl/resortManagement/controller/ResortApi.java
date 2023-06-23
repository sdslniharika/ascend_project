package com.gl.resortManagement.controller;

import com.gl.resortManagement.dto.AddGuest;
import com.gl.resortManagement.dto.DisplayGuest;
import com.gl.resortManagement.service.IGuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/resort")
public class ResortApi {
    @Autowired
    IGuestService service;

    @PostMapping("/addGuest")
    @ResponseStatus(HttpStatus.CREATED)
    public DisplayGuest addGuest(@RequestBody AddGuest addGuest){
        return service.add(addGuest);
    }
    @GetMapping("/findByID/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DisplayGuest findGuestByID(@PathVariable Integer id){
        return service.findGuestById(id);
    }
    @GetMapping("/findAllGuests")
    @ResponseStatus(HttpStatus.OK)
    public List<DisplayGuest> findAllGuests(){
        return service.findAllGuests();
    }
    @GetMapping("/filterBySpainAndArtsHobby")
    @ResponseStatus(HttpStatus.OK)
    public List<DisplayGuest> filterGuestsBySpainAndArtsHobby(){
        return service.filterGuestsBySpainAndArtsHobby();
    }
    @GetMapping("/filterBySpainHobbyAndAgeMoreThan18")
    @ResponseStatus(HttpStatus.OK)
    public List<DisplayGuest> filterGuestsBySpainHobbyAndAgeMoreThan18(){
        return service.filterGuestsBySpainHobbyAndAgeMoreThan18();
    }
    @GetMapping("/filterByFranceAndSports")
    @ResponseStatus(HttpStatus.OK)
    public List<DisplayGuest> filterGuestsByFranceAndSports(){
        return  service.filterGuestsByFranceAndSports();
    }
    @GetMapping("/filterByAgeMoreThan70")
    @ResponseStatus(HttpStatus.OK)
    public List<DisplayGuest> filterGuestsByAgeMoreThan70(){
        return  service.filterGuestsByAgeMoreThan70();
    }
    @GetMapping("/filterByChineseAndRead")
    @ResponseStatus(HttpStatus.OK)
    public List<DisplayGuest> filterGuestsByChineseAndRead(){
        return service.filterGuestsByChineseAndRead();
    }
}
