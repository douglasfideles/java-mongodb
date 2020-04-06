/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CineDAO;

import CineObjetos.Pessoa;
import Utilitarios.Conexao;
import com.google.gson.Gson;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import static com.mongodb.client.model.Filters.eq;
import org.bson.Document;

/**
 *
 * @author usuario
 */
public class PessoaDAO {
    
    MongoCollection mc = null;

    public PessoaDAO() 
    {
        mc = new Conexao().conectar();
    }    
    
    public void salvar(Pessoa p)
    {
        Gson g = new Gson();
        
        String json = g.toJson(p);
        mc.insertOne(Document.parse(json));
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
    
}
