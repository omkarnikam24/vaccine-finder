package com.cowin.vaccinefinder.mail;

import com.cowin.vaccinefinder.model.VaccineDetails;

import java.util.List;

public interface EmailService {

    void sendEmail(List<VaccineDetails> response, String[] recipients, String pinCode);
}
