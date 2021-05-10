package com.cowin.vaccinefinder;

import com.cowin.vaccinefinder.service.VaccineFinderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableFeignClients
@EnableScheduling
@AllArgsConstructor
@Slf4j
public class VaccineFinderApplication {

    private final VaccineFinderService finderService;

    public static void main(String[] args) {
        SpringApplication.run(VaccineFinderApplication.class, args);
    }

    @Scheduled(fixedRateString = "${spring.scheduler.rate}")
    public void start() {
        finderService.findVaccineByPinForNextWeek();
    }
}
