package com.bulish.jennyshop.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface is responsible for providing some basic methods for manipulating with Entity, also it is allowed to
 * create other methods that can be necessary
 * @author Bulish Evgenia
 * @version 1.0
 */
@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {

    Role findRoleByName(String name);
}
