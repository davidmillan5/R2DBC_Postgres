package co.com.bancolombia.demo.services;

import co.com.bancolombia.demo.models.User;
import co.com.bancolombia.demo.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Mono<User> createUser(final User user){
        return this.userRepository
                .save(user);
    }

    public Flux<User> getAllUsers(){
        return this.userRepository
               .findAll();
    }

    public Mono<User> getUserById(final Long id){
        return this.userRepository
               .findById(id);
    }

    public Mono<User> updateUser(final User user){
        return this.userRepository.findById(user.getId())
                .flatMap(u ->{
                    u.setName(user.getName());
                    return this.userRepository.save(u);
                })
                .switchIfEmpty(this.userRepository.save(user.setAsNew()));
    }

    @Transactional
    public Mono<Void> deleteUser(final Long id){
        return this.userRepository
                .findById(id)
                .flatMap(this.userRepository::delete);
    }
}
