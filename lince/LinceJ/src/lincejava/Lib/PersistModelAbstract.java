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
    private Connection conexao;
    
    public PersistModelAbstract() throws ClassNotFoundException, SQLException 
    {
        loadConnection();   
    }
    
    private void loadConnection() throws ClassNotFoundException, SQLException
    {
        Class.forName("com.mysql.jdbc.Driver");
        this.conexao = DriverManager.getConnection("jdbc:mysql://localhost/lince?useSSL=false", "root", "root");
    }
    
    public Connection getConexao()
    {
        return this.conexao;
    }   
}
