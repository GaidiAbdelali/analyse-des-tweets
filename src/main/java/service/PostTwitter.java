package service;

import java.io.IOException;

import org.springframework.stereotype.Service;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.conf.ConfigurationBuilder;

@Service
public class PostTwitter {
	
	 private final static String CONSUMER_KEY = "tgx6gUE1RuUng9VLAGZhKgOr9";
	 private final static String CONSUMER_KEY_SECRET = "FzH59amBqecZRiq65kWH1dmOiMkc36MQlIMkaBIp3j0Wl5wp3M";
	 
	 
	 public void poster(String text) throws TwitterException, IOException {
	       AccessToken accessToken = new AccessToken("4481505203-Pu3goicjea9ijIVQ4Sni0gKl0BYrdji7uciguqC","WhleWk6BOoGDG0x8DG8pM8HQGOJfBsyomZNAsq3pn1lqo");
	       
	ConfigurationBuilder builder = new ConfigurationBuilder();

	builder.setJSONStoreEnabled(true);
	builder.setOAuthConsumerKey(CONSUMER_KEY);
	builder.setOAuthConsumerSecret(CONSUMER_KEY_SECRET);
	builder.setOAuthAccessToken(accessToken.getToken());
	builder.setOAuthAccessTokenSecret(accessToken.getTokenSecret());

	twitter4j.conf.Configuration configuration = builder.build();



	TwitterFactory factory = new TwitterFactory(configuration);
	Twitter twitter = factory.getInstance();

	 Status status = twitter.updateStatus(text);

	       



	    }
	    }
	 
	  




