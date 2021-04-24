package com.nnk.springboot.repositories;

import com.nnk.springboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

  User findByUsername(String username);

  User findById(Integer id);

  void deleteById(Integer id);

  boolean existsById(Integer id);

}
