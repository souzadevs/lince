/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lincejava.Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import lincejava.Lib.PersistModelAbstract;

/**
 *
 * @author Bruno Araujo
 */
public class PaisModel extends lincejava.Lib.PersistModelAbstract
{
    private int id;
    private String nome;

    public PaisModel() throws ClassNotFoundException, SQLException
    {
        super();
    }
    
    public PaisModel(int id, String nome) throws ClassNotFoundException, SQLException
    {
        this.setId(id);
        this.setNome(nome);
    }
 
    public ArrayList read() throws SQLException, ClassNotFoundException
    {
        String sql = "SELECT * FROM pais";
        
        PreparedStatement stmt = getConexao().prepareStatement(sql);
        
        ResultSet results = stmt.executeQuery();
        
        ArrayList<PaisModel> paises = new ArrayList<>();
        
        while(results.next()) {
            paises.add(new PaisModel(
                    Integer.parseInt(results.getString("id")),
                    results.getString("nome")
            ));
        }
        return paises;
    }
    
    public void load(int id)
    {
        
    }
    
    public String toString()
    {
        return this.getNome();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
