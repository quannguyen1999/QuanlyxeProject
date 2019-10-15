package controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import application.Main;
import dao.QuanLyHopDong;
import dao.QuanLyNhanVien;
import dao.QuanLyXe;
import entities.CTHopDong;
import entities.HopDong;
import entities.NhanVien;
import entities.Xe;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Label;
public class ThemHopDong implements Initializable{
	private double xOffset = 0;
	private double yOffset = 0;
	@FXML Label lblTitle;
	@FXML public BorderPane mainBd;
	@FXML JFXTextField txtMaHD;
	@FXML ComboBox<String> boxMaNV=new ComboBox<String>();
	@FXML JFXTextField txtTenNV;
	@FXML JFXDatePicker txtNgayLap;
	@FXML JFXTextField txtTienDat;
	@FXML JFXTextField txtTienPhai;
	@FXML JFXTextField txtCMND;
	@FXML JFXTextField txtDiaChi;
	@FXML JFXTextField txtSoDT;
	@FXML JFXTextField txtTenKH;
	@FXML JFXButton btnThem;
	@FXML JFXButton btnXoaRong;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		makeStageDrageable();

		//box MaNV
		boxMaNV.setEditable(true);
		ObservableList<String> items0 = FXCollections.observableArrayList();
		List<NhanVien> kh0=QuanLyNhanVien.showTatCaNhanVien();
		kh0.forEach(t->{
			items0.add(String.valueOf(t.getMaNV()));
		});
		FilteredList<String> filteredItems0 = new FilteredList<String>(items0);
		boxMaNV.getEditor().textProperty().addListener(new InputFilter(boxMaNV, filteredItems0, false));
		boxMaNV.setItems(filteredItems0);
		
		boxMaNV.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() { 
			public void changed(ObservableValue ov, Number value, Number new_value) 
			{ 
				NhanVien nv=QuanLyNhanVien.timMa(Integer.parseInt(boxMaNV.getValue()));
				if(nv!=null) {
					txtTenNV.setText(nv.getTenNV());
				}
			}
		});
		
		txtNgayLap.setEditable(false);
	}
	public void thongBaoKieuLoi(ActionEvent e, String text) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Dialog");
		alert.setHeaderText(null);
		alert.setContentText(text);
		alert.initOwner(((Node) (e.getSource())).getScene().getWindow());
		alert.showAndWait();
	}
	@FXML
	public void btnThemHopDong(ActionEvent e) {
		int maHD=Integer.parseInt(txtMaHD.getText().toString());
		int maNV=Integer.parseInt(boxMaNV.getValue());
		String tenNV=txtTenNV.getText().toString();
		LocalDate ngayLap=txtNgayLap.getValue();
		Double tienDat=Double.parseDouble(txtTienDat.getText().toString());
		Double tienPhai=Double.parseDouble(txtTienPhai.getText().toString());
		String CMND=txtCMND.getText().toString();
		String diaChi=txtDiaChi.getText().toString();
		String soDT=txtSoDT.getText().toString();
		String tenKH=txtTenKH.getText().toString();
		NhanVien nv=QuanLyNhanVien.timMa(maNV);
		if(nv!=null) {
			HopDong hd=new HopDong(maHD, ngayLap, tienDat, tienPhai, nv);
			if(QuanLyHopDong.themHopDong(hd)==true) {
				CTHopDong ct=new CTHopDong(CMND, diaChi, soDT, tenKH, hd);
				QuanLyHopDong.themChiTietHopDong(ct);
				((Node)(e.getSource())).getScene().getWindow().hide();  
			}else {
				thongBaoKieuLoi(e, "Thêm hợp đồng không thành công");
			}
		}else {
			thongBaoKieuLoi(e, "nhân viên không tồn tại");
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
