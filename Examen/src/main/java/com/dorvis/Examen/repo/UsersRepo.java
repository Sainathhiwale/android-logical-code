package com.dorvis.Examen.repo;

import com.dorvis.Examen.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepo extends JpaRepository<User,Integer> {
    User findByUserNameAndPassword(String userName, String password);
}
