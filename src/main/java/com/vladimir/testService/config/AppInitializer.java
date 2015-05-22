package com.vladimir.testService.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;


public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer
        implements WebApplicationInitializer {

    private static final String SERVLET_NAME = "dispatcher";

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
        appContext.register(WebConfig.class);
        servletContext.addListener(new ContextLoaderListener(appContext));

        appContext.setServletContext(servletContext);

        ServletRegistration.Dynamic servletRegistration = servletContext.addServlet(SERVLET_NAME, new DispatcherServlet(appContext));
        servletRegistration.addMapping("/");
        servletRegistration.setLoadOnStartup(1);

    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] {
                DataConfig.class
        };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[0];
    }

    @Override
    protected String[] getServletMappings() {
        return new String[0];
    }


}
