package ru.academits.schedule;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.academits.model.Contact;

@Component
@EnableScheduling
@EnableAutoConfiguration
public class DeleteRandomContactScheduler {
    @Scheduled(initialDelay = 5000, fixedRate = 10000)

    public void deleteRandomContactScheduler() {
        System.out.println("Scheduler is working...");
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = "http://localhost:8080/phoneBook/rpc/api/v1/getAllContacts";
        Contact[] contacts = restTemplate.getForObject(fooResourceUrl, Contact[].class);
        for (Contact contact : contacts) {
            System.out.println(contact.getId());
        }

        int contactsListSize = contacts.length;
        int randomId = (int) (Math.random() * contactsListSize);
        String url = "http://localhost:8080/phoneBook/rpc/api/v1/deleteContact";
    }
}