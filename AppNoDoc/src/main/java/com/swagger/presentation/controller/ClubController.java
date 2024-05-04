package com.swagger.presentation.controller;

import com.swagger.presentation.dto.ClubDTO;
import com.swagger.service.interfaces.IClubService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/club")
@RequiredArgsConstructor
public class ClubController {

    private final IClubService clubService;

    // Find All
    @GetMapping("/findAll")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<List<ClubDTO>> findAll(){
        return new ResponseEntity<>(this.clubService.findAll(), HttpStatus.OK);
    }

    // Find By Id
    @GetMapping("/find/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<ClubDTO> findById(@PathVariable Long id){
        return new ResponseEntity<>(this.clubService.findById(id), HttpStatus.OK);
    }

    // Save
    @PostMapping("/save")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<ClubDTO> save(@RequestBody ClubDTO clubDTO){
        return new ResponseEntity<>(this.clubService.save(clubDTO), HttpStatus.CREATED);
    }

    // Update
    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<ClubDTO> save(@PathVariable Long id, @RequestBody ClubDTO clubDTO){
        return new ResponseEntity<>(this.clubService.updateClub(id, clubDTO), HttpStatus.CREATED);
    }

    // Delete
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<String> delete(@PathVariable Long id){
        return new ResponseEntity<>(this.clubService.deleteClub(id), HttpStatus.OK);
    }
}
