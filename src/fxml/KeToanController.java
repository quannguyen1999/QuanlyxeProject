package fxml;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class KeToanController implements Initializable{
	private double xOffset = 0;
	private double yOffset = 0;
	@FXML public BorderPane mainBd;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		makeStageDrageable();
		try {
			Parent root=(Parent) FXMLLoader.load(getClass().getResource("/fxml/QuanLyXe.fxml"));
			mainBd.setCenter(root);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void handleExit(ActionEvent e) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setHeaderText(null);
		alert.setContentText("bạn có muốn thoát?");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			System.exit(0);
		} else {
			// ... user chose CANCEL or closed the dialog
		}
	}
	public void btnDangXuat(ActionEvent e) throws IOException {
		((Node)(e.getSource())).getScene().getWindow().hide();  
		Stage primaryStage=new Stage();
		Parent root=(Parent) FXMLLoader.load(getClass().getResource("/fxml/FormLogin.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.initStyle(StageStyle.UNDECORATED);
		Main.primaryStage=primaryStage;
		primaryStage.show();
	}
	public void setControllerWindows() {
		mainBd.setDisable(false);
	}
	public void btnThongTinNguoiDung(ActionEvent e) throws IOException {
		mainBd.setDisable(true);

		Stage primaryStage=new Stage();

		Parent root=(Parent) FXMLLoader.load(getClass().getResource("/fxml/FormUser.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.setAlwaysOnTop(true);
		Main.primaryStage=primaryStage;
		primaryStage.showAndWait();
	}
	public void btnQuanLyXe(ActionEvent e) throws IOException {
		Parent root=(Parent) FXMLLoader.load(getClass().getResource("/fxml/QuanLyXe.fxml"));
		mainBd.setCenter(root);
	}
	public void btnQuanLyKhachHang(ActionEvent e) throws IOException {
		Parent root=(Parent) FXMLLoader.load(getClass().getResource("/fxml/QuanLyKhachHang.fxml"));
		mainBd.setCenter(root);
	}
	public void btnQuanLyXuatHang(ActionEvent e) throws IOException {
		Parent root=(Parent) FXMLLoader.load(getClass().getResource("/fxml/QuanLyXuatHang.fxml"));
		mainBd.setCenter(root);
	}
	public void btnQuanLyHopDong(ActionEvent e) throws IOException {
		Parent root=(Parent) FXMLLoader.load(getClass().getResource("/fxml/QuanLyHopDong.fxml"));
		mainBd.setCenter(root);
	}
	public void btnBaoCaoThongKe(ActionEvent e) throws IOException {
		Parent root=(Parent) FXMLLoader.load(getClass().getResource("/fxml/BaoCaoThongKe.fxml"));
		mainBd.setCenter(root);
	}
	
	
	
	public void btnHideWindow(ActionEvent e) {
		Stage stage=(Stage) ((Node)(e.getSource())).getScene().getWindow();  
		stage.setIconified(true);
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
}
