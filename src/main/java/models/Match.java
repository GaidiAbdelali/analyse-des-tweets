package models;

import java.util.List;

import org.springframework.data.annotation.Id;



public class Match {
	
	@Id
	private String id;
	
	private String firstTeam;
	private String secondTeam;
	private List<Statistics> statistics;
	private String league;
	
	public Match() {}

	public Match(String id, String firstTeam, String secondTeam, List<Statistics> statistics, String league) {
		super();
		this.id = id;
		this.firstTeam = firstTeam;
		this.secondTeam = secondTeam;
		this.statistics = statistics;
		this.league = league;
	}

	public Match(String firstTeam, String secondTeam, String league) {
		super();
		this.firstTeam = firstTeam;
		this.secondTeam = secondTeam;
		this.league = league;
	}

	public Match(String firstTeam, String secondTeam, List<Statistics> statistics, String league) {
		super();
		this.firstTeam = firstTeam;
		this.secondTeam = secondTeam;
		this.statistics = statistics;
		this.league = league;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstTeam() {
		return firstTeam;
	}

	public void setFirstTeam(String firstTeam) {
		this.firstTeam = firstTeam;
	}

	public String getSecondTeam() {
		return secondTeam;
	}

	public void setSecondTeam(String secondTeam) {
		this.secondTeam = secondTeam;
	}

	public List<Statistics> getStatistics() {
		return statistics;
	}

	public void setStatistics(List<Statistics> statistics) {
		this.statistics = statistics;
	}

	public String getLeague() {
		return league;
	}

	public void setLeague(String league) {
		this.league = league;
	}
	
	@Override
	public String toString() {
		
		System.out.println(firstTeam+" - "+secondTeam+"("+league+")");
		statistics.forEach(s -> System.out.println(s.getName() + " [" + s.getFirstCount()+","+s.getSecondCount()+"]"));
		return  "-----------------fin----------------";
	}

}