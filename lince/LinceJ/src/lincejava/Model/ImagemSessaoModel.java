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
public class ImagemSessaoModel
{
    private int id;
    private SessaoModel sessao;
    private ImagemModel imagem;
    private String log;
 
    public ImagemSessaoModel()
    {
        
    }
   
    public ImagemSessaoModel(int id, SessaoModel sessao, ImagemModel imagem, String log)
    {
        this.id = id;
        this.sessao = sessao;
        this.imagem = imagem;
        this.log = log;
    }
    
    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public SessaoModel getSessao()
    {
        return sessao;
    }

    public void setSessao(SessaoModel sessao)
    {
        this.sessao = sessao;
    }

    public ImagemModel getImagem()
    {
        return imagem;
    }

    public void setImagem(ImagemModel imagem)
    {
        this.imagem = imagem;
    }   
    
    public String getLog()
    {
        return log;
    }

    public void setLog(String log)
    {
        this.log = log;
    } 
}
