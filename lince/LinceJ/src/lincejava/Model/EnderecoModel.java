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
public class EnderecoModel extends PersistModelAbstract
{

    private int id;

    
    private PaisModel pais;
    private EstadoModel estado;
    private String cidade;
    private String bairro;
    private String rua;
    private String numero;
    private String complemento;
    
    public EnderecoModel() throws ClassNotFoundException, SQLException
    {
        super();
    }
    
     public int create() throws SQLException, ClassNotFoundException, Exception {
        String sql = "INSERT INTO endereco (id_pais, id_estado, cidade, bairro, rua, numero) VALUES (?,?,?,?,?,?)";
        
        PreparedStatement stmt = this.getConexao().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        
        stmt.setString(1, String.valueOf(this.getPais().getId()));
        stmt.setString(2, String.valueOf(this.getEstado().getId()));
        stmt.setString(3, this.getCidade());
        stmt.setString(4, this.getBairro());
        stmt.setString(5, this.getRua());
        stmt.setString(6, this.getNumero());
//        stmt.setString(, this.getComplemento());
        
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
    
    public void read()
    {
        
    }
    
    public void update() {

    }

    public void delete() {

    }
    
    public void load(int id) throws ClassNotFoundException, SQLException
    {
        String sql = "SELECT * FROM endereco WHERE id = ?";
        
        PreparedStatement stmt = this.getConexao().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        stmt.setString(1, String.valueOf(id));
        
        ResultSet results = stmt.executeQuery();
        
        while(results.next()) {
            PaisModel pais = new PaisModel();
            pais.load(Integer.parseInt(results.getString("id_pais")));
            this.setPais(pais);
            
            EstadoModel estado = new EstadoModel();
            estado.load(Integer.parseInt(results.getString("id_estado")));
            this.setEstado(estado);
            
            this.cidade = results.getString("cidade");
            this.bairro = results.getString("bairro");
            this.rua = results.getString("rua");
            this.numero = results.getString("numero");
            this.complemento = results.getString("complemento");
            
            //Load finalizado, falta colocar o load no momento load do tecnico
            //Também, falta colocar o load do Pais e Estado
            
        }
        
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public PaisModel getPais() {
        return pais;
    }

    public void setPais(PaisModel pais) {
        this.pais = pais;
    }

    public EstadoModel getEstado() {
        return estado;
    }

    public void setEstado(EstadoModel estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }   
}
