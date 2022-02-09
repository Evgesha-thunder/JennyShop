package com.bulish.jennyshop.service;

import com.bulish.jennyshop.entity.Role;
import com.bulish.jennyshop.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.Hibernate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


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
public class UserDto {

        private Long id;
        private String username;
        private String password;
        private String email;
        private String imageLink;
        private Set<Role> roles;


        public UserDto(User user) {
            this.id = user.getId();
            this.username = user.getUsername();
            this.password = user.getPassword();
            this.email = user.getEmail();
            this.roles = new HashSet<>(user.getRoles());
        }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
       UserDto that = (UserDto) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
    }
