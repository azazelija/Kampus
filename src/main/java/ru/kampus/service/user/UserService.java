package ru.kampus.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kampus.dto.RoleEnum;
import ru.kampus.dto.User;
import ru.kampus.entity.RoleEntity;
import ru.kampus.entity.UserEntity;
import ru.kampus.mapper.UserMapper;
import ru.kampus.repository.RoleRepository;
import ru.kampus.repository.UserRepository;
import ru.kampus.service.IUserService;

@Service
public class UserService implements IUserService {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User register(User user) {
        RoleEntity role = roleRepository.findByRoleName(RoleEnum.USER.getRole());

        UserEntity userEntity = userMapper.modelToEntity(user);
        userEntity.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userEntity.setRole(role);
        userEntity = userRepository.save(userEntity);

        return userMapper.entityToModel(userEntity);
    }

    @Override
    public User getByUsername(String username) {
        UserEntity foundUser = userRepository.findByUsername(username);
        return userMapper.entityToModel(foundUser);
    }

    @Override
    public User updateTelegramId(String username, String telegramId) {
        UserEntity userEntity = userRepository.findByUsername(username);
        userEntity.setTelegramId(telegramId);
        userEntity = userRepository.save(userEntity);

        return userMapper.entityToModel(userEntity);
    }
}
