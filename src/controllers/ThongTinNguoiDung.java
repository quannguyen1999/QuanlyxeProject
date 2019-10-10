package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import dao.QuanLyNhanVien;
import entities.NhanVien;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.control.Label;
public class ThongTinNguoiDung implements Initializable{
	private double xOffset = 0;
	private double yOffset = 0;
	@FXML BorderPane bd;
	@FXML Label lblHoTen;
	@FXML Label lblChucVu;
	@FXML Label lblDiaChi;
	@FXML Label lblDT;
	@FXML Label lblGioiTinh;
	@FXML Label lblUsername;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		makeStageDrageable();
	}

	private void makeStageDrageable() {
		bd.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				xOffset = event.getSceneX();
				yOffset = event.getSceneY();
			}
		});
		bd.setOnMouseDragged(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {

				Main.primaryStage.setX(event.getScreenX() - xOffset);
				Main.primaryStage.setY(event.getScreenY() - yOffset);
				Main.primaryStage.setOpacity(0.7f);
			}
		});
		bd.setOnDragDone((e) -> {
			Main.primaryStage.setOpacity(1.0f);
		});
		bd.setOnMouseReleased((e) -> {
			Main.primaryStage.setOpacity(1.0f);
		});

	}
	public void thietLapFormNguoiDung(NhanVien nv) {
		lblHoTen.setText(nv.getTenNV());
		lblChucVu.setText(nv.getChucVu());
		lblDiaChi.setText(nv.getDiaChi());
		lblDT.setText(nv.getDienThoai());;
		lblGioiTinh.setText(nv.getGioiTinh());
		lblUsername.setText(nv.getAccount().getUserName());
	}
	public void btnQuayLai(ActionEvent e) throws IOException {
//		((Node)(e.getSource())).getScene().getWindow().hide();  
//
//		Stage primaryStage=new Stage();
//		Parent root=(Parent) FXMLLoader.load(getClass().getResource("/fxml/Main.fxml"));
//		Scene scene = new Scene(root);
//		scene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
//		primaryStage.setScene(scene);
//		//		primaryStage.initStyle(StageStyle.UNDECORATED);
//		primaryStage.setResizable(false);
//		Main.primaryStage=primaryStage;
//		primaryStage.show();
	}
	public void btnDoiMatKhau(ActionEvent e) throws IOException {
		((Node)(e.getSource())).getScene().getWindow().hide();  
		FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/FormDoiMatKhau.fxml"));
		Parent root=loader.load();
		DoiMatKhau ctlThongTin=loader.getController();
		ctlThongTin.thietLapTenNguoiDung(lblUsername.getText().toString());
		Stage stage=new Stage();
		stage.initOwner(((Node)(e.getSource())).getScene().getWindow());
		stage.initStyle(StageStyle.UNDECORATED);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setScene(new Scene(root));
		Main.primaryStage=stage;
		stage.show();
	}
	public void btnCLoseWindow(ActionEvent e) throws IOException {
		((Node)(e.getSource())).getScene().getWindow().hide();  
	}

}
