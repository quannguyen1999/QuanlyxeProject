package controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import javax.persistence.Column;
import javax.persistence.Id;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;

import application.Main;
import dao.QuanLyAccount;
import dao.QuanLyHopDong;
import dao.QuanLyKhachHang;
import dao.QuanLyNhanVien;
import dao.QuanLyPhieuXuat;
import dao.QuanLyXe;
import entities.Account;
import entities.HopDong;
import entities.KhachHang;
import entities.NhanVien;
import entities.PhieuXuat;
import entities.Xe;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.control.Label;
public class ThemHopDong implements Initializable{
	String username="";
	private double xOffset = 0;
	private double yOffset = 0;
	@FXML Label lblTitle;
	@FXML public BorderPane mainBd;
	@FXML JFXButton btnThem;
	@FXML JFXButton btnXoa;
	@FXML JFXTextField txtMaHD;
	@FXML ComboBox<String> boxMaNV;
	@FXML JFXTextField txtTenNV;
	@FXML JFXTextField txtCMNDNhanVien;
	@FXML JFXTextField txtNoiONV;
	@FXML JFXTextField txtSDTNV;

	@FXML JFXTextField txtTenKH;
	@FXML ComboBox<String> boxMaKH;
	@FXML JFXTextField txtDiaChiKH;
	@FXML JFXTextField txtSoDTKH;
	@FXML JFXTextField txtNoiOKH;
	@FXML JFXTextField txtCMNDKH;
	@FXML JFXTextField txtSL;

	@FXML ComboBox<String> boxMaXe;
	@FXML JFXRadioButton rdXN;
	@FXML JFXRadioButton rdCXN;
	//	@FXML JFXButton btnInPhieuXuat;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		makeStageDrageable();
		txtTenNV.setEditable(false);
		txtCMNDNhanVien.setEditable(false);
		txtNoiONV.setEditable(false);
		txtSDTNV.setEditable(false);

		txtTenKH.setEditable(false);
		txtDiaChiKH.setEditable(false);
		txtSoDTKH.setEditable(false);
		txtNoiOKH.setEditable(false);
		txtCMNDKH.setEditable(false);
		//box MaNV
		loadDuLieuMaKH();
		loadDuLieuMaNV();
		loadDuLieuMaXe();
	}
	@FXML 
	private void btnXoaRong(ActionEvent e) {
		txtMaHD.setText("");
		boxMaNV.setValue("");
		txtTenNV.setText("");
		txtCMNDNhanVien.setText("");
		txtNoiONV.setText("");
		txtSDTNV.setText("");

		txtTenKH.setText("");
		txtDiaChiKH.setText("");
		txtSoDTKH.setText("");
		txtNoiOKH.setText("");
		txtCMNDKH.setText("");

		boxMaNV.setValue("");
		boxMaKH.setValue("");
	}
	private void handleRefersh(ActionEvent e) {
		try {
			boxMaKH.setValue("");
			ObservableList<String> items1 = FXCollections.observableArrayList();
			List<KhachHang> kh1=QuanLyKhachHang.showTatCaKhachHang();
			kh1.forEach(t->{
				items1.add(String.valueOf(t.getMaKH()));
			});
			FilteredList<String> filteredItems1 = new FilteredList<String>(items1);
			boxMaKH.getEditor().textProperty().addListener(new InputFilter(boxMaKH, filteredItems1, false));
			boxMaKH.setItems(filteredItems1);
			
			boxMaKH.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() { 
				public void changed(ObservableValue ov, Number value, Number new_value) 
				{ 
					KhachHang nv=QuanLyKhachHang.timMa(Integer.parseInt(items1.get((int) new_value)));
					txtTenKH.setText("");
					txtDiaChiKH.setText("");
					txtSoDTKH.setText("");
					txtNoiOKH.setText("");
					txtCMNDKH.setText("");
					txtTenKH.setText(nv.getTenKH());
					txtDiaChiKH.setText(nv.getDiaChi());
					txtSoDTKH.setText(nv.getSoDT());
					txtNoiOKH.setText(nv.getDiaChi());
					txtCMNDKH.setText(nv.getCMND());
				}
			});

		} catch (Exception e2) {
			e2.printStackTrace();
		}

	}
	public void btnNhapThongTinKhachHang(ActionEvent e) throws IOException {
		try {
			FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/FormThongTinKhachHang.fxml"));
			Parent parent=loader.load();
			Stage stage=new Stage(StageStyle.DECORATED);
			stage.initOwner(((Node)(e.getSource())).getScene().getWindow());
			stage.initStyle(StageStyle.UNDECORATED);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(new Scene(parent));
			stage.show();
			stage.getIcons().add(new Image("/image/logo.PNG"));
			Main.primaryStage=stage;
			stage.setOnHidden(ev->{
				handleRefersh(e);
			});
		} catch (Exception e2) {
			System.out.println(e2.getMessage());
		}
	}
	public void loadDuLieuMaNV() {
		ObservableList<String> items = FXCollections.observableArrayList();

		NhanVien accs=QuanLyNhanVien.timMa2(username);
		items.add(String.valueOf(accs.getMaNV()));
		FilteredList<String> filteredItems = new FilteredList<String>(items);

		boxMaNV.getEditor().textProperty().addListener(new InputFilter(boxMaNV, filteredItems, false));

		boxMaNV.setItems(filteredItems);

		boxMaNV.setEditable(true);

		boxMaNV.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() { 
			public void changed(ObservableValue ov, Number value, Number new_value) 
			{ 
				NhanVien nv=QuanLyNhanVien.timMa(Integer.parseInt(items.get((int) new_value)));
				txtTenNV.setText("");
				txtCMNDNhanVien.setText("");
				txtNoiONV.setText("");
				txtSDTNV.setText("");
				txtTenNV.setText(nv.getTenNV());
				txtCMNDNhanVien.setText(nv.getCMND());
				txtNoiONV.setText(nv.getDiaChi());
				txtSDTNV.setText(nv.getDienThoai());

			}
		});
	}
	public void loadDuLieuMaKH() {
		ObservableList<String> items = FXCollections.observableArrayList();

		List<KhachHang> accs=QuanLyKhachHang.showTatCaKhachHang();
		accs.forEach(t->{
			items.add(String.valueOf(t.getMaKH()));
		});
		FilteredList<String> filteredItems = new FilteredList<String>(items);

		boxMaKH.getEditor().textProperty().addListener(new InputFilter(boxMaKH, filteredItems, false));

		boxMaKH.setItems(filteredItems);

		boxMaKH.setEditable(true);

		boxMaKH.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() { 
			public void changed(ObservableValue ov, Number value, Number new_value) 
			{ 
				KhachHang nv=QuanLyKhachHang.timMa(Integer.parseInt(items.get((int) new_value)));
				txtTenKH.setText("");
				txtDiaChiKH.setText("");
				txtSoDTKH.setText("");
				txtNoiOKH.setText("");
				txtCMNDKH.setText("");
				txtTenKH.setText(nv.getTenKH());
				txtDiaChiKH.setText(nv.getDiaChi());
				txtSoDTKH.setText(nv.getSoDT());
				txtNoiOKH.setText(nv.getDiaChi());
				txtCMNDKH.setText(nv.getCMND());
			}
		});
	}

	public void loadDuLieuMaXe() {
		ObservableList<String> items = FXCollections.observableArrayList();

		List<Xe> accs=QuanLyXe.showTatCaXe(); //.showTatCaKhachHang();
		accs.forEach(t->{
			items.add(String.valueOf(t.getMaXe()));
		});
		FilteredList<String> filteredItems = new FilteredList<String>(items);

		boxMaXe.getEditor().textProperty().addListener(new InputFilter(boxMaXe, filteredItems, false));

		boxMaXe.setItems(filteredItems);

		boxMaXe.setEditable(true);

		boxMaXe.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() { 
			public void changed(ObservableValue ov, Number value, Number new_value) 
			{ 
			}
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
	public boolean kiemTraSoTien(ActionEvent e,String text) {
		String MaKT=text.trim();
		if(MaKT.isEmpty()==false) {
			if(MaKT.matches("[0-9]+")==true) {
				return true;
			}else {
				thongBaoKieuLoi(e, "Số tiền chỉ nhập số");
				return false;
			}
		}else {
			thongBaoKieuLoi(e, "Tiền đặt chưa nhập");
			return false;
		}
	}
	@FXML
	public void btnThemHopDong(ActionEvent e) {
		boolean continues=true;
		int maHopDong=0;
		try {
			maHopDong=Integer.parseInt(txtMaHD.getText().toString());
		} catch (Exception e2) {
			continues=false;
			thongBaoKieuLoi(e, "mã hợp đồng không được để trống");
			txtMaHD.requestFocus();
			// TODO: handle exception
		}
		LocalDate ngayLap = LocalDate.now();
		String tenNguoiBan=txtTenNV.getText().toString();
		String cMNDNB=txtCMNDNhanVien.getText().toString();
		String noiONB=txtNoiONV.getText().toString();
		String soDTNB=txtSDTNV.getText().toString();
		String maNV="";
		String tenNguoiMua=txtTenKH.getText().toString();
		String cMNDNM=txtCMNDKH.getText().toString();
		String noiONM=txtNoiOKH.getText().toString();
		String soDTNM=txtSoDTKH.getText().toString();
		String maKH="";
		String maXe=boxMaXe.getValue();
		int soLuong=0;
		
		
		if(continues==true) {
			if(boxMaNV.getValue()==null) {
				thongBaoKieuLoi(e, "Mã nhân viên chưa nhập");
				continues=false;
				boxMaNV.requestFocus();
			}else {
				maNV=boxMaNV.getValue();
			}
		}

		if(continues==true) {
			if(QuanLyNhanVien.timMa(Integer.parseInt(maNV))==null) {
				thongBaoKieuLoi(e, "Mã nhân viên không tồn tại");
				continues=false;
				boxMaNV.requestFocus();
			}
		}
		if(continues==true) {
			if(boxMaKH.getValue()==null) {
				thongBaoKieuLoi(e, "Mã khách hàng chưa nhập");
				continues=false;
				boxMaKH.requestFocus();
			}else {
				maKH=boxMaKH.getValue();
			}
		}
		if(continues==true) {
			if(QuanLyKhachHang.timMa(Integer.parseInt(maKH))==null) {
				thongBaoKieuLoi(e, "Mã khách hàng không tồn tại");
				continues=false;
				boxMaKH.requestFocus();
			}
		}
		try {
			soLuong=Integer.parseInt(txtSL.getText().toString());
		} catch (Exception e2) {
			// TODO: handle exception
			thongBaoKieuLoi(e, "Số lượng chỉ nhập số");
			continues=false;
			txtSL.requestFocus();
		}
		Xe xe=QuanLyXe.timMa(maXe);
		if(continues==true) {
			if(xe==null) {
				continues=false;
			}

		}
		if(continues==true) {
			if(xe.getSoLuongLap()<soLuong) {
				continues=false;
				thongBaoKieuLoi(e, "Số lượng xe không đủ");
			}
		}
		if(continues==true) {
			PhieuXuat px=null;
			HopDong hd=new HopDong(maHopDong, xe, ngayLap, maNV, tenNguoiBan, cMNDNB, noiONB, soDTNB,"Chưa xác nhận", maKH, tenNguoiMua, cMNDNM, noiONM, soDTNM, Double.parseDouble(String.valueOf(xe.getDonGia()*soLuong)),soLuong);
			if(rdXN.isSelected()==true) {
				KhachHang kh=QuanLyKhachHang.timMa(Integer.parseInt(hd.getMaKH()));
				px=new PhieuXuat(maHopDong,QuanLyNhanVien.timMa(Integer.parseInt(hd.getMaNV())),kh,hd,ngayLap, xe.getDonGia(),Integer.parseInt(txtSL.getText().toString()), 10);
				hd.setTrangThai("Xác nhận");
			}
			if(QuanLyHopDong.themHopDong(hd)==true) {
				QuanLyPhieuXuat.themPhieuXuat(px);
				xe.setSoLuongLap(xe.getSoLuongLap()-soLuong);
				QuanLyXe.capNhapXe(xe);
				((Node)(e.getSource())).getScene().getWindow().hide();  
			}else {
				thongBaoKieuLoi(e, "Mã hợp đồng đã tồn tại");
			}
		}


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
