import lombok.Data;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Date;
import java.util.List;

@WebServlet(name = "Edit_Advert_Servlet", urlPatterns = {"/advert/edit"})

public class Edit_Advert_Servlet extends HttpServlet{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AdvertsDAO advertsDAO = (AdvertsDAO)this.getServletContext().getAttribute("AdvertsDAO");
        Advert advert;
        request.setCharacterEncoding("UTF-8");
        if (request.getParameter("id") != null) {
            long id = Long.parseLong(request.getParameter("id"));
            advert = advertsDAO.get(id);
        } else {
            advert = new Advert();
        }
        if (advert == null) {
            response.sendError(404); return;
        }
        String name = request.getParameter("name");
        if (name != null) { advert.setName(name); }
        Long id_user = Long.parseLong(request.getParameter("id_user"));
        if (id_user != null) { advert.setId_user(id_user); }
        int price = Integer.parseInt(request.getParameter("price"));
        if (price != 0) { advert.setPrice(price); }
        String description = request.getParameter("description");
        if (description != null) { advert.setDescription(description);
        }
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
        if (request.getParameter("id") == null) {
            response.sendError(404);
            return;
        }
        AdvertsDAO advertsDAO = (AdvertsDAO) this.getServletContext().getAttribute("AdvertsDAO");
        Long id = Long.parseLong(request.getParameter("id"));
        Advert advert = advertsDAO.get(id);
        CategoriesDAO categoriesDAO = (CategoriesDAO) this.getServletContext().getAttribute("CategoriesDAO");
        Category category = categoriesDAO.get(advert.getCategoryid());
        String temp = getClass().getProtectionDomain().getCodeSource().getLocation().toString().substring(6).substring(0, getClass().getProtectionDomain().getCodeSource().getLocation().toString().substring(6).length() - 16) + "Images/id" + String.valueOf(advert.getId()) + ".jpg";
        File f = new File(temp);
        String files = "";
        if (f.exists() && !f.isDirectory()) {
            files = "../Images/id" + String.valueOf(advert.getId()) + ".jpg";
        } else {
            files = "../Images/placeholder.jpg";
        }
        request.setAttribute("path", files);
        if (advert == null) {
            response.sendError(404);
            return;
        }
        request.setAttribute("advert", advert);
        request.setAttribute("category", category);
        List<Category> categories = categoriesDAO.list();
        request.setAttribute("categories", categories);
        this.getServletContext()
                .getRequestDispatcher("/WEB-INF/Pages/edit_advert.jsp")
                .forward(request, response);
    }
}
