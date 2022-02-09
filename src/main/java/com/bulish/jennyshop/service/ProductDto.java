package com.bulish.jennyshop.service;

import com.bulish.jennyshop.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.Hibernate;

import java.util.Objects;


/**
 * Class is an extra abstraction around entity Product that helps simplify interacting between
 * different components in app and avoid any errors that can occur
 * @author Bulish Evgenia
 * @version 1.0
 */

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {


    private Long id;
    private String title;
    private int cost;
    private String imageLink;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ProductDto that = (ProductDto) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}



