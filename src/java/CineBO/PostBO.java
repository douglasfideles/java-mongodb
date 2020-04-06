/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CineBO;

import CineDAO.PostDAO;
import CineObjetos.Post;

/**
 *
 * @author usuario
 */
public class PostBO {
    PostDAO pDAO;

    public PostBO() 
    {
        pDAO = new PostDAO();
    }
    
    public void salvar(Post p)
    {
        pDAO.salvar(p);
    }
    
    public String consultarPorValor(String campo, String valor)
    {
        return pDAO.consultarPorValor(campo, valor);
    }   
    
    public String consultar()
    {
        return pDAO.consultar();
    }
    
    public void excluir(String campo, String valor)
    {
        pDAO.excluir(campo, valor);
    }
    
    public void atualizar(Post p)
    {
        pDAO.atualizar(p);
    }
}
