package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import dao.QuanLyAccount;
import dao.QuanLyPhieuXuat;
import entities.Account;
import entities.PhieuXuat;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class QuanLyXuatHangController implements Initializable{
	private TableView<PhieuXuat> tbl_view;
	TableColumn<PhieuXuat, String> colMaPX;
	TableColumn<PhieuXuat, String> colNgayXuat;
	TableColumn<PhieuXuat, String> colMaKH;
	TableColumn<PhieuXuat, String> colMaNV;
	@FXML 
	private BorderPane bd;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		tbl_view=new TableView<PhieuXuat>();
		colMaPX=new TableColumn<PhieuXuat, String>("Mã phiếu xuất");
		colNgayXuat=new TableColumn<PhieuXuat, String>("Ngày xuất");
		colMaKH=new TableColumn<PhieuXuat, String>("Mã khách hàng");
		colMaNV=new TableColumn<PhieuXuat, String>("Mã nhân viên");

		tbl_view.getColumns().addAll(colMaPX,colMaKH,colMaNV,colNgayXuat);

		bd.setCenter(tbl_view);

		colMaPX.setCellValueFactory(new PropertyValueFactory<>("maPX"));
		colNgayXuat.setCellValueFactory(new PropertyValueFactory<>("ngayXuat"));
		colMaKH.setCellValueFactory(new PropertyValueFactory<>("khachHang"));
		colMaNV.setCellValueFactory(new PropertyValueFactory<>("nhanVien"));
		
		UploaderDuLieuLenBang();
	}
	@SuppressWarnings("unused")
	private void UploaderDuLieuLenBang(){
		List<PhieuXuat> accs=QuanLyPhieuXuat.showTatCaPhieuXuat();
		accs.forEach(t->{
			//			Account newAccount= new Account(t.getUserName(), t.getPassword(), t.getLoaiTK());
			tbl_view.getItems().add(t);
		});
	}
	public void btnNhapThongTinXuatHang(ActionEvent e) throws IOException {
		try {
			Stage primaryStage=new Stage();
			Parent root=(Parent) FXMLLoader.load(getClass().getResource("/fxml/FormThongTinPhieuXuat.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.initStyle(StageStyle.UNDECORATED);
			primaryStage.setAlwaysOnTop(true);
			Main.primaryStage=primaryStage;
			primaryStage.showAndWait();
		} catch (Exception e2) {
			// TODO: handle exception
			System.out.println(e2.getMessage());
		}
	}
	
	
}
