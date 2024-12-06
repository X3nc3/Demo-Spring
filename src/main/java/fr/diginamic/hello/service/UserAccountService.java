package fr.diginamic.hello.service;

import fr.diginamic.hello.entity.UserAccount;
import fr.diginamic.hello.repository.UserAccountRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserAccountService {

    @Autowired
    UserAccountRepository userAccountRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        UserAccount user = new UserAccount("user", passwordEncoder.encode("password"), "ROLE_USER");
        UserAccount admin = new UserAccount("Max", passwordEncoder.encode("mdp"), "ROLE_ADMIN");
        userAccountRepository.save(user);
        userAccountRepository.save(admin);
    }
}
