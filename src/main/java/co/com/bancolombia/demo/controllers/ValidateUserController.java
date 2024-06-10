package co.com.bancolombia.demo.controllers;

import co.com.bancolombia.demo.services.ValidateUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/validate")
public class ValidateUserController {

    private final ValidateUserService validationService;

    public ValidateUserController(ValidateUserService validationService) {
        this.validationService = validationService;
    }

    @GetMapping("/user/{userId}")
    public Mono<String> validateUser(@PathVariable Long userId){
        return validationService.validateUser(userId);
    }
}
