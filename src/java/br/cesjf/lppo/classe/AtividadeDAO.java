package br.cesjf.lppo.classe;

import br.cesjf.lppo.Atividade;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AtividadeDAO {
     public List<Atividade> listaTodos() throws Exception {
        List<Atividade> todos = new ArrayList();
        try {
            Connection conexao = ConexaoJDBC.getInstance();
            Statement operacao = conexao.createStatement();
            ResultSet resultado = operacao.executeQuery("SELECT * FROM atividade");
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
     
     public void criar(Atividade novaAtividade) {
        try {
            Connection conexao = DriverManager.getConnection("jdbc:derby://localhost:1527/lppo-2016-1", "usuario", "senha");
            Statement operacao = conexao.createStatement();
            operacao.executeUpdate(String.format(Locale.US,"INSERT INTO atividade(funcionario, descricao,horas, tipo) VALUES('%s','%s',%d,'%s')",
                    novaAtividade.getFuncionario(), novaAtividade.getDescricao(), novaAtividade.getHoras(), novaAtividade.getTipo()));

        } catch (SQLException ex) {
            Logger.getLogger(AtividadeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    public Atividade buscarPorId(String id) throws Exception {
        Atividade atividade = null;
        try {
            Connection conexao = ConexaoJDBC.getInstance();
            Statement operacao = conexao.createStatement();
            ResultSet resultado = operacao.executeQuery(String.format("SELECT * FROM atividade WHERE id=%s", id));
            if (resultado.next()) {
                atividade = new Atividade();
                atividade.setId(resultado.getLong("id"));
                atividade.setFuncionario(resultado.getString("funcionario"));
                atividade.setDescricao(resultado.getString("descricao"));
                atividade.setHoras(resultado.getInt("horas"));
                atividade.setTipo(resultado.getString("tipo"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AtividadeDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(ex);
        }
        return atividade;
    }
    
    public boolean isCadastrado(Long id) throws Exception{
        try {
            Connection conexao = ConexaoJDBC.getInstance();
            Statement operacao = conexao.createStatement();
            ResultSet resultado = operacao.executeQuery(String.format("SELECT * FROM atividade WHERE id=%d", id));
            if (resultado.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AtividadeDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(ex);
        }
        return false;
    }
    
    public void atualizar(Atividade ativ) throws Exception{
        
        Connection conexao = ConexaoJDBC.getInstance();
        Statement operacao = conexao.createStatement();
        operacao.executeUpdate(String.format("UPDATE atividade SET funcionario='%s', descricao='%s', horas=%d, tipo='%s' WHERE id=%d", ativ.getFuncionario(), ativ.getDescricao(), ativ.getHoras(), ativ.getTipo(), ativ.getId()));
    }
}
