package controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXDatePicker;
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
	String username="";
	private double xOffset = 0;
	private double yOffset = 0;
	@FXML public BorderPane mainBd;
	@FXML Label lblTitle;
	@FXML JFXTextField txtMa;
	@FXML JFXTextField txtDiaChi;
	@FXML JFXTextField txtCMND;
	@FXML JFXTextField txtDienThoai;
	@FXML JFXTextField txtTenKH;
	@FXML JFXDatePicker txtNgaySinh;
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
	public boolean kiemTraMa(ActionEvent e,String text) {
		if(text.isEmpty()==false) {
			try {
				int result=Integer.parseInt(text);
				return true;
			} catch (Exception ev) {
				thongBaoKieuLoi(e, "Mã khách hàng chỉ nhập số");
				return false;
			}
		}else {
			thongBaoKieuLoi(e, "mã khách hàng không được để trống");
		}
		return false;
	}
	public boolean kiemTraDiaChi(ActionEvent e,String ma) {
		String MaKT=ma.trim();
		if(MaKT.isEmpty()==false) {
			if(MaKT.matches("^[A-Za-z\\sÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ]+$")==true) {
				return true;
			}else {
				thongBaoKieuLoi(e, "địa chỉ không được nhập ký tự đặc biệt");
				return false;
			}
		}else {
			thongBaoKieuLoi(e, "địa chỉ không được để trống");
			return false;
		}
	}
	public boolean kiemTraCMND(ActionEvent e,String ma) {
		String MaKT=ma.trim();
		if(MaKT.isEmpty()==false) {
			if(MaKT.length()==9) {
				if(MaKT.matches("[0-9]{9}+")==true) {
					return true;
				}else {
					thongBaoKieuLoi(e, "Mã nhân viên chỉ nhập số");
					return false;
				}
			}else {
				thongBaoKieuLoi(e, "chứng minh nhân dân phải 9 số");
				return false;
			}
		}else {
			thongBaoKieuLoi(e, "chứng minh nhân dân không được để trống");
			return false;
		}
	}
	public boolean kiemTraDienThoai(ActionEvent e,String ma) {
		String MaKT=ma.trim();
		if(MaKT.isEmpty()==false) {
			if(MaKT.length()==10) {
				if(MaKT.matches("[0-9]{10}+")==true) {
					return true;
				}else {
					thongBaoKieuLoi(e, "số điện thoại chỉ nhập số");
					return false;
				}
			}else {
				thongBaoKieuLoi(e, "số điện thoại phải 10 số");
				return false;
			}
		}else {
			thongBaoKieuLoi(e, "Số điện thoại không được để trống");
			return false;
		}
	}
	public boolean kiemTraTenKhachHang(ActionEvent e,String ma) {
		String MaKT=ma.trim();
		if(MaKT.isEmpty()==false) {
			if(MaKT.matches("^[A-Za-z\\sÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ]+$")==true) {
				return true;
			}else {
				thongBaoKieuLoi(e, "Tên khách hàng không được nhập ký tự đặc biệt");
				return false;
			}
		}else {
			thongBaoKieuLoi(e, "Tên khách hàng không được để trống");
			return false;
		}
	}
	public void CLickOK(ActionEvent e) {
		String ma=txtMa.getText().toString();
		String diaChi=txtDiaChi.getText().toString();
		String CMND=txtCMND.getText().toString();
		String DienThoai=txtDienThoai.getText().toString();
		String ten=txtTenKH.getText().toString();
		LocalDate lc=txtNgaySinh.getValue();
		boolean stillContunite=true;
		if(kiemTraMa(e, ma)==true) {
			stillContunite=true;
		}else {
			txtMa.requestFocus();
			stillContunite=false;
		}
		if(stillContunite==true) {
			if(kiemTraDiaChi(e,diaChi)) {
				stillContunite=true;
			}else {
				txtDiaChi.requestFocus();
				stillContunite=false;
			}
		}
		if(stillContunite==true) {
			if(kiemTraCMND(e,CMND)) {
				stillContunite=true;
			}else {
				txtCMND.requestFocus();
				stillContunite=false;
			}
		}
		if(stillContunite==true) {
			if(kiemTraDienThoai(e,DienThoai)) {
				stillContunite=true;
			}else {
				txtDienThoai.requestFocus();
				stillContunite=false;
			}
		}
		if(stillContunite==true) {
			if(kiemTraTenKhachHang(e,ten)) {
				stillContunite=true;
			}else {
				txtTenKH.requestFocus();
				stillContunite=false;
			}
		}
		if(stillContunite==true) {
			if(txtNgaySinh.getValue()==null) {
				stillContunite=false;
				thongBaoKieuLoi(e, "Ngày sinh chưa chọn");
				txtNgaySinh.requestFocus();
			}
		}
		if(stillContunite==true) {
			KhachHang kh=new KhachHang(Integer.parseInt(ma), diaChi, CMND, DienThoai, ten,lc);
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
		txtCMND.setText("");
		txtDienThoai.setText("");
		txtTenKH.setText("");
		txtNgaySinh.setValue(LocalDate.of(1999, 11, 24));
		
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
