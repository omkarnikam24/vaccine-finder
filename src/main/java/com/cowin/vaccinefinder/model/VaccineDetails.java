package com.cowin.vaccinefinder.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@Builder
@ToString
public class VaccineDetails {
    private String centerId;
    private String centerName;
    private String address;
    private String blockName;
    private String pinCode;
    private String fee_type;
    private List<Session> sessions;
}
