/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lincejava.Lib;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Bruno Apublic static void Teste()
    {
        
    }raujo
 */
public class PersistModelAbstract 
{
    private Connection conexao = null;
    
    public PersistModelAbstract() throws ClassNotFoundException, SQLException 
    {
        //loadConnection();   
    }
    
    protected void openConnection() throws ClassNotFoundException, SQLException
    {
        Class.forName("com.mysql.jdbc.Driver"); 
        this.conexao = DriverManager.getConnection("jdbc:mysql://localhost/lince?useSSL=false", "root", "root");     
    }
    
    protected void closeConnection() throws SQLException
    {
        if(!this.conexao.isClosed()) {
            this.conexao.close();
        }
    }
    
    public Connection getConexao()
    {
        return this.conexao;
    }   
}
