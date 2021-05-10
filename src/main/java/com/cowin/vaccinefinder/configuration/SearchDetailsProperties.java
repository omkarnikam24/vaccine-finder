package com.cowin.vaccinefinder.configuration;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties
@Getter
@Setter
@ToString
public class SearchDetailsProperties {

    private List<SearchDetails> searchDetails;

    @Getter
    @Setter
    @ToString
    public static class SearchDetails {
        private String pinCode;
        private String[] recipients;
    }
}


