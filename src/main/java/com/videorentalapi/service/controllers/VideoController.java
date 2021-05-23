package com.videorentalapi.service.controllers;

import com.videorentalapi.service.exception.DuplicateResourceException;
import com.videorentalapi.service.exception.ResourceNotFoundException;
import com.videorentalapi.service.models.Video;
import com.videorentalapi.service.services.VideoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by Akinyele oluwatosin A.
 */
@RestController
@RequestMapping(value = "/api/v1")
@Slf4j
public class VideoController {

    private VideoService videoService;

    private Integer videoId;

    public VideoController(VideoService videoService) {
        this.videoService=videoService;
    }


    /**
     * Get all video.
     *
     * @return the list
     */
    @GetMapping("/video")
    public Page<Video> getAllVideo(Pageable page) throws ResourceNotFoundException {

        Page<Video> videoList = videoService.VideoList(page);

        if(videoList.isEmpty()){
            throw new ResourceNotFoundException("Video list is empty");
        }
        //TODO: handle showing logged in user.

        return videoList;

    }


    @PostMapping("/video")
    @ResponseBody
    public ResponseEntity<Video> createVideo(@Valid @RequestBody Video videoInput) throws DuplicateResourceException {
        Video newVideo = new Video();
        newVideo.setMaxAge(videoInput.getMaxAge());
        newVideo.setReleaseYear(videoInput.getReleaseYear());
        newVideo.setVideoGenreId(videoInput.getVideoGenreId());
        newVideo.setVideoTypeId(videoInput.getVideoTypeId());
        newVideo.setVideoTitle(videoInput.getVideoTitle());
        Video  existingVideo = videoService.findByVideoTitle(newVideo.getVideoTitle());
        if(existingVideo != null){
            throw new DuplicateResourceException("video "+newVideo.getVideoTitle()+" already exist.");
        }
        //TODO: handle required field for specifics
        return ResponseEntity.status(HttpStatus.CREATED).body(videoService.saveVideo(newVideo));
    }
}
