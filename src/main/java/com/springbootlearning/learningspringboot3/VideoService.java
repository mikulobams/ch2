package com.springbootlearning.learningspringboot3;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class VideoService {
    private final VideoRepository repository;

    public VideoService(VideoRepository repository) {
        this.repository = repository;
    }


    public List<VideoEntity> getVideos() {
        return repository.findAll();
    }

    public VideoEntity create(NewVideo newVideo) {
        return repository.saveAndFlush(new VideoEntity(newVideo.name(), newVideo.description()));
    }

    public List<VideoEntity> search(VideoSearch videoSearch) {
        if (StringUtils.hasText(videoSearch.name())
                && StringUtils.hasText(videoSearch.description())) {
            return repository.findByNameContainsOrDescriptionContainsAllIgnoreCase(
                    videoSearch.name(), videoSearch.description());
        }

        if (StringUtils.hasText(videoSearch.name())) {
            return repository.findByNameContainsIgnoreCase
                    (videoSearch.name());
        }
        if (StringUtils.hasText(videoSearch.description())) {
            return repository.findByDescriptionContainsIgnoreCase
                    (videoSearch.description());
        }

        return Collections.emptyList();
    }

    public List<VideoEntity> search(UniversalSearch search) {
        VideoEntity probe = new VideoEntity();
        if (StringUtils.hasText(search.value())) {
            probe.setName(search.value());
            probe.setDescription(search.value());
        }
        Example<VideoEntity> example = Example.of(probe, //
                ExampleMatcher.matchingAny() //
                        .withIgnoreCase() //
                        .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING));
        return repository.findAll(example);
    }
}