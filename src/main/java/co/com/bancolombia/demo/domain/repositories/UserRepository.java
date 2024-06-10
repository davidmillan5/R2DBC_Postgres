package co.com.bancolombia.demo.domain.repositories;

import co.com.bancolombia.demo.domain.entities.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends ReactiveCrudRepository<User,Long> {
}
