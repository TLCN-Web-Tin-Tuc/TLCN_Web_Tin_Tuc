package hcmute.edu.vn.cwservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class CwServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CwServiceApplication.class, args);
    }

}
