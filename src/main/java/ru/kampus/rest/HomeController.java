package ru.kampus.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kampus.service.mail.EmailService;

@Controller
public class HomeController {

    @Autowired
    EmailService emailService;

    @GetMapping("/home")
    public String home() {
        emailService.sendSimpleEmail("cifay19019@pahed.com", "Test", "1");
        return "Home";
    }
}
