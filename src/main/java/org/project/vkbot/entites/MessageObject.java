package org.project.vkbot.entites;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.project.vkbot.Constants;

public record MessageObject(@JsonProperty(Constants.MESSAGE_PEER_ID) Long peerId,
                            @JsonProperty(Constants.MESSAGE_TEXT) String text) {
}
