package models;

import java.util.LinkedList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Crawler {
	
	@Id
	private String id;
	
	private String searshedteam;
	private List<Tweet> tweets;
	
	public Crawler() {
		tweets = new LinkedList<>();
	}
	
	public Crawler(String searshedteam, List<Tweet> tweets) {
		super();
		this.searshedteam = searshedteam;
		this.tweets = tweets;
	}
	
	public Crawler(String id, String searshedteam, List<Tweet> tweets) {
		super();
		this.id = id;
		this.searshedteam = searshedteam;
		this.tweets = tweets;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSearshedteam() {
		return searshedteam;
	}
	
	public void setSearshedteam(String searshedteam) {
		this.searshedteam = searshedteam;
	}
	
	public List<Tweet> getTweets() {
		return tweets;
	}
	
	public void setTweets(List<Tweet> tweets) {
		this.tweets = tweets;
	}
	
	public void addTweet(Tweet tweet){
		tweets.add(tweet);
	}
	
}
