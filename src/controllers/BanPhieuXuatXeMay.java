package controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import entities.HopDong;
import entities.PhieuXuat;
import entities.Xe;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

public class BanPhieuXuatXeMay implements Initializable{
//	public HopDong(int maHopDong, Xe xe, LocalDate ngayLap, String maNV, String tenNguoiBan, String cMNDNB,
//			String noiONB, String soDTNB, String trangThai, String maKH, String tenNguoiMua, String cMNDNM,
//			String noiONM, String soDTNM, Double tienDatThanhToan) {
	@FXML BorderPane bd;
	TableView<PhieuXuat> tbl_view;
	@FXML TableView<String> tblTT=new TableView<String>();
	TableColumn<PhieuXuat, String> colMaPX;
	TableColumn<PhieuXuat, String> colSLXuat;
	TableColumn<PhieuXuat, String> colDonGiaXuat;
	
	@FXML Label txtHoTenNV;
	@FXML Label txtNgay;
	@FXML Label txtThang;
	@FXML Label txtNam;
	@FXML Label txtHoTenKH;
	@FXML Label txtSDT;
	@FXML Label txtDiaChi;
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		tbl_view=new TableView<PhieuXuat>();
		colMaPX=new TableColumn<PhieuXuat, String>("Mã phiếu xuất");
		colSLXuat=new TableColumn<PhieuXuat, String>("Số lượng xuất");
		colDonGiaXuat=new TableColumn<PhieuXuat, String>("Đơn giá xuất");

		tbl_view.getColumns().addAll(colMaPX,colSLXuat,colDonGiaXuat);

		bd.setCenter(tbl_view);

		
		colMaPX.setCellValueFactory(new PropertyValueFactory<>("maPX"));
		
		colSLXuat.setCellValueFactory(cellData -> 
		new SimpleStringProperty(String.valueOf(cellData.getValue().getsLXuat())));

		colDonGiaXuat.setCellValueFactory(cellData -> 
		new SimpleStringProperty(String.format("%.0f",cellData.getValue().getDonGiaXuat())));
		
		colMaPX.setMinWidth(140);
		colSLXuat.setMinWidth(140);
		colDonGiaXuat.setMinWidth(140);
		
		tblTT.getItems().add("asd");
		
		
	}

}
