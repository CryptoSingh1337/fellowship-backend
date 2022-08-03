package com.cyborg.fellowshipweb.bootstrap;

import com.cyborg.fellowshipdataaccess.entity.User;
import com.cyborg.fellowshipdataaccess.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author saranshk04
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class BootstrapData implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @Override
    public void run(String... args) {
        if (userRepository.count() == 0) {
            userRepository.saveAll(createUsers());
        }
        log.info("User count: {}", userRepository.count());
    }

    private List<User> createUsers() {
        return List.of(
                User.builder()
                        .firstName("Josephine")
                        .lastName("Page")
                        .username("test_1")
                        .email("ipsum.ac@icloud.org")
                        .password(encoder.encode("123456"))
                        .build(),
                User.builder()
                        .firstName("Hayes")
                        .lastName("Contreras")
                        .username("test_2")
                        .email("diam.lorem.auctor@aol.net")
                        .password(encoder.encode("123456"))
                        .build(),
                User.builder()
                        .firstName("Xanthus")
                        .lastName("Mejia")
                        .username("test_3")
                        .email("egestas@icloud.ca")
                        .password(encoder.encode("123456"))
                        .build(),
                User.builder()
                        .firstName("Ashton")
                        .lastName("Travis")
                        .username("test_4")
                        .email("porttitor.eros.nec@icloud.ca")
                        .password(encoder.encode("123456"))
                        .build(),
                User.builder()
                        .firstName("Miriam")
                        .lastName("Rocha")
                        .username("test_5")
                        .email("faucibus.morbi@hotmail.com")
                        .password(encoder.encode("123456"))
                        .build()
                );
    }
}