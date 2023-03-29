package com.pragma.powerup.infrastructure.feign.twilio;

import com.pragma.powerup.infrastructure.feign.twilio.dto.request.SMSRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "twilio-service", url = "localhost:8087/api/v1/sms")
public interface ITwilioClientFeign {

    @PostMapping("/processSMS")
    public void processSMS(@RequestBody SMSRequestDto smsRequestDto);
}
