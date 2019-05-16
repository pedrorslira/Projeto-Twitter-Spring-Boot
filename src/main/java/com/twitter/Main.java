package com.twitter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import twitter4j.TwitterException;

@SpringBootApplication
public class Main {
	public static void main(String[] args) throws TwitterException {
		SpringApplication.run(Main.class, args);
	}
}
