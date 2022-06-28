package fr.epita.mob.services.data;

import fr.epita.mob.datamodel.Contact;
import fr.epita.mob.services.exceptions.unableToLoadContactsException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ContactCsvDAO {


    private final String location;

    public ContactCsvDAO(String location){
        this.location = location;
    }//this above set do help in hard coded location
    //ContactCsvDAO its nothing without the location parameter

    public List<Contact> readAll() throws unableToLoadContactsException{

        File file = new File(this.location);
        List<String> rawStringList = null;

        try {
            rawStringList = Files.readAllLines(file.toPath());
        } catch (IOException e) {
            System.out.println("we have technical problem, can not  load !! ");
            try{
                throw  new unableToLoadContactsException(e);
            }
            catch (unableToLoadContactsException ex){
                ex.printStackTrace();
            }

        }

        //remove the header
        rawStringList.remove(0);

        //create something to store the contacts
        List<Contact> contactList = new ArrayList<>();

        //separator is comma
        for (String row : rawStringList){
            String[] parts = row.split(",");

            String firstName = parts[0];
            String lastName = parts[1];
            String companyName = parts[2];
            String address = parts[3];
            String city = parts[4];
            String county = parts[5];
            String state = parts[6];
            String zip = parts[7];
            String phone1 = parts[8];
            String phone = parts[9];
            String email = parts[10];

            Contact contact = new Contact(firstName, lastName, companyName, address, city, county, state, zip, phone1, phone, email);
            contactList.add(contact);

        }

        return contactList;
    }


    public void sort(List<Contact> contactList) {
        Collections.sort(contactList, new Comparator<Contact>() {
            @Override
            public int compare(Contact o1, Contact o2) {
                return o1.getState().compareTo(o2.getState());
            }
        });

    }
}