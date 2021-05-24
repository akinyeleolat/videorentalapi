package com.videorentalapi.service.services;

import com.videorentalapi.service.models.Video;
import com.videorentalapi.service.repository.VideoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class VideoServiceImpl implements VideoService {
    private VideoRepository videoRepository;

    public VideoServiceImpl(VideoRepository videoRepository) {
        this.videoRepository=videoRepository;
    }

    @Override
    public Video findVideoById(Integer id){
        Optional<Video> video =videoRepository.findById(id);

        return video.orElse(null);
    }

    @Override
    public List<Video> VideoList(){
        return videoRepository.findAll();
    }


    /**
     *
     * @return video
     * @param page page
     */
    @Override
    public Page<Video> VideoList(Pageable page){
        return videoRepository.findAll(page);
    }


    @Override
    public Video saveVideo(Video video) {
        return videoRepository.save(video);
    }

    @Override
    public Video findByVideoTitle(String videoTitle){
        Video video = new Video();
        video.setVideoTitle(videoTitle);
        Optional<Video> videoDetails=videoRepository.findByVideoTitle(video.getVideoTitle());
        return videoDetails.orElse(null);
    }
}
