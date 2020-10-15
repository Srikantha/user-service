package com.ing.assignment.user.config;

import com.ing.assignment.user.model.Address;
import com.ing.assignment.user.model.User;
import com.ing.assignment.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private UserService userService;

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        seedData();
    }

    private void seedData() {
        User user1 = User.builder()
                .firstName("George").lastName("Washington").emailId("george.washington@email.com").gender("Male").title("Mr")
                .address(Address.builder().street("Pennsylvania Avenue").city("NW").state("Washington D.C").postCode("2000").build())
                .build();
        userService.createUser(user1);

        User user2 = User.builder()
                .firstName("Abraham").lastName("Lincoln").emailId("abraham.lincoln@email.com").gender("Male").title("Mr")
                .address(Address.builder().street("Pennsylvania Avenue").city("NW").state("Washington D.C").postCode("2000").build())
                .build();
        userService.createUser(user2);

        User user3 = User.builder()
                .firstName("John").lastName("Adams").emailId("john.adams@email.com").gender("Male").title("Mr")
                .address(Address.builder().street("Pennsylvania Avenue").city("NW").state("Washington D.C").postCode("2000").build())
                .build();
        userService.createUser(user3);

        User user4 = User.builder()
                .firstName("Thomas").lastName("Jefferson").emailId("thomas.jefferson@email.com").gender("Male").title("Mr")
                .address(Address.builder().street("Pennsylvania Avenue").city("NW").state("Washington D.C").postCode("2000").build())
                .build();
        userService.createUser(user4);

        User user5 = User.builder()
                .firstName("James").lastName("Madison").emailId("james.madison@email.com").gender("Male").title("Mr")
                .address(Address.builder().street("Pennsylvania Avenue").city("NW").state("Washington D.C").postCode("2000").build())
                .build();
        userService.createUser(user5);

        User user6 = User.builder()
                .firstName("James").lastName("Monroe").emailId("james.monroe@email.com").gender("Male").title("Mr")
                .address(Address.builder().street("Pennsylvania Avenue").city("NW").state("Washington D.C").postCode("2000").build())
                .build();
        userService.createUser(user6);

        User user7 = User.builder()
                .firstName("Andrew").lastName("Jackson").emailId("andrew.jackson@email.com").gender("Male").title("Mr")
                .address(Address.builder().street("Pennsylvania Avenue").city("NW").state("Washington D.C").postCode("2000").build())
                .build();
        userService.createUser(user7);

        User user8 = User.builder()
                .firstName("John").lastName("Tyler").emailId("john.tyler@email.com").gender("Male").title("Mr")
                .address(Address.builder().street("Pennsylvania Avenue").city("NW").state("Washington D.C").postCode("2000").build())
                .build();
        userService.createUser(user8);

        User user9 = User.builder()
                .firstName("Ronald").lastName("Reagan").emailId("ronald.reagan@email.com").gender("Male").title("Mr")
                .address(Address.builder().street("Pennsylvania Avenue").city("NW").state("Washington D.C").postCode("2000").build())
                .build();
        userService.createUser(user9);

        User user10 = User.builder()
                .firstName("Barack").lastName("Obama").emailId("barack.obama@email.com").gender("Male").title("Mr")
                .address(Address.builder().street("Pennsylvania Avenue").city("NW").state("Washington D.C").postCode("2000").build())
                .build();
        userService.createUser(user10);

        log.debug("Users are successfully loaded.");
    }
}
