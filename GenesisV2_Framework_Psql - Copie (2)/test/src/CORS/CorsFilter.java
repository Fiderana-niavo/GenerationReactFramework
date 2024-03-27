package CORS;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CorsFilter implements Filter{

    private void setResponseAccess(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
        response.addHeader("Access-Control-Allow-Headers", "Content-Type");
        // response.setHeader("Access-Control-Allow-Credentials", "true");

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        setResponseAccess(httpResponse);
        
        System.out.println("FILTREEEEEEE -------------------------");

        if(httpRequest.getMethod().equals("OPTIONS")){
            httpResponse.setStatus(HttpServletResponse.SC_ACCEPTED);
            return;
        }       

        chain.doFilter(httpRequest, response);
    }

    @Override
    public void destroy(){
    }
     
}