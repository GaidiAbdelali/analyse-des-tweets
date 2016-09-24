package service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.cybozu.labs.langdetect.Detector;
import com.cybozu.labs.langdetect.DetectorFactory;
import com.cybozu.labs.langdetect.LangDetectException;

import models.Match;
import models.Statistics;
import models.Tweet;
import twitter4j.TwitterException;

@Service
public class Analyse {

	// path to language profiles for classifier
	private static String langProfileDirectory = "profiles/";

	List<String> posWords = new ArrayList<String>();
	List<String> negWords = new ArrayList<String>();
	
	public final String POS_DICO_LINK = "dicos/positive-words.txt";
	public final String NEG_DICO_LINK = "dicos/negative-words.txt";
	
	public final int STATE_NUMBER = 3;
	
	
	public Analyse() throws IOException, LangDetectException {
		
		ClassLoader classLoader = getClass().getClassLoader();
		File posFile = new File(classLoader.getResource(POS_DICO_LINK).getFile());
		File negFile = new File(classLoader.getResource(NEG_DICO_LINK).getFile());
		
		BufferedReader negReader = new BufferedReader(new FileReader(posFile));
		BufferedReader posReader = new BufferedReader(new FileReader(negFile));

		// currently read word
		String word;

		// add words to comparison list
		while ((word = negReader.readLine()) != null) {
			negWords.add(word);
		}
		while ((word = posReader.readLine()) != null) {
			posWords.add(word);
		}

		// cleanup
		negReader.close();
		posReader.close();

		// prepare language classifier
		DetectorFactory.loadProfile(classLoader.getResource(langProfileDirectory).getFile());
	}

	public Map<String, Integer[]> analyser(List<Tweet> tweets)throws IOException, LangDetectException  {
		Map<String, Integer[]> langStats = new HashMap<>();
		
		Detector detector;

		int score = 0;

		for (Tweet tweet : tweets) {
			
			for(int i = 0; i < STATE_NUMBER ; i++){
				try {
					detector = DetectorFactory.create();
					detector.append(tweet.getText());
	
					String detectedLanguage = detector.detect();
					
					Integer[] currentCount = langStats.get(detectedLanguage);
					score = getSentimentScore(tweet.getText());
					
					if(currentCount == null) {
						Integer[] stats = {0,0,0};
						stats[score + 1]++;
						
						langStats.put(detectedLanguage,stats);
					}else{
						currentCount[score + 1]++;
						langStats.put(detectedLanguage,currentCount);
					}
						
				} catch (Exception e) {}
			}
		}
		return langStats;
	}

	private int getSentimentScore(String input) {
		// normalize!
		input = input.toLowerCase();
		input = input.trim();
		// remove all non alpha-numeric non whitespace chars
		input = input.replaceAll("[^a-zA-Z0-9\\s]", "");

		int negCounter = 0;
		int posCounter = 0;

		// so what we got?
		String[] words = input.split(" ");

		// check if the current word appears in our reference lists...
		for (int i = 0; i < words.length; i++) {
			if (posWords.contains(words[i])) {
				posCounter++;
			}
			if (negWords.contains(words[i])) {
				negCounter++;
			}
		}

		int result = (posCounter - negCounter);

		if (result < 0) {
			return -1;
		} else if (result > 0) {
			return 1;
		}
		return 0;
	}


	public void show(Map<String, Integer[]> langStats) {
		langStats.entrySet().forEach(stats -> System.out.println(stats.getKey()+" :: "+java.util.Arrays.toString(stats.getValue())));
	}
	
	public List<Statistics> mergeWithStatistics(Map<String, Integer[]> firstStatics,Map<String, Integer[]> secondStatics){
		
		List<Statistics> statistics = new LinkedList<>();
		
		firstStatics.entrySet().forEach(s -> {
			Statistics statistic = new Statistics();
			statistic.setName(s.getKey());
			
			Integer[] first = s.getValue();
			Integer[] second = secondStatics.get(s.getKey());
			
			if(second != null){
				statistic.setFirstCount(first[0]+second[2]);
				statistic.setSecondCount(first[2]+second[0]);
			}
			
			statistics.add(statistic);
		});
		
		return statistics;
	}
}