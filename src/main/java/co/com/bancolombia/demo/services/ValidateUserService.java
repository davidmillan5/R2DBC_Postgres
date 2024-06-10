package co.com.bancolombia.demo.services;

import reactor.core.publisher.Mono;

public interface ValidateUserService {
    Mono<String>validateUser(Long userId);
}
