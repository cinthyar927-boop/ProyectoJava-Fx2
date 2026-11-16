package org.ejemplos.projectojavafx2;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Cylinder;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.Optional;

/**
 * Controlador principal para la interfaz de JavaFX
 * Gestiona las interacciones del usuario con los componentes de la vista FXML
 */

public class HelloController {
    // ___Componentes de la interfaz inyectados desde el archivo FXML---
    /**
     * Campo de texto para ingresar el radio
     */
    public TextField txtFldRadio;
    /**
     * Campo de texto para ingresar la altura
     */
    public TextField txtFldAltura;
    /**
     * Campo de texto donde se mostrará el resultado del cálculo
     */
    public TextField txtFldResultado;
    /**
     * Botón para ejecutar el cálculo
     */
    public Button btnCalcular;
    /**
     * Botón para limpiar los campos de entrada y salida
     */
    public Button btnBorrar;
    /**
     * Botón para salir de la aplicación
     */
    public Button btnSalir;
    public GridPane panelDibijo;
    public Pane paneDibujo;
    @FXML
    private Label welcomeText;

    /**
     * Metodo asociado  a un evento de botón en la vista para mostrar un saludode bienvenida
     */

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Hola este es mi primer programa de Javafx2!");
    }

    /**
     * Metodo que se ejecuta al presionar el boton calcular
     * (Pendiente de implementar la logica matematica)
     *
     * @param actionEvent Evento de accion del boton
     * @return
     */
    public boolean onActionCalcular(ActionEvent actionEvent) {
        double altura = Double.parseDouble(txtFldAltura.getText());
        double radio = Double.parseDouble(txtFldRadio.getText());
        Cilindro cil = new Cilindro(radio,altura);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Resultados");
        alert.setHeaderText("");
        alert.setContentText(cil.getArea() + " " + cil.getAltura());
        Optional<ButtonType> resp = alert.showAndWait();
        return resp.isPresent() && resp.get() == ButtonType.OK;
    }

    /**
     * Metodo que se ejecuta al presionar el boton de borrar
     * (Pendiente de implementar la limpieza de campos)
     * @param actionEvent Evento de accion del boton
     */
    public void onActionBorrar(ActionEvent actionEvent) {
        txtFldAltura.setText("");
        txtFldRadio.setText("");

    }

    /**
     * Metodo de inicializacion del controlador. Se ejecuta automaticamente
     * despues de que se cargan los elementos de la interfaz
     */
    //Codigo a ejecutar una vez que la escena este completamente cargada
    public void initialize(){
        Platform.runLater(()->{
            Stage stage = (Stage)btnSalir.getScene().getWindow();
            stage.setOnCloseRequest(this::cerrarFormulario);
        });
        Cylinder cyl = new Cylinder(50,100);
        PhongMaterial material = new PhongMaterial(Color.BLUE);
        material.setSpecularColor(Color.WHITE);
        cyl.setMaterial(material);
        //Configuracion de como veremos el cilindro
        cyl.getTransforms().addAll(
           new Rotate(10,Rotate.X_AXIS),
                new Rotate(20,Rotate.Y_AXIS),
                new Rotate(10,Rotate.Z_AXIS)

        );
        //Agrupamos cilindro
        Group grp3D = new Group(cyl);

        //Camara de perspectiva alejada para ver el cilindro completo
        PerspectiveCamera camera = new PerspectiveCamera(true);
        //Recortamos el cilindro
        camera.setNearClip(0.1);
        camera.setFarClip(0.1);
        camera.setTranslateZ(-400);
        SubScene subScene = new SubScene(grp3D,0,0,true, SceneAntialiasing.BALANCED);
        subScene.setFill(Color.TRANSPARENT);
        subScene.setCamera(camera);
        //Define tamaño de la escena
        subScene.widthProperty().bind(paneDibujo.widthProperty());
        subScene.heightProperty().bind(paneDibujo.heightProperty());

        panelDibijo.getChildren().add(subScene);
    }

    /**
     * Cierree la aplicacion de manera segura utilizando Platform.exit().
     * @param actionEvent Evento de accion del boton salir
     */

    public void onActionSalir(ActionEvent actionEvent) {
        if (confirmarSalida())
        Platform.exit();
    }

    /**
     * Controla el evento de cierre de la ventana, verificando si el usuario
     * realmente desea salir mediante una confirmacion
     * @param windowEvent Evento de la ventana
     */
    private void cerrarFormulario(WindowEvent windowEvent){
        if (!confirmarSalida())
            windowEvent.consume();
    }

    /**
     * Muestra una ventana de alerta para confirmar la salida de la aplicacion
     * @return true si el usuario presiona ok, false en caso de lo contrario
     */
        public boolean confirmarSalida(){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Salir");
            alert.setHeaderText("");
            alert.setContentText("¿Cerrar la ventana?");
            Optional<ButtonType> resp = alert.showAndWait();
            return resp.isPresent() && resp.get() == ButtonType.OK;

        }
    }





