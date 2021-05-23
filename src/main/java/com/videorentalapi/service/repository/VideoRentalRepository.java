package com.videorentalapi.service.repository;

import com.videorentalapi.service.models.VideoRental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * The interface User repository.
 *
 * @author Oluwatosin Akinyele
 */
@Repository
public interface VideoRentalRepository extends JpaRepository<VideoRental, Long> {
    VideoRental findById(long id);
}
