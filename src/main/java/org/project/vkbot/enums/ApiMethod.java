package org.project.vkbot.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.project.vkbot.Constants;

@RequiredArgsConstructor
@Getter
public enum ApiMethod
{
    MESSAGE_SEND(Constants.VK_API_METHOD_MESSAGE_SEND);
    private final String methodPath;
}
