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
public class TecnicoModel extends PersistModelAbstract
{
    
    private int id;
    private String nome;
    private String cpf;
    private String ativo;
    private EnderecoModel endereco;
    private ContatoModel contato;
    
    public TecnicoModel() throws ClassNotFoundException, SQLException
    {
        super();
    }
    
    public TecnicoModel(int id, String nome, String cpf, String ativo, EnderecoModel endereco, ContatoModel contato) throws ClassNotFoundException, SQLException
    {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.ativo = ativo;
        this.endereco = endereco;
        this.contato = contato;
    }
    
    public void any(){}
   
    public void loadChildId() throws ClassNotFoundException, SQLException
    {
        String sql = "SELECT tecnico.id_contato, tecnico.id_endereco FROM tecnico WHERE tecnico.id = ?";
        
        PreparedStatement stmt = getConexao().prepareStatement(sql);
        
        stmt.setInt(1, this.getId()); // 1ª correção
        
        ResultSet results = stmt.executeQuery();
        
        while(results.next()) {
            this.getContato().setId(Integer.parseInt(results.getString("id_contato")));
            this.getEndereco().setId(Integer.parseInt(results.getString("id_endereco")));
        }   
    }
    
    public int create() throws ClassNotFoundException, Exception
    {
        this.contato.create();
        this.endereco.create();
        
        String sql = "INSERT INTO tecnico (nome, cpf, ativo, id_contato, id_endereco) VALUES (?,?,?,?,?)";
        
        PreparedStatement stmt = this.getConexao().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, this.getNome());
        stmt.setString(2, this.getCpf());
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
        String sql = "SELECT * FROM tecnico";
        
        PreparedStatement stmt = this.getConexao().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        
        ResultSet results = stmt.executeQuery();
        
        ArrayList<TecnicoModel> tecnico = new ArrayList<>();
        
        TecnicoModel tecnicoModel;
        ContatoModel contatoModel;
        EnderecoModel enderecoModel;
        
        while(results.next()) {
            tecnicoModel = new TecnicoModel();
            tecnicoModel.setId(Integer.parseInt(results.getString("id")));
            tecnicoModel.setNome(results.getString("nome"));
            tecnicoModel.setCpf(results.getString("cpf"));
            tecnicoModel.setAtivo(results.getString("ativo"));

            contatoModel = new ContatoModel();
            contatoModel.load(Integer.parseInt(results.getString("id_contato")));
//            contatoModel.setId(Integer.parseInt(results.getString("id_contato")));

            //Colocar load aqui para carregar todo o contato

            enderecoModel = new EnderecoModel();
            enderecoModel.load(Integer.parseInt(results.getString("id_endereco")));
//            enderecoModel.setId());
            //Colocar load aqui para carrega todo o endereco

            tecnicoModel.setContato(contatoModel);
            tecnicoModel.setEndereco(enderecoModel);

            tecnico.add(tecnicoModel);
        }
        return tecnico;
    }
    
    public String toString() {
    
     return this.getNome();
     
    };
            
    public void update() throws ClassNotFoundException, SQLException, Exception
    {
        
        loadChildId(); // Refazer este ponto (colocar o load id dentro do EnderecoModel e ContatoModel)
        
        this.endereco.update();
        this.contato.update();
        
        String sql = "Update tecnico set nome = ?, cpf = ?, ativo = ?  where id = ?";
        PreparedStatement stmt = this.getConexao().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, this.getNome());
        stmt.setString(2, this.getCpf());
        stmt.setString(3, this.getAtivo());
        stmt.setString(4, String.valueOf(this.getId()));
        
        try
        {
            stmt.executeUpdate();
            
        } catch(SQLException e)
        {
            throw new RuntimeException() ;
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
    
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    public String getAtivo() {
        return ativo;
    }

    public void setAtivo(String ativo) {
        this.ativo = ativo;
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
