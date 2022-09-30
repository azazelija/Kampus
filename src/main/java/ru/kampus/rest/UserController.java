package ru.kampus.rest;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kampus.dto.User;
import ru.kampus.service.IUserService;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    IUserService userService;

    @PostMapping
    public ResponseEntity<User> register(@RequestBody User user) {
        User registerUser = userService.register(user);
        return new ResponseEntity<>(registerUser, HttpStatus.CREATED);
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> getByUsername(@PathVariable String username) {
        User user = userService.getByUsername(username);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    @PutMapping("/{username}/{telegramId}")
    public ResponseEntity<User> updateTelegramId(@PathVariable String username, String telegramId) {
        User user = userService.updateTelegramId(username, telegramId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
