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
public class TecnicoSessao
{   
    private int id;
    private TecnicoModel tecnico;
    private SessaoModel sessao;

    public TecnicoSessao()
    {
        
    }
    
    public TecnicoSessao(int id, TecnicoModel tecnico, SessaoModel sessao)
    {
        this.id = id;
        this.tecnico = tecnico;
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

    public TecnicoModel getTecnico()
    {
        return tecnico;
    }

    public void setTecnico(TecnicoModel tecnico)
    {
        this.tecnico = tecnico;
    }

    public SessaoModel getSessao()
    {
        return sessao;
    }

    public void setSessao(SessaoModel sessao)
    {
        this.sessao = sessao;
    }  
}
