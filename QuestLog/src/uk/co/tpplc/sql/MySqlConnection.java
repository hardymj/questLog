package uk.co.tpplc.sql;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import uk.co.tpplc.dto.Quest;
import uk.co.tpplc.dto.Voted;


public class MySqlConnection {
	protected Connection getConnection(){
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/questLog?");
		    return conn;
		} catch (SQLException ex) {
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError1: " + ex.getErrorCode());
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public void submitQuest(Quest quest){
		try {
			Connection conn = getConnection();
			String systems = "insert into quest(title,description,user,value)values(?,?,?,?)";
			CallableStatement proc_select = conn.prepareCall(systems);
			
			proc_select.setString(1,quest.getTitle());
			proc_select.setString(2,quest.getDescription());
			proc_select.setString(3,quest.getUser());
			proc_select.setString(4,quest.getValue());			
			proc_select.execute();
			proc_select.close();
			conn.close();			
		}
		catch (SQLException ex) {
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError2: " + ex.getErrorCode());
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public ArrayList<Quest> retrieveQuests(String userId){		
		ArrayList<Quest> quests = new ArrayList<Quest>();		
		try {
			Connection conn = getConnection();
			String systems = "select q.id,q.title,q.description,q.user,q.value ,(select sum(qv.voted) from questvote qv where qv.questid = q.id) as votes,(select qv2.voted END from questvote qv2 join user u on u.id = qv2.userid where qv2.questId = q.id and u.googleId = ?) as voted from quest q order by (select sum(qv.voted) from questvote qv where qv.questid = q.id) desc";
				
			CallableStatement proc_select = conn.prepareCall(systems);
			if(userId == null || userId.equals("")){
				userId = "0";
			}
			//Integer intUserId = Integer.parseInt(userId);			
			proc_select.setString(1,userId); //Need to add in real userid
			
			ResultSet rs = proc_select.executeQuery();
			while (rs.next()) {
				Integer id = (Integer) rs.getObject("id");
				String title = (String) rs.getObject("title");
				String description = (String) rs.getObject("description");
				String user = (String) rs.getObject("user");
				String value = (String) rs.getObject("value");
				BigDecimal votes = (BigDecimal) rs.getObject("votes");
				Integer votesInteger = votes != null ? votes.intValue() : null;
				Integer votedInteger = (Integer) rs.getObject("voted");
				
				Quest quest = new Quest();
				quest.setId(id);
				quest.setTitle(title);
				quest.setDescription(description);
				quest.setUser(user);
				quest.setValue(value);
				quest.setVotes(votesInteger);
				quest.setVoted(votedInteger);
				quests.add(quest);
			}
			
			conn.close();
			return quests;
		}
		catch (SQLException ex) {
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError2: " + ex.getErrorCode());
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return quests;
	}
	
	public void updateVote(Voted voted){
		try {
			Connection conn = getConnection();
			
			String systems = "select id from user where username = ? or googleId = ?";
			CallableStatement proc_select = conn.prepareCall(systems);
			proc_select.setString(1,voted.getEmail());
			proc_select.setString(2,voted.getUserId());
			ResultSet rs = proc_select.executeQuery();
			Integer userId = -1;
			while (rs.next()) {
				userId = (Integer) rs.getObject("id");
			}
			proc_select.close();
			
			if(userId == -1){
				systems = "insert into user (username,googleId) values(?,?)";
				proc_select = conn.prepareCall(systems);
				proc_select.setString(1,voted.getEmail());
				proc_select.setString(2,voted.getUserId());
				proc_select.execute();
				proc_select.close();
				
				systems = "select id from user where username = ? or googleId = ?";
				proc_select = conn.prepareCall(systems);
				proc_select.setString(1,voted.getEmail());
				proc_select.setString(2,voted.getUserId());
				rs = proc_select.executeQuery();
				userId = -1;
				while (rs.next()) {
					userId = (Integer) rs.getObject("id");
				}
				proc_select.close();
			}			
			systems = "select id from questVote where questId = ? and userId = ?";
			proc_select = conn.prepareCall(systems);
			proc_select.setInt(1,voted.getQuestId());
			proc_select.setInt(2,userId);
			rs = proc_select.executeQuery();
			
			Integer id = -1;
			while (rs.next()) {
				id = (Integer) rs.getObject("id");
			}
			rs.close();
			proc_select.close();
			
			if(id == -1){
				systems = "insert into questVote (questId,userId,voted) values("+voted.getQuestId()+","+userId+","+userId+")";
			}
			else{
				systems = "update questVote set voted = "+voted.getVoted() + " where id = "+id;
			}
			proc_select = conn.prepareCall(systems);
			proc_select.execute();
			proc_select.close();
			conn.close();
			
		}
		catch (SQLException ex) {
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError2: " + ex.getErrorCode());
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
}