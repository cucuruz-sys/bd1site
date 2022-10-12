import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "AdvertsServlet", urlPatterns = {"/adverts"})

public class AdvertsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            javax.servlet.ServletException, IOException {
        AdvertsDAO advertsDAO = (AdvertsDAO)this.getServletContext().getAttribute("AdvertsDAO");
        if (request.getParameter("id") == null) {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.print("<html><head><title>Список объявлений</title></head>");
            out.print("<body><table>");
            List<Advert> adverts = advertsDAO.list();
            for (Advert ad : adverts) {
                out.print("<tr><td>");
                out.print(String.format(
                        "<a href='/advert?id=%d'>%s</a>", ad.getId(),
                        ad.getName()
                ));
                out.print("</td></tr>");
            }
            out.print("</table></body></html>");
        } else {
            response.sendRedirect("advert?id=" + request.getParameter("id"));
        }
    }
}
