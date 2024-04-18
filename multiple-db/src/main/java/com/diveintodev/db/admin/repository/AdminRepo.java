package com.diveintodev.db.admin.repository;

import com.diveintodev.db.admin.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepo extends JpaRepository<Admin,Integer> {
}
