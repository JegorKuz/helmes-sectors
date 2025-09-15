package com.helmes.sectors.service;

import com.helmes.sectors.dto.UserDto;
import com.helmes.sectors.entity.Sector;
import com.helmes.sectors.entity.User;
import com.helmes.sectors.exception.SectorIdDoesNotExistException;
import com.helmes.sectors.exception.UsernameWasNotFoundException;
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
    private final SectorRepository sectorRepository;

    public void saveUser(String username, UserDto userDto) {
        User user = userRepository.findByUsername(username).orElseThrow(() ->
                new UsernameWasNotFoundException("Username was not found."));

        Sector sector = sectorRepository.findById(userDto.getSectorId()).orElseThrow(() ->
                new SectorIdDoesNotExistException("Sector with ID: " + userDto.getSectorId() + ", does not exist."));

        user.setSector(sector);
        user.setName(userDto.getName());
        user.setTerms(userDto.getTerms());
        userRepository.save(user);
    }
}