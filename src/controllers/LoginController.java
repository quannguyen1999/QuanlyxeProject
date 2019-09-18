package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import application.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginController implements Initializable{
	private double xOffset = 0;
	private double yOffset = 0;
	@FXML private JFXTextField txtUser;
	@FXML private JFXPasswordField txtPass;
	
	@FXML VBox mainBd;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		try {
			makeStageDrageable();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	
	}
	private void makeStageDrageable() {
		mainBd.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				xOffset = event.getSceneX();
				yOffset = event.getSceneY();
			}
		});
		mainBd.setOnMouseDragged(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {

				Main.primaryStage.setX(event.getScreenX() - xOffset);
				Main.primaryStage.setY(event.getScreenY() - yOffset);
				Main.primaryStage.setOpacity(0.7f);
			}
		});
		mainBd.setOnDragDone((e) -> {
			Main.primaryStage.setOpacity(1.0f);
		});
		mainBd.setOnMouseReleased((e) -> {
			Main.primaryStage.setOpacity(1.0f);
		});

	}
	public void btnLogin(ActionEvent e) throws IOException {
		if(txtUser.getText().toString().equals("admin") && txtPass.getText().toString().equals("123")) {
			((Node)(e.getSource())).getScene().getWindow().hide();  
			Stage primaryStage=new Stage();
			Parent root=(Parent) FXMLLoader.load(getClass().getResource("/fxml/Main.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
			primaryStage.setScene(scene);
			Main.primaryStage=primaryStage;
			primaryStage.show();
		}else if(txtUser.getText().toString().equals("nhanvien") && txtPass.getText().toString().equals("123")) {
			((Node)(e.getSource())).getScene().getWindow().hide();  
			Stage primaryStage=new Stage();
			Parent root=(Parent) FXMLLoader.load(getClass().getResource("/fxml/NhanVien.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
			primaryStage.setScene(scene);
			Main.primaryStage=primaryStage;
			primaryStage.show();
		}else if(txtUser.getText().toString().equals("ketoan") && txtPass.getText().toString().equals("123")) {
			((Node)(e.getSource())).getScene().getWindow().hide();  
			Stage primaryStage=new Stage();
			Parent root=(Parent) FXMLLoader.load(getClass().getResource("/fxml/KeToan.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
			primaryStage.setScene(scene);
			Main.primaryStage=primaryStage;
			primaryStage.show();
		}else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Nhập sai thông tin!");
			alert.showAndWait();
		}
	}
	public void btnCLoseWindow() {
		System.exit(0);
	}
	public void btnHideWindow(ActionEvent e) {
		Stage stage=(Stage) ((Node)(e.getSource())).getScene().getWindow();  
		stage.setIconified(true);
	}
	public void btnHuy() {
		txtUser.setText("");
		txtPass.setText("");
	}

}
