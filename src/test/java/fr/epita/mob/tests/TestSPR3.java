package fr.epita.mob.tests;

import fr.epita.mob.datamodel.Contact;
import fr.epita.mob.spring.Config;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.inject.Inject;
import javax.inject.Named;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes ={Config.class})
public class TestSPR3 {
    static {
        System.setProperty("conf.file", "src/test/resources/conf.properties");
    }
    @Inject
    @Named("contact")
    Contact contact ;
        @Test
    public void testSPR3(){
            Assertions.assertNotNull(contact);
            System.out.println(contact.getFirstname()); //through Config getting the data and printing out
        }


}
