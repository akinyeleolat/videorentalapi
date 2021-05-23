package com.videorentalapi.service.repository;
import com.videorentalapi.service.models.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * The interface User repository.
 *
 * @author Oluwatosin Akinyele
 */
@Repository
public interface VideoRepository extends JpaRepository<Video, Long>{

    Optional<Video> findById(long id);
    Optional<Video> findByVideoTitle(String videoTitle);
}
