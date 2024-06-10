package co.com.bancolombia.demo.services.impl;

import co.com.bancolombia.demo.domain.repositories.UserRepository;
import co.com.bancolombia.demo.exceptions.UserNotFoundException;
import co.com.bancolombia.demo.services.ValidateUserService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ValidateUserImpl implements ValidateUserService {

    private final UserRepository userRepository;

    public ValidateUserImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Mono<String> validateUser(Long userId) {
        return userRepository.existsById(userId)
                .flatMap(exists -> {
                    if (exists) {
                        return Mono.just("User exists.");
                    } else {
                        return Mono.error(new UserNotFoundException("User not found."));
                    }
                });
    }
}
