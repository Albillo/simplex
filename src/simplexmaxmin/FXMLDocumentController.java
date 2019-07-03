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

    @FXML   private ComboBox ComboBoxMaxMin, FUno, FDos, FTres;
    @FXML   private TextField TextZXUno, TextZXDos, TextZXTres;
    @FXML   private TextField TextFUnoXUno, TextFUnoXDos, TextFUnoXTres, TextFUnoXCuatro;
    @FXML   private TextField TextFDosXUno, TextFDosXDos, TextFDosXTres, TextFDosXCuatro;
    @FXML   private TextField TextFTresXUno, TextFTresXDos, TextFTresXTres, TextFTresXCuatro;
    
    // Matriz global de dimencion 7 * 4
    double MatrizEntrante[][] = new double[4][7];
    double FilaPiboteEntrePuntoPibote[] = new double[7];
    
    // Añadimos texto al ComboBox
    private void SetTextCombo() {
        ComboBoxMaxMin.getItems().addAll( "Maximizar", "Minimizar" );
        FUno.getItems().addAll( ">=", "=", "=<" );
        FDos.getItems().addAll( ">=", "=", "=<" );
        FTres.getItems().addAll( ">=", "=", "=<" );
    }

    @FXML private void Empezar() {
        String seleccionado = (String) ComboBoxMaxMin.getValue();
        String uno = (String) FUno.getValue();
        String dos = (String) FDos.getValue();
        String tres = (String) FTres.getValue();
        
        if(seleccionado == null){
            Alerta("Selecciona una opcion.\nMaximizar o Minimizar");
            return;
        }
//        if( ValidarTextos() )
            if("=<".equals(uno) || "=".equals(uno))
                MatrizEntrante[1][3]=1;
            else
                MatrizEntrante[1][3]=-1;
            if("=<".equals(dos) || "=".equals(dos))
                MatrizEntrante[2][4]=1;
            else
                MatrizEntrante[2][4]=-1;
            if("=<".equals(tres) || "=".equals(tres))
                MatrizEntrante[3][5]=1;
            else
                MatrizEntrante[3][5]=-1;
            
            if("Maximizar".equals(seleccionado))
                Maximizar();
            else
                
                Minimizar();
            
    }
    
    void Minimizar(){
        int columna_pibote = 0;
        int fila_pibote = 0;
        boolean HayPositivoUno = false, HayPositivoDos = false, HayPositivoTres = false;
        
        // Igualamos los valores de Z 
        for (int j = 0; j < 3; j++) {
            MatrizEntrante[0][j] = MatrizEntrante[0][j] * -1;
        }
        
        // Iterar hasta que no existan negativos en Z
        while(HayPositivoUno != true || HayPositivoDos != true || HayPositivoTres != true ){

            // Obtenemos columna pibote
            double mas_negativo = MatrizEntrante[0][0];
            for (int j = 0; j < MatrizEntrante.length; j++) {
                
                // Tomara el mayor positivo
                if(MatrizEntrante[0][j] > mas_negativo){ // 
                    mas_negativo = MatrizEntrante[0][j];
                    columna_pibote = j;
                }
            }
            // Obtenemos fila pibote
            double menor = 999999999;
            for (int i = 0; i < MatrizEntrante.length; i++) {
                // Recorremos nuestra matriz sin tocar la fila pibote
                if(i != columna_pibote){
                    // Filas que sean mayor a cero
                    if(MatrizEntrante[i][columna_pibote] > 0){
                        // Resultado entre la columna pibote
                        if(MatrizEntrante[i][6] / MatrizEntrante[i][columna_pibote] < menor){
                            menor = MatrizEntrante[i][6] / MatrizEntrante[i][columna_pibote];
                            fila_pibote = i;
                        }
                    }
                }
            }

            // Nueva fila pibote
            for(int j = 0; j < MatrizEntrante[fila_pibote].length; j++)
                FilaPiboteEntrePuntoPibote[j] = MatrizEntrante[fila_pibote][j] / MatrizEntrante[fila_pibote][columna_pibote];
            for(int j = 0; j < MatrizEntrante[fila_pibote].length; j++)
                MatrizEntrante[fila_pibote][j] = FilaPiboteEntrePuntoPibote[j];
            
            // Imprimimos alineadamente los resultados
            System.out.println("X1\tX2\tX3\tS1\tS2\tS3\tZ");

            // Recoreremos y cambiaremos lo valores sin tocar la fila pivote
            for(int i = 0; i < MatrizEntrante.length; i++) {
                for(int j = 0; j < MatrizEntrante[i].length; j++) {
                    if(i != fila_pibote){

                        // Multiplicamos nuestra fila actual con nuestra columna pivote, para obtener el resultanto a cero para nuestra fila actual con nuestra columna pivote
                        double valorColumnaPivote = MatrizEntrante[i][columna_pibote] * -1;

                        FilaPiboteEntrePuntoPibote[j] = (valorColumnaPivote * MatrizEntrante[fila_pibote][j]) + (MatrizEntrante[i][j]);
                    }
                }
                
                // Imprimimos resultados
                for(int j = 0; j < MatrizEntrante[i].length; j++) {
                    if(i != fila_pibote)
                        MatrizEntrante[i][j] = FilaPiboteEntrePuntoPibote[j];

                    System.out.print(round(MatrizEntrante[i][j], 2)+"\t");
                }
                System.out.println("");
            }
            
            System.out.println("");
            HayPositivoUno = MatrizEntrante[0][1] >= 0;
            HayPositivoDos = MatrizEntrante[0][2] >= 0;
            HayPositivoTres = MatrizEntrante[0][3] >= 0;
        }

        for(int i = 0; i < MatrizEntrante.length; i++){
            if(MatrizEntrante[i][0] == 1)
                System.out.print("X1: "+ MatrizEntrante[i][6]);
            if(MatrizEntrante[i][1] == 1)
                System.out.print("\tX2: "+ MatrizEntrante[i][6]);
            if(MatrizEntrante[i][2] == 1)
                System.out.print("\tX3: "+ MatrizEntrante[i][6]);
        }
        System.out.print("\tZ: "+ MatrizEntrante[0][6]);
    }

    void Maximizar(){
        int columna_pibote = 0;
        int fila_pibote = 0;
        boolean HayPositivoUno = false, HayPositivoDos = false, HayPositivoTres = false;
        
        // Igualamos los valores de Z 
        for (int j = 0; j < 3; j++) {
            MatrizEntrante[0][j] = MatrizEntrante[0][j] * -1;
        }
        
        // Iterar hasta que no existan negativos en Z
        while(HayPositivoUno != true || HayPositivoDos != true || HayPositivoTres != true ){

            // Obtenemos columna pibote
            double mas_negativo = MatrizEntrante[0][0];
            for (int j = 0; j < MatrizEntrante.length; j++) {
                
                // Tomara el menor megativo
                if(MatrizEntrante[0][j] < mas_negativo){ // 
                    mas_negativo = MatrizEntrante[0][j];
                    columna_pibote = j;
                }
            }

            // Obtenemos fila pibote
            double menor = 999999999;
            for (int i = 0; i < MatrizEntrante.length; i++) {
                // Recorremos nuestra matriz sin tocar la fila pibote
                if(i != columna_pibote){
                    // Filas que sean mayor a cero
                    if(MatrizEntrante[i][columna_pibote] > 0){
                        // Resultado entre la columna pibote
                        if(MatrizEntrante[i][6] / MatrizEntrante[i][columna_pibote] < menor){
                            menor = MatrizEntrante[i][6] / MatrizEntrante[i][columna_pibote];
                            fila_pibote = i;
                        }
                    }
                }
            }

            // Nueva fila pibote
            for(int j = 0; j < MatrizEntrante[fila_pibote].length; j++)
                FilaPiboteEntrePuntoPibote[j] = MatrizEntrante[fila_pibote][j] / MatrizEntrante[fila_pibote][columna_pibote];
            for(int j = 0; j < MatrizEntrante[fila_pibote].length; j++)
                MatrizEntrante[fila_pibote][j] = FilaPiboteEntrePuntoPibote[j];
            
            // Imprimimos alineadamente los resultados
            System.out.println("X1\tX2\tX3\tS1\tS2\tS3\tZ");

            // Recoreremos y cambiaremos lo valores sin tocar la fila pivote
            for(int i = 0; i < MatrizEntrante.length; i++) {
                for(int j = 0; j < MatrizEntrante[i].length; j++) {
                    if(i != fila_pibote){

                        // Multiplicamos nuestra fila actual con nuestra columna pivote, para obtener el resultanto a cero para nuestra fila actual con nuestra columna pivote
                        double valorColumnaPivote = MatrizEntrante[i][columna_pibote] * -1;

                        FilaPiboteEntrePuntoPibote[j] = (valorColumnaPivote * MatrizEntrante[fila_pibote][j]) + (MatrizEntrante[i][j]);
                    }
                }
                
                // Imprimimos resultados
                for(int j = 0; j < MatrizEntrante[i].length; j++) {
                    if(i != fila_pibote)
                        MatrizEntrante[i][j] = FilaPiboteEntrePuntoPibote[j];

                    System.out.print(round(MatrizEntrante[i][j], 2)+"\t");
                }
                System.out.println("");
            }
            System.out.println("");
            HayPositivoUno = MatrizEntrante[0][1] >= 0;
            HayPositivoDos = MatrizEntrante[0][2] >= 0;
            HayPositivoTres = MatrizEntrante[0][3] >= 0;
        }

        for(int i = 0; i < MatrizEntrante.length; i++){
            if(MatrizEntrante[i][0] == 1)
                System.out.print("X1: "+ MatrizEntrante[i][6]);
            if(MatrizEntrante[i][1] == 1)
                System.out.print("\tX2: "+ MatrizEntrante[i][6]);
            if(MatrizEntrante[i][2] == 1)
                System.out.print("\tX3: "+ MatrizEntrante[i][6]);
        }
        System.out.print("\tZ: "+ MatrizEntrante[0][6]);
    }
    
    // Redondear a n digitos un valor double
    double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
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
        
        String uno = (String) FUno.getValue();
        String dos = (String) FDos.getValue();
        String tres = (String) FTres.getValue();
        
        if(uno == null){
            Alerta("Selecciona una opcion para f1.");
            return false;
        }
        if(dos == null){
            Alerta("Selecciona una opcion para f2.");
            return false;
        }
        if(tres == null){
            Alerta("Selecciona una opcion para f3.");
            return false;
        }
        
        // Pasamos los valores del TextField a la Matriz
        // Z
        MatrizEntrante[0][0]=Integer.parseInt(TextZXUno.getText());
        MatrizEntrante[0][1]=Integer.parseInt(TextZXDos.getText());
        MatrizEntrante[0][2]=Integer.parseInt(TextZXTres.getText());

        // F1
        MatrizEntrante[1][0]=Integer.parseInt(TextFUnoXUno.getText());
        MatrizEntrante[1][1]=Integer.parseInt(TextFUnoXDos.getText());
        MatrizEntrante[1][2]=Integer.parseInt(TextFUnoXTres.getText());
        MatrizEntrante[1][6]=Integer.parseInt(TextFUnoXCuatro.getText());

        // F2
        MatrizEntrante[2][0]=Integer.parseInt(TextFDosXUno.getText());
        MatrizEntrante[2][1]=Integer.parseInt(TextFDosXDos.getText());
        MatrizEntrante[2][2]=Integer.parseInt(TextFDosXTres.getText());
        MatrizEntrante[2][6]=Integer.parseInt(TextFDosXCuatro.getText());

        //F3
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

        // Minimizar
        MatrizEntrante[0][0]=-5;
        MatrizEntrante[0][1]=-4;
        MatrizEntrante[0][2]=0;

        MatrizEntrante[1][0]=2;
        MatrizEntrante[1][1]=2;
        MatrizEntrante[1][2]=0;
        MatrizEntrante[1][6]=14;

        MatrizEntrante[2][0]=6;
        MatrizEntrante[2][1]=3;
        MatrizEntrante[2][2]=0;
        MatrizEntrante[2][6]=36;

        MatrizEntrante[3][0]=5;
        MatrizEntrante[3][1]=10;
        MatrizEntrante[3][2]=0;
        MatrizEntrante[3][6]=60;
        
        /*
        // Maximizar
        MatrizEntrante[0][0]=2;
        MatrizEntrante[0][1]=2;
        MatrizEntrante[0][2]=-3;

        MatrizEntrante[1][0]=-1;
        MatrizEntrante[1][1]=1;
        MatrizEntrante[1][2]=1;
        MatrizEntrante[1][6]=4;

        MatrizEntrante[2][0]=2;
        MatrizEntrante[2][1]=-1;
        MatrizEntrante[2][2]=1;
        MatrizEntrante[2][6]=2;

        MatrizEntrante[3][0]=1;
        MatrizEntrante[3][1]=1;
        MatrizEntrante[3][2]=3;
        MatrizEntrante[3][6]=12;
        
        MatrizEntrante[1][3]=1;
        MatrizEntrante[2][4]=1;
        MatrizEntrante[3][5]=1;
        */
    }
}
