package com.bulish.jennyshop.service;

import com.bulish.jennyshop.entity.Role;

/**
 * Interface is service layer between repository and entity, provides methods for manipulating with entity
 * @author Bulish Evgenia
 * @version 1.0
 */
public interface RoleService {
    Role findRoleByName(String name);
}
