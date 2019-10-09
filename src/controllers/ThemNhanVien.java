package controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;

import application.Main;
import dao.QuanLyAccount;
import dao.QuanLyNhanVien;
import entities.Account;
import entities.NhanVien;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

public class ThemNhanVien implements Initializable{
	private double xOffset = 0;
	private double yOffset = 0;
	QuanLyAccount ql=new QuanLyAccount();
	QuanLyNhanVien qlNV=new QuanLyNhanVien();
	@FXML public BorderPane mainBd;
	@FXML JFXTextField txtMa;
	@FXML JFXTextField txtTen;
	@FXML JFXDatePicker txtNamSinh;
	@FXML JFXTextField txtDiaChi;
	@FXML ComboBox<String> box;
	@FXML JFXTextField txtDienThoai;
	@FXML JFXTextField txtLuong;
	@FXML JFXRadioButton rdNam;
	@FXML JFXRadioButton rdNu;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		makeStageDrageable();
		box.setEditable(true);
		loadDuLieu();
	}

	public  void loadDuLieu() {
		ObservableList<String> items = FXCollections.observableArrayList();

		List<Account> accs=QuanLyAccount.showTatCaAccount();
		accs.forEach(t->{
			Account newAccount= new Account(t.getUserName(), t.getPassword(), t.getLoaiTK());
			items.add(newAccount.getUserName());
		});
		FilteredList<String> filteredItems = new FilteredList<String>(items);

		box.getEditor().textProperty().addListener(new InputFilter(box, filteredItems, false));

		box.setItems(filteredItems);

	}
	public void btnCLoseWindow(ActionEvent e) throws IOException {
		((Node)(e.getSource())).getScene().getWindow().hide();  
	}
	public void thongBaoKieuLoi(ActionEvent e, String text) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Dialog");
		alert.setHeaderText(null);
		alert.setContentText(text);
		alert.initOwner(((Node) (e.getSource())).getScene().getWindow());
		alert.showAndWait();
	}
	public void btnThem(ActionEvent e) throws IOException {
		try {
			Account acc=ql.timMa(box.getValue());
			if(acc!=null) {
				String textMa=txtMa.getText().toString();
				String textTen=txtTen.getText().toString();
				String textNamSinh=txtNamSinh.getValue().toString();
				String textDiaChi= txtDiaChi.getText().toString();
				String textDienThoai=txtDienThoai.getText().toString();
				String textLuong=txtLuong.getText().toString();
				if(textMa.isEmpty()==false
						&& textTen.isEmpty()==false
						&& textNamSinh.isEmpty()==false 
						&& textDiaChi.isEmpty()==false
						&& textDienThoai.isEmpty()==false 
						&& textLuong.isEmpty()==false) {
					NhanVien nv=null;
					if(rdNam.isSelected()==true) {
						nv=new NhanVien(Integer.parseInt(textMa), acc.getLoaiTK(), textDiaChi,textDienThoai,rdNam.getText().toString(),Double.parseDouble(textLuong),txtNamSinh.getValue(),textTen,acc);
					}else {
						nv=new NhanVien(Integer.parseInt(textMa), acc.getLoaiTK(), textDiaChi,textDienThoai,"Nu",Double.parseDouble(textLuong),txtNamSinh.getValue(),textTen,acc);
					}
					
					if(nv!=null) {
						int result=qlNV.themNV(nv);
						if(result==-1) {
							thongBaoKieuLoi(e, "usename đã có người sử dụng");
						}else if(result==0) {
							thongBaoKieuLoi(e, "bị trùng mã");
						}else {
							((Node) (e.getSource())).getScene().getWindow().hide();
						}
					}else {
						thongBaoKieuLoi(e, "thêm không thành công");
					}
					
				}else {
					thongBaoKieuLoi(e, "yêu cầu nhập đầy đủ và hợp lệ");
				}
			}else {
				thongBaoKieuLoi(e, "username không tồn tại");
			}
		} catch (Exception e2) {
			System.out.println(e2.getMessage());
		}
	}
	@FXML
	public void btnXoaRong(ActionEvent e) {
		txtMa.setText("");
		txtTen.setText("");
		box.setValue("");
		txtNamSinh.setValue(LocalDate.of(2000,1,1));
		txtDiaChi.setText("");
		txtDienThoai.setText("");
		txtLuong.setText("");
		rdNam.setSelected(true);
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
