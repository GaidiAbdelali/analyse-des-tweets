package models;

public class Tags {
	
	private String title;
	private String 	content;
	private String leagues;
	private String hashtags;
	
	public Tags() {}
	
	public Tags(String title, String content, String leagues, String hashtags) {
		super();
		this.title = title;
		this.content = content;
		this.leagues = leagues;
		this.hashtags = hashtags;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getLeagues() {
		return leagues;
	}

	public void setLeagues(String leagues) {
		this.leagues = leagues;
	}

	public String getHashtags() {
		return hashtags;
	}

	public void setHashtags(String hashtags) {
		this.hashtags = hashtags;
	}
	
	public String toString() {
		return this.getTitle()+"/t"+this.getLeagues()+"/n"+this.getContent()+"/n"+"#"+this.getHashtags();
		
		
	}
	

}
