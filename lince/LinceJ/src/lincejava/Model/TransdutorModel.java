
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
    private String descricao;
    private String numero_serie;
    private String marca;
    private String modelo;
    private String tipoSinal;
    private String escalaX;
    private String escalaY;
    
    
    public EquipamentoModel() throws ClassNotFoundException, SQLException
    {
        super();
    }
    
    public EquipamentoModel(int id, String descricao, String numero_serie, String marca, String modelo, String tipoSinal, String escalaX, String escalaY) throws ClassNotFoundException, SQLException
    {
        this.setId(id);
        this.setDescricao(descricao);
        this.setNumeroSerie(numero_serie);
        this.setMarca(marca);
        this.setModelo(modelo);
        this.setTipoSinal(tipoSinal);
        this.setEscalaX(escalaX);
        this.setEscalaY(escalaY);
    }
    
    public void any(){}
    
    public int create() throws SQLException, ClassNotFoundException, Exception {
        String sql = "INSERT INTO equipamento (descricao, numero_serie, marca, modelo, tipo_sinal, escala_x, escala_y) VALUES (?,?,?,?,?,?,?)";
        
        PreparedStatement stmt = this.getConexao().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        
        stmt.setString(1, this.getDescricao());
        stmt.setString(2, this.getNumeroSerie());
        stmt.setString(3, this.getMarca());
        stmt.setString(4, this.getModelo());
        stmt.setString(5, this.getTipoSinal());
        stmt.setString(6, this.getEscalaX());
        stmt.setString(7, this.getEscalaY());
        
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
                    results.getString("descricao"),
                    results.getString("numero_serie"),
                    results.getString("marca"),
                    results.getString("modelo"),
                    results.getString("tipo_sinal"),
                    results.getString("escala_x"),
                    results.getString("escala_y")
            ));
        }
        
        return equipamentos;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNumeroSerie() {
        return numero_serie;
    }

    public void setNumeroSerie(String numero_serie) {
        this.numero_serie = numero_serie;
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

    public String getTipoSinal() {
        return tipoSinal;
    }

    public void setTipoSinal(String tipoSinal) {
        this.tipoSinal = tipoSinal;
    }

    public String getEscalaX() {
        return escalaX;
    }

    public void setEscalaX(String escalaX) {
        this.escalaX = escalaX;
    }

    public String getEscalaY() {
        return escalaY;
    }

    public void setEscalaY(String escalaY) {
        this.escalaY = escalaY;
    }
    
    @Override
    public String toString()
    {
        return this.getId() + "\n" + this.getDescricao();
    }
    
    
}
