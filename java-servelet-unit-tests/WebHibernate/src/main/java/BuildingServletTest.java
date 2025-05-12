import jakarta.servlet.http.*;
import models.Building;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;
import org.mockito.MockedStatic;

import utils.HibernateUtils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.mockito.Mockito.*;

public class BuildingServletTest {
    
    @Test
    public void testDoPostCreateBuilding() throws Exception {
        // Arrange
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        Session mockSession = mock(Session.class);
        Transaction mockTx = mock(Transaction.class);

        when(request.getParameter("buildingName")).thenReturn("Mock Tower");
        when(mockSession.beginTransaction()).thenReturn(mockTx);
        StringWriter sw = new StringWriter();
        when(response.getWriter()).thenReturn(new PrintWriter(sw));

        try (MockedStatic<HibernateUtils> mockStatic = mockStatic(HibernateUtils.class)) {
            mockStatic.when(HibernateUtils::getSession).thenReturn(mockSession);

            BuildingServlet servlet = new BuildingServlet();
            servlet.doPost(request, response);

            verify(mockSession).saveOrUpdate(any(Building.class));
            verify(mockTx).commit();
        }

        sw.flush();
        System.out.println("doPost output: " + sw);
    }
    
    @Test
    public void testDoGetListAllBuildings() throws Exception {
        // Arrange
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        Session mockSession = mock(Session.class);
        Query mockQuery = mock(Query.class);
        StringWriter sw = new StringWriter();

        Building dummy = new Building();
        dummy.setBuildingName("Mock Building");

        when(mockQuery.list()).thenReturn(Collections.singletonList(dummy));
        when(mockSession.createQuery("FROM Building")).thenReturn(mockQuery);
        when(response.getWriter()).thenReturn(new PrintWriter(sw));

        try (MockedStatic<HibernateUtils> mockStatic = mockStatic(HibernateUtils.class)) {
            mockStatic.when(HibernateUtils::getSession).thenReturn(mockSession);

            BuildingServlet servlet = new BuildingServlet();
            servlet.doGet(request, response);

            verify(mockSession).createQuery("FROM Building");
        }

        sw.flush();
        System.out.println("doGet output: " + sw);
    }
    
    @Test
    public void doPostWithEmptyBuildingName() throws Exception {
        // Arrange
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        Session mockSession = mock(Session.class);
        Transaction mockTx = mock(Transaction.class);

        when(request.getParameter("buildingName")).thenReturn("");
        when(mockSession.beginTransaction()).thenReturn(mockTx);
        StringWriter sw = new StringWriter();
        when(response.getWriter()).thenReturn(new PrintWriter(sw));

        try (MockedStatic<HibernateUtils> mockStatic = mockStatic(HibernateUtils.class)) {
            mockStatic.when(HibernateUtils::getSession).thenReturn(mockSession);

            BuildingServlet servlet = new BuildingServlet();
            servlet.doPost(request, response);

            verify(mockSession).saveOrUpdate(any(Building.class));
            verify(mockTx).commit();
        }

        sw.flush();
        System.out.println("doPost output: " + sw);
    }

    @Test
    public void doPostWithHibernateException() throws Exception {
        // Arrange
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        Session mockSession = mock(Session.class);
        Transaction mockTx = mock(Transaction.class);

        when(request.getParameter("buildingName")).thenReturn("Problematic Building");
        when(mockSession.beginTransaction()).thenReturn(mockTx);
        HibernateException expectedException = new HibernateException("Database connection lost");
        doThrow(expectedException).when(mockSession).saveOrUpdate(any(Building.class));
        
        StringWriter sw = new StringWriter();
        when(response.getWriter()).thenReturn(new PrintWriter(sw));

        try (MockedStatic<HibernateUtils> mockStatic = mockStatic(HibernateUtils.class)) {
            mockStatic.when(HibernateUtils::getSession).thenReturn(mockSession);

            BuildingServlet servlet = new BuildingServlet();
            servlet.doPost(request, response);

            verify(mockTx).rollback();
        }

        sw.flush();
        assert(sw.toString().contains("Error: Database connection lost"));
    }

    @Test
    public void doGetWithEmptyBuildingsList() throws Exception {
        // Arrange
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        Session mockSession = mock(Session.class);
        Query mockQuery = mock(Query.class);
        StringWriter sw = new StringWriter();

        when(mockQuery.list()).thenReturn(new ArrayList<>());
        when(mockSession.createQuery("FROM Building")).thenReturn(mockQuery);
        when(response.getWriter()).thenReturn(new PrintWriter(sw));

        try (MockedStatic<HibernateUtils> mockStatic = mockStatic(HibernateUtils.class)) {
            mockStatic.when(HibernateUtils::getSession).thenReturn(mockSession);

            BuildingServlet servlet = new BuildingServlet();
            servlet.doGet(request, response);

            verify(mockSession).createQuery("FROM Building");
        }

        sw.flush();
        assert(sw.toString().equals("[]"));
    }

    @Test
    public void doGetWithMultipleBuildings() throws Exception {
        // Arrange
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        Session mockSession = mock(Session.class);
        Query mockQuery = mock(Query.class);
        StringWriter sw = new StringWriter();

        Building building1 = new Building();
        building1.setBuildingName("Tower A");
        Building building2 = new Building();
        building2.setBuildingName("Tower B");
        Building building3 = new Building();
        building3.setBuildingName("Tower C");

        when(mockQuery.list()).thenReturn(Arrays.asList(building1, building2, building3));
        when(mockSession.createQuery("FROM Building")).thenReturn(mockQuery);
        when(response.getWriter()).thenReturn(new PrintWriter(sw));

        try (MockedStatic<HibernateUtils> mockStatic = mockStatic(HibernateUtils.class)) {
            mockStatic.when(HibernateUtils::getSession).thenReturn(mockSession);

            BuildingServlet servlet = new BuildingServlet();
            servlet.doGet(request, response);

            verify(mockSession).createQuery("FROM Building");
            verify(response).setContentType("application/json");
            verify(response).setCharacterEncoding("UTF-8");
        }

        sw.flush();
        String output = sw.toString();
        assert(output.contains("Tower A"));
        assert(output.contains("Tower B"));
        assert(output.contains("Tower C"));
    }


}