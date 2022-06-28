package fr.epita.mob.tests;

import fr.epita.mob.spring.Config;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import javax.inject.Inject;
import javax.inject.Named;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {Config.class})

public class TestSPR4 {
    static {
        System.setProperty("conf.file", "src/test/resources/conf.properties");
    }

    @Inject
    @Named("db.mainDatasource")
    DataSource dataSource;
    @Test
    public void testConnectionFromInject() throws SQLException {
        Assertions.assertNotNull(dataSource);
        Connection connection = dataSource.getConnection();
        System.out.println(connection.getSchema());//to check the connection is working or not
        connection.close();
    }
}