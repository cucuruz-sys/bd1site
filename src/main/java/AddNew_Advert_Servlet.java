import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "AddNew_Advert_Servlet", urlPatterns = {"/new"})

public class AddNew_Advert_Servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AdvertsDAO advertsDAO = (AdvertsDAO)this.getServletContext().getAttribute("AdvertsDAO");
        Advert advert;
        request.setCharacterEncoding("UTF-8");
        advert = new Advert();
        String name = request.getParameter("name");
        if (name != null) { advert.setName(name); }
        Long id_user = Long.parseLong(request.getParameter("id_user"));
        if (id_user != null) { advert.setId_user(id_user); }
        int price = Integer.parseInt(request.getParameter("price"));
        if (price != 0) { advert.setPrice(price); }
        String description = request.getParameter("description");
        if (description != null) { advert.setDescription(description);
        }
        advert.setId(0);
        Date date = Date.valueOf(request.getParameter("date"));
        if (date != null) {
            advert.setDate(date);
        }
        advert.setCategoryid(Long.parseLong(request.getParameter("categoryId")));
        try {
            advertsDAO.save(advert);
        } catch (SQLException e) {
            log("Failed to save advert", e);
            response.sendError(500, "Failed to save advert: " + e.getMessage());
        }
        response.sendRedirect("/adverts");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CategoriesDAO categoriesDAO = (CategoriesDAO) this.getServletContext().getAttribute("CategoriesDAO");
        List<Category> categories = categoriesDAO.list();
        request.setAttribute("categories", categories);
        this.getServletContext()
                .getRequestDispatcher("/WEB-INF/Pages/add_new_advert.jsp")
                .forward(request, response);
    }
}
