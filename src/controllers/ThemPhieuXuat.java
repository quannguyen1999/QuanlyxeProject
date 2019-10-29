package controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import application.Main;
import dao.QuanLyAccount;
import dao.QuanLyHopDong;
import dao.QuanLyKhachHang;
import dao.QuanLyLoaiXe;
import dao.QuanLyNhanVien;
import dao.QuanLyPhieuXuat;
import dao.QuanLyXe;
import entities.Account;
import entities.HopDong;
import entities.KhachHang;
import entities.Loaixe;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ComboBoxBase;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
public class ThemPhieuXuat implements Initializable{
	@FXML Label lblTitle;
	@FXML ImageView img;
	@FXML ComboBox<String> boxMaXe;
	@FXML ComboBox<String> boxMaNV;
	@FXML ComboBox<String> boxMaKH;
	@FXML ComboBox<String> boxMaHD;
	@FXML Label lblTenXe;
	@FXML Label lblMauXe;
	@FXML Label lblLoaiXe;
	@FXML Label lblNhaSX;

	@FXML JFXTextField txtPX;
	@FXML JFXDatePicker txtNgayXuat;
	@FXML JFXTextField txtDonGiaXuat;
	@FXML JFXTextField txtSoLuongXuat;
	private double x, y;
	@FXML
	private void draged(MouseEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setX(event.getScreenX() - x);
		stage.setY(event.getScreenY() - y);
	}

	@FXML
	private void pressed(MouseEvent event) {
		x = event.getSceneX();
		y = event.getSceneY();
	}
	public void thongBaoKieuLoi(ActionEvent e, String text) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Dialog");
		alert.setHeaderText(null);
		alert.setContentText(text);
		alert.initOwner(((Node) (e.getSource())).getScene().getWindow());
		alert.showAndWait();
	}
	private void handleRefersh(ActionEvent e) {
		try {
			btnXoaRong(e);
			ObservableList<String> items1 = FXCollections.observableArrayList();
			List<KhachHang> kh1=QuanLyKhachHang.showTatCaKhachHang();
			kh1.forEach(t->{
				items1.add(String.valueOf(t.getMaKH()));
			});
			FilteredList<String> filteredItems1 = new FilteredList<String>(items1);
			boxMaKH.getEditor().textProperty().addListener(new InputFilter(boxMaKH, filteredItems1, false));
			boxMaKH.setItems(filteredItems1);

		} catch (Exception e2) {
			e2.printStackTrace();
		}

	}
	private void resetMaKH() {
		boxMaKH.getItems().clear();
		boxMaKH.setEditable(true);
		ObservableList<String> items1 = FXCollections.observableArrayList();
		List<KhachHang> kh1=QuanLyKhachHang.showTatCaKhachHang();
		kh1.forEach(t->{
			items1.add(String.valueOf(t.getMaKH()));
		});
		FilteredList<String> filteredItems1 = new FilteredList<String>(items1);
		boxMaKH.getEditor().textProperty().addListener(new InputFilter(boxMaKH, filteredItems1, false));
		boxMaKH.setItems(filteredItems1);
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
			Main.primaryStage=stage;
			stage.setOnHidden(ev->{
				handleRefersh(e);
			});
		} catch (Exception e2) {
			System.out.println(e2.getMessage());
		}
	}
	public boolean kiemTraMaPhieuXuat(ActionEvent e,String text) {
		String MaKT=text.trim();
		if(MaKT.isEmpty()==false) {
			return true;
		}else {
			thongBaoKieuLoi(e, "mã phiếu xuất không được để trống");
			return false;
		}
	}
	@FXML void btnClickOK(ActionEvent e) {
	}
	@FXML 
	private void btnXoaRong(ActionEvent e) {
		txtPX.setText("");
		boxMaNV.setValue("");
		boxMaKH.setValue("");
		txtNgayXuat.setValue(LocalDate.of(2000, 11, 1));
		boxMaXe.setValue("");
		txtDonGiaXuat.setText("");
		txtSoLuongXuat.setText("");

		lblTenXe.setText("");
		lblMauXe.setText("");
		lblLoaiXe.setText("");
		lblNhaSX.setText("");
		Image image = new Image("/image/Blade-110C_den.PNG");
		img.setImage(image);

	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		txtNgayXuat.setEditable(false);
		//box MaNV
		boxMaNV.setEditable(false);
		boxMaNV.setDisable(true);
		//		ObservableList<String> items0 = FXCollections.observableArrayList();
		//		List<NhanVien> kh0=QuanLyNhanVien.showTatCaNhanVien();
		//		kh0.forEach(t->{
		//			items0.add(String.valueOf(t.getMaNV()));
		//		});
		//		FilteredList<String> filteredItems0 = new FilteredList<String>(items0);
		//		boxMaNV.getEditor().textProperty().addListener(new InputFilter(boxMaNV, filteredItems0, false));
		//		boxMaNV.setItems(filteredItems0);
		//
		//		//box MaKH
		//		boxMaKH.setEditable(true);
		//		ObservableList<String> items1 = FXCollections.observableArrayList();
		//		List<KhachHang> kh1=QuanLyKhachHang.showTatCaKhachHang();
		//		kh1.forEach(t->{
		//			items1.add(String.valueOf(t.getMaKH()));
		//		});
		//		FilteredList<String> filteredItems1 = new FilteredList<String>(items1);
		//		boxMaKH.getEditor().textProperty().addListener(new InputFilter(boxMaKH, filteredItems1, false));
		//		boxMaKH.setItems(filteredItems1);


		//filter ma xe
		boxMaKH.setEditable(false);
		boxMaKH.setDisable(true);
		ObservableList<String> items = FXCollections.observableArrayList();

		List<Xe> accs=QuanLyXe.showTatCaXe();
		accs.forEach(t->{
			items.add(t.getMaXe());
		});
		FilteredList<String> filteredItems = new FilteredList<String>(items);

		boxMaXe.getEditor().textProperty().addListener(new InputFilter(boxMaXe, filteredItems, false));

		boxMaXe.setItems(filteredItems);

		boxMaXe.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() { 
			public void changed(ObservableValue ov, Number value, Number new_value) 
			{ 
				Xe xe=QuanLyXe.timMa(items.get((int) new_value).toString());
				Loaixe lx=QuanLyLoaiXe.timMa(xe.getLx().getMaloai());
				System.out.println(lx);
				lblTenXe.setText(xe.getLx().getTenxe());
				lblMauXe.setText(xe.getLx().getMauson());
				lblNhaSX.setText(xe.getLx().getNuocSX());
				lblLoaiXe.setText(xe.getLx().getLoaixe());
				File currentDirFile = new File("");
				String helper = currentDirFile.getAbsolutePath();
				String begin=kiemTraChuoi(helper);
				System.out.println("file:///"+begin+"/"+xe.getLx().getHinhanh());
				Image image = new Image("file:///"+begin+"/src/"+xe.getLx().getHinhanh());
				img.setImage(image);
			}
		});

		//filter hd 
		boxMaHD.setEditable(true);
		ObservableList<String> itemHD = FXCollections.observableArrayList();

		List<HopDong> listHD=QuanLyHopDong.showTatCaHopDong();
		listHD.forEach(t->{
			itemHD.add(String.valueOf(t.getMaHopDong()));
		});
		FilteredList<String> filteredItemsHD = new FilteredList<String>(itemHD);

		boxMaHD.getEditor().textProperty().addListener(new InputFilter(boxMaHD, filteredItemsHD, false));

		boxMaHD.setItems(filteredItemsHD);

		boxMaHD.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() { 
			public void changed(ObservableValue ov, Number value, Number new_value) 
			{ 
				int textTim=Integer.parseInt(itemHD.get((int)new_value));
				HopDong qlhd=QuanLyHopDong.timMaHopDong(textTim);
				boxMaNV.getItems().add(qlhd.getMaNV());
				boxMaNV.setValue(qlhd.getMaNV());
				boxMaKH.getItems().add(qlhd.getMaKH());
				boxMaKH.setValue(qlhd.getMaKH());
			}
		});

	}
	private static String kiemTraChuoi(String text) {
		String newTextResult="";
		for(int i=0;i<=text.length()-1;i++) {
			if((int)text.charAt(i)==92) {
				newTextResult+="/";
			}else {
				newTextResult+=text.charAt(i);
			}
		}
		return newTextResult;
	}
	public void btnCLoseWindow(ActionEvent e) throws IOException {
		((Node) (e.getSource())).getScene().getWindow().hide();
	}

}
