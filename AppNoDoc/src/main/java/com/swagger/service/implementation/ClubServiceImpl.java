package com.swagger.service.implementation;

import com.swagger.exception.exception.UserNotFoundException;
import com.swagger.persistence.entity.ClubEntity;
import com.swagger.persistence.persistence.ClubRepository;
import com.swagger.presentation.dto.ClubDTO;
import com.swagger.service.interfaces.IClubService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClubServiceImpl implements IClubService {

    private final ModelMapper modelMapper;
    private final ClubRepository clubRepository;

    @Override
    public List<ClubDTO> findAll() {
        return this.clubRepository.findAll().stream().map(entity -> this.modelMapper.map(entity, ClubDTO.class)).toList();
    }

    @Override
    public ClubDTO findById(Long id) {
        ClubEntity userEntity = this.clubRepository.findById(id).orElseGet(ClubEntity::new);
        return this.modelMapper.map(userEntity, ClubDTO.class);
    }

    @Override
    public ClubDTO save(ClubDTO clubDTO) {
        ClubEntity userEntity = this.modelMapper.map(clubDTO, ClubEntity.class);

        ClubEntity userSaved = this.clubRepository.save(userEntity);

        return this.modelMapper.map(userSaved, ClubDTO.class);
    }

    @Override
    public ClubDTO updateClub(Long id, ClubDTO clubDTO) {

        ClubEntity currentClubEntity = this.clubRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User with ID " + id + " doesnt exist."));

        currentClubEntity.setName(clubDTO.getName());
        currentClubEntity.setCountry(clubDTO.getCountry());

        ClubEntity userUpdated = this.clubRepository.save(currentClubEntity);

        return this.modelMapper.map(userUpdated, ClubDTO.class);
    }

    @Override
    public String deleteClub(Long id) {

        ClubEntity currentClubEntity = this.clubRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User with ID " + id + " doesnt exist."));

        this.clubRepository.delete(currentClubEntity);

        return "User correctly removed.";
    }
}
