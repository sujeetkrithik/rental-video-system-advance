package com.rental.rental.video.system.repository;

import com.rental.rental.video.system.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<Video, Long> {

}

