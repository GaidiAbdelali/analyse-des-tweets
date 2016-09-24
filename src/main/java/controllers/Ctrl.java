package controllers;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cybozu.labs.langdetect.LangDetectException;

import Repositories.MatchRepository;
import Repositories.UserRepository;
import models.Match;
import models.Statistics;
import models.Tags;
import models.User;
import service.Analyse;
import service.CrawlerTwiter;
import service.PostTwitter;
import twitter4j.TwitterException;

@Controller
public class Ctrl { 
	
	@Autowired
	MatchRepository matchRepo;
	
	@Autowired
	CrawlerTwiter crawler;
	
	@Autowired
	PostTwitter post;
	
	@Autowired
	Analyse analyse;

	@Autowired
	DataSource dataSource;
	
	@RequestMapping(value= "/", method = RequestMethod.GET)
	public String root(Model model){
		
		addMatchesToModel(model);
		sendUsername(model);
		
		return "index";
	}
	
	@RequestMapping(value= "/index", method = RequestMethod.GET)
	public String index(Model model){
		addMatchesToModel(model);
		sendUsername(model);
		return "index";
	}
	
	@RequestMapping(value= "/{matchId}", method = RequestMethod.GET)
	public String match1(@PathVariable String matchId ,Model model){
		
		addMatchesToModel(model);
		
		Match match = matchRepo.findById(matchId);
		model.addAttribute("match", match);
		
		model.addAttribute("graphA",Statistics.listToJson(match.getStatistics(),1));
		model.addAttribute("graphB",Statistics.listToJson(match.getStatistics(),2));
		
		model.addAttribute("graphC",Statistics.listToJsonDonut(match.getStatistics(),1));
		model.addAttribute("graphD",Statistics.listToJsonDonut(match.getStatistics(),2));
		
		sendUsername(model);
		
		return "index";
	}

	@RequestMapping("/admin")
	public String adminform(){
		return "adminform" ;
	}
	
	@RequestMapping( value="/addMatch" , method = RequestMethod.POST)
	public String addMatch(@ModelAttribute("match") Match match,Model model){
	
		Match finalMatch = new Match(match.getFirstTeam(),match.getSecondTeam(),match.getLeague());
		
		try {
			Map<String, Integer[]> firstStatics =  
					analyse.analyser(crawler.start(match.getFirstTeam()).getTweets());
			
			Map<String, Integer[]> secondStatics =
					analyse.analyser(crawler.start(match.getSecondTeam()).getTweets());
			
			finalMatch.setStatistics(analyse.mergeWithStatistics(firstStatics,secondStatics));
			
			matchRepo.insert(finalMatch);
			
		} catch (IOException | LangDetectException | TwitterException e) {
			e.printStackTrace();
		}
		
		addMatchesToModel(model);
		
		return "redirect:index" ;
	}
	
	
	@RequestMapping(value="/poster", method = RequestMethod.POST)
	 public String posterr(@ModelAttribute("tags") Tags tags){
		
		post= new PostTwitter();
		try {
			post.poster(tags.toString());
			System.out.println("hello");
		} catch (TwitterException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		return "index" ;
	}
	
	@RequestMapping("/login")
	public String login(String error,Model model){
		if(error != null)
			model.addAttribute("error","informations are incorrect");
		
		return "login" ;
	}
	
	@RequestMapping("/fail")
	public String fail(){
		return "404" ;
	}
	
	private List<String> colorList(){
		List<String> colors = new LinkedList<>();
		colors.add("blue-bg");
		colors.add("brown-bg");
		colors.add("dark-bg");
		colors.add("green-bg");
		
		return colors;
	}
	
	private void addMatchesToModel(Model model){
		Long count = matchRepo.count();
		
		Page<Match> ms = matchRepo.findAll(new PageRequest(Math.toIntExact(count/4), 4, Direction.ASC, "id"));

		List<Match> matchs = new LinkedList<>();
		ms.forEach( m -> matchs.add(m));

		if(matchs.size() < 4 && Math.toIntExact(count/4) >= 1){
			
			ms = matchRepo.findAll(new PageRequest(Math.toIntExact(count/4)-1, 4, Direction.ASC, "id"));
			
			if(matchs.size() == 0 ){
				ms.forEach( m -> matchs.add(m));
			}else{
				List<Match> t = new LinkedList<>();
				ms.forEach( m -> t.add(m));
				
				int size = matchs.size();
				for(int i = 0; i < 4 - size;i++ ){
					matchs.add(t.get(3-i));
				}
			}
		}
		
		model.addAttribute("matchs", matchs);
		model.addAttribute("colors", colorList());
	}
	
	@RequestMapping(value= "/signup", method = RequestMethod.POST)
	public String signup(@ModelAttribute User user,Model model){

		if(!user.getPassword().equals(user.getRetype())){
			model.addAttribute("error","retype password is incorrect");
			return "signup";
		}else{
			UserRepository userRepo =new UserRepository();
			userRepo.insert(dataSource,user);
			return "index";
		}
		
	}
	
	@RequestMapping(value= "/signupPage", method = RequestMethod.GET)
	public String signupPage(){
		return "signup";
	}
	
	private void sendUsername(Model model){
		model.addAttribute("username",((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
	}
}
