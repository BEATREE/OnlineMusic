package club.teenshare.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.SessionAware;

/**
 * Servlet Filter implementation class UserFilter
 */
@WebFilter("/UserFilter")
public class AdminFilter implements Filter {

    /**
     * Default constructor. 
     */
    public AdminFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, 
			FilterChain chain) throws IOException, ServletException {
		//将类型进行转化
		 HttpServletRequest req = (HttpServletRequest) request;
		 HttpServletResponse res = (HttpServletResponse) response;
		 req.setCharacterEncoding("UTF-8");
		 res.setCharacterEncoding("UTF-8");
		 HttpSession session = req.getSession();//获取session
		 PrintWriter out = res.getWriter();	//得到输出的对象
		 if (session.getAttribute("currentAdmin")!=null) {
			 chain.doFilter(request, response);
		}else{
			res.sendRedirect(req.getContextPath()+"/alogin.jsp");
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
