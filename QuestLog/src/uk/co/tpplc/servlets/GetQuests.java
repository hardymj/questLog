package uk.co.tpplc.servlets;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import uk.co.tpplc.dto.Quest;
import uk.co.tpplc.sql.MySqlConnection;

public class GetQuests extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public GetQuests() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MySqlConnection mySql = new MySqlConnection();
		String user = request.getParameter("user");
		ArrayList<Quest> quests = mySql.retrieveQuests(user);
		String jsonString = new Gson().toJson(quests);		
		PrintWriter out;
		try {
			out = response.getWriter();
			out.write(jsonString);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}