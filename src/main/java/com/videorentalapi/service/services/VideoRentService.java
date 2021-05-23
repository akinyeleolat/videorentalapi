package com.videorentalapi.service.services;

import com.videorentalapi.service.models.VideoRental;
import com.videorentalapi.service.models.VideoTypeEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VideoRentService {

    /**
     * get Unit price for video type
     * @param videoTypeId
     * @return
     */
    public  Integer getPrice(VideoTypeEnum videoTypeId);


    /**
     * get rental price for regular movie
     * @param unitPrice
     * @param numOfDays
     * @return
     */
    public Double calcMovieRentalPrice(Integer unitPrice, Integer numOfDays);

    /**
     * get rental price for children movie
     * @param unitPrice
     * @param numOfDays
     * @param maxAge
     * @return
     */
    public Double calcMovieRentalPrice(Integer unitPrice, Integer numOfDays, Integer maxAge);


    /**
     * get rental price for new release movie
     * @param unitPrice
     * @param numOfDays
     * @param videoReleaseYear
     * @return
     */
    public Double calcMovieRentalPrice(Integer unitPrice, Integer numOfDays, String videoReleaseYear);


    /**
     * save video rental info
     * @param userId
     * @param videoId
     * @param username
     * @param rentPrice
     */
    public VideoRental saveRentalInfo(Integer userId, Integer videoId, String username, Double rentPrice, Integer numOfDays);


    /**
     * get video rental info by ID
     * @param videoRentalId
     * @return
     */
    public VideoRental findById(Integer videoRentalId);

    /**
     * handle rent
     * @param rentInput
     * @return
     */
    public Double handleVideoRentalPrice(VideoRental rentInput);


    /**
     * pageable video list
     * @param page
     * @return
     */
    Page<VideoRental> VideoRentList(Pageable page);
}
