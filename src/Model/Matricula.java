package Model;


/**
 *
 * @author Edilson
 * @Since Classe criada em 08/04/2016
 */
public class Matricula {
    
    private Integer codmatricula;
    private String nomealuno;
    private String descricao;

    /**
     * @return the codmatricula
     */
    public Integer getCodmatricula() {
        return codmatricula;
    }

    /**
     * @param codmatricula the codmatricula to set
     */
    public void setCodmatricula(Integer codmatricula) {
        this.codmatricula = codmatricula;
    }

    /**
     * @return the nomealuno
     */
    public String getNomealuno() {
        return nomealuno;
    }

    /**
     * @param nomealuno the nomealuno to set
     */
    public void setNomealuno(String nomealuno) {
        this.nomealuno = nomealuno;
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
