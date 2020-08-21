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
public class ImagemModel
{    
    private int id;
    private PacienteModel paciente;
    private String tipo;

    public ImagemModel()
    {
        
    }
    
    public ImagemModel(int id, PacienteModel paciente, String tipo)
    {
        this.id = id;
        this.paciente = paciente;
        this.tipo = tipo;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public PacienteModel getPaciente()
    {
        return paciente;
    }

    public void setPaciente(PacienteModel paciente)
    {
        this.paciente = paciente;
    }

    public String getTipo()
    {
        return tipo;
    }

    public void setTipo(String tipo)
    {
        this.tipo = tipo;
    }
}
