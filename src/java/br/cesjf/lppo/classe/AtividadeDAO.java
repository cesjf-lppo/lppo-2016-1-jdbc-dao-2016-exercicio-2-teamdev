package br.cesjf.lppo.classe;

import br.cesjf.lppo.Atividade;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AtividadeDAO {
     public List<Atividade> listaTodos() throws Exception {
        List<Atividade> todos = new ArrayList();
        try {
            Connection conexao = ConexaoJDBC.getInstance();
            Statement operacao = conexao.createStatement();
            ResultSet resultado = operacao.executeQuery("SELECT * FROM estabelecimento");
            while (resultado.next()) {
                Atividade ativ = new Atividade();
                ativ.setId(resultado.getLong("id"));
                ativ.setDescricao(resultado.getString("descricao"));
                ativ.setFuncionario(resultado.getString("funcionario"));
                ativ.setTipo(resultado.getString("tipo"));
                ativ.setHoras(resultado.getInt("horas"));
                todos.add(ativ);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AtividadeDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(ex);
        }
        return todos;
    }
}
