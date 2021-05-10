package com.cowin.vaccinefinder.service;

import com.cowin.vaccinefinder.client.CowinFeignClient;
import com.cowin.vaccinefinder.configuration.SearchDetailsProperties;
import com.cowin.vaccinefinder.mail.EmailServiceImpl;
import com.cowin.vaccinefinder.mapper.ResponseMapper;
import com.cowin.vaccinefinder.model.CalenderByPinResponse;
import com.cowin.vaccinefinder.model.VaccineDetails;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
@AllArgsConstructor
public class VaccineFinderServiceImpl implements VaccineFinderService {

    private static final String DATE_FORMAT = "dd-MM-yyyy";

    private final CowinFeignClient cowinFeignClient;
    private final EmailServiceImpl emailService;
    private final ResponseMapper mapper;
    private final SearchDetailsProperties searchDetailsProperties;

    @Override
    public void findVaccineByPinForNextWeek() {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        String date = today.format(formatter);
        log.info(searchDetailsProperties.getSearchDetails().toString());
        searchDetailsProperties.getSearchDetails().forEach(searchDetails -> {
            log.info("Getting Vaccine availability for pincode : {}", searchDetails.getPinCode());
            CalenderByPinResponse response = cowinFeignClient.calendarByPin(getHeaders(), searchDetails.getPinCode(), date);
            log.info("response : {}", response);
            List<VaccineDetails> vaccineDetails = mapper.mapResponse(response);
            boolean isVaccineAvailable = vaccineDetails.stream().anyMatch(details -> details.getSessions().stream().anyMatch(session -> session.getAvailableCapacity() > 0));
            if (isVaccineAvailable) {
                log.info("Found Vaccines for pincode : {}", searchDetails.getPinCode());
                emailService.sendEmail(vaccineDetails, searchDetails.getRecipients(), searchDetails.getPinCode());
                log.info("Mail sent");
            } else {
                log.info("No Vaccines Available for pincode : {}", searchDetails.getPinCode());
            }
        });
    }

    private Map<String, String> getHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("accept", "application/json");
        headers.put("Accept-Language", "en_US");
        headers.put("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36");
        return headers;
    }
}
