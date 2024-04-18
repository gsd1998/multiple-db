package com.diveintodev.db.user.repository;

import com.diveintodev.db.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Integer> {
}
