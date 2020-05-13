package com.anta.java8.completablefuture.stackoverflow;

import org.jsoup.nodes.Document;

public interface StackOverflowClient {

	String mostRecentQuestionAbout(String tag);
	Document mostRecentQuestionsAbout(String tag);

}
