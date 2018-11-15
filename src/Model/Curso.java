package Model;


/**
 * Classe criada para incluir, alterar e excluir dados da classe Curso
 * @author Edilson Murbach
 * @Since Data: 08/04/16
 */
public class Curso {
    
    private Integer CodCurso;
    private String descricao;

    /**
     * @return the CodCurso
     */
    public Integer getCodCurso() {
        return CodCurso;
    }

    /**
     * @param CodCurso the CodCurso to set
     */
    public void setCodCurso(Integer CodCurso) {
        this.CodCurso = CodCurso;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    
    
}
