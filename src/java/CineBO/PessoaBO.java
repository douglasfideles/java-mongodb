/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CineBO;

import CineDAO.PessoaDAO;
import CineObjetos.Pessoa;

/**
 *
 * @author usuario
 */
public class PessoaBO {
   
    PessoaDAO pDAO;

    public PessoaBO() 
    {
        pDAO = new PessoaDAO();
    }
    
    public void salvar(Pessoa p)
    {
        pDAO.salvar(p);
    }
    
    public String consultarPorValor(String campo, String valor)
    {
        return pDAO.consultarPorValor(campo, valor);
    }   
    
}
