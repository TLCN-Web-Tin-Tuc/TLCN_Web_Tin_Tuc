package hcmute.edu.vn.modservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ModServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ModServiceApplication.class, args);
    }
}
