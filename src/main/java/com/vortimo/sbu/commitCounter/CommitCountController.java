package com.vortimo.sbu.commitCounter;

import com.google.gson.JsonArray;
import com.vortimo.sbu.commitCounter.external.GithubService;
import com.vortimo.sbu.commitCounter.model.CommitInfo;
import com.vortimo.sbu.commitCounter.model.TimeUnit;
import com.vortimo.sbu.commitCounter.model.Timespan;
import com.vortimo.sbu.commitCounter.validation.ParamsValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Map;

import static com.vortimo.sbu.commitCounter.util.Util.*;

@RestController
public class CommitCountController {
    private Logger logger = LoggerFactory.getLogger(CommitCountController.class);

    @Autowired
    private GithubService githubService;

    @CrossOrigin
    @GetMapping(path="/commits", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getRepoCommits(@RequestParam String githubRepoUrl, @RequestParam String timeUnit, @RequestParam String timeAmount){
        logger.debug(String.format("Request with the following=%s | %s | %s",githubRepoUrl,timeUnit,timeAmount));
        ParamsValidator.validateParams(githubRepoUrl, timeUnit, timeAmount);
        Timespan timespan = new Timespan(TimeUnit.valueOf(timeUnit),Long.parseLong(timeAmount));
        Map<String,Integer>  usernameCommitCounts = githubService.getRepoCommits(filterUrlPath(githubRepoUrl), timespan);
        String result = mapToJson(usernameCommitCounts);
        logger.debug(String.format("Response=%s",result));
        return result;
    }

}
