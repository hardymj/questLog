package uk.co.tpplc.servlets;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uk.co.tpplc.dto.Voted;
import uk.co.tpplc.sql.MySqlConnection;

public class UserVoted extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public UserVoted() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String questId = request.getParameter("quest");
		String user = request.getParameter("user");
		String voted = request.getParameter("voted");
		String email = request.getParameter("email");
		
		Voted votedDTO = new Voted();
		
		votedDTO.setQuestId(Integer.parseInt(questId));
		votedDTO.setUserId(user);
		votedDTO.setVoted(Integer.parseInt(voted));
		votedDTO.setEmail(email);
		
		MySqlConnection mySql = new MySqlConnection();
		mySql.updateVote(votedDTO);
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}