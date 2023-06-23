package com.example.demo;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AddressBook {
    private ContactDB db;
    public AddressBook(){
        db = new ContactDB();
    }
    public void addContact(Contact contact) throws Exception {
        if(db.cMap.containsKey(contact.getName().toLowerCase())){
            throw new Exception();
        }
        db.cMap.put(contact.getName().toLowerCase(), contact);
    }

    public void deleteContact(String name) throws Exception {
        if(db.cMap.containsKey(name)){
            db.cMap.remove(name);
        }
        else{
            throw new Exception();
        }
    }

    public void updateContact(String name, Contact contact) throws Exception {
        if(db.cMap.containsKey(name)){
            db.cMap.put(name, contact);
        }
        else{
            throw new Exception();
        }
    }

    public List<Contact> searchByName(String name) throws Exception {
        List<Contact> contactList =  db.cMap.values().stream().filter(contact -> contact.getName().toLowerCase().startsWith(name.toLowerCase())).collect(Collectors.toList());
        if(contactList.isEmpty()){
            throw new Exception();
        }
        return contactList;
    }

    public List<Contact> searchByOrganisation(String organisation) throws Exception {
        List<Contact> contactList = db.cMap.values().stream().filter(contact -> contact.getOrganisation().toLowerCase().startsWith(organisation.toLowerCase())).collect(Collectors.toList());
        if(contactList.isEmpty()){
            throw new Exception();
        }
        return contactList;
    }

}