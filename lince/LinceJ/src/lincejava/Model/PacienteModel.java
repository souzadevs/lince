/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lincejava.Model;

/**
 *
 * @author Bruno Araujo
 */
public class PacienteModel
{
    private int id;
    private double peso;
    private String grupoManejo;
    private String raca;

    public PacienteModel()
    {
        
    }
    
    public PacienteModel(int id, double peso, String grupoManejo, String raca)
    {
        this.id = id;
        this.peso = peso;
        this.grupoManejo = grupoManejo;
        this.raca = raca;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public double getPeso()
    {
        return peso;
    }

    public void setPeso(double peso)
    {
        this.peso = peso;
    }

    public String getGrupoManejo()
    {
        return grupoManejo;
    }

    public void setGrupoManejo(String grupoManejo)
    {
        this.grupoManejo = grupoManejo;
    }

    public String getRaca()
    {
        return raca;
    }

    public void setRaca(String raca)
    {
        this.raca = raca;
    }  
}
