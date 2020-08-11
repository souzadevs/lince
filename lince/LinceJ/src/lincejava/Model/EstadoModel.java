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
public final class EstadoModel extends PersistModelAbstract
{
    private int id;
    private String nome;
    private int idPais;

    
    
    public EstadoModel() throws ClassNotFoundException, SQLException
    {
        super();
    }
    
    public EstadoModel(int id, String nome, int idPais) throws ClassNotFoundException, SQLException
    {     
        this.setId(id);
        this.setNome(nome);
        this.setIdPais(idPais);
    } 
    
    public ArrayList read() throws SQLException, ClassNotFoundException
    {
        this.openConnection();
        String sql = "SELECT * FROM estado";
        PreparedStatement stmt = getConexao().prepareStatement(sql);
        ResultSet results = stmt.executeQuery();
        ArrayList<EstadoModel> estados = new ArrayList<>();
        while(results.next()) {
            estados.add(new EstadoModel(
                    Integer.parseInt(results.getString("id")),
                    results.getString("nome"),
                    Integer.parseInt(results.getString("id_pais"))
            ));
        }
        this.closeConnection();
        
        return estados;
    }
    
    public ArrayList read(String like) throws SQLException, ClassNotFoundException
    {
        String sql = "SELECT * FROM estado";
        PreparedStatement stmt = getConexao().prepareStatement(sql);
        ResultSet results = stmt.executeQuery();
        ArrayList<String> estados = new ArrayList<>();
        while(results.next()) {
            estados.add(results.getString("nome"));
        }
        
        return estados;
    }
    
    /**
     *
     * @return 
     */
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
    
    public int getIdPais() {
        return idPais;
    }

    public void setIdPais(int idPais) {
        this.idPais = idPais;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
