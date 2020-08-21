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
public class EmpresaModel
{
    private int id;
    private String nome;
    private EnderecoModel endereco;
    private ContatoModel contato;

    public EmpresaModel()
    {
        
    }

    public EmpresaModel(int id, String nome, EnderecoModel endereco, ContatoModel contato)
    {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.contato = contato;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public EnderecoModel getEndereco()
    {
        return endereco;
    }

    public void setEndereco(EnderecoModel endereco)
    {
        this.endereco = endereco;
    }

    public ContatoModel getContato()
    {
        return contato;
    }

    public void setContato(ContatoModel contato)
    {
        this.contato = contato;
    }
}
