package org.project.vkbot.entites;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.project.vkbot.Constants;

@Data
public class EventObject
{
    @JsonProperty(Constants.MESSAGE)
    private MessageObject message;
    // можно добавить остальные поля
}
