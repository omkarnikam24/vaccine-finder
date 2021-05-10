package com.cowin.vaccinefinder.mapper;

import com.cowin.vaccinefinder.model.CalenderByPinResponse;
import com.cowin.vaccinefinder.model.VaccineDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class ResponseMapper {

    private static final int MINIMUM_AGE_LIMIT = 18;

    public List<VaccineDetails> mapResponse(CalenderByPinResponse response) {
        List<VaccineDetails> vaccineDetails = response.getCenters().stream()
                .collect(Collectors.toMap(center -> center, center -> center.getSessions().stream()
                        .filter(session -> session.getMinAgeLimit() == MINIMUM_AGE_LIMIT).collect(Collectors.toList())))
                .entrySet().stream().filter(centerListEntry -> !centerListEntry.getValue().isEmpty())
                .map(centerListEntry -> VaccineDetails.builder()
                        .centerName(centerListEntry.getKey().getName())
                        .centerId(String.valueOf(centerListEntry.getKey().getCenterId()))
                        .pinCode(centerListEntry.getKey().getPincode())
                        .fee_type(centerListEntry.getKey().getFeeType())
                        .blockName(centerListEntry.getKey().getBlock())
                        .address(centerListEntry.getKey().getAddress())
                        .sessions(centerListEntry.getValue())
                        .build())
                .collect(Collectors.toList());
        log.info("vaccineDetails : {}", vaccineDetails);
        return vaccineDetails;
    }
}
