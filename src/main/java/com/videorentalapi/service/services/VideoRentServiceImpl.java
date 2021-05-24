package com.videorentalapi.service.services;


import com.videorentalapi.service.models.*;
import com.videorentalapi.service.repository.VideoRentalRepository;
import com.videorentalapi.service.repository.VideoUnitPriceRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Optional;

@Service
public class VideoRentServiceImpl implements  VideoRentService {

    private VideoUnitPriceRepository videoUnitPriceRepository;
    private VideoRentalRepository videoRentalRepository;
    private VideoService videoService;


    public VideoRentServiceImpl(VideoUnitPriceRepository videoUnitPriceRepository, VideoRentalRepository videoRentalRepository, VideoService videoService) {
        this.videoUnitPriceRepository=videoUnitPriceRepository;
        this.videoRentalRepository = videoRentalRepository;
        this.videoService = videoService;
    }

    @Override
    public Integer getPrice(VideoTypeEnum videoTypeId){

        VideoTypeUnitPrice videoTypeUnitPrice = new VideoTypeUnitPrice();
        videoTypeUnitPrice.setVideoTypeId(videoTypeId);
        Optional<VideoTypeUnitPrice> nVideoTypeUnitPrice = videoUnitPriceRepository.findByVideoTypeId(videoTypeId);

        return nVideoTypeUnitPrice.map(VideoTypeUnitPrice::getUnitPrice).orElse(null);
    }


    @Override
    public VideoRental findById(Integer videoRentalId) {
        Optional<VideoRental> rentedVideo =videoRentalRepository.findById(videoRentalId);

        return rentedVideo.orElse(null);
    }


    @Override
    public Double calcMovieRentalPrice(Integer unitPrice, Integer numOfDays){
        double rentalPrice = unitPrice * numOfDays;
        return rentalPrice;
    }


    @Override
    public Double calcMovieRentalPrice(Integer unitPrice, Integer numOfDays, Integer maxAge) {
        double rentalPrice = unitPrice * numOfDays + (maxAge/2);
        return  rentalPrice;
    }


    @Override
    public Double calcMovieRentalPrice(Integer unitPrice, Integer numOfDays, String releaseYearInput) {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        Integer releaseYear = currentYear - Integer.parseInt(releaseYearInput);
        double rentalPrice = unitPrice * numOfDays - releaseYear;
        return  rentalPrice;
    }


    @Override
    public Double processVideoRentalPrice(VideoRental videoRentalInput){

        double rentalPrice;
        Video videoDetails = videoService.findVideoById(videoRentalInput.getVideoId());
        Integer rentPrice = this.getPrice(videoDetails.getVideoTypeId());


        switch(videoDetails.getVideoTypeId()){
            case CHILDREN:
                rentalPrice = this.calcMovieRentalPrice(rentPrice,videoRentalInput.getNumOfDays(),videoDetails.getMaxAge());
                break;
            case NEW:
                rentalPrice = this.calcMovieRentalPrice(rentPrice,videoRentalInput.getNumOfDays(),videoDetails.getReleaseYear());
                break;
            case REGULAR:
                rentalPrice = this.calcMovieRentalPrice(rentPrice, videoRentalInput.getNumOfDays());
                break;
            default:
                rentalPrice =Double.parseDouble(null);
        }
        return rentalPrice;
    }

    @Override
    public VideoRental saveRentalInfo(Integer userId, Integer videoId, String username, Double rentPrice, Integer numOfDays){
        VideoRental videoRental = new VideoRental();
        videoRental.setRentPrice(rentPrice);
        videoRental.setNumOfDays(numOfDays);
        videoRental.setVideoId(videoId);
        videoRental.setUserId(userId);
        return videoRentalRepository.save(videoRental);
    }


    @Override
    public Page<VideoRental> VideoRentList(Pageable page){
        return videoRentalRepository.findAll(page);
    }


}
