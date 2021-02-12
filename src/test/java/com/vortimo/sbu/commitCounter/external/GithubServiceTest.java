package com.vortimo.sbu.commitCounter.external;

import com.vortimo.sbu.commitCounter.model.CommitInfo;
import com.vortimo.sbu.commitCounter.model.TimeUnit;
import com.vortimo.sbu.commitCounter.model.Timespan;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

@SpringBootTest
public class GithubServiceTest {
    static Logger logger = LoggerFactory.getLogger(GithubServiceTest.class);

    @Autowired
    private GithubService githubService;

    @Test
    void contextLoads() {
        assertThat(githubService).isNotNull();
    }

    @Test
    void testGetRepoCommits(){
        String repoUrl="https://api.github.com/repos/facebook/react/commits";
        Timespan timespan= new Timespan(TimeUnit.DAY, 1L);
        Map<String,Integer> commits = githubService.getRepoCommits(repoUrl, timespan);
        assertThat(commits != null && !(commits.isEmpty()));
        logger.debug(Arrays.asList(commits).toString());
    }

}
