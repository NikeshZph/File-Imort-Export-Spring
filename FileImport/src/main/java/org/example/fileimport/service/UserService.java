package org.example.fileimport.service;

import org.example.fileimport.entity.UserEntity;

import java.util.Optional;

public interface UserService {
    Optional<UserEntity> findByEmail(String email);
    void save(UserEntity user);



}
