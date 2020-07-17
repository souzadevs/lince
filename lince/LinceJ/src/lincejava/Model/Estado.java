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
public class Estado 
{
    private int id;
    private String nome;
    private String sigla;
    
    public Estado(int id, String nome, String sigla)
    {
        this.id = id;
        this.nome = nome;
        this.sigla = sigla;
    }
    
    /**
     *
     * @return 
     */
    public String toString()
    {
        return this.getNome();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }
}
