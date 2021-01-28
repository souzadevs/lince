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
    private int id_animal;
    private double peso;
    private String grupoManejo;
    private String raca;
    private SessaoModel sessao;

    public PacienteModel()
    {
        
    }
    
    public PacienteModel(int id, int id_animal, double peso, String grupoManejo, String raca, SessaoModel sessao) {
        this.id = id;
        this.id_animal = id_animal;
        this.peso = peso;
        this.grupoManejo = grupoManejo;
        this.raca = raca;
        this.sessao = sessao;
    }
    
    public SessaoModel getSessao() {
        return sessao;
    }

    public void setSessao(SessaoModel sessao) {
        this.sessao = sessao;
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
    
    public int getId_animal() {
        return id_animal;
    }

    public void setId_animal(int id_animal) {
        this.id_animal = id_animal;
    }
}
