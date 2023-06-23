package com.example.demo;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AddressBook {

    private Trie<Contact> trie;
    public AddressBook() {
        //TODO
        trie=new Trie<>();
    }

    public void addContact(Contact contact) throws Exception {
        //TODO
        if(!searchByName(contact.getName().toLowerCase()).isEmpty()){
            throw new Exception();
        }
        trie.insert(contact.getName().toLowerCase(), contact);
    }

    public void deleteContact(String name) {
        //TODO
        Contact contact=searchByName(name).stream().findAny().get();
        trie.delete(name,contact);
    }

    public void updateContact(String name, Contact contact) throws Exception {
        //TODO
        List<Contact> results = trie.search(name.toLowerCase());
        if(!results.isEmpty()){
            deleteContact(name.toLowerCase());
            trie.insert(name.toLowerCase(), contact);
        }
        else{
            trie.insert(name.toLowerCase(), contact);
        }
    }

    public List<Contact> searchByName(String name) {
        //TODO
       // return trie.search(name.toLowerCase());
        List<Contact> contacts= trie.search("");
        return contacts.stream().filter(contact -> contact.getName().toLowerCase().startsWith(name.toLowerCase())).collect(Collectors.toList());
    }

    public List<Contact> searchByOrganisation(String organisation) {
        //TODO
        List<Contact> contacts= trie.search("");
        return contacts.stream().filter(contact -> contact.getOrganisation().toLowerCase().startsWith(organisation.toLowerCase())).collect(Collectors.toList());
    }

}