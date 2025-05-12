

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Building;
import utils.HibernateUtils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.google.gson.Gson;

/**
 * Servlet implementation class BuildingServlet
 */
@WebServlet("/BuildingServlet")
public class BuildingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuildingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        // Query data
        Session session = HibernateUtils.getSession();
        @SuppressWarnings("unchecked")
        List<Building> buildings = session.createQuery("FROM Building").list();

        // Set response headers
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Convert to JSON and write
        PrintWriter out = response.getWriter();
        String json = new Gson().toJson(buildings); // Requires Gson
        out.print(json);
        out.flush();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    Session session = HibernateUtils.getSession();
	    Transaction ta = null;
	    String message = "Success";

	    try {
	        ta = session.beginTransaction();
	        Building building = new Building();
	        building.setBuildingName(request.getParameter("buildingName"));
	        session.saveOrUpdate(building);
	        ta.commit();
	    } catch (HibernateException ex) {
	        if (ta != null) {
	            ta.rollback();
	        }
	        ex.printStackTrace();
	        message = "Error: " + ex.getMessage(); // ✅ easier to test
	    } finally {
	        session.close();
	    }

	    response.getWriter().append(message);
	}

}
