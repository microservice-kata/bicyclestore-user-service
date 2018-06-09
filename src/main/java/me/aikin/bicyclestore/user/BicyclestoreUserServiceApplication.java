package me.aikin.bicyclestore.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
@EnableAutoConfiguration
public class BicyclestoreUserServiceApplication {
    public static void main(String[] args) {

        SpringApplication.run(BicyclestoreUserServiceApplication.class, args);

        log.info("max memory:{} MB", Runtime.getRuntime().maxMemory() / 1024 / 1024);
        log.info("total memory:{} MB", Runtime.getRuntime().totalMemory() / 1024 / 1024);
        log.info("free memory:{} MB", Runtime.getRuntime().freeMemory() / 1024 / 1024);
        log.info("used memory:{} MB", (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1024 / 1024);
        log.info("available processors:{}", Runtime.getRuntime().availableProcessors());
    }
}
