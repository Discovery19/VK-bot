package org.project.vkbot.response;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.project.vkbot.Constants;
import org.project.vkbot.entites.Event;
import org.project.vkbot.enums.ApiMethod;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public record Response(Event event, String accessToken) {

    private List<NameValuePair> getQueryParameters() {
        List<NameValuePair> nameValuePairs = new ArrayList<>();
        nameValuePairs.add(new BasicNameValuePair("message", "Вы написали: " + event.getEventObject().getMessage().text()));
        nameValuePairs.add(new BasicNameValuePair("peer_id", String.valueOf(event.getEventObject().getMessage().peerId())));
        nameValuePairs.add(new BasicNameValuePair("access_token", accessToken));
        nameValuePairs.add(new BasicNameValuePair("v", Constants.VK_API_VERSION));
        nameValuePairs.add(new BasicNameValuePair("random_id", String.valueOf(new SecureRandom().nextInt())));

        return nameValuePairs;
    }

    public void processResponse(ApiMethod method) {
        try (CloseableHttpClient client = HttpClientBuilder.create()
                .setDefaultRequestConfig(RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD).build())
                .build()) {
            HttpGet httpGet = new HttpGet(Constants.VK_API_ENDPOINT + method.getMethodPath());
            httpGet.setURI(new URIBuilder(httpGet.getURI()).addParameters(getQueryParameters()).build());
            processResponse(client, httpGet);
        } catch (Exception e) {

            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    private void processResponse(CloseableHttpClient client, HttpGet httpGet) {
        try (CloseableHttpResponse response = client.execute(httpGet)) {
            log.debug(httpGet.toString());

            HttpEntity entity = response.getEntity();
            String responseString = EntityUtils.toString(entity, "UTF-8");
            JsonNode jsonNode = new ObjectMapper().readTree(responseString);

            log.debug("Received: " + responseString);
            checkErrors(jsonNode.path("error"));
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    private void checkErrors(JsonNode jsonNode) {
        if (!jsonNode.isEmpty()) {
            log.error("Received an error: '" + jsonNode.path("error_msg").asText() +
                    "' with code [" + jsonNode.path("error_code").asText() + "]\n" +
                    "The following request parameters were passed:\n" +
                    jsonNode.path("request_params").toPrettyString());
        }
    }
}
