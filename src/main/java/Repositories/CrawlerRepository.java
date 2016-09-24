package Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import models.Crawler;

@Repository
public interface CrawlerRepository extends  MongoRepository<Crawler, String> {

}
