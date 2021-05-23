package com.videorentalapi.service.repository;

import com.videorentalapi.service.models.VideoTypeEnum;
import com.videorentalapi.service.models.VideoTypeUnitPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * The interface User repository.
 *
 * @author Oluwatosin Akinyele
 */
@Repository
public interface VideoUnitPriceRepository extends JpaRepository<VideoTypeUnitPrice, Long>{
    VideoTypeUnitPrice findByVideoTypeId(VideoTypeEnum videoTypeId);
}
