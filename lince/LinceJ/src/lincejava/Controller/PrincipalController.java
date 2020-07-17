package lincejava.Controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeView;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
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
  // Variáveis gerais 
    private String quemChamou = "";
    
// Preencher TreeTableView
    
    TreeItem<String> raiz = new TreeItem<>("Visão geral");
    
    TreeItem<String> tecnico1 = new TreeItem<>("João dos Santos");
    TreeItem<String> item_t1_l1 = new TreeItem<>("Sessão 1");
    TreeItem<String> item_t1_l2 = new TreeItem<>("Sessão 2");

    TreeItem<String> tecnico2 = new TreeItem<>("Antero Peixoto");
    TreeItem<String> item_t2_l1 = new TreeItem<>("Sessão 3");
    TreeItem<String> item_t2_l2 = new TreeItem<>("Sessão 4");
   
   
    @FXML
    void initialize()
    {
        
        TreeItem<String> tecnicos = new TreeItem<String>();
        
        tecnicos.setValue("Tecnicos");
        
        tecnicos.getChildren().addAll(
                new TreeItem<String>("José"), 
                new TreeItem<String>("Marcelo"),
                new TreeItem<String>("Severino")
        );
        
        tecnicoTreeView = new JFXTreeView<String>(tecnicos);
        
        AnchorPaneAside.getChildren().add(tecnicoTreeView);
             
//      AnchorPaneAside.getChildren().add(tecnicoTreeView);
        
//       tecnico1.getChildren().setAll(item_t1_l1,item_t1_l2);
//       tecnico2.getChildren().setAll(item_t2_l1,item_t2_l2);
//       raiz.getChildren().setAll(tecnico1, tecnico2);
//       treeTableViewCol1.setCellFactory(new Callback<TreeTableColumn.CellDatafeatures<String, String>,ObservableValue<String>>() { 
//           @Override
//            publicObservableValue<String> call(treeTableColumn.CellDataFeatures<String, String> param) {
//               return new SimpleStringProperty(param.getValue().Value());
//
//            }
//       }
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
            enderecoModel.setPais("Brasil");
            enderecoModel.setEstado("SP");
            enderecoModel.setCidade("Pirassununga");
            enderecoModel.setBairro("Vila Guilhermina");
            enderecoModel.setRua("Av. Joaquim Cristóvão");
            enderecoModel.setNumero("965");
            enderecoModel.create();
            tecnicoModel.setEndereco(enderecoModel); 
            
            ContatoModel contatoModel = new ContatoModel();
            contatoModel.setCelular1("985645745");
            contatoModel.setCelular2("956523587");
            contatoModel.setEmail("bruno@ig.com");
            contatoModel.setFixo("35632386");
            contatoModel.create();
            tecnicoModel.setContato(contatoModel);
            
            if(tecnicoModel.create()) {
                System.out.println("Tecnico salvou!");
            } else
            {
                System.out.println("Tecnico não salvou!");
            }
        paneCadastrar.setVisible(false);
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

