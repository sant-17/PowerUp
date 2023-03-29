package com.pragma.twilioservice.controller;

import com.pragma.twilioservice.model.SMSSendRequest;
import com.pragma.twilioservice.service.SMSService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/sms")
@Slf4j
public class SMSRestController {

    @Autowired
    SMSService smsService;

    @PostMapping("/processSMS")
    public String processSMS(@RequestBody SMSSendRequest sendRequest){
        log.info("processSMS Started sendRequest: " + sendRequest.toString());
        return smsService.sendSMS(sendRequest.getDestinationSMSNumber(), sendRequest.getSmsMessages());
    }
}
