package co.com.bancolombia.demo.services;

import co.com.bancolombia.demo.domain.entities.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface UserService {
    Mono<User> createUser(User user);
    Flux<User> getAllUsers();
    Mono<User> getUserById(Long id);
    Mono<User> updateUser(User user);
    Mono<Void> deleteUser(Long id);
}
