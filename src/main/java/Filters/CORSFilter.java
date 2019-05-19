package Filters;

import Responses.BaseResponse;
import Utils.JwTokenHelper;
import com.google.gson.Gson;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class CORSFilter
 */
// Enable it for Servlet 3.x implementations
/* @ WebFilter(asyncSupported = true, urlPatterns = { "/*" }) */
public class CORSFilter implements Filter {

    final JwTokenHelper jwTokenHelper = JwTokenHelper.getInstance();

    /**
     * Default constructor.
     */
    public CORSFilter() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @see Filter#destroy()
     */
    @Override
    public void destroy() {
        // TODO Auto-generated method stub
    }

    /**
     * @param servletRequest
     * @param servletResponse
     * @param chain
     * @throws java.io.IOException
     * @throws javax.servlet.ServletException
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;

        // Authorize (allow) all domains to consume the content
        ((HttpServletResponse) servletResponse).addHeader("Access-Control-Allow-Origin", "*");
        ((HttpServletResponse) servletResponse).addHeader("Access-Control-Allow-Methods", "GET, OPTIONS, HEAD, PUT, POST");

        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if ("/auth".equals(request.getPathInfo())) {
            chain.doFilter(request, servletResponse);
        } else {
            String token = request.getHeader("privateKey");
            if (token == null || token.isEmpty()) {
                sendUnAuthorizeResponse(response, "Forbidden Access");
            }
            if (jwTokenHelper.claimKey(token)) {
                chain.doFilter(request, servletResponse);
            } else {
                sendUnAuthorizeResponse(response, "Invalid Token");
            }

        }

    }

    /**
     * @param fConfig
     * @throws javax.servlet.ServletException
     * @see Filter#init(FilterConfig)
     */
    @Override
    public void init(FilterConfig fConfig) throws ServletException {
        // TODO Auto-generated method stub
    }

    private void sendUnAuthorizeResponse(HttpServletResponse response, String message) throws IOException {
        BaseResponse myResponse = new BaseResponse(message, 0);
        String json = new Gson().toJson(myResponse);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

}
