package simplexmaxmin;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/** @author DELL */
public class FXMLDocumentController implements Initializable {

    @FXML   private ComboBox ComboBoxMaxMin;
    @FXML   private TextField TextZXUno, TextZXDos, TextZXTres, TextZXCuatro;
    @FXML   private TextField TextFUnoXUno, TextFUnoXDos, TextFUnoXTres, TextFUnoXCuatro;
    @FXML   private TextField TextFDosXUno, TextFDosXDos, TextFDosXTres, TextFDosXCuatro;
    @FXML   private TextField TextFTresXUno, TextFTresXDos, TextFTresXTres, TextFTresXCuatro;
    
    // Matriz global de dimencion 7 * 4
    double MatrizEntrante[][] = new double[4][7];
    
    private void SetTextCombo() {
        // Añadimos texto al ComboBox
        ComboBoxMaxMin.getItems().addAll( "Maximizar", "Minimizar" );
    }

    @FXML private void Empezar() {
        String seleccionado = (String) ComboBoxMaxMin.getValue();
        if(seleccionado == null){
            Alerta("Selecciona una opcion.\nMaximizar o Minimizar");
            return;
        }
        if( ValidarTextos() )
            if("Maximizar".equals(seleccionado))
                Maximizar();
            else
                Alerta("Minimizar");
    }
    
    void Maximizar(){
        for (double[] MatrizEntrante1 : MatrizEntrante) {
            for (int j = 0; j < MatrizEntrante1.length; j++) {
                System.out.print(MatrizEntrante1[j] + ", ");
            }
            System.out.println(" ");
        }
    }
    
    @FXML private Boolean ValidarTextos(){
        if("".equals(TextZXUno.getText())){
            Alerta("Requiero un valor para Z en x1");
            return false;
        }
        if("".equals(TextZXDos.getText())){
            Alerta("Requiero un valor para Z en x2");
            return false;
        }
        if("".equals(TextZXTres.getText())){
            Alerta("Requiero un valor para Z en x3");
            return false;
        }
        if("".equals(TextZXCuatro.getText())){
            Alerta("Requiero un valor para Z");
            return false;
        }
        if("".equals(TextFUnoXUno.getText())){
            Alerta("Requiero un valor para f1 en x1");
            return false;
        }
        if("".equals(TextFUnoXDos.getText())){
            Alerta("Requiero un valor para f1 en x2");
            return false;
        }
        if("".equals(TextFUnoXTres.getText())){
            Alerta("Requiero un valor para f1 en x3");
            return false;
        }
        if("".equals(TextFUnoXCuatro.getText())){
            Alerta("Requiero un valor para f1");
            return false;
        }
        if("".equals(TextFDosXUno.getText())){
            Alerta("Requiero un valor para f2 en x1");
            return false;
        }
        if("".equals(TextFDosXDos.getText())){
            Alerta("Requiero un valor para f2 en x2");
            return false;
        }
        if("".equals(TextFDosXTres.getText())){
            Alerta("Requiero un valor para f2 en x3");
            return false;
        }
        if("".equals(TextFDosXCuatro.getText())){
            Alerta("Requiero un valor para f2");
            return false;
        }
        if("".equals(TextFTresXUno.getText())){
            Alerta("Requiero un valor para f3 en x1");
            return false;
        }
        if("".equals(TextFTresXDos.getText())){
            Alerta("Requiero un valor para f3 en x2");
            return false;
        }
        if("".equals(TextFTresXTres.getText())){
            Alerta("Requiero un valor para f3 en x3");
            return false;
        }
        if("".equals(TextFTresXCuatro.getText())){
            Alerta("Requiero un valor para f3");
            return false;
        }
        
        MatrizEntrante[0][0]=Integer.parseInt(TextZXUno.getText());
        MatrizEntrante[0][1]=Integer.parseInt(TextZXDos.getText());
        MatrizEntrante[0][2]=Integer.parseInt(TextZXTres.getText());
        MatrizEntrante[0][6]=Integer.parseInt(TextZXCuatro.getText());

        MatrizEntrante[1][0]=Integer.parseInt(TextFUnoXUno.getText());
        MatrizEntrante[1][1]=Integer.parseInt(TextFUnoXDos.getText());
        MatrizEntrante[1][2]=Integer.parseInt(TextFUnoXTres.getText());
        MatrizEntrante[1][6]=Integer.parseInt(TextFUnoXCuatro.getText());

        MatrizEntrante[2][0]=Integer.parseInt(TextFDosXUno.getText());
        MatrizEntrante[2][1]=Integer.parseInt(TextFDosXDos.getText());
        MatrizEntrante[2][2]=Integer.parseInt(TextFDosXTres.getText());
        MatrizEntrante[2][6]=Integer.parseInt(TextFDosXCuatro.getText());

        MatrizEntrante[3][0]=Integer.parseInt(TextFTresXUno.getText());
        MatrizEntrante[3][1]=Integer.parseInt(TextFTresXDos.getText());
        MatrizEntrante[3][2]=Integer.parseInt(TextFTresXTres.getText());
        MatrizEntrante[3][6]=Integer.parseInt(TextFTresXCuatro.getText());

        return true;
    }
    
    @FXML public static void Alerta(String NotaAlerta){                                     //Le pasamos la alerta para notificar
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informacion");
        alert.setHeaderText("");
        alert.setContentText(NotaAlerta);
        alert.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SetTextCombo();
    }
}
