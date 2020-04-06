/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CineDAO;

import CineObjetos.Post;
import Utilitarios.Conexao;
import com.google.gson.Gson;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.currentDate;
import static com.mongodb.client.model.Updates.set;
import org.bson.Document;

/**
 *
 * @author usuario
 */
public class PostDAO {
    MongoCollection mc = null;

    public PostDAO() 
    {
        mc = new Conexao().conectar();
    }    
    
    public void salvar(Post p)
    {
        Gson g = new Gson();
        
        String json = g.toJson(p);
        mc.insertOne(Document.parse(json));
    }
    
    public void excluir(String campo, String valor)
    {
        mc.deleteMany(eq(campo, valor));
       
    }
    
    public void atualizar(Post p)
    {
        mc.updateOne(eq("id", p.getId()),
        combine(set("titulo", p.getTitulo()), set("texto", p.getTexto()), set("categoria", p.getCategoria()), currentDate("lastModified")));
    }
    
    public String consultarPorValor(String campo, String valor)
    {
        FindIterable<Document> findIterable = mc.find(eq(campo, valor));
        MongoCursor c = findIterable.iterator();
        String json = "";
        while (c.hasNext()) {
            Document nextElement = (Document) c.next();
            json = json + nextElement.toJson()+",";
        }
        
        int t = json.length();
        json = json.substring(0, t-1);
        json = "["+json+"]";
        
        return json;
    }

    public String consultar()
    {
        FindIterable<Document> findIterable = mc.find(new Document());
      
        
        MongoCursor c = findIterable.iterator();
        String json = "";
        while (c.hasNext()) {
            Document nextElement = (Document) c.next();
            json = json + nextElement.toJson()+",";
        }
        int t = json.length();
        json = json.substring(0, t-1);
        json = "["+json+"]";
        
        return json;
    }
    
}
