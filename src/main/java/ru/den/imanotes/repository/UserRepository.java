package ru.den.imanotes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.den.imanotes.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
