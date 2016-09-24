package Repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import models.Match;

@Repository
public interface MatchRepository extends MongoRepository<Match, String>{

	public Page<Match> findAll(Pageable pageable);
	public Match findById(String id);
	
}
