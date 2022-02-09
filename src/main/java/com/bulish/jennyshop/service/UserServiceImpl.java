package com.bulish.jennyshop.service;

import com.bulish.jennyshop.entity.Role;
import com.bulish.jennyshop.entity.User;
import com.bulish.jennyshop.entity.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.*;


/**
 * Class is service layer between repository and entity, provides methods for manipulating with entity
 * @author Bulish Evgenia
 * @version 1.0
 */

@Service
public class UserServiceImpl implements UserService{

    private final PasswordEncoder encoder;
    private final UserRepository userRepository;
    private final RoleService roleService;

    public UserServiceImpl(PasswordEncoder encoder, UserRepository userRepository, RoleService roleService) {
        this.encoder = encoder;
        this.userRepository = userRepository;
        this.roleService=roleService;
    }

    @Transactional
    @Override
    public void save(UserDto user) {
        Role role = roleService.findRoleByName("ROLE_USER");
    User user1 = new User(user);
    user1.setPassword(encoder.encode(user1.getPassword()));
    Set<Role> roleSet = new HashSet<>();
    roleSet.add(role);
    user1.setRoles(roleSet);
     userRepository.save(user1);
        if (user.getId() == null) {
            user.setId(user1.getId());
        }
    }
}
