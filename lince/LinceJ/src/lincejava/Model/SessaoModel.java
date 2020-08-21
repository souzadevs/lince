/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lincejava.Model;

import java.sql.Date;

/**
 *
 * @author Bruno Araujo
 */
public class SessaoModel
{
    private int id;
    private Date dtCriacaoSessao;
    private EquipamentoModel equipamento;
    private EmpresaModel empresa;

    public SessaoModel()
    {
        
    }

    public SessaoModel(int id, Date dtCriacaoSessao, EquipamentoModel equipamento, EmpresaModel empresa)
    {
        this.id = id;
        this.dtCriacaoSessao = dtCriacaoSessao;
        this.equipamento = equipamento;
        this.empresa = empresa;
    }
    
    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public Date getDtCriacaoSessao()
    {
        return dtCriacaoSessao;
    }

    public void setDtCriacaoSessao(Date dtCriacaoSessao)
    {
        this.dtCriacaoSessao = dtCriacaoSessao;
    }

    public EquipamentoModel getEquipamento()
    {
        return equipamento;
    }

    public void setEquipamento(EquipamentoModel equipamento)
    {
        this.equipamento = equipamento;
    }

    public EmpresaModel getEmpresa()
    {
        return empresa;
    }

    public void setEmpresa(EmpresaModel empresa)
    {
        this.empresa = empresa;
    }  
}
