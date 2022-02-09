package com.bulish.jennyshop.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Interface is responsible for providing some basic methods for manipulating with Entity, also it is allowed to
 * create other methods that can be necessary
 * @author Bulish Evgenia
 * @version 1.0
 */

@Repository
public interface UserRepository extends JpaRepository<User,Long>, JpaSpecificationExecutor<User> {

    Optional<User> findUserByUsername(String username);


}
