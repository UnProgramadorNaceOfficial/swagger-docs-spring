package com.swagger.service.interfaces;

import com.swagger.presentation.dto.ClubDTO;

import java.util.List;

public interface IClubService {
    List<ClubDTO> findAll();
    ClubDTO findById(Long id);
    ClubDTO save(ClubDTO clubDTO);
    ClubDTO updateClub(Long id, ClubDTO clubDTO);
    String deleteClub(Long id);
}
