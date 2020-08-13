/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lincejava.Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import lincejava.Lib.PersistModelAbstract;

/**
 *
 * @author Bruno Araujo
 */
public class TecnicoModel extends PersistModelAbstract
{
    //  nome 
    //  id_endereco 
    //  id_contato
    
    private int id;
    private String nome;
    private EnderecoModel endereco;
    private ContatoModel contato;
    
    public TecnicoModel() throws ClassNotFoundException, SQLException
    {
        
    }
    
    public int create() throws ClassNotFoundException, Exception
    {
        Integer idContato = this.contato.create();
        Integer idEndereco = this.endereco.create();
        
        String sql = "INSERT INTO tecnico (nome, id_contato, id_endereco) VALUES (?,?,?)";
        
        PreparedStatement stmt = this.getConexao().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        
        stmt.setString(1, this.getNome());
        stmt.setString(2, Integer.toString(contato.getId()));
        stmt.setString(3, Integer.toString(endereco.getId()));
        
        try
        {
            stmt.execute();
            ResultSet lastId = stmt.getGeneratedKeys();
            lastId.next();
            this.setId(lastId.getInt(1));
            return this.getId();
        } catch(SQLException e)
        {
            throw new Exception(e);
        } 
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
    
     public EnderecoModel getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoModel endereco) {
        this.endereco = endereco;
    }

    public ContatoModel getContato() {
        return contato;
    }

    public void setContato(ContatoModel contato) {
        this.contato = contato;
    }
}
