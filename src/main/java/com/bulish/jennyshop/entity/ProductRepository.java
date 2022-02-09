package com.bulish.jennyshop.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

/**
 * Interface is responsible for providing some basic methods for manipulating with Entity, also it is allowed to
 * create other methods that can be necessary
 * @author Bulish Evgenia
 * @version 1.0
 */
public interface ProductRepository extends JpaRepository<Product,Long> , JpaSpecificationExecutor<Product> {

 Optional<Object> findByTitle(String tittle);

}




