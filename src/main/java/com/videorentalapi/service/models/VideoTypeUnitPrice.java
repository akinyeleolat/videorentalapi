package com.videorentalapi.service.models;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity(name = "VideoTypeUnitPrice")
@Table(name = "videoTypeUnitPrice",  uniqueConstraints={@UniqueConstraint(columnNames={"videoTypeId"})})
public class VideoTypeUnitPrice implements Serializable{

    public VideoTypeUnitPrice(){

    }

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private long id;

    @Column(name = "unitPrice", nullable = false)
    private Integer unitPrice;

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

    public Integer getUnitPrice() {
        return this.unitPrice;
    }

    public void setUnitPrice(Integer unitPrice) {
        this.unitPrice = unitPrice;
    }

    public VideoTypeEnum getVideoTypeId() {
        return this.videoTypeId;
    }

    public void setVideoTypeId(VideoTypeEnum videoTypeId) {
        this.videoTypeId = videoTypeId;
    }
}
