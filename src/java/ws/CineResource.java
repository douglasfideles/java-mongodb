/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import CineBO.PessoaBO;
import CineBO.PostBO;
import CineObjetos.Pessoa;
import CineObjetos.Post;
import com.google.gson.Gson;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author usuario
 */
@Path("cine")
public class CineResource {

    @Context
    private UriInfo context;

    PessoaBO pBO;
    PostBO postBO;
    
    public CineResource() {
        
        pBO = new PessoaBO();
        postBO = new PostBO();
    }
    
    @Path("/{campo} {valor}")
    @GET
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public String consultarPorValor(@PathParam("campo") String campo, @PathParam("valor") String valor) {

        return pBO.consultarPorValor(campo, valor);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String getXml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }


    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
    
    @Path("/cadastrar")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
        System.out.println(content);
        Gson g = new Gson();        
        Pessoa p = g.fromJson(content, Pessoa.class);
        pBO.salvar(p);
    }
    
    //POST
    
    @Path("/buscar/{campo} {valor}")
    @GET
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public String consultaPost(@PathParam("campo") String campo, @PathParam("valor") String valor) {
        
        return postBO.consultarPorValor(campo, valor);
    }
    @Path("/listarPost")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        
        return postBO.consultar();
    }

    @Path("/post")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putUsuario(String content) {
        System.out.println(content);
        Gson g = new Gson();        
        Post post = g.fromJson(content, Post.class);
        postBO.salvar(post);
    }
    
    @Path("/excluir/{campo} {valor}")
    @GET
    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public void excluir(@PathParam("campo") String campo, @PathParam("valor") String valor) {
        postBO.excluir(campo, valor);
    }
    
    @Path("/atualizar")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void atualizarPost(String content) {
        System.out.println(content);
        Gson g = new Gson();        
        Post post = g.fromJson(content, Post.class);
        postBO.atualizar(post);
    }
}
