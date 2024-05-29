package org.project.vkbot.entites;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.Setter;
import org.project.vkbot.Constants;
import org.project.vkbot.enums.ApiCallback;

@Getter
@Setter
public class Event
{
    @JsonProperty(Constants.EVENT_TYPE)
    private ApiCallback type;

    @JsonProperty(Constants.EVENT_GROUP_ID)
    private Long groupId;

    @JsonProperty(Constants.EVENT_SECRET)
    private String secret;

    @JsonValue
    @JsonProperty(Constants.EVENT_OBJECT)
    private EventObject eventObject;

}
