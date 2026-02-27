package com.rental.rental.video.system.controller;

import com.rental.rental.video.system.service.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RentalController {

    private final RentalService rentalService;

    @PostMapping("/videos/{videoId}/rent")
    public ResponseEntity<?> rent(@PathVariable Long videoId,
                                  Authentication authentication) {

        rentalService.rentVideo(videoId, authentication.getName());
        return ResponseEntity.ok("Video rented successfully");
    }

    @PostMapping("/videos/{videoId}/return")
    public ResponseEntity<?> returnVideo(@PathVariable Long videoId,
                                         Authentication authentication) {

        rentalService.returnVideo(videoId, authentication.getName());
        return ResponseEntity.ok("Video returned successfully");
    }
}
