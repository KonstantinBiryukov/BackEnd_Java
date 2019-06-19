package ru.academits;

import org.springframework.beans.BeanUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;
import ru.academits.dto.ContactDto;
import ru.academits.model.Contact;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, JpaRepositoriesAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@EnableScheduling
public class PhonebookSpringApplication {
    public static void main(String[] args) {
        ContactDto contactDto = new ContactDto();
        Contact contactEntity = new Contact();
        System.out.println(contactDto.getFirstName());
        BeanUtils.copyProperties(contactDto, contactEntity);

        SpringApplication.run(PhonebookSpringApplication.class, args);
    }
}