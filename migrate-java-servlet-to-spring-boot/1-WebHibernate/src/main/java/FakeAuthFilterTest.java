

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.junit.Before;
import org.junit.Test;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.servlet.FilterChain;
public class FakeAuthFilterTest {
	 private FakeAuthFilter filter;
	    private HttpServletRequest request;
	    private HttpServletResponse response;
	    private FilterChain chain;

	    @Before
	    public void setUp() {
	        filter = new FakeAuthFilter();
	        request = mock(HttpServletRequest.class);
	        response = mock(HttpServletResponse.class);
	        chain = mock(FilterChain.class);
	    }

	    @Test
	    public void testDoFilter_WithValidToken() throws Exception {
	        filter = new FakeAuthFilter();
	        request = mock(HttpServletRequest.class);
	        response = mock(HttpServletResponse.class);
	        chain = mock(FilterChain.class);
	        // Arrange
	        when(request.getHeader("X-Auth-Token")).thenReturn("fake-token");

	        // Act
	        filter.doFilter(request, response, chain);

	        // Assert
	        verify(chain, times(1)).doFilter(request, response);
	        verify(response, never()).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	    }

	    @Test
	    public void testDoFilter_WithInvalidToken() throws Exception {
	        // Arrange
	        when(request.getHeader("X-Auth-Token")).thenReturn("invalid-token");

	        StringWriter stringWriter = new StringWriter();
	        PrintWriter printWriter = new PrintWriter(stringWriter);
	        when(response.getWriter()).thenReturn(printWriter);

	        // Act
	        filter.doFilter(request, response, chain);

	        // Assert
	        verify(response).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	        printWriter.flush();
	        assertEquals("Unauthorized", stringWriter.toString());
	        verify(chain, never()).doFilter(request, response);
	    }

	    @Test
	    public void testDoFilter_WithoutToken() throws Exception {
	        // Arrange
	        when(request.getHeader("X-Auth-Token")).thenReturn(null);

	        StringWriter stringWriter = new StringWriter();
	        PrintWriter printWriter = new PrintWriter(stringWriter);
	        when(response.getWriter()).thenReturn(printWriter);

	        // Act
	        filter.doFilter(request, response, chain);

	        // Assert
	        verify(response).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	        printWriter.flush();
	        assertEquals("Unauthorized", stringWriter.toString());
	        verify(chain, never()).doFilter(request, response);
	    }
}
