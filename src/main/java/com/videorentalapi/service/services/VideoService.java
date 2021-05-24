package com.videorentalapi.service.services;

import com.videorentalapi.service.models.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface VideoService {


    /**
     * pageable video list
     * @param page
     * @return
     */
    Page<Video> VideoList(Pageable page);

    /**
     * video list
     * @return
     */
    public List<Video> VideoList();

    /**
     * find video by id
     * @param id
     * @return
     */
    public Video findVideoById(Integer id);


    /**
     * Save Videoe
     * @param video
     */
    public Video saveVideo(Video video);

    /**
     * find video by videoTitle
     * @param videoTitle
     * @return
     */
    public Video findByVideoTitle(String videoTitle);
}
