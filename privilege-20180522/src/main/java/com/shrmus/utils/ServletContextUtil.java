package com.shrmus.utils;

import javax.servlet.ServletContext;

import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

@Component
public final class ServletContextUtil {
	private static ServletContext serveltContext = null;  
    
    private ServletContextUtil(){};  
      
    public synchronized static ServletContext getServletContext() {  
          
        if(null == serveltContext) {  
            WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();    
            serveltContext = webApplicationContext.getServletContext();   
        }   
        return serveltContext;  
    }  
}
