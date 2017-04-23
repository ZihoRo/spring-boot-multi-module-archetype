#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.web.configuration;

import ${package}.web.interceptor.DemoInterceptor;
import ${package}.web.filter.DemoFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.Filter;

/**
 * Created by ${userName} on ${today}.
 */
@Configuration
public class ApplicationConfiguration extends WebMvcConfigurerAdapter {

    /**
     * 添加拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // AntPathMatcher.match(String pattern, String path)
        // 当要匹配所有链接时，urlPattern="/**"是最好的
        registry.addInterceptor(demoInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }

    /**
     * 添加过滤器
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean registerDemoFilter() {
        // org.apache.catalina.core.ApplicationFilterFactory.createFilterChain(ServletRequest request, Wrapper wrapper, Servlet servlet)
        // matchDispatcher(FilterMap filterMap, DispatcherType type)
        // matchFiltersURL(FilterMap filterMap, String requestPath) -> matchFiltersURL(String testPath, String requestPath)
        // 当要匹配所有链接时，urlPattern="*"是最好的
        FilterRegistrationBean registration = new FilterRegistrationBean(demoFilter());
        registration.addUrlPatterns("*");
        registration.addInitParameter("applicationName", "${parentArtifactId}");
        registration.setName("demoFilter");
        return registration;
    }

    public Filter demoFilter() {
        return new DemoFilter();
    }

    public HandlerInterceptor demoInterceptor() {
        return new DemoInterceptor();
    }
}
