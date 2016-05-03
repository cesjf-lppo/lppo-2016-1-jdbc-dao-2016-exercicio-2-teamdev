package br.cesjf.lppo.servlets;


import br.cesjf.lppo.Atividade;
import br.cesjf.lppo.classe.AtividadeDAO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AtividadeController", urlPatterns = {"/atividade/listar.html", "/atividade/cadastrar.html"})
public class AtividadeController extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getRequestURI().contains("listar.html")) {
            List<Atividade> lista = new ArrayList<>();
            AtividadeDAO dao = new AtividadeDAO();
            try {
                lista = dao.listaTodos();
            } catch (Exception ex) {
                Logger.getLogger(AtividadeController.class.getName()).log(Level.SEVERE, null, ex);
                lista= new ArrayList<Atividade>();
                request.setAttribute("erro", "problema ao listar os estabelecimentos");
            }
            request.setAttribute("atividades", lista);
            request.getRequestDispatcher("/WEB-INF/views/atividade/listar.jsp").forward(request, response);
        }
    }
}