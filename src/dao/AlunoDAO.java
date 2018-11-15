package dao;

import Model.Aluno;
import Model.OperacaoBanco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import util.Acoes;

/**
 *
 * @author edilsongm
 */
public class AlunoDAO implements Acoes {

    Connection con = null;

    OperacaoBanco op = new OperacaoBanco();

    public AlunoDAO() {
        op.carregarDriver();
    }

    @Override
    public void incluir(Aluno a) {
        Connection conexao = op.obterConexao();
        PreparedStatement pre = null;

        if (a != null) {
            try {
                String sql = ("Insert into alunos (nome, curso, idade, ra) values (?,?,?,?)");
                pre = conexao.prepareStatement(sql);
                pre.setString(1, a.getNome());
                pre.setString(2, a.getCurso());
                pre.setInt(3, a.getIdade());
                pre.setString(4, a.getRa());
                pre.executeUpdate();
                System.out.println("Inclusão realizada com sucesso!");
                JOptionPane.showMessageDialog(null, "Aluno incluido com sucesso!");
            } catch (SQLException c) {
                System.out.println("Erro ao incluir: " + c.getMessage());
            }
        }
    }

    /*
    public void incluirAluno(Aluno a) {
        Connection conexao = op.obterConexao();
        PreparedStatement pre = null;

        try {
            String sql = ("Insert into alunos (nome, curso, idade, ra) values (?,?,?,?)");
            pre = conexao.prepareStatement(sql);
            pre.setString(1, a.getNome());
            pre.setString(2, a.getCurso());
            pre.setInt(3, a.getIdade());
            pre.setString(4, a.getRa());
            pre.executeUpdate();
            System.out.println("Inclusão realizada com sucesso!");
        } catch (SQLException c) {
            System.out.println("Erro ao incluir: " + c.getMessage());
        }
    }
     */
    @Override
    public void alterar(Aluno a) {
        Connection conexao = op.obterConexao();
        PreparedStatement pre = null;

        if (a != null) {
            try {
                String sql = "Update Alunos set (nome=?, curso=?, idade=?, ra=?) where codigo=?";
                pre = conexao.prepareStatement(sql);
                pre.setString(1, a.getNome());
                pre.setString(2, a.getCurso());
                pre.setInt(3, a.getIdade());
                pre.setString(4, a.getRa());
                pre.executeUpdate();
                System.out.println("Alteração realizada com sucesso!");
            } catch (SQLException c) {
                System.out.println("Erro ao alterar " + c.getMessage());
            }
        }
    }
    
    @Override
    public void excluir(Aluno a) {
        Connection conexao = op.obterConexao();
        PreparedStatement pre = null;

        try {
            String sql = "delete from alunos where codigo=?";
            pre = conexao.prepareStatement(sql);
            //pre.setString(1, a.setCodigo());
            pre.executeUpdate();
            System.out.println("Aluno excluido com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao excluir: " + e.getMessage());
        }
    }
    
    @Override
    public void listar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public ResultSet listarAlunos() {
        Statement st;
        ResultSet rs = null;

        //representacao na memória de uma tabela
        try {
            Connection conexao = op.obterConexao();
            st = conexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            //TYPE_SCROLL_SENSITIVE: possibilidade de acessar os registros
            //CONCUR_UPDATABLE: alterar valores contidos no rs
            rs = st.executeQuery("Select * from Alunos order by descricao");
            //exibindo temporariamente os dados codigo e descricao
            System.out.println("Listagem dos Alunos\n");
            System.out.println("Nome - Idade  -  Curso  - RA ");
            while (rs.next()) {
                System.out.println(rs.getString("Nome") + rs.getString("Idade ") + "Curso " + rs.getString("RA "));
            }
            rs.first();//volta ao primeiro registro
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return rs;
    }

    public ResultSet buscarAlunoporNome(String descricao) {
        PreparedStatement st;
        ResultSet rs = null;
        try {
            Connection conexao = op.obterConexao();
            st = conexao.prepareStatement("select * from alunos where nome like ?");
            st.setString(1, descricao + '%');
            rs = st.executeQuery();
            System.out.println("\nAluno buscado");
            while (rs.next()) {
                System.out.println(rs.getString("Nome") + rs.getString("Idade ") + "Curso " + rs.getString("RA "));
                rs.first();
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
        return rs;
    }



}
