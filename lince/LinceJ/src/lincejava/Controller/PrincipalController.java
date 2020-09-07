package lincejava.Controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeView;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import lincejava.Model.ContatoModel;
import lincejava.Model.EnderecoModel;
import lincejava.Model.EquipamentoModel;
import lincejava.Model.EstadoModel;
import lincejava.Model.PaisModel;
import lincejava.Model.TecnicoModel;

public class PrincipalController {

    @FXML
    private Button btnTecnico;

    @FXML
    private Button btnFazenda;

    @FXML
    private Button btnEquipamento;

    @FXML
    private Button btnConfiguracoes;

    @FXML
    private Button btnSair;

    @FXML
    private AnchorPane AnchorPaneAside;

    @FXML
    private JFXTreeView<?> tecnicoTreeView;

    @FXML
    private Pane paneTecnico;

    @FXML
    private Pane paneEquipamento;
    
    @FXML
    private Button btnAdicionarTecnico;

    @FXML
    private ImageView btnFechaPanePesquisar;

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
    private JFXComboBox<?> cdEstado;

    @FXML
    private JFXTextField cdPais;

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
    private Button btnSalvarEquipamento;
    
    @FXML
    private Button btnCancelarEquipamento;

    @FXML
    private Pane paneImagem;

    @FXML
    private ToggleGroup Trace;
    
    @FXML 
    private TableView<TecnicoModel> tecnicoTableView;
    
    @FXML 
    private TableView<EquipamentoModel> equipamentoTableView;
      
    @FXML
    private JFXComboBox<EstadoModel> cbEstado;
    
    @FXML
    private JFXComboBox<PaisModel> cbPais;

    @FXML
    private TableColumn<TecnicoModel, String> tecnicoTableViewId;

    @FXML
    private TableColumn<TecnicoModel, String> tecnicoTableViewNome;
    
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

   
    
    private ArrayList<PaisModel> paises;
    private ArrayList<EstadoModel> estados;
    
// Preencher TreeTableView
    
    TreeItem<String> raiz = new TreeItem<>("Visão geral");
    
    TreeItem<String> tecnico1 = new TreeItem<>("João dos Santos");
    TreeItem<String> item_t1_l1 = new TreeItem<>("Sessão 1");
    TreeItem<String> item_t1_l2 = new TreeItem<>("Sessão 2");

    TreeItem<String> tecnico2 = new TreeItem<>("Antero Peixoto");
    TreeItem<String> item_t2_l1 = new TreeItem<>("Sessão 3");
    TreeItem<String> item_t2_l2 = new TreeItem<>("Sessão 4");
   
   
    @FXML
    void initialize() throws ClassNotFoundException, SQLException
    {
        //loadComboBoxData();
        loadEquipamentoFormData();
    }
    
    private void loadTecnicoComboBoxData() throws ClassNotFoundException, SQLException
    {
        this.paises = new PaisModel().read();
        this.estados = new EstadoModel().read();
        
        // System.out.println("Aqui");
        
        this.cbPais.setItems(FXCollections.observableArrayList(this.paises));
        //this.cbEstado.setItems(FXCollections.observableArrayList(this.estados));
        this.cbPais.getSelectionModel().select(30);
        
        ArrayList<EstadoModel> estadosSelecionados = new ArrayList<>();
        
        this.estados.forEach((estado) -> {       
            if(estado.getIdPais() == this.cbPais.getSelectionModel().getSelectedItem().getId()){
                //System.out.println(estado.getNome());
                estadosSelecionados.add(estado);
            }
        });
        
        this.cbEstado.setItems(FXCollections.observableArrayList(estadosSelecionados));
        this.cbEstado.getSelectionModel().selectFirst();
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
        //this.cadastroTableView.getColumns().addAll(tcEquipamentoId, tcEquipamentoNome);
         
        this.equipamentoTableView.setItems(FXCollections.observableArrayList(resultados));
    }
    
    public void loadEquipamentoFormData()
    {
        ArrayList<String> sinal = new ArrayList<>();
        sinal.add("Pal-M");
        sinal.add("NTSC");
        
        this.cbEquipamentoSinal.setItems(FXCollections.observableArrayList(sinal));
    }
    
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
    void togglePais()
    {
        ArrayList<EstadoModel> estadosSelecionados = new ArrayList<>();
        
        this.estados.forEach((estado) -> {       
            if(estado.getIdPais() == this.cbPais.getSelectionModel().getSelectedItem().getId()){
                //System.out.println(estado.getNome());
                estadosSelecionados.add(estado);
            }
        });
        
        this.cbEstado.setItems(FXCollections.observableArrayList(estadosSelecionados));
        this.cbEstado.getSelectionModel().selectFirst();
    }
    
    @FXML
    void btnFecharJanelas()
    {
        closePanes();   
    }

    @FXML
    void toggleTecnicoAtivo()
    {
        
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
    void btnFazendaAction() 
    {       
        closePanes();
        //paneListar.setVisible(true);
    }
    
        
    @FXML
    private void btnAdicionarTecnicoAction() {
    
       this.btnAdicionarTecnico.setDisable(false);
       this.btnCancelarTecnico.setDisable(false);
    }
    
    
    @FXML
    private void btnAdicionarEquipamentoAction() {
    
       this.btnSalvarEquipamento.setDisable(false);
       this.btnCancelarEquipamento.setDisable(false);
       
    }
    
    
    @FXML
    private void btnCancelarTecnicoAction() {
    
       this.btnAdicionarTecnico.setDisable(false);
       this.btnCancelarTecnico.setDisable(false);
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
        System.out.println("Equipamento chamou");
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
    void btnSalvarTecnicoAction() throws ClassNotFoundException, SQLException, Exception {
            EnderecoModel enderecoModel = new EnderecoModel();
            enderecoModel.setPais(this.cbPais.getSelectionModel().getSelectedItem());
            enderecoModel.setEstado(this.cbEstado.getSelectionModel().getSelectedItem());
            enderecoModel.setCidade(this.txtTecnicoCidade.getText());
            enderecoModel.setBairro(this.txtTecnicoBairro.getText());
            enderecoModel.setRua(this.txtTecnicoEndereco.getText());
            enderecoModel.setNumero(this.txttecnicoNumero.getText());

            ContatoModel contatoModel = new ContatoModel();
            contatoModel.setCelular1(this.txtTecnicoCelular.getText());
            contatoModel.setCelular2(this.txtTecnicoCelular.getText());
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
    void btnSalvarEquipamentoAction() throws ClassNotFoundException, SQLException, Exception {
        

            EquipamentoModel eqpt = new EquipamentoModel(
                    0,
                    this.txtEquipamentoDescricao.getText(),
                    this.txtEquipamentoMarca.getText(),
                    this.txtEquipamentoModelo.getText(),
                    this.cbEquipamentoSinal.getSelectionModel().getSelectedItem(),
                    this.txtEquipamentoEixoX.getText(),
                    this.txtEquipamentoEixoY.getText()
            );
            
            
//            
            if(eqpt.create() != -1) {
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
    void btnSalvarFazenda() throws ClassNotFoundException, SQLException, Exception {
            //paneCadastrar.setVisible(false);
            System.out.println("Empresa cancelou");

    }
}

