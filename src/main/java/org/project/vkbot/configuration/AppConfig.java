package org.project.vkbot.configuration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class AppConfig
{
    @Value("${token.confirmation}")
    public String confirmationToken;

    @Value("${token.access}")
    public String accessToken;

    @Value("${secret.key}")
    public String secretKey;
}
