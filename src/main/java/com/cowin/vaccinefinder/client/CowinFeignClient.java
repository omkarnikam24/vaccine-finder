package com.cowin.vaccinefinder.client;

import com.cowin.vaccinefinder.configuration.FeignClientConfiguration;
import com.cowin.vaccinefinder.model.CalenderByPinResponse;
import com.cowin.vaccinefinder.model.CowinResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "findByPin", url = "${api.host}", configuration = FeignClientConfiguration.class)
public interface CowinFeignClient {

    @GetMapping(value = "${api.findByPinEndpoint}", produces = "application/json")
    CowinResponse findByPin(@RequestHeader Map<String, String> headers, @RequestParam("pincode") String pinCode, @RequestParam("date") String date);

    @GetMapping(value = "${api.calendarByPinEndpoint}", produces = "application/json")
    CalenderByPinResponse calendarByPin(@RequestHeader Map<String, String> headers, @RequestParam("pincode") String pinCode, @RequestParam("date") String date);
}
