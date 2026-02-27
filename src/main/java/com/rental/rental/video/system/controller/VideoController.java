package com.rental.rental.video.system.controller;

import com.rental.rental.video.system.entity.Video;
import com.rental.rental.video.system.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/videos")
@RequiredArgsConstructor
public class VideoController {

    private final VideoService videoService;

    @GetMapping("/getVideos")
    public List<Video> getAll() {
        return videoService.getAllVideos();
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Video create(@RequestBody Video video) {
        return videoService.createVideo(video);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Video update(@PathVariable Long id, @RequestBody Video video) {
        return videoService.updateVideo(id, video);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable Long id) {
        videoService.deleteVideo(id);
    }
}
