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
import java.util.ArrayList;
import lincejava.Lib.PersistModelAbstract;

/**
 *
 * @author Bruno Araujo
 */
public class EmpresaModel extends PersistModelAbstract
{
    private int id;
    private String nome;
    private String cnpj;
    private String ativo;
    private EnderecoModel endereco;
    private ContatoModel contato;

    public EmpresaModel() throws ClassNotFoundException, SQLException
    {
        super();
    }

    public EmpresaModel(int id, String nome, String cnpj, String ativo, EnderecoModel endereco, ContatoModel contato) throws ClassNotFoundException, SQLException
    {
        super();
        this.id = id;
        this.nome = nome;
        this.cnpj = cnpj;
        this.ativo = ativo;
        this.endereco = endereco;
        this.contato = contato;
    }

   public int create() throws ClassNotFoundException, Exception
    {
        int idContato = this.contato.create();
        int idEndereco = this.endereco.create();
        
        String sql = "INSERT INTO empresa (nome, cnpj, ativo, id_contato, id_endereco) VALUES (?,?,?,?,?)";
        
        PreparedStatement stmt = this.getConexao().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        
        stmt.setString(1, this.getNome());
        stmt.setString(2, this.getCnpj());
        stmt.setString(3, this.getAtivo());
        stmt.setString(4, Integer.toString(contato.getId()));
        stmt.setString(5, Integer.toString(endereco.getId()));
        
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
    
    public ArrayList read() throws ClassNotFoundException, SQLException
    {
        String sql = "SELECT * FROM empresa";
        
        PreparedStatement stmt = this.getConexao().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        
        ResultSet results = stmt.executeQuery();
        
        ArrayList<EmpresaModel> empresa = new ArrayList<>();
        
        EmpresaModel empresaModel;
        ContatoModel contatoModel;
        EnderecoModel enderecoModel;
        
        while(results.next()) {
             empresaModel = new EmpresaModel();
             
             empresaModel.setId(Integer.parseInt(results.getString("id")));
             empresaModel.setNome(results.getString("nome"));
             empresaModel.setCnpj(results.getString("cnpj"));
             empresaModel.setAtivo(results.getString("ativo"));

             contatoModel = new ContatoModel();
             contatoModel.setId(Integer.parseInt(results.getString("id_contato")));
             //Colocar load aqui para carregar todo o contato
             
             enderecoModel = new EnderecoModel();
             enderecoModel.setId(Integer.parseInt(results.getString("id_endereco")));
             //Colocar load aqui para carrega todo o endereco
             
             empresaModel.setContato(contatoModel);
             empresaModel.setEndereco(enderecoModel);
             
             empresa.add(empresaModel);
        }
        return empresa;
    }

    public void update() throws ClassNotFoundException, SQLException, Exception{
        
        this.endereco.update();
        this.contato.update();
        
        String sql = "Update empresa set nome = ?, cnpj = ?, ativo = ?  where id = ?";
        PreparedStatement stmt = this.getConexao().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, this.getNome());
        stmt.setString(2, this.getCnpj());
        stmt.setString(3, this.getAtivo());
        stmt.setString(4, String.valueOf(this.getId()));
        
        try
        {
            stmt.executeUpdate();
            
        } catch(SQLException e)
        {
            throw new Exception(e.getMessage());
        } 
      
    }
    
    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public String getCnpj() 
    {
        return this.cnpj;
    }

    public void setCnpj(String cnpj)
    {
        this.cnpj = cnpj;
    }
    
    
    public String getAtivo() {
        return ativo;
    }

    public void setAtivo(String ativo) {
        this.ativo = ativo;
    }
   
    public EnderecoModel getEndereco()
    {
        return endereco;
    }

    public void setEndereco(EnderecoModel endereco)
    {
        this.endereco = endereco;
    }

    public ContatoModel getContato()
    {
        return contato;
    }

    public void setContato(ContatoModel contato)
    {
        this.contato = contato;
    }
}
