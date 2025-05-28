package com.springbootlearning.learningspringboot3;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VideoRepository extends JpaRepository
        <VideoEntity, Long> {

    List<VideoEntity> findByName(String name);
    List<VideoEntity> findFirst5ByName(String name);
    List<VideoEntity> findDistinctByName(String name);
    List<VideoEntity> findFirstByName(String name);
    List<VideoEntity> findTopByDescription(String desc);
    List<VideoEntity> findByNameContainsIgnoreCase(String partialName);

    List<VideoEntity> findByDescriptionContainsIgnoreCase(String partialDescription);

    List<VideoEntity> findByNameContainsOrDescriptionContainsAllIgnoreCase(String partialName,
                                                                           String partialDescription);

    @Query("select v from VideoEntity v where v.name = ?1")
    List<VideoEntity> findCustomerReport(String name);
}
