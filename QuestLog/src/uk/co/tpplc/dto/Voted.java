package uk.co.tpplc.dto;

public class Voted {

	private Integer questId;
	private String userId;
	private Integer voted;
	private String email;
	public Integer getQuestId() {
		return questId;
	}
	public void setQuestId(Integer questId) {
		this.questId = questId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Integer getVoted() {
		return voted;
	}
	public void setVoted(Integer voted) {
		this.voted = voted;
	}
	public void setEmail(String email) {
		this.email= email;		
	}
	public String getEmail(){
		return this.email;
	}
}