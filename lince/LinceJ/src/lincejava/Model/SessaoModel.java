
package lincejava.Model;

import java.sql.Date;

/**
 *
 * @author Bruno Araujo
 */
public class SessaoModel
{
    private int id;
    private EquipamentoModel equipamento;
    private EmpresaModel empresa;
    private TecnicoModel tecnico;
    private TransdutorModel transdutor;
    private Date log;


    public SessaoModel()
    {
        
    }

    public SessaoModel(int id, EquipamentoModel equipamento, EmpresaModel empresa, TecnicoModel tecnico, TransdutorModel transdutor, Date log)
    {
        this.id = id;
        this.equipamento = equipamento;
        this.empresa = empresa;
        this.tecnico = tecnico;
        this.transdutor = transdutor;
        this.log= log;
    }
    
    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }
    public Date getLog()
    {
        return log;
    }

    public void setLog(Date log)
    {
        this.log = log;
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
    
    public TecnicoModel getTecnico()
    {
        return tecnico;
    }

    public void setTecnico(TecnicoModel tecnico)
    {
        this.tecnico = tecnico;
    }  
    
    public TransdutorModel getTransdutor()
    {
        return transdutor;
    }

    public void setTransdutor(TransdutorModel transdutor)
    {
        this.transdutor = transdutor;
    } 
    
}
