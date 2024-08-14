package com.lms.learning_management_system.entities.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import com.lms.learning_management_system.dto.UserDTO;
import com.lms.learning_management_system.entities.RoleEntity;
import com.lms.learning_management_system.entities.UserEntity;
import com.lms.learning_management_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;

@Service
public class UserServiceImpl implements IUserService, UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private BCryptPasswordEncoder bCryptEncoder;

    @Override
    public UUID saveUser(UserEntity user) {
        //Encode password before saving to DB
        user.setPassword(bCryptEncoder.encode(user.getPassword()));
        return userRepo.save(user).getId();
    }

    //find user by username
    @Override
    public Optional<UserEntity> findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> opt = userRepo.findByUsername(username);

        org.springframework.security.core.userdetails.User springUser=null;

        if(opt.isEmpty()) {
            throw new UsernameNotFoundException("User with username: " +username +" not found");
        }else {
            UserEntity user = opt.get();	//retrieving user from DB
            RoleEntity role = user.getRole();
            Set<GrantedAuthority> ga = new HashSet<>();

            springUser = new org.springframework.security.core.userdetails.User(
                    username,
                    user.getPassword(),
                    ga );
        }

        return springUser;
    }

}