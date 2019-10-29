package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.sun.javafx.application.LauncherImpl;

import application.Main;
import application.Loading;
import dao.QuanLyAccount;
import dao.QuanLyNhanVien;
import entities.Account;
import entities.NhanVien;
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
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.input.KeyCode;
public class DangNhap implements Initializable{
	private double xOffset = 0;
	private double yOffset = 0;
	@FXML private JFXTextField txtUser;
	@FXML private JFXPasswordField txtPass;
	@FXML VBox mainBd;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		txtUser.setText("ThanhTung99");
		txtPass.setText("123406");
		
		// TODO Auto-generated method stub
		try {
			txtPass.setOnKeyPressed(e->{
				if(e.getCode()==KeyCode.ENTER) {
					
				}
			});
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
		Account acc=null;
		boolean continuses=true;
		if(userName.isEmpty()==true) {
			continuses=false;
			thongBaoKieuLoi(e, "UserName chưa nhập");
			txtUser.requestFocus();
		}

		if(continuses==true) {
			if(passWord.isEmpty()==true) {
				continuses=false;
				thongBaoKieuLoi(e, "Mật khẩu chưa nhập");
				txtPass.requestFocus();
			}
		}

		if(continuses==true) {
			acc=QuanLyAccount.timMa(userName);
			if(acc!=null) {
				if(passWord.contentEquals(acc.getPassword())==true) {

				}else {
					thongBaoKieuLoi(e,"mật khẩu không hợp lệ");
					txtPass.requestFocus();
					acc=null;
				}
				return acc;
			}else {
				thongBaoKieuLoi(e, "tên người dùng không tồn tại");
				acc=null;
			}
		}


		return acc;
	}
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
				case "Nhân viên":
					((Node)(e.getSource())).getScene().getWindow().hide();  
					loader=new FXMLLoader(getClass().getResource("/fxml/KeToan.fxml"));
					root=loader.load();
					KeToanController ctlKeToan=loader.getController();
					NhanVien nv=QuanLyNhanVien.timMa2(txtUser.getText().toString());
					ctlKeToan.ThietLapTenNguoiDangNhap(nv.getTenNV());
					ctlKeToan.userName=txtUser.getText().toString();
					stage=new Stage();
					stage.initStyle(StageStyle.UNDECORATED);
					stage.setScene(new Scene(root));
					stage.getIcons().add(new Image("/image/logo.PNG"));
					stage.show();
					break;
				case "admin":
					((Node)(e.getSource())).getScene().getWindow().hide();  
					loader=new FXMLLoader(getClass().getResource("/fxml/Main.fxml"));
					root=loader.load();
					AdminController ctlAdmin=loader.getController();
					NhanVien nv1=QuanLyNhanVien.timMa2(txtUser.getText().toString());
					ctlAdmin.ThietLapTenNguoiDangNhap(nv1.getTenNV());
					ctlAdmin.userName=txtUser.getText().toString();
					stage=new Stage();
					stage.initStyle(StageStyle.UNDECORATED);
					stage.setScene(new Scene(root));
					stage.getIcons().add(new Image("/image/logo.PNG"));
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
