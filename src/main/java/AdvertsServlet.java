import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdvertsServlet", urlPatterns = {"/adverts"})

public class AdvertsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AdvertsDAO advertsDAO = (AdvertsDAO)this.getServletContext().getAttribute("AdvertsDAO");
        if (request.getParameter("id") == null) {
            List<Advert> adverts = advertsDAO.list();
            request.setAttribute("adverts", adverts);
            int BiggerId=0;
            for (Advert el:adverts) {
                if(BiggerId<Math.toIntExact(el.getId())){
                    BiggerId=Math.toIntExact(el.getId());
                }
            }
            String[] files= new String[BiggerId];
            for (Advert el:adverts) {
                String temp = getClass().getProtectionDomain().getCodeSource().getLocation().toString().substring(6).substring(0 ,getClass().getProtectionDomain().getCodeSource().getLocation().toString().substring(6).length() - 16 ) + "Images/id" + String.valueOf(el.getId()) + ".jpg";
                File f = new File(temp);
                if(f.exists() && !f.isDirectory()) {
                    files[Math.toIntExact(el.getId()) - 1] = "../Images/id" + String.valueOf(el.getId()) + ".jpg";
                } else {
                    files[Math.toIntExact(el.getId()) - 1] ="../Images/placeholder.jpg";
                }
            }
            request.setAttribute("pathes", files);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Pages/adverts.jsp");
            dispatcher.forward(request, response);
//            response.setContentType("text/html;charset=UTF-8");
//            PrintWriter out = response.getWriter();
//            out.print("<html><head><title>Список объявлений</title></head>");
//            out.print("<body><table>");
//            for (Advert ad : adverts) {
//                out.print("<tr><td>");
//                out.print(String.format(
//                        "<a href='/advert?id=%d'>%s</a>", ad.getId(),
//                        ad.getName()
//                ));
//                out.print("</td></tr>");
//            }
//            out.print("</table></body></html>");
        } else {
            response.sendRedirect("advert?id=" + request.getParameter("id"));
        }
    }
}
