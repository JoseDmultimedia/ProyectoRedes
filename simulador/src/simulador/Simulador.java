/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulador;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author DELL
 */
public class Simulador extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Agrega valore simulando un smartroom");
        
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        Text scenetitle = new Text("Smartroom");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        Label nameTemperatura = new Label("Temperatura:");
        grid.add(nameTemperatura, 0, 1);

        TextField temperatura = new TextField();
        grid.add(temperatura, 1, 1);
        
        Label nameHumedad = new Label("Humedad:");
        grid.add(nameHumedad, 0, 2);

        TextField humedad = new TextField();
        grid.add(humedad,1,2);
        
        Label nameMovimiento = new Label("Movimiento:");
        grid.add(nameMovimiento, 0, 3);

        TextField movimiento = new TextField();
        grid.add(movimiento,1,3);
        
        Label nameLuz = new Label("Luz:");
        grid.add(nameLuz, 0, 4);

        TextField luz = new TextField();
        grid.add(luz,1,4);
        
        Label nameToque = new Label("Toque:");
        grid.add(nameToque, 0, 5);

        TextField toque = new TextField();
        grid.add(toque,1,5);
        
        Label nameFecha = new Label("Fecha:");
        grid.add(nameFecha, 0, 6);

        TextField fecha = new TextField();
        grid.add(fecha,1,6);
        
        Label nameHora = new Label("Hora:");
        grid.add(nameHora, 0, 7);

        TextField hora = new TextField();
        grid.add(hora,1,7);
        
        Label id_usuariodr = new Label("Usuario:");
        grid.add(id_usuariodr, 0, 8);

        TextField usuario = new TextField();
        grid.add(usuario,1,8);
        
        Button btn = new Button("Envia");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 9);
        
        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 6);

        Scene scene = new Scene(grid, 300, 275);
        primaryStage.setScene(scene);
        
        primaryStage.show();
        
        btn.setOnAction(new EventHandler<ActionEvent>() {
 
        @Override
        public void handle(ActionEvent e) {
            
            try {
                URL url = new URL("https://Project-josedavgarcia615767.codeanyapp.com/smartroom");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setDoOutput(true);
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json");
                
                String inputJson = "{\"temperatura:\"" + temperatura.getText().toString()+"\",humedad:\""+ humedad.getText().toString()+"\",movimiento:\""+ movimiento.getText().toString()
                       + "\",luz:\""  + luz.getText().toString() +  "\",toque:\""+toque.getText().toString() +"\",fecha:\""+fecha.getText().toString()+"\",hora:\""+hora.getText().toString()+"\",id_usuariodr:\""+id_usuariodr.getText().toString()+ "]".getBytes(StandardCharsets.UTF_8);
                
                OutputStream os = conn.getOutputStream();
           
                byte[] input = inputJson.getBytes("utf-8");
                os.write(input, 0, input.length);           
                os.flush();
                
                if (conn.getResponseCode() != 204) {
                    throw new RuntimeException("Failed : HTTP error code : "
                            + conn.getResponseCode());
                }
                
                conn.disconnect();
               
            } catch (MalformedURLException ex) {
                Logger.getLogger(Simulador.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Simulador.class.getName()).log(Level.SEVERE, null, ex);
            }
            actiontarget.setFill(Color.FIREBRICK);
            actiontarget.setText("Valores Enviados");
            temperatura.setText("");
            humedad.setText("");
            movimiento.setText("");
            luz.setText("");
            toque.setText("");
            fecha.setText("");
            hora.setText("");
            id_usuariodr.setText("");
        }
});
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
