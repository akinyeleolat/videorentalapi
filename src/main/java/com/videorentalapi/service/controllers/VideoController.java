package com.videorentalapi.service.controllers;

import com.videorentalapi.service.exception.DuplicateResourceException;
import com.videorentalapi.service.exception.ResourceNotFoundException;
import com.videorentalapi.service.models.User;
import com.videorentalapi.service.models.Video;
import com.videorentalapi.service.models.VideoRental;
import com.videorentalapi.service.security.SecurityService;
import com.videorentalapi.service.services.VideoRentService;
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


    private SecurityService securityService;


    private VideoRentService videoRentService;

    private Integer videoId;

    public VideoController(VideoService videoService,SecurityService securityService,VideoRentService videoRentService) {
        this.videoService=videoService;
        this.securityService=securityService;
        this.videoRentService=videoRentService;
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


    @PostMapping("/rent")
    public ResponseEntity<VideoRental> rentVideo(@Valid @RequestBody  VideoRental videoRentInput){
        User currentUser = securityService.getUser();
        double rentalPrice = videoRentService.processVideoRentalPrice(videoRentInput);
        VideoRental rentedVideo = videoRentService.saveRentalInfo((int) currentUser.getId(),videoRentInput.getVideoId(),currentUser.getUsername(), rentalPrice,videoRentInput.getNumOfDays());
        return ResponseEntity.status(HttpStatus.CREATED).body(rentedVideo);
    }




}
