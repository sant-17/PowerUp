package com.pragma.powerup.infrastructure.feign.twilio.service;

import com.pragma.powerup.infrastructure.feign.twilio.dto.request.SMSRequestDto;

public interface ITwilioFeignClientService {
    void sendMessage(SMSRequestDto smsRequestDto);
}
