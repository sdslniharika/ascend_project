package com.gl.resortManagement.dao;

import com.gl.resortManagement.entity.Guest;

import java.util.List;
import java.util.Optional;

public interface IGuestDao{
    Guest add(Guest guest);
    Optional<Guest> findById(int id);
    List<Guest> findAllGuests();



}
