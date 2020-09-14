package lincejava.Controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TreeItem;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import lincejava.Model.ContatoModel;
import lincejava.Model.EmpresaModel;
import lincejava.Model.EnderecoModel;
import lincejava.Model.EquipamentoModel;
import lincejava.Model.EstadoModel;
import lincejava.Model.PaisModel;
import lincejava.Model.TecnicoModel;

public class PrincipalController {

    //***************Principal***********
    // Controles
    
    @FXML private Button btnTecnico;
    @FXML
    private Button btnFazenda;
    @FXML
    private Button btnEquipamento;
    @FXML private Button btnConfiguracoes;
    @FXML
    private Button btnSair;
    

    //Procedimentos
    
        public void closePanes()
    {
        List<Parent> panes = new ArrayList<>();
        panes.add(this.paneTecnico);
        panes.add(this.paneEquipamento);
        //panes.add(this.paneListar);
        
        panes.forEach(cnsmr -> {
            cnsmr.setVisible(false);
        });
    }
    
   
    @FXML
    void btnFecharJanelas()
    {
        closePanes();   
    }
    
    
    //***************Tecnicos***********
    // Controles

    @FXML
    private Pane paneTecnico;
    @FXML
    private Button btnAdicionarTecnico;
    @FXML
    private JFXTextField txtTecnicoNome;    
    @FXML
    private JFXTextField txtTecnicoID;
    @FXML
    private JFXTextField txtTecnicoEndereco;
    @FXML
    private JFXTextField txttecnicoNumero;
    @FXML
    private JFXTextField txtTecnicoBairro;
    @FXML
    private JFXTextField txtTecnicoCidade;
    @FXML
    private JFXComboBox<EstadoModel> cbTecnicoEstado;
    @FXML
    private JFXComboBox<PaisModel>  cbTecnicoPais;
    @FXML
    private JFXTextField txtTecnicoTelefone;
    @FXML
    private JFXTextField txtTecnicoCelular;
    @FXML
    private JFXTextField txtTecnicoEmail;
    @FXML
    private Button btnSalvarTecnico;
    @FXML
    private Button btnCancelarTecnico;
    @FXML 
    private TableView<TecnicoModel> tecnicoTableView;
    @FXML
    private TableColumn<TecnicoModel, String> tecnicoTableViewId;
    @FXML
    private TableColumn<TecnicoModel, String> tecnicoTableViewNome;


    
    //Procedimentos
    
    
    private void loadTecnicoComboBoxData() throws ClassNotFoundException, SQLException
    {
        this.paises = new PaisModel().read();
        this.estados = new EstadoModel().read();
        
        // System.out.println("Aqui");
        
        this.cbTecnicoPais.setItems(FXCollections.observableArrayList(this.paises));
        //this.cbEstado.setItems(FXCollections.observableArrayList(this.estados));
        this.cbTecnicoPais.getSelectionModel().select(2);
        
        ArrayList<EstadoModel> estadosSelecionados = new ArrayList<>();
        
        this.estados.forEach((estado) -> {       
            if(estado.getIdPais() == this.cbTecnicoPais.getSelectionModel().getSelectedItem().getId()){
                //System.out.println(estado.getNome());
                estadosSelecionados.add(estado);
            }
        });
        
        this.cbTecnicoEstado.setItems(FXCollections.observableArrayList(estadosSelecionados));
        this.cbTecnicoEstado.getSelectionModel().selectFirst();
    }
    
    public void loadTecnicoTableView() throws ClassNotFoundException, SQLException
    {
        TecnicoModel tecnico = new TecnicoModel();
        ArrayList<TecnicoModel> resultados = tecnico.read();
        
        TableColumn<TecnicoModel, String> tcTecnicoId = new TableColumn("ID");
        tcTecnicoId.setCellValueFactory(new PropertyValueFactory<>("id"));
        
        TableColumn<TecnicoModel, String> tcTecnicoNome = new TableColumn("NOME");
        tcTecnicoNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        
        this.tecnicoTableView.getItems().clear();
        this.tecnicoTableView.getColumns().clear();
        
        tcTecnicoId.setPrefWidth(tecnicoTableView.getWidth() * 0.2);
        tcTecnicoNome.setPrefWidth(tecnicoTableView.getWidth() * 0.8);
        
        this.tecnicoTableView.getColumns().addAll(tcTecnicoId, tcTecnicoNome);
        
        this.tecnicoTableView.setItems(FXCollections.observableArrayList(resultados));
    }
    
    @FXML
    private void btnAdicionarTecnicoAction() {
    
       this.paneTecnico.prefHeight(560);
        this.btnAdicionarTecnico.setDisable(false);
       this.btnCancelarTecnico.setDisable(false);
    }
    
    @FXML
    void btnTecnicoAction() throws ClassNotFoundException, SQLException 
    {       
        closePanes();
        paneTecnico.setVisible(true);
        loadTecnicoTableView();
        loadTecnicoComboBoxData();
    }
   
    @FXML
    void toggleTecnicoAtivo()
    {
        
    }
    
    @FXML
    void btnSalvarTecnicoAction() throws ClassNotFoundException, SQLException, Exception {
            EnderecoModel enderecoModel = new EnderecoModel();
            enderecoModel.setPais(this.cbTecnicoPais.getSelectionModel().getSelectedItem());
            enderecoModel.setEstado(this.cbTecnicoEstado.getSelectionModel().getSelectedItem());
            enderecoModel.setCidade(this.txtTecnicoCidade.getText());
            enderecoModel.setBairro(this.txtTecnicoBairro.getText());
            enderecoModel.setRua(this.txtTecnicoEndereco.getText());
            enderecoModel.setNumero(this.txttecnicoNumero.getText());

            ContatoModel contatoModel = new ContatoModel();
            contatoModel.setCelular(this.txtTecnicoCelular.getText());
            contatoModel.setEmail(this.txtTecnicoEmail.getText());
            contatoModel.setFixo(this.txtTecnicoTelefone.getText());

            TecnicoModel tecnicoModel = new TecnicoModel(
                    txtTecnicoNome.getText(),
                    enderecoModel,
                    contatoModel
            );

            if(tecnicoModel.create() != -1) {
                loadTecnicoTableView();
                System.out.println("Tecnico salvou!");
                
            } else {
                System.out.println("Tecnico não salvou!");
            }
            
    }
    
    @FXML
    private void btnCancelarTecnicoAction() {
    
       this.btnAdicionarTecnico.setDisable(false);
       this.btnCancelarTecnico.setDisable(false);
    }
    
    
    
  //***************Empresa***********
    // Controles
 @FXML
    private Pane paneEmpresa;
    @FXML
    private Button btnAdicionarEmpresa;
    @FXML
    private JFXTextField txtEmpresaNome;    
    @FXML
    private JFXTextField txtEmpresaID;
    @FXML
    private JFXTextField txtEmpresaEndereco;
    @FXML
    private JFXTextField txtEmpresaNumero;
    @FXML
    private JFXTextField txtEmpresaBairro;
    @FXML
    private JFXTextField txtEmpresaCidade;
    @FXML
    private JFXComboBox<EstadoModel> cbEmpresaEstado;
    @FXML
    private JFXComboBox<PaisModel> cbEmpresaPais;
    @FXML
    private JFXTextField txtEmpresaTelefone;
    @FXML
    private JFXTextField txtEmpresaCelular;
    @FXML
    private JFXTextField txtEmpresaEmail;
    @FXML private Button btnSalvarEmpresa;
    @FXML private Button btnCancelarEmpresa;
    @FXML private TableView<EmpresaModel> empresaTableView;
    @FXML private TableColumn<EmpresaModel, String> empresaTableViewId;
    @FXML private TableColumn<EmpresaModel, String> empresaTableViewNome;

    

    //Procedimentos
 public void loadEmpresaTableView() throws ClassNotFoundException, SQLException
    {
        EmpresaModel empresa = new EmpresaModel();
        ArrayList<EmpresaModel> resultados = empresa.read();
        
        TableColumn<EmpresaModel, String> tcEmpresaId = new TableColumn("ID");
        tcEmpresaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        
        TableColumn<EmpresaModel, String> tcEmpresaNome = new TableColumn("NOME");
        tcEmpresaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        
        this.empresaTableView.getItems().clear();
        this.empresaTableView.getColumns().clear();
        
        tcEmpresaId.setPrefWidth(empresaTableView.getWidth() * 0.3);
        tcEmpresaNome.setPrefWidth(empresaTableView.getWidth() * 0.7);
        this.empresaTableView.getColumns().addAll(tcEmpresaId, tcEmpresaNome);
         
        this.empresaTableView.setItems(FXCollections.observableArrayList(resultados));
    }
    
    private void loadEmpresaComboBoxData() throws ClassNotFoundException, SQLException
    {
        this.paises = new PaisModel().read();
        this.estados = new EstadoModel().read();
        
       
        this.cbEmpresaPais.setItems(FXCollections.observableArrayList(this.paises));
        //this.cbEmpresaEstado.setItems(FXCollections.observableArrayList(this.estados));
        this.cbEmpresaPais.getSelectionModel().select(2);
        
        ArrayList<EstadoModel> estadosSelecionados = new ArrayList<>();
        
        this.estados.forEach((estado) -> {       
            if(estado.getIdPais() == this.cbEmpresaPais.getSelectionModel().getSelectedItem().getId()){
                //System.out.println(estado.getNome());
                estadosSelecionados.add(estado);
            }
        });
        
        this.cbEmpresaEstado.setItems(FXCollections.observableArrayList(estadosSelecionados));
        this.cbEmpresaEstado.getSelectionModel().selectFirst();
    }
    
        
    @FXML
    void btnEmpresaAction() throws ClassNotFoundException, SQLException 
    {       
        closePanes();
        paneEmpresa.setVisible(true);
        loadEmpresaTableView();
        loadEmpresaComboBoxData();
    }
    
    
@FXML
    void btnSalvarEmpresaAction() throws ClassNotFoundException, SQLException, Exception {
            EnderecoModel enderecoModel = new EnderecoModel();
            enderecoModel.setPais(this.cbEmpresaPais.getSelectionModel().getSelectedItem());
            enderecoModel.setEstado(this.cbEmpresaEstado.getSelectionModel().getSelectedItem());
            enderecoModel.setCidade(this.txtEmpresaCidade.getText());
            enderecoModel.setBairro(this.txtEmpresaBairro.getText());
            enderecoModel.setRua(this.txtEmpresaEndereco.getText());
            enderecoModel.setNumero(this.txtEmpresaNumero.getText());

            ContatoModel contatoModel = new ContatoModel();
            contatoModel.setCelular(this.txtEmpresaCelular.getText());
            contatoModel.setEmail(this.txtEmpresaEmail.getText());
            contatoModel.setFixo(this.txtEmpresaTelefone.getText());

            EmpresaModel empresaModel = new EmpresaModel(
                    0,
                    txtEmpresaNome.getText(),
                    enderecoModel,
                    contatoModel
            );

            if(empresaModel.create() != -1) {
                loadEmpresaTableView();
                System.out.println("Empresa salvou!");
                
            } else {
                System.out.println("Tecnico não salvou!");
            }
            
    }
    
    @FXML
    private void btnAdicionarEmpresaAction() {
    
       this.btnSalvarEquipamento.setDisable(false);
       this.btnCancelarEquipamento.setDisable(false);
       
    }
    
        @FXML
    private void btnCancelarEmpresaAction() {
    
       this.btnAdicionarEmpresa.setDisable(false);
       this.btnCancelarEmpresa.setDisable(false);
    }
    
   //***************Equiopamento***********
    // Controles

    
    @FXML
    private Pane paneEquipamento;
    @FXML
    private Button btnSalvarEquipamento;
    @FXML
    private Button btnCancelarEquipamento;
    @FXML
    private ImageView btnFechaPanePesquisar;
    @FXML 
    private TableView<EquipamentoModel> equipamentoTableView;
    @FXML
    private JFXTextField txtEquipamentoDescricao;
    @FXML
    private JFXTextField txtEquipamentoSerie;
    @FXML
    private JFXTextField txtEquipamentoMarca;
    @FXML
    private JFXTextField txtEquipamentoModelo;
    @FXML
    private JFXComboBox<String> cbEquipamentoSinal;
    @FXML
    private JFXTextField txtEquipamentoEixoX;
    @FXML
    private JFXTextField txtEquipamentoEixoY;  

    
    
    //Procedimentos

    
    public void loadEquipamentoTableView() throws ClassNotFoundException, SQLException
    {
        EquipamentoModel equipamento = new EquipamentoModel();
        ArrayList<EquipamentoModel> resultados = equipamento.read();
        
        TableColumn<EquipamentoModel, String> tcEquipamentoId = new TableColumn("ID");
        tcEquipamentoId.setCellValueFactory(new PropertyValueFactory<>("id"));
        
        TableColumn<EquipamentoModel, String> tcEquipamentoNome = new TableColumn("DESCRIÇÃO");
        tcEquipamentoNome.setCellValueFactory(new PropertyValueFactory<>("descricao"));
//<<<<<<< HEAD
//
//        this.cadastroTableView = new TableView<>();
//        this.cadastroTableView.getItems().clear();
//        this.cadastroTableView.getColumns().clear();
//=======
        
        this.equipamentoTableView.getItems().clear();
        this.equipamentoTableView.getColumns().clear();
        
        tcEquipamentoId.setPrefWidth(equipamentoTableView.getWidth() * 0.3);
        tcEquipamentoNome.setPrefWidth(equipamentoTableView.getWidth() * 0.7);
        this.equipamentoTableView.getColumns().addAll(tcEquipamentoId, tcEquipamentoNome);
         
        this.equipamentoTableView.setItems(FXCollections.observableArrayList(resultados));
    }
    
    
    public void loadEquipamentoFormData()
    {
        ArrayList<String> sinal = new ArrayList<>();
        sinal.add("Pal-M");
        sinal.add("NTSC");
        
        this.cbEquipamentoSinal.setItems(FXCollections.observableArrayList(sinal));
    }
    
    
        
    @FXML
    void btnFazendaAction() 
    {       
        closePanes();
        //paneListar.setVisible(true);
    }
    
    
    @FXML
    void btnSalvarEquipamentoAction() throws ClassNotFoundException, SQLException, Exception {
        

            EquipamentoModel eqpt = new EquipamentoModel(
                    0,
                    this.txtEquipamentoDescricao.getText(),
                    this.txtEquipamentoSerie.getText(),
                    this.txtEquipamentoMarca.getText(),
                    this.txtEquipamentoModelo.getText(),
                    this.cbEquipamentoSinal.getSelectionModel().getSelectedItem(),
                    this.txtEquipamentoEixoX.getText(),
                    this.txtEquipamentoEixoY.getText()
             );
            
                     
            if(eqpt.create() != -1) {
                loadEquipamentoTableView();
                System.out.println("Equipamento cadastrou!");
            } else {
                System.out.println("Equipamento não cadasrou!");
            }
            EquipamentoModel equipamento = new EquipamentoModel();
            ArrayList<EquipamentoModel> lst = equipamento.read();
            lst.forEach(eqp -> {
                System.out.println(eqp.getDescricao());
            });
    }
    
    @FXML
    private void btnAdicionarEquipamentoAction() {
    
       this.btnSalvarEquipamento.setDisable(false);
       this.btnCancelarEquipamento.setDisable(false);
       
    }
    
//***************Imagem***********
    //Controles 

    @FXML
    private Pane paneImagem;
    @FXML
    private ToggleGroup Trace;
    //@FXML
    //private JFXComboBox<EstadoModel> cbEstado;
    //@FXML
    //private JFXComboBox<PaisModel> cbPais;

    
    
    
    private ArrayList<PaisModel> paises;
    private ArrayList<EstadoModel> estados;
    
// Preencher TreeTableView
    
   
   
    @FXML
    void initialize() throws ClassNotFoundException, SQLException
    {
        //loadComboBoxData();
        loadEquipamentoFormData();
        //loadEmpresaFormData();
    }
    
    
    

    
    @FXML 
    void toggleTecnicoPais()
    {
        ArrayList<EstadoModel> estadosSelecionados = new ArrayList<>();
        
        this.estados.forEach((estado) -> {       
            if(estado.getIdPais() == this.cbTecnicoPais.getSelectionModel().getSelectedItem().getId()){
                //System.out.println(estado.getNome());
                estadosSelecionados.add(estado);
            }
        });
        
        this.cbTecnicoEstado.setItems(FXCollections.observableArrayList(estadosSelecionados));
        this.cbTecnicoEstado.getSelectionModel().selectFirst();
    }
    
    @FXML 
    void toggleEmpresaPais()
    {
        ArrayList<EstadoModel> estadosSelecionados = new ArrayList<>();
        
        this.estados.forEach((estado) -> {       
            if(estado.getIdPais() == this.cbEmpresaPais.getSelectionModel().getSelectedItem().getId()){
                //System.out.println(estado.getNome());
                estadosSelecionados.add(estado);
            }
        });
        
        this.cbEmpresaEstado.setItems(FXCollections.observableArrayList(estadosSelecionados));
        this.cbEmpresaEstado.getSelectionModel().selectFirst();
    }
    
    
    

    
    
    @FXML
    private void btnCancelarEquipamentoAction() {
    
       this.btnSalvarEquipamento.setDisable(false);
       this.btnCancelarEquipamento.setDisable(false);
       
    }
    
    
    @FXML
    void btnEquipamentoAction() throws ClassNotFoundException, SQLException 
    {       
        closePanes();
        paneEquipamento.setVisible(true);
        loadEquipamentoTableView();
        loadTecnicoComboBoxData();
        
    }
    
    @FXML
    void btnSairAction() throws ClassNotFoundException, SQLException
    {   
        closePanes();
         ArrayList<EstadoModel> estados = new EstadoModel().read();
        estados.forEach((estado) -> {
            System.out.println("Nome: " + estado.getNome());
        });  
    }
    

     @FXML
    void btnSalvarFazenda() throws ClassNotFoundException, SQLException, Exception {
            //paneCadastrar.setVisible(false);
            System.out.println("Empresa cancelou");

    }
}

