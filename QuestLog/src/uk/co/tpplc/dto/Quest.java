package uk.co.tpplc.dto;
public class Quest {
	private Integer id;
	private String title;
	private String description;
	private String user;
	private String value;
	private Integer votes;
	private Integer voted;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Integer getVotes() {
		return votes;
	}
	public void setVotes(Integer votes) {
		this.votes = votes;
	}
	public void setVoted(Integer voted) {
		this.voted = voted;		
	}
	public Integer getVoted(){
		return this.voted;
	}
	public void setId(Integer id) {
		this.id = id;		
	}
	public Integer getId(){
		return id;
	}
}
