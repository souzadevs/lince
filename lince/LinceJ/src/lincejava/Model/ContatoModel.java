/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lincejava.Model;

import lincejava.Lib.PersistModelAbstract;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Bruno Araujo
 */
public class ContatoModel extends PersistModelAbstract{

    private int id;
    private String fixo;
    private String celular1;
    private String celular2;
    private String email;
    
    public ContatoModel() throws ClassNotFoundException, SQLException
    {
        super();
    }

    public int create() throws SQLException, ClassNotFoundException, Exception {
        String sql = "INSERT INTO contato (fixo, celular_1, celular_2, email) VALUES (?,?,?,?)";
        
        PreparedStatement stmt = this.getConexao().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        
        stmt.setString(1, this.getFixo());
        stmt.setString(2, this.getCelular1());
        stmt.setString(3, this.getCelular2());
        stmt.setString(4, this.getEmail());
        
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

    public void read() {

    }
    
    public void update() {
        
    }

    public void delete() {

    }
    
    public String getCelular1() {
        return celular1;
    }

    public void setCelular1(String celular1) {
        this.celular1 = celular1;
    }

    public String getCelular2() {
        return celular2;
    }

    public void setCelular2(String celular2) {
        this.celular2 = celular2;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFixo() {
        return fixo;
    }

    public void setFixo(String fixo) {
        this.fixo = fixo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
