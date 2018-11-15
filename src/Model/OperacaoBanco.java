package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class OperacaoBanco {
    Connection con =null;
    
    public OperacaoBanco (){
        this.carregarDriver();
        
    }
        public void carregarDriver(){
        try{
            //carrega o drive
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver carregado");
        }
        catch (ClassNotFoundException ex){
            //se não carregar
            System.out.println("O driver MySQL não pode ser carregado. Erro: "+ex);
        }
    }
        public Connection obterConexao(){
        try{//verifica se conexao fechada ou inexistente
            //abre conexao
            if((con==null)||(con.isClosed())){
                con = DriverManager.getConnection("jdbc:mysql://localhost/provaJean", "root", "");
                System.out.println("Conexão obtida com sucesso");
            }
        }
        catch(SQLException ex) {
            System.out.println("SQLExcepition: "+ex);
        }
        catch(Exception ex){
            System.out.println("Excepiton: "+ex);
        }
        return  con; //retorna uma conexão onde serão feitas as conexoes e operacoes com o BD
        }
            public void fecharConexao(){
        try{
            if((con!=null)&&(!con.isClosed())){
                con.close();
                System.out.println("Conexão fechada!");
            }
        }
        catch (SQLException ex){
            System.out.println("SQLException: "+ex);
        }
        catch(Exception ex){
            System.out.println("Exception: "+ex);
        }
    }//fim do método fecharConexao
            
            
        public void incluirAluno(Aluno a){
        Connection conexao = this.obterConexao();
        PreparedStatement pre = null;
       
        try{
            String sql = ("Insert into alunos (nome, curso, idade, ra) values (?,?,?,?)");
                pre = conexao.prepareStatement(sql);
                pre.setString(1, a.getNome());
                pre.setString(2, a.getCurso());
                pre.setInt(3, a.getIdade());
                pre.setString(4, a.getRa());
                pre.executeUpdate();
                System.out.println("Inclusão realizada com sucesso!");
        }catch(SQLException c){
            System.out.println("Erro ao incluir: "+c.getMessage());
        }
        
    }
            public void alterar(Aluno a){
        Connection conexao = this.obterConexao();
        PreparedStatement pre = null;
        try{
            String sql = "Update Alunos set (nome=?, curso=?, idade=?, ra=?) where codigo=?";
            pre = conexao.prepareStatement(sql);
            pre = conexao.prepareStatement(sql);
            pre.setString(1, a.getNome());
            pre.setString(2, a.getCurso());
            pre.setInt(3, a.getIdade());
            pre.setString(4, a.getRa());
            pre.executeUpdate();
            System.out.println("Alteração realizada com sucesso!");
        }
        catch(SQLException c) {
            System.out.println("Erro ao alterar "+c.getMessage());
        }
            }
        
        public void excluirAlunos (Aluno a){
        Connection conexao  = this.obterConexao();
        PreparedStatement pre = null;
    
        try{
            String sql = "delete from alunos where codigo=?";
            pre=conexao.prepareStatement(sql);
            //pre.setString(1, a.setCodigo());
            pre.executeUpdate();
            System.out.println("Aluno excluido com sucesso!");
        }
        catch(SQLException e){
            System.out.println("Erro ao excluir: "+e.getMessage());
        }
    }
        
        public ResultSet listarAlunos(){
            Statement st;
        ResultSet rs = null;
        
        //representacao na memória de uma tabela
        try{
            Connection conexao = this.obterConexao();
            st=conexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            //TYPE_SCROLL_SENSITIVE: possibilidade de acessar os registros
            //CONCUR_UPDATABLE: alterar valores contidos no rs
            rs=st.executeQuery("Select * from Alunos order by descricao");
            //exibindo temporariamente os dados codigo e descricao
            System.out.println("Listagem dos Alunos\n");
            System.out.println("Nome - Idade  -  Curso  - RA ");
            while (rs.next()){
                    System.out.println(rs.getString("Nome")+rs.getString("Idade ")+"Curso "+rs.getString("RA "));
            }
            rs.first();//volta ao primeiro registro
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return rs;
        }
 
        public ResultSet buscarAlunoporNome(String descricao){
        PreparedStatement st;
        ResultSet rs = null;
        try{
            Connection conexao = this.obterConexao();
            st = conexao.prepareStatement("select * from alunos where nome like ?");
            st.setString(1, descricao+ '%');
            rs = st.executeQuery();
            System.out.println("\nAluno buscado");
            while(rs.next()){
                System.out.println(rs.getString("Nome")+rs.getString("Idade ")+"Curso "+rs.getString("RA "));
                rs.first();
            }
            }catch(Exception e){
                    System.out.println("Erro: "+ e.getMessage());
                    }
            return rs;
        }
        
        //--------------------------------------------------------------------------------------------------------
        
        
        public void incluirCurso(Curso c){
            Connection conexao = this.obterConexao();
            PreparedStatement pre =null;
            try{
                String sql = "Insert into Curso (codcurso, descricao)";
                pre = conexao.prepareStatement(sql);
              //  pre.setString(1, c.getCodCurso());
                pre.setString(2, c.getDescricao());
                pre.executeUpdate();
                System.out.println("Curso cadastrado com sucesso");
            }
            catch(SQLException a){
                System.out.println("Erro ao incluir "+a.getMessage());        
            }
        }
        
        public void alterarCurso (Curso c){
            Connection conexao = this.obterConexao();
            PreparedStatement pre = null;
            try{
                String sql = "Update Curso set (codcurso=?, descricao=?) where codcurso=?";
                pre = conexao.prepareStatement(sql);
                //pre.setString(1, c.getCodCurso());
                pre.setString(2, c.getDescricao());
                pre.executeUpdate();
                System.out.println("Curso alterado com sucesso");
            }
            catch(SQLException a){
                System.out.println("Erro ao alterar "+a.getMessage());
            }
        }
        
        public void excluirCurso (Curso c){
            Connection conexao = this.obterConexao();
            PreparedStatement pre = null;
            try{
                String sql = "dele from Curso where codcurso ";
                pre = conexao.prepareStatement(sql);
               // pre.setString(1, c.getCodCurso());
                pre.setString(2, c.getDescricao());
                pre.executeUpdate();
                System.out.println("Curso excluido com sucesso");
            }
            catch(SQLException a){
                System.out.println("Erro ao excluir: "+a.getMessage());
            }
        }
        
        public ResultSet listarCurso (){
            Statement st;
            ResultSet rs = null;
            
            try{
                Connection conexao = this.obterConexao();
                st = conexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
                rs=st.executeQuery("Select * from Curso order by descricao");
                System.out.println("Listagem dos cursos");
                System.out.println("CodCurso - Descrição ");        
                while(rs.next()){
                    System.out.println(rs.getString("Código")+rs.getString("Descrição"));
                }
                rs.first();
            }
           catch(Exception e){
                System.out.println(e.getMessage());
            }
            return rs;
    }
    
        public ResultSet buscarCursoporNome(String Descricao1){
            Statement st;
            ResultSet rs = null;
            
            try{
                Connection conexao = this.obterConexao();
                st = conexao.prepareStatement("Select * from Curso where descricao like");
                st.setCursorName(Descricao1);
                rs = st.executeQuery(Descricao1);
                System.out.println("Curso buscado \n");
                while(rs.next()){
                    System.out.println(rs.getString("CodCurso")+rs.getString("Descrição"));
                    rs.first();
                }
            }
                catch(Exception e ){
                        System.out.println("Erro: "+e.getMessage());
                        }
                return rs;
        }
        
        //-------------------------------------------------------------------------------------------
        
        public void incluirMatricula(Matricula ma){
            Connection conexao = this.obterConexao();
        PreparedStatement pre = null; //objeto que executa o banco
        try{
            String sql = "Insert into Matricula (codmatricula, nomealuno, descricao) values (?,?,?)";
            pre = conexao.prepareStatement(sql);
            //pre.setString(1, ma.getCodmatricula());
            pre.setString(2, ma.getDescricao());
            pre.setString(3, ma.getNomealuno());
            pre.executeUpdate();
            System.out.println("Matricula efetuada.");
        }
        catch(SQLException ex){
            System.out.println("Erro ao incluir matricula; "+ex.getMessage());
        }
        
    }//fim incluirMatricula
        
        
        public void alterarMatricula (Matricula ma){
            Connection conexao = this.obterConexao();
            PreparedStatement pre = null;
            try{
                String sql = "Update Matricula set (codmatricula=?, descricao=?, nomealuno=?) where codmatricula=?)";
                pre = conexao.prepareStatement(sql);
                //pre.setString(1, ma.getCodmatricula());
                pre.setString(2, ma.getDescricao());
                pre.setString(3, ma.getNomealuno());
                pre.executeUpdate();
                System.out.println("Matricula alterada");
            }
            catch(SQLException ex){
                System.out.println("Erro ao alterar matricula; "+ex.getMessage());
            }
        }//fim alterarMatricula
        
        public void excluirMatricula (Matricula ma){
            Connection conexao = this.obterConexao();
            PreparedStatement pre = null;
            try{
                String sql = "delete from Matricula where codmatricula=?";
                pre = conexao.prepareStatement(sql);
                //pre.setString(1, ma.getCodmatricula());
                pre.setString(2, ma.getDescricao());
                pre.setString(3, ma.getNomealuno());
                pre.executeUpdate();
                System.out.println("Matricula deletada");
            }
            catch(SQLException ex){
                System.out.println("Erro ao excluir matricula: "+ex.getMessage());
            }
        }//fim excluirMatricula
        
        public void listarMatricula(){
            Statement st;
            ResultSet rs = null;
            
            try{
                Connection conexao = this.obterConexao();
                st = conexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                rs = st.executeQuery("Select * from Matricula order by descricao");
                System.out.println("Lista de matricula");
                System.out.println("Codmatricula    -   Descricao   -   NomeAluno");
                while(rs.next()){
                    System.out.println(rs.getString("codmatricula")+rs.getString("descricao")+rs.getString("nomealuno"));
                }
                rs.first();
            }
            catch(Exception ex){
                System.out.println("Erro ao listar: "+ex.getMessage());
            }
        }//fim listarMatricula
        
}//fim OperacaoBanco