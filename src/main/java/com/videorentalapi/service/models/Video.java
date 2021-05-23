package com.videorentalapi.service.models;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity(name = "Video")
@Table(name = "video", uniqueConstraints={@UniqueConstraint(columnNames={"videoTitle"})})
public class Video  implements Serializable{

    public Video(){

    }

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private long id;

    @Column(name = "maxAge")
    private Integer maxAge;


    @Column(name = "releaseYear")
    private Integer releaseYear;


    @Column(name = "videoGenreId", nullable = false)
    private VideoGenreEnum videoGenreId;

    @Column(name = "videoTitle", nullable = false, unique=true)
    private String videoTitle;


    @Column(name = "videoTypeId", nullable = false)
    private VideoTypeEnum videoTypeId;


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

    public Integer getMaxAge() {
        return this.maxAge;
    }

    public void setMaxAge(Integer maxAge) {
        this.maxAge = maxAge;
    }

    public Integer getReleaseYear() {
        return this.releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public VideoGenreEnum getVideoGenreId() {
        return this.videoGenreId;
    }

    public void setVideoGenreId(VideoGenreEnum videoGenreId) {
        this.videoGenreId = videoGenreId;
    }

    public String getVideoTitle() {
        return this.videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public VideoTypeEnum getVideoTypeId() {
        return this.videoTypeId;
    }

    public void setVideoTypeId(VideoTypeEnum videoTypeId) {
        this.videoTypeId = videoTypeId;
    }

}
