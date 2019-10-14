package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextField;

import application.Main;
import dao.QuanLyKhachHang;
import entities.KhachHang;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
public class ThemKhachHang implements Initializable{
	private double xOffset = 0;
	private double yOffset = 0;
	@FXML public BorderPane mainBd;
	@FXML Label lblTitle;
	@FXML JFXTextField txtMa;
	@FXML JFXTextField txtDiaChi;
	@FXML JFXTextField txtEmail;
	@FXML JFXTextField txtDienThoai;
	@FXML JFXTextField txtTenKH;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		makeStageDrageable();
	}
	public void thongBaoKieuLoi(ActionEvent e, String text) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Dialog");
		alert.setHeaderText(null);
		alert.setContentText(text);
		alert.initOwner(((Node) (e.getSource())).getScene().getWindow());
		alert.showAndWait();
	}
	public boolean kiemTraMa(String text) {
		if(text.isEmpty()==false) {
			try {
				int result=Integer.parseInt(text);
				return true;
			} catch (Exception e) {
				// TODO: handle exception
				return false;
			}
		}
		return false;
	}
	public boolean kiemTraDiaChi(String text) {
		if(text.isEmpty()==false) {
			return true;
		}
		return false;
	}
	public boolean kiemTraEmail(String text) {
		if(text.isEmpty()==false) {
			return true;
		}
		return false;
	}
	public boolean kiemTraDienThoai(String text) {
		if(text.isEmpty()==false) {
			return true;
		}
		return false;
	}
	public boolean kiemTraTen(String text) {
		if(text.isEmpty()==false) {
			return true;
		}
		return false;
	}
	public void CLickOK(ActionEvent e) {
		String ma=txtMa.getText().toString();
		String diaChi=txtDiaChi.getText().toString();
		String Email=txtEmail.getText().toString();
		String DienThoai=txtDienThoai.getText().toString();
		String ten=txtTenKH.getText().toString();
		boolean stillContunite=true;
		if(kiemTraMa(ma)==true) {
			stillContunite=true;
		}else {
			thongBaoKieuLoi(e, "yêu cầu nhập mã");
			txtMa.requestFocus();
			stillContunite=false;
		}
		if(stillContunite==true) {
			if(kiemTraDiaChi(diaChi)) {
				stillContunite=true;
			}else {
				thongBaoKieuLoi(e, "yêu cầu nhập địa chỉ");
				txtDiaChi.requestFocus();
				stillContunite=false;
			}
		}
		if(stillContunite==true) {
			if(kiemTraEmail(Email)) {
				stillContunite=true;
			}else {
				thongBaoKieuLoi(e, "yêu cầu nhập email");
				txtEmail.requestFocus();
				stillContunite=false;
			}
		}
		if(stillContunite==true) {
			if(kiemTraDienThoai(DienThoai)) {
				stillContunite=true;
			}else {
				thongBaoKieuLoi(e, "yêu cầu nhập điện thoại");
				txtDienThoai.requestFocus();
				stillContunite=false;
			}
		}
		if(stillContunite==true) {
			if(kiemTraTen(ten)) {
				stillContunite=true;
			}else {
				thongBaoKieuLoi(e, "yêu cầu nhập tên");
				txtTenKH.requestFocus();
				stillContunite=false;
			}
		}
		if(stillContunite==true) {
			KhachHang kh=new KhachHang(Integer.parseInt(ma), diaChi, Email, DienThoai, ten);
			if(lblTitle.getText().toString().equals("Cập nhập khách hàng")) {
				if(QuanLyKhachHang.suaKH(kh)==true) {
					((Node)(e.getSource())).getScene().getWindow().hide();  
				}
			}else {
				if(QuanLyKhachHang.themAcc(kh)==true) {
					((Node)(e.getSource())).getScene().getWindow().hide();  
				}else{
					thongBaoKieuLoi(e, "thêm khách hàng không thành công");
				};
			}
			
		
		}


	}
	public void btnXoaRong(ActionEvent e) {
		txtMa.setText("");
		txtDiaChi.setText("");
		txtEmail.setText("");
		txtDienThoai.setText("");
		txtTenKH.setText("");
	}
	public void btnCLoseWindow(ActionEvent e) throws IOException {
		((Node)(e.getSource())).getScene().getWindow().hide();  
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
