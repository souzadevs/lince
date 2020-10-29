
package lincejava.Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import lincejava.Lib.PersistModelAbstract;


public class EquipamentoModel extends PersistModelAbstract
{
    private int id;
    private String serial;
    private String marca;
    private String modelo;
    private String sinal;
    private String ativo;

    
    
    public EquipamentoModel() throws ClassNotFoundException, SQLException
    {
        super();
    }
    
    public EquipamentoModel(int id, String serial, String marca, String modelo, String sinal, String ativo) throws ClassNotFoundException, SQLException
    {
  
       this.id = id; ;
       this.serial = serial;
       this.marca = marca;
       this.modelo = modelo;
       this.sinal = sinal;
       this.ativo = ativo;
    }
    
    public void any(){}
    
    public int create() throws SQLException, ClassNotFoundException, Exception {
        String sql = "INSERT INTO equipamento (serial, marca, modelo, sinal, ativo) VALUES (?,?,?,?,?)";
        
        PreparedStatement stmt = this.getConexao().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        
        stmt.setString(1, this.getSerial());
        stmt.setString(2, this.getMarca());
        stmt.setString(3, this.getModelo());
        stmt.setString(4, this.getSinal());
        stmt.setString(5, this.getAtivo());

        
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
    
    public ArrayList read() throws SQLException, ClassNotFoundException
    {
        String sql = "SELECT * FROM equipamento";
        PreparedStatement stmt = getConexao().prepareStatement(sql);
        ResultSet results = stmt.executeQuery();
        ArrayList<EquipamentoModel> equipamentos = new ArrayList<>();
        while(results.next()) {
            equipamentos.add(new EquipamentoModel(
                    Integer.parseInt(results.getString("id")),
                    results.getString("serial"),
                    results.getString("marca"),
                    results.getString("modelo"),
                    results.getString("sinal"),
                    results.getString("ativo")
            ));
        }
        
        return equipamentos;
    }
    
    public void update() throws ClassNotFoundException, SQLException, Exception{

        
        String sql = "Update equipamento set serial = ?, marca = ?, modelo = ?, sinal = ?, ativo = ?  where id = ?";
        PreparedStatement stmt = this.getConexao().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, this.getSerial());
        stmt.setString(2, this.getMarca());
        stmt.setString(3, this.getModelo());
        stmt.setString(4, this.getSinal());
        stmt.setString(5, this.getAtivo());
        stmt.setString(6, String.valueOf(this.getId()));
        
        try
        {
            stmt.executeUpdate();
            
        } catch(SQLException e)
        {
            throw new Exception(e.getMessage());
        }   
    }
    
    public String toString() {
    
     return this.getMarca() + " - " + this.getModelo();
     
    };
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }
    
    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getSinal() {
        return sinal;
    }

    public void setSinal(String sinal) {
        this.sinal = sinal;
    }

    public String getAtivo() {
        return ativo;
    }

    public void setAtivo(String ativo) {
        this.ativo = ativo;
    }
}