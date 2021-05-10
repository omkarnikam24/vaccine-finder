package com.cowin.vaccinefinder.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;

import java.util.List;

@ToString
public class CowinResponse {

    @JsonProperty("sessions")
    private List<Session> sessions;
}
