package util;

import Model.Aluno;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author edilsongm
 */
public interface Acoes {
    
    public void incluir(Aluno a);
    public void alterar(Aluno a);
    public void excluir(Aluno a);
    public void listar();
    
}
