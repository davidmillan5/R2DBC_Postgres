package co.com.bancolombia.demo.services.impl;

import co.com.bancolombia.demo.domain.entities.User;
import co.com.bancolombia.demo.domain.repositories.UserRepository;
import co.com.bancolombia.demo.services.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Mono<User> createUser(User user) {
        return this.userRepository
                .save(user);
    }

    @Override
    public Flux<User> getAllUsers() {
        return this.userRepository
                .findAll();
    }

    @Override
    public Mono<User> getUserById(Long id) {
        return this.userRepository
                .findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("User not found")));
    }

    @Override
    public Mono<User> updateUser(User user) {
        return this.userRepository.findById(user.getId())
                .flatMap(existingUser  ->{
                    existingUser .setName(user.getName());
                    return this.userRepository.save(existingUser );
                })
                .switchIfEmpty(this.userRepository.save(user.setAsNew()));
    }

    @Override
    @Transactional
    public Mono<Void> deleteUser(Long id) {
        return this.userRepository
                .findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("User not found")))
                .flatMap(userRepository::delete);
    }
}