package coms362.cards.webapp;

import coms362.cards.socket.ServletContextHolder;

public class WebappConfig {
    private String resourceBase;
    private String contextPath;
    private String defaultsDescriptor;
    private ServletContextHolder servletContextHolder;

    public WebappConfig(String resourceBase, String contextPath, String defaultsDescriptor,
            ServletContextHolder servletContextHolder) {
        this.resourceBase = resourceBase;
        this.contextPath = contextPath;
        this.defaultsDescriptor = defaultsDescriptor;
        this.servletContextHolder = servletContextHolder;
    }

    public String getResourceBase() {
        return resourceBase;
    }

    public String getContextPath() {
        return contextPath;
    }

    public String getDefaultsDescriptor() {
        return defaultsDescriptor;
    }
    
    public ServletContextHolder getServletContextHolder() {
        return servletContextHolder;
    }
}
