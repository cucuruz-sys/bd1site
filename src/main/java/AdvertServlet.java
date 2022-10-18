import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AdvertServlet", urlPatterns = {"/advert"})

public class AdvertServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("id") == null) {
            response.sendError(404); return;
        }
        AdvertsDAO advertsDAO = (AdvertsDAO) this.getServletContext().getAttribute("AdvertsDAO");
        Long id = Long.parseLong(request.getParameter("id"));
        Advert advert = advertsDAO.get(id);

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
}
