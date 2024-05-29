package org.project.vkbot.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.project.vkbot.configuration.AppConfig;
import org.project.vkbot.entites.Event;
import org.project.vkbot.enums.ApiCallback;
import org.project.vkbot.enums.ApiMethod;
import org.project.vkbot.response.Response;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class VkApiService
{
    private final AppConfig config;

    public String doResponse(Event event)
    {
        if (!event.getSecret().equals(config.getSecretKey()))
        {
            log.error("Received secret key error " + event.getSecret());

            return "error";
        }

        log.info("Received: " + event);

        if (event.getType() == ApiCallback.CONFIRMATION)
        {
            return config.getConfirmationToken();
        }
        else
            if (event.getType() == ApiCallback.MESSAGE_NEW)
        {
            new Response(event, config.accessToken).processResponse(ApiMethod.MESSAGE_SEND);
        }
        return "ok";
    }
}