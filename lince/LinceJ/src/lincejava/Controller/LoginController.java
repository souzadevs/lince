package lincejava.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private JFXTextField txtUsuario;

    @FXML
    private JFXButton btnLogin;

    @FXML
    private JFXButton btnCancelar;

    @FXML
    private JFXPasswordField txtSenha;
    
    @FXML
    public void btnLoginAction() throws IOException
    {
        if("saulo".equals(txtUsuario.getText()) && "123".equals(txtSenha.getText())) {
            //System.out.println("Programadores java!!!");
            Parent ap = FXMLLoader.load(getClass().getResource("/lincejava/View/Principal.fxml")); //Carrego o anchor pane do arquivo
        
            Scene sc = new Scene(ap); //Crio uma cena com o AnchorPane carregado (nossa tela principal)
            
            Stage st = new Stage(); //Crio um novo palco vazio para iniciarmos a cena 
            
            st.setScene(sc); //Insiro a cena no palco

            Stage stg = (Stage) btnCancelar.getScene().getWindow(); //Fecho o palco inicial, pegando a "janela" aberta do login através do seu "filho", o botão cancelar
    
            stg.close(); // Fecho o palco (janela) de login 
            
            st.show(); // Inicio um novo palco (stage) com a cena.
        }  
    }
}