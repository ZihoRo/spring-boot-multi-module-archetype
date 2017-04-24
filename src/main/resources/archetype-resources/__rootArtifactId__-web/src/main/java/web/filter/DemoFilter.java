#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.web.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by ${userName} on ${today}.
 */
@Slf4j
public class DemoFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("enter filter init");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("enter filter doFilter");
        chain.doFilter(request, response);
    }

    public void destroy() {
        log.info("enter filter destroy");
    }
}
