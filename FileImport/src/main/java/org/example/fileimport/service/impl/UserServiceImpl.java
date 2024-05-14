package org.example.fileimport.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.fileimport.entity.UserEntity;
import org.example.fileimport.repo.UserEntityRepository;
import org.example.fileimport.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserEntityRepository userEntityRepository;
    @Override
    public Optional<UserEntity>     findByEmail(String email) {
        return userEntityRepository.findByEmail(email);
    }

    @Override
    public void save(UserEntity user) {
        userEntityRepository.save(user);
    }


}
