package lincejava.Controller;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
import javafx.scene.layout.GridPane;
import javax.swing.JOptionPane;
import lincejava.Model.TransdutorModel;

public class PrincipalController {

    //***************Principal***********
    // Controles
    
    @FXML private Button btnTecnico;
    @FXML private Button btnFazenda;
    @FXML private Button btnEquipamento;
    @FXML private Button btnTransdutor;
    @FXML private Button btnNovaSessao;
    @FXML private Button btnAbrirSessao;
    @FXML private Button btnConfiguracoes;
    @FXML private Button btnSair;
    private String operacaoCadastro;

    

    //Procedimentos
    
    public void closePanes()
    {
        List<Parent> panes = new ArrayList<>();
        panes.add(this.paneTecnico);
        panes.add(this.paneEquipamento);
        panes.add(this.paneEmpresa);
        panes.add(this.paneTransdutor);
        panes.add(this.paneNovaSessao);
        panes.add(this.paneImagemInterpretar);


        
        panes.forEach(cnsmr -> {
            cnsmr.setVisible(false);
        });
    }
    
 
    @FXML
    void btnFecharJanelas()
    {
        closePanes();   
    }
   
    
    @FXML
    void btnTecnicoAction() throws ClassNotFoundException, SQLException 
    {       
        closePanes();
        gridTecnico.setVisible(false);
        gridTecnico.setDisable(true);
        paneTecnico.setPrefHeight(400);
        paneTecnico.setVisible(true);
        loadTecnicoTableView();
        loadTecnicoComboBoxData();
    }
    
    
    
    @FXML
    void btnEmpresaAction() throws ClassNotFoundException, SQLException 
    {       
        closePanes();
        gridEmpresa.setVisible(false);
        gridEmpresa.setDisable(true);
        paneEmpresa.setPrefHeight(400);
        paneEmpresa.setVisible(true);
        loadEmpresaTableView();
        loadEmpresaComboBoxData();
    }
    
    @FXML
    void btnEquipamentoAction() throws ClassNotFoundException, SQLException 
    {       
        closePanes();
        gridEquipamento.setVisible(false);
        gridEquipamento.setDisable(true);
        paneEquipamento.setPrefHeight(400);
        paneEquipamento.setVisible(true);
        loadEquipamentoTableView();
    }
    
    @FXML
    void btnTransdutorAction() throws ClassNotFoundException, SQLException 
    {       
        closePanes();
        gridTransdutor.setVisible(false);
        gridTransdutor.setDisable(true);
        paneTransdutor.setPrefHeight(400);
        paneTransdutor.setVisible(true);
        loadTransdutorTableView();
    }
    
    @FXML
    void btnNovaSessaoAction() throws ClassNotFoundException, SQLException 
    {       
        closePanes();
        loadComboTecnicoSessao();
        loadComboEquipamentoSessao();
        loadComboTransdutorSessao();
        paneNovaSessao.setVisible(true);
        gridNovaSessao.setVisible(true);
        gridNovaSessao.setDisable(false);
        
       // loadTecnicoComboBoxData();

    }
    
    @FXML
    void btnAbrirSessaoAction() throws ClassNotFoundException, SQLException 
    {       
        closePanes();
        paneNovaSessao.setVisible(true);
       // loadTecnicoComboBoxData();

    }
    
    @FXML
    void btnSairAction() throws ClassNotFoundException, SQLException
    {   

        Alert alert = new Alert(AlertType.CONFIRMATION, "Tem certeza que deseja sair do sistema? ", ButtonType.YES, ButtonType.NO);
         alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                System.exit(0);
            }

    }
    
    
    //***************Tecnicos***********
    // Controles

    @FXML private Pane paneTecnico;
    @FXML private GridPane gridTecnico;
    @FXML private Button btnAdicionarTecnico;
    @FXML private JFXTextField txtTecnicoId;    
    @FXML private JFXTextField txtTecnicoNome;    
    @FXML private JFXTextField txtTecnicoCpf;
    @FXML private JFXTextField txtTecnicoEndereco;
    @FXML private JFXTextField txtTecnicoNumero;
    @FXML private JFXTextField txtTecnicoBairro;
    @FXML private JFXTextField txtTecnicoCidade;
    @FXML private JFXComboBox<EstadoModel> cbTecnicoEstado;
    @FXML private JFXComboBox<PaisModel>  cbTecnicoPais;
    @FXML private JFXTextField txtTecnicoFixo;
    @FXML private JFXTextField txtTecnicoCelular;
    @FXML private JFXTextField txtTecnicoEmail;
    @FXML private JFXToggleButton tglTecnicoAtivo;
    @FXML private Button btnSalvarTecnico;
    @FXML private Button btnCancelarTecnico;
    @FXML private TableView<TecnicoModel> tecnicoTableView;
    @FXML private TableColumn<TecnicoModel, String> tecnicoTableViewId;
    @FXML private TableColumn<TecnicoModel, String> tecnicoTableViewNome;


    
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
        tcTecnicoId.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        
        TableColumn<TecnicoModel, String> tcTecnicoNome = new TableColumn("NOME");
        tcTecnicoNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        
        this.tecnicoTableView.getItems().clear();
        this.tecnicoTableView.getColumns().clear();
        
        tcTecnicoId.setPrefWidth(tecnicoTableView.getWidth() * 0.3);
        tcTecnicoNome.setPrefWidth(tecnicoTableView.getWidth() * 0.7);
        
        this.tecnicoTableView.getColumns().addAll(tcTecnicoId, tcTecnicoNome);
        this.tecnicoTableView.setItems(FXCollections.observableArrayList(resultados));
    }
    
    @FXML
    public void tecnicoSelecionar(){
        
        this.paneTecnico.setPrefHeight(720);
        this.gridTecnico.setVisible(true);
        
        TecnicoModel tecnico = tecnicoTableView.getSelectionModel().getSelectedItem();
        
        this.txtTecnicoNome.setText(tecnico.getNome());
        this.txtTecnicoCpf.setText(tecnico.getCpf());
        
       if ( "A".equals(tecnico.getAtivo())) {
           this.tglTecnicoAtivo.setSelected(true);
       } else {
           this.tglTecnicoAtivo.setSelected(false);
       }
           
        this.txtTecnicoId.setText(String.valueOf(tecnico.getId()));
        this.txtTecnicoEndereco.setText(tecnico.getEndereco().getRua());   
        this.txtTecnicoNumero.setText(tecnico.getEndereco().getNumero());
        this.txtTecnicoBairro.setText(tecnico.getEndereco().getBairro());
        this.txtTecnicoCidade.setText(tecnico.getEndereco().getCidade());
        //this.txtTecnicoPais.setText(tecnico.getEndereco().getPais());
        //this.txtTecnicoEstado.setText(tecnico.getEndereco().getEstado());
        this.txtTecnicoFixo.setText(tecnico.getContato().getFixo());
        this.txtTecnicoCelular.setText(tecnico.getContato().getCelular());
        this.txtTecnicoEmail.setText(tecnico.getContato().getEmail());

    }
    
    @FXML
    private void btnAdicionarTecnicoAction() {
    
       operacaoCadastro = "Add";
       this.gridTecnico.setDisable(false);
       this.txtTecnicoNome.setText("");
       this.txtTecnicoCpf.setText("");
       this.txtTecnicoEndereco.setText("");
       this.txtTecnicoNumero.setText("");
       this.txtTecnicoBairro.setText("");
       this.txtTecnicoCidade.setText("");
       this.txtTecnicoFixo.setText("");
       this.txtTecnicoCelular.setText("");
       this.txtTecnicoEmail.setText("");
       //this.cbTecnicoPais.;
       //this.cbTecnicoEstado = null;
       this.tglTecnicoAtivo.setSelected(true);      
       this.paneTecnico.setPrefHeight(720);
       this.gridTecnico.setVisible(true);
       this.txtTecnicoNome.requestFocus();
    }
    
    @FXML
    private void btnEditarTecnicoAction() {
    
        operacaoCadastro = "Edit";
        this.gridTecnico.setDisable(false);
        this.txtTecnicoNome.requestFocus();
       //this.btnCancelarTecnico.setDisable(false);
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
            enderecoModel.setNumero(this.txtTecnicoNumero.getText());

            ContatoModel contatoModel = new ContatoModel();
            contatoModel.setCelular(this.txtTecnicoCelular.getText());
            contatoModel.setEmail(this.txtTecnicoEmail.getText());
            contatoModel.setFixo(this.txtTecnicoFixo.getText());

            TecnicoModel tecnicoModel = new TecnicoModel(
                    Integer.parseInt(txtTecnicoId.getText()),
                    txtTecnicoNome.getText(),
                    txtTecnicoCpf.getText(),
                    tglTecnicoAtivo.isSelected() == true ? "A":"I",
                    enderecoModel,
                    contatoModel
            );

            if (operacaoCadastro == "Add") {
               tecnicoModel.create();
               operacaoCadastro = "";     
            } else {                 
                tecnicoModel.update();
                operacaoCadastro = "";
            }
            loadTecnicoTableView();
            this.gridTecnico.setDisable(true);
    }
    
    @FXML
    private void btnCancelarTecnicoAction() {
    
       this.gridTecnico.setDisable(true);
    }
    
    
    
  //***************Empresa***********
    // Controles
    @FXML private Pane paneEmpresa;
    @FXML private Button btnAdicionarEmpresa;
    @FXML private JFXTextField txtEmpresaId;    
    @FXML private JFXTextField txtEmpresaNome;    
    @FXML private JFXTextField txtEmpresaCnpj;
    @FXML private JFXTextField txtEmpresaEndereco;
    @FXML private JFXTextField txtEmpresaNumero;
    @FXML private JFXTextField txtEmpresaBairro;
    @FXML private JFXTextField txtEmpresaCidade;
    @FXML private JFXComboBox<EstadoModel> cbEmpresaEstado;
    @FXML private JFXComboBox<PaisModel> cbEmpresaPais;
    @FXML private JFXTextField txtEmpresaTelefone;
    @FXML private JFXTextField txtEmpresaCelular;
    @FXML private JFXTextField txtEmpresaEmail;
    @FXML private JFXToggleButton tglEmpresaAtivo;
    @FXML private Button btnSalvarEmpresa;
    @FXML private Button btnCancelarEmpresa;
    @FXML private TableView<EmpresaModel> empresaTableView;
    @FXML private TableColumn<EmpresaModel, String> empresaTableViewId;
    @FXML private TableColumn<EmpresaModel, String> empresaTableViewNome;
    @FXML private GridPane gridEmpresa;

    

    //Procedimentos
 public void loadEmpresaTableView() throws ClassNotFoundException, SQLException
    {
        EmpresaModel empresa = new EmpresaModel();
        ArrayList<EmpresaModel> resultados = empresa.read();
        
        TableColumn<EmpresaModel, String> tcEmpresaId = new TableColumn("CNPJ");
        tcEmpresaId.setCellValueFactory(new PropertyValueFactory<>("cnpj"));
        
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
                    Integer.parseInt(txtEmpresaId.getText()),
                    txtEmpresaNome.getText(),
                    txtEmpresaCnpj.getText(),
                    tglEmpresaAtivo.isSelected() == true ? "A":"I",
                    enderecoModel,
                    contatoModel
            );

            if (operacaoCadastro == "Add") {
               empresaModel.create();
               operacaoCadastro = "Vazio";
               System.out.println("Tecnico salvou!");
     
            } else {
                empresaModel.update();
                operacaoCadastro = "Vazio";
                System.out.println("Tecnico atualizou!");
            }
            loadEmpresaTableView();
            this.gridEmpresa.setDisable(true);

    }
    
    @FXML
    public void empresaSelecionar(){
        
        this.paneEmpresa.setPrefHeight(720);
        this.gridEmpresa.setVisible(true);
        
        EmpresaModel empresa = empresaTableView.getSelectionModel().getSelectedItem();
        
        
        this.txtEmpresaNome.setText(empresa.getNome());
        this.txtEmpresaCnpj.setText(empresa.getCnpj());
        
       if ( "A".equals(empresa.getAtivo())) {
           this.tglEmpresaAtivo.setSelected(true);
       } else {
           this.tglEmpresaAtivo.setSelected(false);
       }
           
        this.txtEmpresaId.setText(String.valueOf(empresa.getId()));
        this.txtEmpresaEndereco.setText(empresa.getEndereco().getRua());   
        this.txtTecnicoNumero.setText(empresa.getEndereco().getNumero());
        this.txtTecnicoBairro.setText(empresa.getEndereco().getBairro());
        this.txtTecnicoCidade.setText(empresa.getEndereco().getCidade());
        //this.txtTecnicoPais.setText(empresa.getEndereco().getPais());
        //this.txtTecnicoEstado.setText(empresa.getEndereco().getEstado());
        this.txtEmpresaTelefone.setText(empresa.getContato().getFixo());
        this.txtEmpresaCelular.setText(empresa.getContato().getCelular());
        this.txtEmpresaEmail.setText(empresa.getContato().getEmail());

    }
    
    
    
     @FXML
    private void btnAdicionarEmpresaAction() {
    
       operacaoCadastro = "Add";
       this.gridEmpresa.setDisable(false);
       this.txtEmpresaNome.setText("");
       this.txtEmpresaCnpj.setText("");
       this.txtEmpresaEndereco.setText("");
       this.txtEmpresaNumero.setText("");
       this.txtEmpresaBairro.setText("");
       this.txtEmpresaCidade.setText("");
       this.txtEmpresaTelefone.setText("");
       this.txtEmpresaCelular.setText("");
       this.txtEmpresaEmail.setText("");
       //this.cbEmpresaPais.;
       //this.cbEmpresaEstado = null;
       this.tglEmpresaAtivo.setSelected(true);      
       this.paneEmpresa.setPrefHeight(720);
       this.gridEmpresa.setVisible(true);
       this.txtEmpresaNome.requestFocus();
    }
    
    @FXML
    private void btnEditarEmpresaAction(){
        operacaoCadastro = "Edit";
        this.gridEmpresa.setDisable(false);
        this.txtEmpresaNome.requestFocus();
       //this.btnCancelarTecnico.setDisable(false);
    }

     @FXML
    private void btnCancelarEmpresaAction() {
    
       this.gridEmpresa.setDisable(true);
    }

    
    
   //***************Equipamento***********
    // Controles

    @FXML private GridPane gridEquipamento;
    @FXML private Pane paneEquipamento;
    @FXML private Button btnSalvarEquipamento;
    @FXML private Button btnCancelarEquipamento;
    @FXML private ImageView btnFechaPanePesquisar;
    @FXML private TableView<EquipamentoModel> equipamentoTableView;
    @FXML private JFXTextField txtEquipamentoSerial;
    @FXML private JFXTextField txtEquipamentoMarca;
    @FXML private JFXTextField txtEquipamentoModelo;
    @FXML private JFXComboBox<String> cbEquipamentoSinal;
    @FXML private JFXToggleButton tglEquipamentoAtivo;

    
    
    //Procedimentos

    
    public void loadEquipamentoTableView() throws ClassNotFoundException, SQLException
    {
        EquipamentoModel equipamento = new EquipamentoModel();
        ArrayList<EquipamentoModel> resultados = equipamento.read();
        TableColumn<EquipamentoModel, String> tcEquipamentoId = new TableColumn("Serial");
        tcEquipamentoId.setCellValueFactory(new PropertyValueFactory<>("Serial"));
        
        TableColumn<EquipamentoModel, String> tcEquipamentoNome = new TableColumn("Modelo");
        tcEquipamentoNome.setCellValueFactory(new PropertyValueFactory<>("modelo"));

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
    void btnSalvarEquipamentoAction() throws ClassNotFoundException, SQLException, Exception {
        

            EquipamentoModel equipamento = new EquipamentoModel(
                    0,
                    this.txtEquipamentoSerial.getText(),
                    this.txtEquipamentoMarca.getText(),
                    this.txtEquipamentoModelo.getText(),
                    this.cbEquipamentoSinal.getSelectionModel().getSelectedItem(),
                    tglEquipamentoAtivo.isSelected() == true ? "A":"I"

             );
            
            if (operacaoCadastro == "Add") {
               equipamento.create();
               operacaoCadastro = "Vazio";
               System.out.println("Equipamento salvou!");
     
            } else {
                equipamento.update();
                operacaoCadastro = "Vazio";
                System.out.println("Equipamento atualizou!");
            }
            loadEquipamentoTableView();
            this.gridEquipamento.setDisable(true);
    }
    
    @FXML
    public void equipamentoSelecionar(){
        
        this.paneEquipamento.setPrefHeight(720);
        this.gridEquipamento.setVisible(true);
        
        EquipamentoModel equipamento = equipamentoTableView.getSelectionModel().getSelectedItem();
        this.txtEquipamentoSerial.setText(equipamento.getSerial());
        this.txtEquipamentoMarca.setText(equipamento.getMarca());
        this.txtEquipamentoModelo.setText(equipamento.getModelo());
        //this.cbEquipamentoSinal.setText(equipamento.getSinal());

       if ( "A".equals(equipamento.getAtivo())) {
           this.tglEquipamentoAtivo.setSelected(true);
       } else {
           this.tglEquipamentoAtivo.setSelected(false);
       }
         
    }

    @FXML
    private void btnAdicionarEquipamentoAction() {
    
       operacaoCadastro = "Add";
       this.gridEquipamento.setDisable(false);
       this.txtEquipamentoSerial.setText("");
       this.txtEquipamentoMarca.setText("");
       this.txtEquipamentoModelo.setText("");
       //this.cbEquipamentoSinal.setText("");
       this.tglEquipamentoAtivo.setSelected(true);      
       this.paneEquipamento.setPrefHeight(720);
       this.gridEquipamento.setVisible(true);
       this.txtEquipamentoMarca.requestFocus();

    }
    
    @FXML
    private void btnEditarEquipamentoAction(){
        operacaoCadastro = "Edit";
        this.gridEquipamento.setDisable(false);
        this.txtEquipamentoMarca.requestFocus();
        this.btnCancelarTecnico.setDisable(false);
    }

     @FXML
    private void btnCancelarEquipamentoAction() {
    
       this.gridEquipamento.setDisable(true);
    }
    
    
    
      
   //***************Transdutor***********
    // Controles

    @FXML private GridPane gridTransdutor;
    @FXML private Pane paneTransdutor;
    @FXML private Button btnSalvarTransdutor;
    @FXML private Button btnCancelarTransdutor;
    @FXML private TableView<TransdutorModel> transdutorTableView;
    @FXML private JFXTextField txtTransdutorSerial;
    @FXML private JFXTextField txtTransdutorMarca;
    @FXML private JFXTextField txtTransdutorModelo;
    @FXML private JFXComboBox<String> cbTransdutorSinal;
    @FXML  private JFXTextField txtTransdutorEixoX;
    @FXML private JFXTextField txtTransdutorEixoY;  
    @FXML private JFXToggleButton tglTransdutorAtivo;

    
    
    //Procedimentos

    
    public void loadTransdutorTableView() throws ClassNotFoundException, SQLException
    {
        TransdutorModel transdutor = new TransdutorModel();
        ArrayList<TransdutorModel> resultados = transdutor.read();
        TableColumn<TransdutorModel, String> tcTransdutorId = new TableColumn("Serial");
        tcTransdutorId.setCellValueFactory(new PropertyValueFactory<>("Serial"));
        
        TableColumn<TransdutorModel, String> tcTransdutorNome = new TableColumn("Modelo");
        tcTransdutorNome.setCellValueFactory(new PropertyValueFactory<>("modelo"));

        this.transdutorTableView.getItems().clear();
        this.transdutorTableView.getColumns().clear();
        
        tcTransdutorId.setPrefWidth(transdutorTableView.getWidth() * 0.3);
        tcTransdutorNome.setPrefWidth(transdutorTableView.getWidth() * 0.7);
        this.transdutorTableView.getColumns().addAll(tcTransdutorId, tcTransdutorNome);
         
        this.transdutorTableView.setItems(FXCollections.observableArrayList(resultados));
    }
    

    
    @FXML
    void btnSalvarTransdutorAction() throws ClassNotFoundException, SQLException, Exception {
        

            TransdutorModel transdutor = new TransdutorModel(
                    0,
                    this.txtTransdutorSerial.getText(),
                    this.txtTransdutorMarca.getText(),
                    this.txtTransdutorModelo.getText(),
                    this.txtTransdutorEixoX.getText(),
                    this.txtTransdutorEixoY.getText(),
                    tglTransdutorAtivo.isSelected() == true ? "A":"I"

             );
            
            if (operacaoCadastro == "Add") {
               transdutor.create();
               operacaoCadastro = "Vazio";
               System.out.println("Transdutor salvou!");
     
            } else {
                transdutor.update();
                operacaoCadastro = "Vazio";
                System.out.println("Transdutor atualizou!");
            }
            loadTransdutorTableView();
            this.gridTransdutor.setDisable(true);
    }
    
    @FXML
    public void transdutorSelecionar(){
        
        this.paneTransdutor.setPrefHeight(720);
        this.gridTransdutor.setVisible(true);
        
        TransdutorModel transdutor = transdutorTableView.getSelectionModel().getSelectedItem();
        
        this.txtTransdutorSerial.setText(transdutor.getSerial());
        this.txtTransdutorMarca.setText(transdutor.getMarca());
        this.txtTransdutorModelo.setText(transdutor.getModelo());
        this.txtTransdutorEixoX.setText(transdutor.getEixoX());
        this.txtTransdutorEixoY.setText(transdutor.getEixoY());
       

        
       if ( "A".equals(transdutor.getAtivo())) {
           this.tglTransdutorAtivo.setSelected(true);
       } else {
           this.tglTransdutorAtivo.setSelected(false);
       }
         
    }

    @FXML
    private void btnAdicionarTransdutorAction() {
    
       operacaoCadastro = "Add";
       this.gridTransdutor.setDisable(false);
       this.txtTransdutorSerial.setText("");
       this.txtTransdutorMarca.setText("");
       this.txtTransdutorModelo.setText("");
       this.txtTransdutorEixoX.setText("");
       this.txtTransdutorEixoY.setText("");
       this.tglTransdutorAtivo.setSelected(true);      
       this.paneTransdutor.setPrefHeight(720);
       this.gridTransdutor.setVisible(true);
       this.txtTransdutorMarca.requestFocus();

    }
    
    @FXML
    private void btnEditarTransdutorAction(){
        operacaoCadastro = "Edit";
        this.gridTransdutor.setDisable(false);
        this.txtTransdutorMarca.requestFocus();
       //this.btnCancelarTecnico.setDisable(false);
    }

     @FXML
    private void btnCancelarTransdutorAction() {
    
       this.gridTransdutor.setDisable(true);
    }
    
    
    
    
     //***************Iniciar nova sessao***********
    // Controles

    @FXML private Pane paneNovaSessao;
    @FXML private GridPane gridNovaSessao;
    @FXML private JFXTextField txtDescricaoSessao;    
    @FXML private JFXComboBox<TecnicoModel> cbTecnicoSessao;
    @FXML private JFXComboBox<EquipamentoModel> cbEquipamentoSessao;
    @FXML private JFXComboBox<TransdutorModel> cbTransdutorSessao;
    @FXML private Button btnSalvarSessao;
    @FXML private Button btnCancelarSessao;
    
    
    private void loadComboTecnicoSessao() throws ClassNotFoundException, SQLException
    {
        this.tecnicos = new TecnicoModel().read();
        this.cbTecnicoSessao.setItems(FXCollections.observableArrayList(this.tecnicos));
        this.cbTecnicoSessao.getSelectionModel().select(2);
        
        ArrayList<TecnicoModel> tecnicosSelecionados = new ArrayList<>();
        
        this.tecnicos.forEach((tecnico) -> {     
             tecnicosSelecionados.add(tecnico);
        });
        
        this.cbTecnicoSessao.setItems(FXCollections.observableArrayList(tecnicosSelecionados));
        this.cbTecnicoSessao.getSelectionModel().selectFirst();
    }
    
    private void loadComboEquipamentoSessao() throws ClassNotFoundException, SQLException
    {
        this.equipamentos = new EquipamentoModel().read();
        this.cbEquipamentoSessao.setItems(FXCollections.observableArrayList(this.equipamentos));
        this.cbEquipamentoSessao.getSelectionModel().select(2);
        
        ArrayList<EquipamentoModel> equipamentosSelecionados = new ArrayList<>();
        
        this.equipamentos.forEach((equipamento) -> {     
             equipamentosSelecionados.add(equipamento);
        });
        
        this.cbEquipamentoSessao.setItems(FXCollections.observableArrayList(equipamentosSelecionados));
        this.cbEquipamentoSessao.getSelectionModel().selectFirst();
    }
    
    private void loadComboTransdutorSessao() throws ClassNotFoundException, SQLException
    {
        this.transdutores = new TransdutorModel().read();
        this.cbTransdutorSessao.setItems(FXCollections.observableArrayList(this.transdutores));
        this.cbTransdutorSessao.getSelectionModel().select(2);
        
        ArrayList<TransdutorModel> transdutoresSelecionados = new ArrayList<>();
        
        this.transdutores.forEach((transdutor) -> {     
             transdutoresSelecionados.add(transdutor);
        });
        
        this.cbTransdutorSessao.setItems(FXCollections.observableArrayList(transdutoresSelecionados));
        this.cbTransdutorSessao.getSelectionModel().selectFirst();
    }
    
    @FXML
    private void btnSalvarSessaoAction() {
    
      closePanes();
    }
    
    @FXML
    private void btnCancelarSessaoAction() {
    
      closePanes();
    }
    
    
    
    
//***************Interpretar Imagem***********
    //Controles 

    @FXML private Pane paneImagemInterpretar;
    @FXML private ToggleGroup Trace;


    
    // Procedimentos
    
    private ArrayList<PaisModel> paises;
    private ArrayList<EstadoModel> estados;
    private ArrayList<TecnicoModel> tecnicos;
    private ArrayList<EquipamentoModel> equipamentos;
    private ArrayList<TransdutorModel> transdutores;

    
    
    
    
    
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
  void toggleEmpresaPais()   {
     ArrayList<EstadoModel> estadosSelecionados = new ArrayList<>();
        
     // this.estados.forEach((estado) -> {       
     //      if(estado.getIdPais() == this.cbEmpresaPais.getSelectionModel().getSelectedItem().getId()){
     //          System.out.println(estado.getNome());
     //          estadosSelecionados.add(estado);
     //      }
     // }

        
     //this.cbEmpresaEstado.setItems(FXCollections.observableArrayList(estadosSelecionados));
     //this.cbEmpresaEstado.getSelectionModel().selectFirst();
 
  }
}