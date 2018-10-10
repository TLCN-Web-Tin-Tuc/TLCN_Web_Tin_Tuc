package hcmute.edu.vn.nuservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class NuServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(NuServiceApplication.class, args);
    }
}
