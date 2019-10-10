package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import application.Main;
import dao.QuanLyAccount;
import dao.QuanLyNhanVien;
import entities.Account;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DangNhap implements Initializable{
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
	public void thongBaoKieuLoi(ActionEvent e, String text) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Dialog");
		alert.setHeaderText(null);
		alert.setContentText(text);
		alert.initOwner(((Node) (e.getSource())).getScene().getWindow());
		alert.showAndWait();
	}
	public Account kiemTraUserName(ActionEvent e,String userName,String passWord) {
		Account acc=QuanLyAccount.timMa(userName);
//		System.out.println(acc);
		if(acc!=null) {
			if(passWord.equals(acc.getPassword())==true) {
				return acc; 
			}else {
//				System.out.println(passWord);
//				System.out.println(acc.getPassword());
				thongBaoKieuLoi(e, "mật khẩu không hợp lệ");
				txtPass.requestFocus();
			}
		}else {
			thongBaoKieuLoi(e, "Tài khoản không tồn tại");
			txtUser.requestFocus();
		}
		return null;
	}
//	@FXML 
//	private void keyPressed(KeyEvent keyEvent) {
//		if(keyEvent.getCode()==KeyCode.ENTER) {
//	}
	public void btnLogin(ActionEvent e) throws IOException {
		FXMLLoader loader;
		Parent root;
		Stage stage;
		String userName=txtUser.getText().toString();
		String passWord=txtPass.getText().toString();
		{
			Account acc=kiemTraUserName(e, userName, passWord);
			if(acc!=null) {
				switch (acc.getLoaiTK()) {
				case "Ke toan truong":

					break;
				case "Ke toan":
					((Node)(e.getSource())).getScene().getWindow().hide();  
					loader=new FXMLLoader(getClass().getResource("/fxml/KeToan.fxml"));
					root=loader.load();
					KeToanController ctlKeToan=loader.getController();
					ctlKeToan.ThietLapTenNguoiDangNhap(txtUser.getText().toString());
					stage=new Stage();
					stage.setScene(new Scene(root));
					stage.show();
					break;
				case "Admin":
					((Node)(e.getSource())).getScene().getWindow().hide();  
					loader=new FXMLLoader(getClass().getResource("/fxml/Main.fxml"));
					root=loader.load();
					AdminController ctlAdmin=loader.getController();
					ctlAdmin.ThietLapTenNguoiDangNhap(txtUser.getText().toString());
					stage=new Stage();
					stage.setScene(new Scene(root));
					stage.show();
					break;
				case "Nhân viên":
					((Node)(e.getSource())).getScene().getWindow().hide();  
					loader=new FXMLLoader(getClass().getResource("/fxml/NhanVien.fxml"));
					root=loader.load();
					NhanVienController ctlNhanVien=loader.getController();
					ctlNhanVien.ThietLapTenNguoiDangNhap(txtUser.getText().toString());
					stage=new Stage();
					stage.setScene(new Scene(root));
					stage.show();
					break;
				default:
					break;
				}
			}
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
