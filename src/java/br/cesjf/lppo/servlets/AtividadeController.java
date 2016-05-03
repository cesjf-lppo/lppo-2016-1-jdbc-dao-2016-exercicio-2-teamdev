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
import javax.servlet.http.HttpSession;

@WebServlet(name = "AtividadeController", urlPatterns = {"/atividade/listar.html", "/AtividadeController"})
public class AtividadeController extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AtividadeDAO dao = new AtividadeDAO();
        HttpSession session = request.getSession(true);
        String url = (String) request.getParameter("url");
        String idEditar = (String) request.getParameter("id");
        session.setAttribute("editar", false);
        
        if("editar".equals(url)){
            Atividade atividadeEditar = null;
            try {
                atividadeEditar = dao.buscarPorId(idEditar);
            } catch (Exception ex) {
                Logger.getLogger(AtividadeController.class.getName()).log(Level.SEVERE, null, ex);
            }
            session.setAttribute("atividadeEditar", atividadeEditar);
            session.setAttribute("editar", true);
            response.sendRedirect("/TeamDev/atividade/listar.html");
        }
        
        if (request.getRequestURI().contains("listar.html")) {
            List<Atividade> lista = new ArrayList<>();
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
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getRequestURI().contains("listar.html")){
            Atividade formulario = new Atividade();
            formulario.setFuncionario(request.getParameter("funcionario"));
            formulario.setDescricao(request.getParameter("descricao"));
            formulario.setHoras(Integer.parseInt(request.getParameter("horas")));
            formulario.setTipo(request.getParameter("tipo"));
            HttpSession session = request.getSession(true);
            AtividadeDAO dao = new AtividadeDAO();
            Atividade edicao = (Atividade)session.getAttribute("atividadeEditar");
            try {
                if(edicao != null && dao.isCadastrado(edicao.getId())){
                    try {
                        formulario.setId(edicao.getId());
                        edicao = formulario;
                        dao.atualizar(edicao);
                        session.setAttribute("atividadeEditar", null);
                    } catch (Exception ex) {
                        Logger.getLogger(AtividadeController.class.getName()).log(Level.SEVERE, null, ex);
                    }       
                }else dao.criar(formulario);
            } catch (Exception ex) {
                Logger.getLogger(AtividadeController.class.getName()).log(Level.SEVERE, null, ex);
            }
            response.sendRedirect("listar.html");
        }
    }
}