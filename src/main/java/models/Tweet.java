package models;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@Document
public class Tweet {
	
	@Id
	private long id;

	private String text;
	
	private String location;
	
	@Field(value="created_at")
	private Date createdAt;
	
	@Field(value="retweet_count")
	private int retweetCount;
	
	public Tweet() {}
	
	public Tweet(long id, String text, String location, Date createdAt, int retweetCount) {
		super();
		this.id = id;
		this.text = text;
		this.location = location;
		this.createdAt = createdAt;
		this.retweetCount = retweetCount;
	}


	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text;
	}


	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}


	public Date getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}


	public int getRetweetCount() {
		return retweetCount;
	}


	public void setRetweetCount(int i) {
		this.retweetCount = i;
	}

	public long getId() {
		return id;
	}

	public void setId(long l) {
		this.id = l;
	}

	@Override
	public String toString() {
		return this.id+"   text:  "+ text +"   location  :  "+location;
	}
}
