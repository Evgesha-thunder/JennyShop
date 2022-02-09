package com.bulish.jennyshop.service;

import com.bulish.jennyshop.entity.Role;
import com.bulish.jennyshop.entity.RoleRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Class is service layer between repository and entity, provides methods for manipulating with entity
 * @author Bulish Evgenia
 * @version 1.0
 */

@Service
public class RoleServiceImpl implements RoleService{

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Transactional
    @Override
    public Role findRoleByName(String name) {
        return roleRepository.findRoleByName(name);
    }
}
