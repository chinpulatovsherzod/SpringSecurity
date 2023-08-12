package com.example.springsecurity.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/greetings")
public class GreetingsController {

    @GetMapping
    public ResponseEntity<String> sayHello(){
        return ResponseEntity.ok("Hello from out API");
    }

    @GetMapping("/say-good-bye")
    public ResponseEntity<String> sayGoodBye(){
        return ResponseEntity.ok("Good bye see you later");
    }

}

// проблема в том что когда в postman запускаю get в Authorization-Bearer Token и зову "/api/v1/greetings" дает ошибку, и не показывает "Hello from out API".
// и не могу получить токен http://localhost:8080/api/v1/auth/authenticate когда запускаю это в postman