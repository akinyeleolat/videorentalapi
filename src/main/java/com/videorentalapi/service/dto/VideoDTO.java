package com.videorentalapi.service.dto;

import com.videorentalapi.service.models.User;
import com.videorentalapi.service.models.Video;
import org.springframework.data.domain.Page;

public class VideoDTO {
    private User currentUser;
    private Page<Video> video;

    public VideoDTO(){
    }


    public Page<Video> getVideo() {
        return video;
    }

    public void setVideo(Page<Video> video){
        this.video = video;
    }

    public User getCurrentUser(){
        return currentUser;
    }


    public void setCurrentUser(User currentUser){
        this.currentUser = currentUser;
    }
}
