package com.klima.docker_manager.config;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.exception.DockerException;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.httpclient5.ApacheDockerHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DockerClientConfig {

    @Value("${docker.socket.path}")
    private String dockerSocketPath;

    @Bean
    public DockerClient buildDockerClient(){
        DefaultDockerClientConfig.Builder dockerClientConfigBuilder = DefaultDockerClientConfig
                .createDefaultConfigBuilder();

        if (this.dockerSocketPath != null && this.dockerSocketPath.startsWith("unix://")){
            dockerClientConfigBuilder.withDockerHost(dockerSocketPath)
                    .withDockerTlsVerify(false);
        }
        else if (this.dockerSocketPath != null && this.dockerSocketPath.startsWith("tcp://")){
            dockerClientConfigBuilder.withDockerHost(dockerSocketPath);
        }else {
            throw new IllegalArgumentException("Docker socket path must use tcp:// on Windows");
        }
        DefaultDockerClientConfig dockerClientConfig = dockerClientConfigBuilder
                .build();

        ApacheDockerHttpClient dockerHttpClient = new ApacheDockerHttpClient.Builder()
                .dockerHost(dockerClientConfig.getDockerHost()).build();

       return DockerClientBuilder.getInstance(dockerClientConfig)
               .withDockerHttpClient(dockerHttpClient)
               .build();
    }

    @Bean
    public CommandLineRunner testDockerConection(DockerClient dockerClient){
        return args -> {
            try{
                String dockerVersion = dockerClient.versionCmd().exec().getVersion();
                System.out.println("Docker version: "+ dockerVersion);
            }catch (DockerException e){
                System.out.println("Erro ao se conectar com o Docker: " + e.getMessage());
            }
        };
    }
}
