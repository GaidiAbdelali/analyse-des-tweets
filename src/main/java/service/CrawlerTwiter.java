package service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Repositories.CrawlerRepository;
import models.Crawler;
import models.Tweet;
import twitter4j.JSONException;
import twitter4j.JSONObject;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.conf.ConfigurationBuilder;

@Service
public class CrawlerTwiter {

     private final static String CONSUMER_KEY = "tgx6gUE1RuUng9VLAGZhKgOr9";
     private final static String CONSUMER_KEY_SECRET = "FzH59amBqecZRiq65kWH1dmOiMkc36MQlIMkaBIp3j0Wl5wp3M";
     
     private Twitter twitter;
     
     @Autowired
     CrawlerRepository repo;
     
	 public Crawler start(String searchedTeam) throws TwitterException, IOException {
    	AccessToken accessToken = new AccessToken("4481505203-Pu3goicjea9ijIVQ4Sni0gKl0BYrdji7uciguqC","WhleWk6BOoGDG0x8DG8pM8HQGOJfBsyomZNAsq3pn1lqo");
       
    	ConfigurationBuilder builder = new ConfigurationBuilder();

		builder.setJSONStoreEnabled(true);
		builder.setOAuthConsumerKey(CONSUMER_KEY);
		builder.setOAuthConsumerSecret(CONSUMER_KEY_SECRET);
		builder.setOAuthAccessToken(accessToken.getToken());
		builder.setOAuthAccessTokenSecret(accessToken.getTokenSecret());
		
		twitter4j.conf.Configuration configuration = builder.build();

		TwitterFactory factory = new TwitterFactory(configuration);
		twitter = factory.getInstance();

    	Query query = new Query(searchedTeam);
    	query.setCount(100);
    	
    	int searchResultCount;
    	long lowestTweetId = Long.MAX_VALUE;
    	int searchResultSomme = 0;
    	
    	Crawler crawler = new Crawler();
    	
    	do {
    		QueryResult queryResult = twitter.search(query);
    		searchResultCount = queryResult.getTweets().size();
    		
    		searchResultSomme += searchResultCount;
    		
    		crawler.setSearshedteam(searchedTeam);
    		
    		for (Status tweet : queryResult.getTweets()) {
    			
    			crawler.addTweet(createModelFromStatus(searchedTeam, tweet));
    			
		        if (tweet.getId() < lowestTweetId) {
		            lowestTweetId = tweet.getId();
		            query.setMaxId(lowestTweetId);
		        }
    		}
    	} while (searchResultCount != 0 && searchResultSomme < 100 );
    	
    	return crawler;
    	
    }
    
     
     public Tweet createModelFromStatus(String searchedTeam,Status tweet){
    	 
		 Tweet t = new Tweet();
		 
		 t.setId(tweet.getId());
		 t.setText(tweet.getText());
		 
		 
		 t.setLocation(tweet.getUser().getLocation());
		 
		 t.setCreatedAt(tweet.getCreatedAt());
		 t.setRetweetCount(tweet.getRetweetCount());
		 
		 return t;
     }

	public Twitter getTwitter() {
		return twitter;
	}

	public void setTwitter(Twitter twitter) {
		this.twitter = twitter;
	}
	
}


