import org.postgresql.ds.PGSimpleDataSource;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener()
public class DbInitListener implements ServletContextListener
{
    public void contextInitialized(ServletContextEvent sce) {
        // создание пула соединений с базой данных
        PGSimpleDataSource poolingDataSource = new PGSimpleDataSource();
        // пропущена установка параметров соединения
        poolingDataSource.setUser("postgres");
        poolingDataSource.setPassword("5656");
        poolingDataSource.setURL("jdbc:postgresql://localhost:5432/Site1");
        // создание DAO-объектов
        AdvertsDAO advertsDAO = new AdvertsDAO(poolingDataSource);
        ServletContext sc = sce.getServletContext();

        // сохранение инициализированных объектов в контекст сервлетов
        sc.setAttribute("AdvertsDAO", advertsDAO);
        sc.setAttribute("datasource", poolingDataSource);
    }
}
