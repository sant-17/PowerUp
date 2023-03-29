package com.pragma.powerup.infrastructure.feign.twilio.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SMSRequestDto {
    private String destinationSMSNumber;
    private String smsMessages;
}
