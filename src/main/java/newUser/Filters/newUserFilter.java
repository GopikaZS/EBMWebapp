package newUser.Filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class newUserFilter
 */
@WebFilter("/newUserFilter")
public class newUserFilter extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public newUserFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        // Retrieve parameters from the request
        String firstName = request.getParameter("firstName").trim();
        String lastName = request.getParameter("lastName").trim();
        String phoneNumber = request.getParameter("phoneNumber").trim();
        String street = request.getParameter("street").trim();
        String district = request.getParameter("district").trim();
        String state = request.getParameter("state").trim();
        String pincode = request.getParameter("postCode").trim();
        String requestType = request.getParameter("isAccount");

        // Initialize error message
        StringBuilder errorMessage = new StringBuilder();

        // Perform input validation
        if (firstName.isEmpty()) {
            errorMessage.append("First name is required.\n");
        }
        if (lastName.isEmpty()) {
            errorMessage.append("Last name is required.\n");
        }
        if (phoneNumber.isEmpty()) {
            errorMessage.append("Phone number is required.\n");
        } else if (phoneNumber.length() != 10) {
            errorMessage.append("Phone number must be 10 digits long.\n");
        }
        if (street.isEmpty()) {
            errorMessage.append("Street is required.\n");
        }
        if (district.isEmpty()) {
            errorMessage.append("District is required.\n");
        }
        if (state.isEmpty()) {
            errorMessage.append("State is required.\n");
        }
        if (pincode.isEmpty()) {
            errorMessage.append("Pincode is required.\n");
        }
        if (requestType == null) {
            errorMessage.append("Please select an option for request.\n");
        }

        // If there are validation errors, forward to an error page or handle them accordingly
        if (errorMessage.length() > 0) {
            request.setAttribute("errorMessage", errorMessage.toString());
            request.getRequestDispatcher("/errorPage.jsp").forward(request, response);
        } else {
            // If validation succeeds, proceed with the request
            chain.doFilter(request, response);
        }
    }
    
    
	public void destroy() {
		// TODO Auto-generated method stub
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
