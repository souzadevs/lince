/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lincejava.Model;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Bruno Araujo
 */
public class TecnicoModel extends lincejava.Lib.PersistModelAbstract
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
        super();
    }
    
    public boolean create() throws ClassNotFoundException, Exception
    {
//        Integer idContato = this.contato.create();
//        Integer idEndereco = this.endereco.create();
        
        String sql = "INSERT INTO tecnico (nome, id_contato, id_endereco) VALUES (?,?,?)";
        
        PreparedStatement stmt = this.getConexao().prepareStatement(sql);
        
        stmt.setString(1, this.getNome());
        stmt.setString(2, Integer.toString(contato.getId()));
        stmt.setString(3, Integer.toString(endereco.getId()));
        
        if(!stmt.execute())
            return true;
        else
            return false;
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
