package com.vortimo.sbu.commitCounter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackageClasses = CommitCountController.class)
public class CommitCounterApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommitCounterApplication.class, args);
	}

}
