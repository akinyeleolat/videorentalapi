package com.videorentalapi.service.models;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity(name = "VideoRental")
@Table(name = "videoRental")
public class VideoRental implements Serializable {

    public VideoRental(){

    }

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private long id;


    @Column(name = "numOfDays", nullable = false)
    private Integer numOfDays;

    @Column(name = "rentPrice", nullable = false)
    private Double rentPrice;

    @Column(name = "userId", nullable = false)
    private Integer userId;

    @Column(name = "videoId", nullable = false)
    private Integer videoId;


    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false)
    private Date createdAt;


    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = false)
    private Date updatedAt;

    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }


    public Integer getNumOfDays() {
        return this.numOfDays;
    }

    public void setNumOfDays(Integer numOfDays) {
        this.numOfDays = numOfDays;
    }

    public Double getRentPrice() {
        return this.rentPrice;
    }

    public void setRentPrice(Double rentPrice) {
        this.rentPrice = rentPrice;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getVideoId() {
        return this.videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }
}
