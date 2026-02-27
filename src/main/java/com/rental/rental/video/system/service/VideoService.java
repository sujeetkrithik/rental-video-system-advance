package com.rental.rental.video.system.service;

import com.rental.rental.video.system.entity.Video;
import com.rental.rental.video.system.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VideoService {
    private final VideoRepository videoRepository;

    public List<Video> getAllVideos() {
        return videoRepository.findAll();
    }

    public Video createVideo(Video video) {
        return videoRepository.save(video);
    }

    public Video updateVideo(Long id, Video updated) {
        Video video = videoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Video not found"));

        video.setTitle(updated.getTitle());
        video.setDirector(updated.getDirector());
        video.setGenre(updated.getGenre());
        return videoRepository.save(video);
    }

    public void deleteVideo(Long id) {
        videoRepository.deleteById(id);
    }
}
