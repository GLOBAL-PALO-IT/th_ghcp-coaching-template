

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Building;
import models.Room;
import utils.HibernateUtils;

import java.io.IOException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Servlet implementation class HelloServlet
 */
@WebServlet("/HelloServlet")
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HelloServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// send hello world message
		response.getWriter().append("Hello World");
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
			Room room = new Room("Room 1", new Building("Building 1"));
			session.saveOrUpdate(room);
			ta.commit();
		} catch (HibernateException ex) {
			
			if (ta != null) {
				ta.rollback();
			}
			ex.printStackTrace();
			message = ex.getStackTrace().toString();
		} finally {
			session.close();
		}
		response.getWriter().append(message);	
	}

}
