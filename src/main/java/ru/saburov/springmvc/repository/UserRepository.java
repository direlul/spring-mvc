package ru.saburov.springmvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.saburov.springmvc.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
