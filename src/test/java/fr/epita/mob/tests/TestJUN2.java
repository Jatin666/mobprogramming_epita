package fr.epita.mob.tests;

import fr.epita.mob.datamodel.Contact;
import fr.epita.mob.services.data.ContactCsvDAO;
import fr.epita.mob.services.exceptions.unableToLoadContactsException;
import org.junit.*;
import org.junit.jupiter.api.Assertions;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public class TestJUN2 {

    private static List<Contact> contactList;//storing the contacst into list
    //call the class and assign the variable to use the class
    private static ContactCsvDAO csvDAO;

    @Before
    public void beforeMethod() throws unableToLoadContactsException {
        contactList = csvDAO.readAll();
        System.out.println("before");

    }

    @BeforeClass
    public static void beforeClassMethod() throws URISyntaxException {
        String filepath = Objects.requireNonNull(TestMVN2.class.getResource("/17-contacts.csv")).toURI().getPath();
        csvDAO = new ContactCsvDAO(filepath);
        System.out.println("beforeclass");
    }

    @After
    public void afterMethod() throws URISyntaxException, unableToLoadContactsException {
//perform some fetching to be able to print
        //The junit test itself should just display the list of persons present in the file.
        String filepath = Objects.requireNonNull(TestMVN2.class.getResource("/17-contacts.csv")).toURI().getPath();
        csvDAO = new ContactCsvDAO(filepath);
        contactList = csvDAO.readAll();
        System.out.print(contactList);
        System.out.println("after");
    }

    @AfterClass
    public static void afterClassMethod() {
        //we need to clear after the test is finished
        contactList.clear();

        System.out.println("afterclass");

    }

    @Test
    public void Test_Junit2() {
        System.out.println("hii Jatin here");

    }

    @Test
    //Create a JUnit test with correct assertions to validate that the list produced by your
    // deserialization class is matching the results that one would expect.
    // Remember to follow the “given-when-then” approach
    public void validate(){
        //creating array list and to add first name to compare
        List<String> namesTest = new ArrayList<String>(Arrays.asList("Lenna","Donette","Mitsue","Leota","Sage","Abel","Kiley","Graciela","Mattie","Gladys","Yuki","Fletcher","Bette","Veronika","Willard","Maryann","Alisha"));
        IntStream.range(0,contactList.size()).forEach(index ->{
            //using the condition assertionequal to comapare namestest with contact list(deserialization class)
            Assertions.assertEquals(namesTest.get(index),contactList.get(index).getFirstname());

        });System.out.println("It is working");//checking it out is it working

    }
}