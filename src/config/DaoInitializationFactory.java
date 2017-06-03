package config;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import dao.UsineDao;

@WebListener
public class DaoInitializationFactory implements ServletContextListener {
	private static final String ATT_DAO_FACTORY = "usinedao";

	private UsineDao daoFactory;

	@Override
	public void contextInitialized(ServletContextEvent event) {
		ServletContext servletContext = event.getServletContext();
		this.daoFactory = UsineDao.getInstance();

		servletContext.setAttribute(ATT_DAO_FACTORY, this.daoFactory);
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
	}
}