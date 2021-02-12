package com.vortimo.sbu.commitCounter.validation;

import com.vortimo.sbu.commitCounter.model.TimeUnit;

import java.util.regex.Pattern;

public class ParamsValidator {
    public static void validateParams(String githubRepoUrl, String timeUnit, String timeAmount){
        if(!isValidGithubRepo(githubRepoUrl)){
            throw new RuntimeException("Github repo url should be in the form 'https://github.com/<owner>/<repo>/'. E.g https://github.com/facebook/react/");
        }

        if(!isValidTimeUnit(timeUnit)){
            throw new RuntimeException("Valid Time unit values (NOT case sensitive): HOUR, DAY, WEEK, MONTH. ");
        }

        if(!isValidTimeUnit(timeUnit)){
            throw new RuntimeException("Valid time unit is an integer value of 1 or 2 digits.");
        }
    }

    private static boolean isValidGithubRepo(String githubRepoUrl){

        return githubRepoUrl != null && Pattern.matches("https://github.com/\\w+/\\w+/",githubRepoUrl);
    }

    private static boolean isValidTimeUnit(String timeUnit){
        return timeUnit != null
                && (TimeUnit.valueOf(timeUnit.toUpperCase()) != null);
    }

    private static boolean isValidTimeAmount(String timeAmount){
        return timeAmount != null
                && Pattern.matches("\\d{1,2}", timeAmount);
    }
}
