package co.com.bancolombia.demo.domain.repositories;

import co.com.bancolombia.demo.domain.entities.User;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends R2dbcRepository<User,Long> {
}
