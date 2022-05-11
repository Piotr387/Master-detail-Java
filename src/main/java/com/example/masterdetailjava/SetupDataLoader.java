package com.example.masterdetailjava;

import com.example.masterdetailjava.user.UserDTO;
import com.example.masterdetailjava.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;

    @Autowired
    UserService userService;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (alreadySetup)
            return;

        List<UserDTO> userDTOList = new ArrayList<>(Arrays.asList(
                new UserDTO("pracownik1", "pracownik1@firma.com", "Jan", "Kowalski"),
                new UserDTO("pracownik2", "pracownik2@firma.com", "Piotr", "Nowak")
        ));

        userDTOList.forEach( userDTO -> userService.createUser(userDTO));
    }
}
