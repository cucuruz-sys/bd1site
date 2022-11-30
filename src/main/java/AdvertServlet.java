import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

@WebServlet(name = "AdvertServlet", urlPatterns = {"/advert"})

public class AdvertServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("id") == null) {
            response.sendError(404); return;
        }
        AdvertsDAO advertsDAO = (AdvertsDAO) this.getServletContext().getAttribute("AdvertsDAO");
        Long id = Long.parseLong(request.getParameter("id"));
        Advert advert = advertsDAO.get(id);
        String temp = getClass().getProtectionDomain().getCodeSource().getLocation().toString().substring(6).substring(0 ,getClass().getProtectionDomain().getCodeSource().getLocation().toString().substring(6).length() - 16 ) + "Images/id" + String.valueOf(advert.getId()) + ".jpg";
        File f = new File(temp);
        String files="";
        if(f.exists() && !f.isDirectory()) {
            files = "../Images/id" + String.valueOf(advert.getId()) + ".jpg";
        } else {
            files ="../Images/placeholder.jpg";
        }
        request.setAttribute("path", files);
        if (advert == null) { response.sendError(404); return; }
        request.setAttribute("advert", advert);
        this.getServletContext()
                .getRequestDispatcher("/WEB-INF/Pages/advert.jsp")
                .forward(request, response);
//        response.setContentType("text/html;charset=UTF-8");
//        PrintWriter out = response.getWriter();
//        out.print(String.format("<html><head><title>Детальная информация о фильме %s</title></head>", advert.getName()));
//        out.print("<body>");
//        out.print("<h1>" + advert.getName() + "</h1><h4>" + advert.getCategory() + "</h4>");
//        out.print("<div>" + "Id: " + advert.getId() + "</div>");
//        out.print("<div>" + "Дата: " + advert.getDate() + "</div>");
//        out.print("<div>" + "Цена: " + advert.getPrice() + "</div>");
//        out.print("<div>" + "Изображение:" + advert.getPicture_ref() + "</div>");
//        out.print("</body></html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AdvertsDAO advertsDAO = (AdvertsDAO)this.getServletContext().getAttribute("AdvertsDAO");
        CategoriesDAO categoriesDAO = (CategoriesDAO)this.getServletContext().getAttribute("CategoriesDAO");
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
        try {
            advertsDAO.delete(Long.parseLong(request.getParameter("id")));
        } catch (SQLException e) {
            log("Failed to delete advert", e);
            response.sendError(500, "Failed to delete advert: " + e.getMessage());
        }
        response.sendRedirect("/adverts");
    }
}
