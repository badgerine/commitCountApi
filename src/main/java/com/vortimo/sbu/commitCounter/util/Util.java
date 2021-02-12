package com.vortimo.sbu.commitCounter.util;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.vortimo.sbu.commitCounter.model.CommitInfo;
import com.vortimo.sbu.commitCounter.model.Timespan;

import java.time.LocalDateTime;
import java.util.Map;

public class Util {

    private static Gson gson = new Gson();

    public static String mapToJson(Map<String,Integer>  usernameCommitCounts){
        JsonArray jsonArray = new JsonArray();
        for(Map.Entry<String,Integer> entry : usernameCommitCounts.entrySet()){
            jsonArray.add(gson.toJsonTree(new CommitInfo(entry.getKey(), entry.getValue()), CommitInfo.class));
        }
        return gson.toJson(jsonArray);
    }


    public static String filterUrlPath(String githubRepo){
        String[] urlParts = githubRepo.split("github.com") ;
        if(urlParts[1] == null || urlParts[1].isEmpty())
        {
            throw new RuntimeException("Url is not a complete Github repo url.");
        } else {
            return urlParts[1].trim().replaceAll("/$","/commits");
        }
    }

    public static LocalDateTime generateDateParam(Timespan timespan){
        LocalDateTime dateTimeSince = LocalDateTime.now();
        switch (timespan.getTimeUnit()){
            case HOUR:
                return dateTimeSince.minusHours(timespan.getAmount());
            case DAY:
                return dateTimeSince.minusDays(timespan.getAmount());
            case WEEK:
                return dateTimeSince.minusWeeks(timespan.getAmount());
            case MONTH:
                return dateTimeSince.minusMonths(timespan.getAmount());
        }
        return dateTimeSince;
    }
}
