package com.bonvoyage.user.repository;

import com.bonvoyage.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,Long>{
}
