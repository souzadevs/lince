package lincejava.Controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeView;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.TreeView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import lincejava.Model.ContatoModel;
import lincejava.Model.EnderecoModel;
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
    private AnchorPane AnchorPaneCadastro;

    @FXML
    private Pane paneListar;

    @FXML
    private Button btnAdicionar;

    @FXML
    private Button btnExcluir1;

    @FXML
    private Button btnEditar1;

    @FXML
    private ImageView btnFechaPanePesquisar;

    @FXML
    private Pane paneCadastrar;

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
    private Button btnSalvarCadastro;

    @FXML
    private Button btnCancelarCadastro;

    @FXML
    private Pane paneCadastrarEquipamento;

    @FXML
    private Button btnCancelarCadastro1;

    @FXML
    private Pane paneImagem;

    @FXML
    private ToggleGroup Trace;
    
    @FXML 
    private TableView tecnicoTableView;
    
    @FXML
    private JFXComboBox<EstadoModel> cbEstado;
    
    @FXML
    private JFXComboBox<PaisModel> cbPais;
    
    
    
  // Variáveis gerais 
    private String quemChamou = "";
    
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
        loadComboBoxData();
        
        
//        TreeItem<String> tecnicos = new TreeItem<String>();
//        
//        tecnicos.setValue("Tecnicos");
//        
//        tecnicos.getChildren().addAll(
//                new TreeItem<String>("José"), 
//                new TreeItem<String>("Marcelo"),
//                new TreeItem<String>("Severino")
//        );
//        
//        tecnicoTreeView = new JFXTreeView<String>(tecnicos);
//        
//        AnchorPaneAside.getChildren().add(tecnicoTreeView);
        
        // Popular tabela de técnico
        
        
        
       
        
        
    }
    
    private void loadComboBoxData() throws ClassNotFoundException, SQLException
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
    void toggleTecnicoAtivo()
    {
        
    }
    
    @FXML
    void btnTecnicoAction() 
    {       
        paneListar.setVisible(true);
        quemChamou = "tecnico";
        System.out.println("Tecnico chamou");
    }

    
    @FXML
    void btnFazendaAction() 
    {       
        paneListar.setVisible(true);
        quemChamou = "fazenda";
        System.out.println("Fazenda chamou");
    }
    
    
    @FXML
    void btnEquipamentoAction() 
    {       
        paneListar.setVisible(true);
        quemChamou = "equipamento";
        System.out.println("Equipamento chamou");
    }
    
    @FXML
    void btnSairAction() throws ClassNotFoundException, SQLException
    {   
         ArrayList<EstadoModel> estados = new EstadoModel().read();
        estados.forEach((estado) -> {
            System.out.println("Nome: " + estado.getNome());
        });  
    }
    
    @FXML
    void btnAdicionarAction() {
        
        switch(this.quemChamou) {

        case "tecnico":
        paneCadastrar.setVisible(true);
        System.out.println("Tecnico chamou");
        break;

        case "equipamento":
        paneCadastrarEquipamento.setVisible(true);
        System.out.println("Equipamento chamou");
        break;

        case "fazenda":
        paneCadastrar.setVisible(true);
        System.out.println("Empresa chamou");
        break;
        
        }    
    }

    @FXML
    void btnCancelarCadastroAction() {
        
    switch(this.quemChamou) {

        case "tecnico":
        paneCadastrar.setVisible(false);
        System.out.println("Tecnico cancelou");
        break;

        case "equipamento":
        paneCadastrarEquipamento.setVisible(false);
        System.out.println("Equipamento cancelou");

        break;

        case "fazenda":
        paneCadastrar.setVisible(false);
        System.out.println("Empresa cancelou");
        break;
        }
    }
    
    @FXML
    void btnSalvarCadastroAction() throws ClassNotFoundException, SQLException, Exception {
    System.out.println(this.quemChamou);
    switch(this.quemChamou) {

        case "tecnico":
            TecnicoModel tecnicoModel = new TecnicoModel();
            tecnicoModel.setNome(txtTecnicoNome.getText());

            EnderecoModel enderecoModel = new EnderecoModel();
            enderecoModel.setPais(this.cbPais.getSelectionModel().getSelectedItem());
            enderecoModel.setEstado(this.cbEstado.getSelectionModel().getSelectedItem());
            enderecoModel.setCidade(this.txtTecnicoCidade.getText());
            enderecoModel.setBairro(this.txtTecnicoBairro.getText());
            enderecoModel.setRua(this.txtTecnicoEndereco.getText());
            enderecoModel.setNumero(this.txttecnicoNumero.getText());
            tecnicoModel.setEndereco(enderecoModel); 

            ContatoModel contatoModel = new ContatoModel();
            contatoModel.setCelular1(this.txtTecnicoCelular.getText());
            contatoModel.setCelular2(this.txtTecnicoCelular.getText());
            contatoModel.setEmail(this.txtTecnicoEmail.getText());
            contatoModel.setFixo(this.txtTecnicoTelefone.getText());
            tecnicoModel.setContato(contatoModel);

            if(tecnicoModel.create() != -1) {
                System.out.println("Tecnico salvou!");
            } else {
                System.out.println("Tecnico não salvou!");
            }
            
            // paneCadastrar.setVisible(false);
        break;

        case "equipamento":
            paneCadastrarEquipamento.setVisible(false);
            System.out.println("Equipamento cancelou");

        break;

        case "fazenda":
            paneCadastrar.setVisible(false);
            System.out.println("Empresa cancelou");
        break;
        
        }
    }
}

