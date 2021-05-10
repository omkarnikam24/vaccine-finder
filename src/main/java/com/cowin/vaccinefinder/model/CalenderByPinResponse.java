package com.cowin.vaccinefinder.model;

import com.cowin.vaccinefinder.model.Session;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
@Setter
public class CalenderByPinResponse {

    @JsonProperty("centers")
    private List<Center> centers;

    @ToString
    @Getter
    @Setter
    @EqualsAndHashCode
    public static class Center {

        @JsonProperty("center_id")
        private int centerId;
        @JsonProperty("name")
        private String name;
        @JsonProperty("address")
        private String address;
        @JsonProperty("state_name")
        private String state;
        @JsonProperty("district_name")
        private String district;
        @JsonProperty("block_name")
        private String block;
        @JsonProperty("pincode")
        private String pincode;
        @JsonProperty("from")
        private String from;
        @JsonProperty("to")
        private String to;
        @JsonProperty("fee_type")
        private String feeType;
        @JsonProperty("vaccine_fees")
        private Vaccine vaccine;
        @JsonProperty("sessions")
        private List<Session> sessions;
    }

    @ToString
    @Getter
    @Setter
    public static class Vaccine {
        @JsonProperty("vaccine")
        private String vaccineName;
        @JsonProperty("fee")
        private String fee;
    }
}
