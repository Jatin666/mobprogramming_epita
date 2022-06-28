package fr.epita.mob.services;

import fr.epita.mob.datamodel.Contact;
import fr.epita.mob.datamodel.ContactDAO;
import fr.epita.mob.spring.Config;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.inject.Inject;
import javax.inject.Named;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {Config.class})
public class ContactJDBCDAO {
    static {
        System.setProperty("conf.file", "src/test/resources/conf.properties");
    }

    @Inject
    @Named("db.mainDatasource")
    DataSource dataSource;

    @BeforeEach
    public void initialize() throws SQLException {
        Connection connect = this.dataSource.getConnection();
        PreparedStatement preparedStatement = connect.prepareStatement("CREATE TABLE IF NOT EXISTS contact  (firstname VARCHAR(255), lastname VARCHAR(255), companyName varchar(255), address varchar(255), city varchar(40), county varchar(40), state varchar(255), zip varchar(255),phone varchar(255),phone1 varchar(255),email varchar(255))");
        preparedStatement.execute();
    }

    @Test //test insertion in to contact , using assertion
    public void test() throws SQLException {
        Contact contact = new Contact("Pavan","Inouye","C 4 Network Inc","6 Greenleaf Ave","San Jose","Santa Clara","CA","95111","408-540-1785","408-813-4592","vinouye@aol.com");
        ContactDAO contactDAO = new ContactDAO(dataSource);
        contactDAO.insert(contact);

        Connection connection = dataSource.getConnection();
        ResultSet resultSet = connection.prepareStatement("select * from contact where firstname = 'Pavan'").executeQuery();
        String fetchedName = null;
        while (resultSet.next()) {
            fetchedName = resultSet.getString("firstname");
        }
        Assertions.assertNotNull(fetchedName);
        System.out.println(fetchedName);
    }


}
