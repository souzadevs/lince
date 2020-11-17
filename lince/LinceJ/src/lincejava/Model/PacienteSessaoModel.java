
package lincejava.Model;

/**
 *
 * @author Bruno Araujo
 */
public class PacienteSessaoModel
{
    private int id;
    private SessaoModel sessao;
    private PacienteModel paciente;
    private String log;
 
    public PacienteSessaoModel()
    {
        
    }
   
    public PacienteSessaoModel(int id, SessaoModel sessao, PacienteModel paciente, String log)
    {
        this.id = id;
        this.sessao = sessao;
        this.paciente = paciente;
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

    public PacienteModel getPaciente()
    {
        return paciente;
    }

    public void setPAciente(PacienteModel paciente)
    {
        this.paciente = paciente;
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
