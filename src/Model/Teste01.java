package Model;


/**
 * 
 * @author Edilson Gotardi
 */
public class Teste01 {
    public static void main (String [] args){
        OperacaoBanco db = new OperacaoBanco();
        
        Aluno p=new Aluno ();
        p.setNome("Edilson");
        p.setIdade(22);
        p.setCurso("ADS");
        p.setRa("222");
        db.incluirAluno(p);
        //db.alterar(p);
        //db.excluirMatricula(null);
        
        System.out.println("-----------------------------");
        
        /*
        Curso c = new Curso();
        c.setDescricao("Análise e Desenvolvimento de Sistemas");
        db.incluirCurso(c);
        db.alterarCurso(c);
        db.excluirCurso(c);
        
        System.out.println("-----------------------------");
       
        Matricula ma = new Matricula();
        ma.setDescricao("Jogos");
        ma.setNomealuno("Vanessa");
        db.incluirMatricula(ma);
        db.alterarMatricula(ma);
        db.excluirMatricula(ma);
*/
    }
    
}