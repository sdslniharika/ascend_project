package com.gl.resortManagement.service;

import com.gl.resortManagement.dao.GuestDao;
import com.gl.resortManagement.dto.AddGuest;
import com.gl.resortManagement.dto.DisplayGuest;
import com.gl.resortManagement.entity.Guest;
import com.gl.resortManagement.exceptions.GuestNotFoundException;
import com.gl.resortManagement.exceptions.InvalidInputException;
import com.gl.resortManagement.util.Util;
import com.gl.resortManagement.validations.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class GuestService implements IGuestService{
    @Autowired
    private GuestDao guestDao;
    @Autowired
    private Validator validator;
    @Autowired
    Util util;
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public DisplayGuest add(AddGuest addGuest) {
        try {
            validator.validateAddGuest(addGuest);
            Guest guest = util.toGuest(addGuest);
            guest = guestDao.add(guest);
            return util.toDisplayGuest(guest);
        }catch (InvalidInputException | GuestNotFoundException customExceptions){
            logger.error(customExceptions.getMessage());
        }catch(Exception exception){
            logger.error("Some Error Occurred");
            logger.error(exception.getMessage());
            exception.printStackTrace();
        }
        return new DisplayGuest();
    }

    @Override
    public DisplayGuest findGuestById(Integer id) {
        try {
            validator.validateId(id);
            Optional<Guest> optionalGuest = guestDao.findById(id);
            if (optionalGuest.isEmpty()) {
                throw new GuestNotFoundException("No Guest Found with the given ID");
            } else {
                Guest guest = optionalGuest.get();
                return util.toDisplayGuest(guest);
            }
        }catch (InvalidInputException | GuestNotFoundException customExceptions){
            logger.error(customExceptions.getMessage());
        }catch(Exception exception){
            logger.error("Some Error Occurred");
        }
        return new DisplayGuest();

    }

    @Override
    public List<DisplayGuest> findAllGuests() {
        try {
            List<Guest> guestList = guestDao.findAllGuests();
            if (guestList.isEmpty()) {
                throw new GuestNotFoundException("No Guests present at the Resort right Now");
            } else {
                return util.toDisplayGuestList(guestList);
            }
        }catch (InvalidInputException | GuestNotFoundException customExceptions){
            logger.error(customExceptions.getMessage());
        }catch(Exception exception){
            logger.error("Some Error Occurred");
        }
        return new ArrayList<DisplayGuest>();
    }

    @Override
    public List<DisplayGuest> filterGuestsBySpainAndArtsHobby() {
        try {
            List<Guest> guestList = guestDao.findAllGuests();
            guestList = guestList.stream()
                    .filter(guest -> guest.getCountry().toString().equalsIgnoreCase("Spain"))
                    .filter(guest -> guest.getHobby().toString().equalsIgnoreCase("Music") || guest.getHobby().toString().equalsIgnoreCase("Dance"))
                    .collect(Collectors.toList());
            return util.toDisplayGuestList(guestList);
        }catch (InvalidInputException | GuestNotFoundException customExceptions){
            logger.error(customExceptions.getMessage());
        }catch(Exception exception){
            logger.error("Some Error Occurred");
        }
        return new ArrayList<DisplayGuest>();
    }

    @Override
    public List<DisplayGuest> filterGuestsBySpainHobbyAndAgeMoreThan18() {
        try {
            List<Guest> guestList = guestDao.findAllGuests();
            guestList = guestList.stream()
                    .filter(guest -> guest.getCountry().toString().equalsIgnoreCase("Spain"))
                    .filter(guest -> guest.getHobby().toString().equalsIgnoreCase("Drink"))
                    .filter(guest -> 2023-guest.getDob().getYear() > 18)
                    .collect(Collectors.toList());
            return util.toDisplayGuestList(guestList);

        }catch (InvalidInputException | GuestNotFoundException customExceptions){
            logger.error(customExceptions.getMessage());
        }catch(Exception exception){
            logger.error("Some Error Occurred");
        }
        return new ArrayList<DisplayGuest>();
    }

    @Override
    public List<DisplayGuest> filterGuestsByFranceAndSports() {
        try {
            List<Guest> guestList = guestDao.findAllGuests();
            guestList = guestList.stream()
                    .filter(guest -> guest.getCountry().toString().equalsIgnoreCase("France"))
                    .filter(guest -> guest.getHobby().toString().equalsIgnoreCase("WaterSports"))
                    .collect(Collectors.toList());
            return util.toDisplayGuestList(guestList);
        }catch (InvalidInputException | GuestNotFoundException customExceptions){
            logger.error(customExceptions.getMessage());
        }catch(Exception exception){
            logger.error("Some Error Occurred");
        }
        return new ArrayList<DisplayGuest>();
    }

    @Override
    public List<DisplayGuest> filterGuestsByAgeMoreThan70() {
        try {
            List<Guest> guestList = guestDao.findAllGuests();
            guestList = guestList.stream()
                    .filter(guest -> 2023-guest.getDob().getYear() > 70)
                    .collect(Collectors.toList());
            return util.toDisplayGuestList(guestList);
        }catch (InvalidInputException | GuestNotFoundException customExceptions){
            logger.error(customExceptions.getMessage());
        }catch(Exception exception){
            logger.error("Some Error Occurred");
        }
        return new ArrayList<DisplayGuest>();
    }

    @Override
    public List<DisplayGuest> filterGuestsByChineseAndRead() {
        try {
            List<Guest> guestList = guestDao.findAllGuests();
            guestList = guestList.stream()
                    .filter(guest -> guest.getLanguage().toString().equalsIgnoreCase("Chinese"))
                    .filter(guest -> guest.getHobby().toString().equalsIgnoreCase("Read"))
                    .collect(Collectors.toList());
            return util.toDisplayGuestList(guestList);
        }catch (InvalidInputException | GuestNotFoundException customExceptions){
            logger.error(customExceptions.getMessage());
        }catch(Exception exception){
            logger.error("Some Error Occurred");
        }
        return new ArrayList<DisplayGuest>();
    }
}
