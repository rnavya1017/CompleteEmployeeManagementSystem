package com.example.CompleteEmpManagementSystem.service;

import com.example.CompleteEmpManagementSystem.dto.RoleUpdateRequest;
import com.example.CompleteEmpManagementSystem.dto.StatusUpdateRequest;
import com.example.CompleteEmpManagementSystem.dto.UserResponse;
import com.example.CompleteEmpManagementSystem.exception.InvalidPasswordException;
import com.example.CompleteEmpManagementSystem.exception.UserNotFoundException;
import com.example.CompleteEmpManagementSystem.model.Role;
import com.example.CompleteEmpManagementSystem.model.User;
import com.example.CompleteEmpManagementSystem.repository.RoleRepository;
import com.example.CompleteEmpManagementSystem.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.CompleteEmpManagementSystem.dto.ChangePasswordRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(
            UserRepository userRepository,
            RoleRepository roleRepository, PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }


    // GET /api/users


    @Override
    public List<UserResponse> getAllUsers() {

        return userRepository.findAll()
                .stream()
                .map(UserResponse::new)
                .toList();
    }



    // GET /api/users/{id}

    @Override
    public UserResponse getUserById(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new UserNotFoundException(
                                "User not found with id: " + id
                        )
                );

        return new UserResponse(user);
    }



    // PUT /api/users/{id}/roles


    @Override
    public UserResponse updateRoles(
            Long id,
            RoleUpdateRequest request) {

        // Find existing user
        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new UserNotFoundException(
                                "User not found with id: " + id
                        )
                );


        // Find all requested roles from roles table
        Set<Role> roles = new HashSet<>();

        for (var roleName : request.getRoles()) {

            Role role = roleRepository
                    .findByName(roleName)
                    .orElseThrow(() ->
                            new RuntimeException(
                                    "Role not found: " + roleName
                            )
                    );

            roles.add(role);
        }


        // Replace user's existing roles
        user.setRoles(roles);


        // Save updated user
        User savedUser = userRepository.save(user);


        return new UserResponse(savedUser);
    }



    // PUT /api/users/{id}/status


    @Override
    public UserResponse updateStatus(
            Long id,
            StatusUpdateRequest request) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new UserNotFoundException(
                                "User not found with id: " + id
                        )
                );


        // Enable / disable account
        user.setEnabled(request.isEnabled());


        User savedUser = userRepository.save(user);


        return new UserResponse(savedUser);
    }


// PUT /api/users/change-password


    @Override
    public void changePassword(
            ChangePasswordRequest request) {

        // Get the currently logged-in username
        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        String username = authentication.getName();


        // Find the currently logged-in user
        User user = userRepository
                .findByUsername(username)
                .orElseThrow(() ->
                        new UserNotFoundException(
                                "User not found with username: "
                                        + username
                        )
                );


        // Check whether current password is correct
        if (!passwordEncoder.matches(
                request.getCurrentPassword(),
                user.getPassword())) {

            throw new InvalidPasswordException(
                    "Current password is incorrect"
            );
        }


        // Encrypt the new password before storing it
        String encodedPassword =
                passwordEncoder.encode(
                        request.getNewPassword()
                );

        user.setPassword(encodedPassword);


        // Save updated password
        userRepository.save(user);
    }
}