package com.pragma.powerup.infrastructure.feign.twilio.service.imp;

import com.pragma.powerup.infrastructure.feign.twilio.ITwilioClientFeign;
import com.pragma.powerup.infrastructure.feign.twilio.dto.request.SMSRequestDto;
import com.pragma.powerup.infrastructure.feign.twilio.service.ITwilioFeignClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class TwilioFeignClientService implements ITwilioFeignClientService {

    private final ITwilioClientFeign twilioClientFeign;
    @Override
    public void sendMessage(SMSRequestDto smsRequestDto) {
        twilioClientFeign.processSMS(smsRequestDto);
    }
}
