package co.com.bancolombia.demo.services;

import co.com.bancolombia.demo.domain.entities.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface UserService {
    Mono<User> createUser(final User user);
    Flux<User> getAllUsers();
    Mono<User> getUserById(final Long id);
    Mono<User> updateUser(final User user);
    Mono<Void> deleteUser(final Long id);
}
