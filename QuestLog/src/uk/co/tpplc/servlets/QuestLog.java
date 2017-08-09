package uk.co.tpplc.servlets;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uk.co.tpplc.dto.Quest;
import uk.co.tpplc.sql.MySqlConnection;

public class QuestLog extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public QuestLog() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MySqlConnection mySql = new MySqlConnection();
		String action = request.getParameter("action");
		if(action != null){		
			String title = request.getParameter("title");
			String description = request.getParameter("description");
			String user = request.getParameter("user");
			String value = request.getParameter("value");			
			if(title != null){
				Quest quest = new Quest();
				quest.setTitle(title);
				quest.setDescription(description);
				quest.setUser(user);
				quest.setValue(value);		
				mySql.submitQuest(quest);
			}
		}
		
		/*ArrayList<Quest> quests = mySql.retrieveQuests("");
		request.setAttribute("quests",quests);*/
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("questLog2.html");
		requestDispatcher.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}