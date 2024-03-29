package net.cardinal.blog.listener;

import net.cardinal.blog.service.ServiceImpl.ServiceManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ApplicationListener implements ServletContextListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServiceManager.getInstance(sce.getServletContext());
        LOGGER.info("App Blog started");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServiceManager.getInstance(sce.getServletContext()).destroy();
        LOGGER.info("App Blog ended");
    }
}
