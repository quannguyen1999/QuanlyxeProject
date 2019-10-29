package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import application.Main;
import dao.QuanLyAccount;
import dao.QuanLyNhanVien;
import entities.Account;
import entities.NhanVien;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class BanHopDongBuonBanXeMayController implements Initializable{
	String username="";
	private double xOffset = 0;
	private double yOffset = 0;
	@FXML BorderPane mainBd;
	@FXML Label txtMaHD;
	@FXML Label txtNgayLap;
	@FXML Label txtThangLap;
	@FXML Label txtNamLap;
	@FXML Label txtTenBenBan;
	@FXML Label txtCMNDBenBan;
	@FXML Label txtDiaChiBenBan;
	@FXML Label txtTenBenMua;
	@FXML Label txtCMNDBenMua;
	@FXML Label txtDiaChiBenMua;
	
	
	@FXML Label txtNhanHieu;
	@FXML Label txtLoaiXe;
	@FXML Label txtTenXe;
	@FXML Label txtMau;
	@FXML Label txtGia;
	
	@FXML JFXButton btnInPhieuXuat;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		makeStageDrageable();
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
	
	public void btnCLoseWindow(ActionEvent e) throws IOException {
		((Node)(e.getSource())).getScene().getWindow().hide();  
	}

}
