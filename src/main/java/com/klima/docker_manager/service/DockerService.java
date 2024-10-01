package com.klima.docker_manager.service;

import com.github.dockerjava.api.DockerClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class DockerService {
    private final DockerClient dockerClient;

    public DockerService(DockerClient client) {
        this.dockerClient = client;
    }

    public 

}
