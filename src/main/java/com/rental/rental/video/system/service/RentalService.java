package com.rental.rental.video.system.service;

import com.rental.rental.video.system.entity.Rental;
import com.rental.rental.video.system.entity.User;
import com.rental.rental.video.system.entity.Video;
import com.rental.rental.video.system.repository.RentalRepository;
import com.rental.rental.video.system.repository.UserRepository;
import com.rental.rental.video.system.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RentalService {
    private final RentalRepository rentalRepository;
    private final VideoRepository videoRepository;
    private final UserRepository userRepository;

    public void rentVideo(Long videoId, String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow();

        if (rentalRepository.countByUserAndReturnedAtIsNull(user) >= 2)
            throw new RuntimeException("Rental limit exceeded");

        Video video = videoRepository.findById(videoId)
                .orElseThrow(() -> new RuntimeException("Video not found"));

        if (!video.isAvailable())
            throw new RuntimeException("Video not available");

        video.setAvailable(false);

        Rental rental = Rental.builder()
                .user(user)
                .video(video)
                .rentedAt(LocalDateTime.now())
                .build();

        rentalRepository.save(rental);
    }

    public void returnVideo(Long videoId, String email) {

        User user = userRepository.findByEmail(email).orElseThrow();
        Video video = videoRepository.findById(videoId).orElseThrow();

        Rental rental = rentalRepository
                .findByUserAndVideoAndReturnedAtIsNull(user, video)
                .orElseThrow(() -> new RuntimeException("Rental not found"));

        rental.setReturnedAt(LocalDateTime.now());
        video.setAvailable(true);
    }

}
