package com.vortimo.sbu.commitCounter.external;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.vortimo.sbu.commitCounter.model.Timespan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static com.vortimo.sbu.commitCounter.util.Util.generateDateParam;

@Service
public class GithubService {
    static Logger logger = LoggerFactory.getLogger(GithubService.class);
    @Autowired
    private Gson gson;
    @Autowired
    private WebClient webClient;
    private static final String BASE_URL = "https://api.github.com/repos";

    @Bean
    private WebClient webClient(){
        return WebClient.create(BASE_URL);
    }

    public Map<String,Integer> getRepoCommits(String repoUrl, Timespan timespan){
        LocalDateTime dateTimeSince = generateDateParam(timespan);
        String jsonResponse = retrieveJsonResponse(repoUrl, dateTimeSince);
        return parseUsernameCount(jsonResponse);
    }

    private String retrieveJsonResponse(String repoUrl, LocalDateTime dateTimeSince){
        final String awaitedResponse;


        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path(repoUrl).queryParam("since",dateTimeSince).build())
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    private Map<String,Integer> parseUsernameCount(String rawJsonResponse){
        JsonArray jsonArray = gson.fromJson(rawJsonResponse, JsonArray.class);
        Map<String,Integer> committersCounts = new HashMap<>();

        for(JsonElement element :jsonArray){
            String username = element.getAsJsonObject().getAsJsonObject("committer").get("login").getAsString();
            Integer count;
            if((count = committersCounts.get(username)) == null){
                committersCounts.put(username, 1);
            }else {
                committersCounts.replace(username, ++count);
            }
        }
        return committersCounts;
    }

}
