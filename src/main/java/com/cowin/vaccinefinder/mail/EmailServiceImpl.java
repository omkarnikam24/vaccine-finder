package com.cowin.vaccinefinder.mail;

import com.cowin.vaccinefinder.model.VaccineDetails;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@AllArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender emailSender;
    private final ObjectMapper objectMapper;

    @Override
    public void sendEmail(List<VaccineDetails> response, String[] recipients, String pinCode) {
        try {
            String subject = "Vaccine Availability - Available for pincode - " + pinCode;
            log.info("Sending Email");
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(recipients);
            message.setSubject(subject);
            message.setText(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(response));
            emailSender.send(message);
        } catch (JsonProcessingException e) {
            log.error("Error while parsing response {}", e.getMessage());
        }
    }
}
