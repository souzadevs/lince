
package lincejava.Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import lincejava.Lib.PersistModelAbstract;


public class TransdutorModel extends PersistModelAbstract
{
    private int id;
    private String serial;
    private String marca;
    private String modelo;
    private String eixoX;
    private String eixoY;
    private String ativo;

    
    
    public TransdutorModel() throws ClassNotFoundException, SQLException
    {
        super();
    }
    
    public TransdutorModel(int id, String serial, String marca, String modelo, String eixoX, String eixoY, String ativo) throws ClassNotFoundException, SQLException
    {
  
       this.id = id; ;
       this.serial = serial;
       this.marca = marca;
       this.modelo = modelo;
       this.eixoX = eixoX;
       this.eixoY = eixoY;
       this.ativo = ativo;
    }
    
    public void any(){}
    
    public int create() throws SQLException, ClassNotFoundException, Exception {
        String sql = "INSERT INTO transdutor (serial, marca, modelo, eixo_x, eixo_y, ativo) VALUES (?,?,?,?,?,?)";
        
        PreparedStatement stmt = this.getConexao().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        
        stmt.setString(1, this.getSerial());
        stmt.setString(2, this.getMarca());
        stmt.setString(3, this.getModelo());
        stmt.setString(4, this.getEixoX());
        stmt.setString(5, this.getEixoY());
        stmt.setString(6, this.getAtivo());

        
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
        String sql = "SELECT * FROM transdutor";
        PreparedStatement stmt = getConexao().prepareStatement(sql);
        ResultSet results = stmt.executeQuery();
        ArrayList<TransdutorModel> transdutores = new ArrayList<>();
        while(results.next()) {
            transdutores.add(new TransdutorModel(
                    Integer.parseInt(results.getString("id")),
                    results.getString("serial"),
                    results.getString("marca"),
                    results.getString("modelo"),
                    results.getString("eixo_x"),
                    results.getString("eixo_y"),
                    results.getString("ativo")
            ));
        }
        
        return transdutores;
    }
    
    public void update() throws ClassNotFoundException, SQLException, Exception{

        
        String sql = "Update transdutor set serial = ?, marca = ?, modelo = ?, eixo_x = ?, eixo_Y = ?, ativo = ?  where id = ?";
        PreparedStatement stmt = this.getConexao().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, this.getSerial());
        stmt.setString(2, this.getMarca());
        stmt.setString(3, this.getModelo());
        stmt.setString(4, this.getEixoX());
        stmt.setString(5, this.getEixoY());
        stmt.setString(6, this.getAtivo());
        stmt.setString(7, String.valueOf(this.getId()));
        
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

    public String getEixoX() {
        return eixoX;
    }

    public void setEixoX(String eixoX) {
        this.eixoX = eixoX;
    }

    public String getEixoY() {
        return eixoY;
    }

    public void setEixoY(String eixoY) {
        this.eixoY = eixoY;
    }

    public String getAtivo() {
        return ativo;
    }

    public void setAtivo(String ativo) {
        this.ativo = ativo;
    }
}