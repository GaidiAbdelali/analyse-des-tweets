package Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import models.Tweet;

@Repository
public interface TweetRepository extends MongoRepository<Tweet, Long> {
	
	
}
