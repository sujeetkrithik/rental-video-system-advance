package com.rental.rental.video.system.repository;

import com.rental.rental.video.system.entity.Rental;
import com.rental.rental.video.system.entity.User;
import com.rental.rental.video.system.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RentalRepository extends JpaRepository<Rental, Long> {

    long countByUserAndReturnedAtIsNull(User user);

    Optional<Rental> findByUserAndVideoAndReturnedAtIsNull(User user, Video video);
}