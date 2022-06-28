package fr.epita.mob.datamodel;
import org.jetbrains.annotations.NotNull;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ContactDAO {
    DataSource dataSource;
    public ContactDAO(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public void insert(@NotNull Contact contact) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO contact(firstname,lastname,companyName,address,city,county,state,zip,phone1,phone,email) VALUES(?,?,?,?,?,?,?,?,?,?,?)");
        preparedStatement.setString(1, contact.getFirstname());
        preparedStatement.setString(2, contact.getLastname());
        preparedStatement.setString(3, contact.getCompanyName());
        preparedStatement.setString(4, contact.getAddress());
        preparedStatement.setString(5, contact.getCity());
        preparedStatement.setString(6, contact.getCounty());
        preparedStatement.setString(7, contact.getState());
        preparedStatement.setString(8, contact.getZip());
        preparedStatement.setString(9, contact.getPhone());
        preparedStatement.setString(10, contact.getPhone1());
        preparedStatement.setString(11, contact.getEmail());
        preparedStatement.execute();
    }
}
