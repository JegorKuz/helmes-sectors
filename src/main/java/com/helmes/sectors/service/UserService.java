package com.helmes.sectors.service;

import com.helmes.sectors.dto.UserDto;
import com.helmes.sectors.entity.User;
import com.helmes.sectors.mapper.UserMapper;
import com.helmes.sectors.repository.SectorRepository;
import com.helmes.sectors.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final SectorRepository sectorRepository;

    public void saveUser(UserDto userDto) {
        User user = userMapper.dtoToUser(userDto);
        sectorRepository.findById(userDto.getSectorId()).ifPresentOrElse(
                sector -> {
                    user.setSector(sector);
                    userRepository.save(user);
                },
                () -> log.error("Sector with ID: {}, is not present in database.", userDto.getSectorId()));
    }
}
