package fr.epita.mob.spring;


import fr.epita.mob.datamodel.Contact;
import fr.epita.mob.services.ContactJDBCDAO;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class Config {

        @Bean //TestSPR1
        public String myFirstBean(){
             return "Hello from Spring, Jatin";
        }

        @Bean
        public fr.epita.mob.datamodel.Contact contact(){
            return new Contact("Veronika","Inouye","C 4 Network Inc","6 Greenleaf Ave","San Jose","Santa Clara","CA","95111","408-540-1785","408-813-4592","vinouye@aol.com");
        }
    @Bean("conf.mainConf")
    public fr.epita.mob.services.spring.config getConf() {
        return fr.epita.mob.services.spring.config.getInstance();
    }

    @Bean("db.mainDatasource")
    public DataSource dataSource(@Qualifier("conf.mainConf") fr.epita.mob.services.spring.config conf){
        return new DriverManagerDataSource(conf.getDBUrl(), conf.getDBUser(), conf.getDBPassword());
    }

    @Bean("singleton.ConJDBCDAO")
    public ContactJDBCDAO getJdbcdao() {
        return new ContactJDBCDAO();
    }


}
