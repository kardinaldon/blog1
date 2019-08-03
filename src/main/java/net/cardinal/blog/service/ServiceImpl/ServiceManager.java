package net.cardinal.blog.service.ServiceImpl;

import net.cardinal.blog.service.BusinessService;
import net.cardinal.blog.util.AppUtil;
import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import java.sql.SQLException;
import java.util.Properties;

public class ServiceManager {
    public static ServiceManager getInstance(ServletContext context) {
        ServiceManager instance = (ServiceManager) context.getAttribute(SERVICE_MANAGER);
        if (instance==null) {
            instance = new ServiceManager(context);
            context.setAttribute(SERVICE_MANAGER,instance);
        }
        return instance;
    }

    public void destroy () {
        try {
            dataSource.close();
        } catch (SQLException e) {
            LOGGER.info("Service manager instance destroyed " + e.getMessage(), e);
        }

    }

    public BusinessService getBusinessService() {
        return businessService;
    }

    public String getApplicationProperty(String property) {
        return applicationProperties.getProperty(property);
    }

    private static final String SERVICE_MANAGER = "SERVICE_MANAGER";
    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceManager.class);
    final Properties applicationProperties = new Properties();
    final BasicDataSource dataSource;  //instance of pool
    final BusinessService businessService;
    private ServiceManager (ServletContext context) {
        AppUtil.loadProperties(applicationProperties, "application.properties");
        dataSource = createBasicDataSource();
        businessService = new BusinessServiceImpl();
        LOGGER.info("Service manager instance created");
    }

    private BasicDataSource createBasicDataSource () {
        BasicDataSource ds = new BasicDataSource();
        ds.setDefaultAutoCommit(false);
        ds.setRollbackOnReturn(true);
        ds.setDriverClassName(getApplicationProperty("db.driver"));
        ds.setUrl(getApplicationProperty("db.url"));
        ds.setUsername(getApplicationProperty("db.username"));
        ds.setPassword(getApplicationProperty("db.password"));
        ds.setInitialSize(Integer.parseInt(getApplicationProperty("db.pool.initSize")));
        ds.setMaxTotal(Integer.parseInt(getApplicationProperty("db.pool.maxSize")));
        return ds;
    }
}
