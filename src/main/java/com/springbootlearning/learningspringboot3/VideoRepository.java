package com.springbootlearning.learningspringboot3;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VideoRepository extends JpaRepository
        <VideoEntity, Long> {

    List<VideoEntity> findByName(String name);
}
