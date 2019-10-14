package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextField;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import application.Main;
import dao.QuanLyXe;
import entities.Xe;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
public class ThemXe implements Initializable{
	private double xOffset = 0;
	private double yOffset = 0;
	@FXML public BorderPane mainBd;
	@FXML JFXTextField txtMaXe;
	@FXML JFXTextField txtDonViTinh;
	@FXML JFXTextField txtMoTa;
	@FXML JFXTextField txtNhaSX;
	@FXML ChoiceBox<String> choiceBoxTenXe = new ChoiceBox<String>(); 
	@FXML ChoiceBox<String> choiceBoxLoaiXe = new ChoiceBox<String>(); 
	@FXML ChoiceBox<String> choiceBoxMauXe = new ChoiceBox<String>(); 
	@FXML JFXTextField txtThongTinBaoHanh;
	@FXML Label lblTitle;
	@FXML ImageView img;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		makeStageDrageable();
		String listTayGa[]= {"SH-300c","Vision-110C"};
		choiceBoxTenXe.getItems().add("SH-300c");
		choiceBoxTenXe.getItems().add("Vision-110C");
		choiceBoxTenXe.setValue("SH-300c");

		String listMauTayGaSH[]= {"Trang","Den"};
		String listMauTayGaVision[]= {"Xanh duong","Do","Vang"};
		choiceBoxMauXe.getItems().add("Trang");
		choiceBoxMauXe.getItems().add("Den");
		choiceBoxMauXe.setValue("Den");

		String st[] = { "Xe tay ga", "Xe so"};
		choiceBoxLoaiXe.getItems().add("Xe tay ga");
		choiceBoxLoaiXe.getItems().add("Xe so");
		choiceBoxLoaiXe.setValue("Xe tay ga");

		String listXeSo[]= {"SUPER-CUB","Blade-110C"};
		String listMauXeSoSubCub[]= {"Xanh duong"};
		String listMauXeBlade[]= {"Den","Xanh duong"};
		choiceBoxLoaiXe.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() { 
			public void changed(ObservableValue ov, Number value, Number new_value) 
			{ 
				if(st[new_value.intValue()].contentEquals("Xe tay ga")) {
					choiceBoxTenXe.getItems().clear();
					String listTayGa[]= {"SH-300c","Vision-110C"};
					choiceBoxTenXe.getItems().add("SH-300c");
					choiceBoxTenXe.getItems().add("Vision-110C");
					choiceBoxTenXe.setValue("SH-300c");

					choiceBoxMauXe.getItems().clear();
					String listMauTayGa[]= {"Trang","Den"};
					choiceBoxMauXe.getItems().add("Trang");
					choiceBoxMauXe.getItems().add("Den");
					choiceBoxMauXe.setValue("Den");

					Image image = new Image("/image/SH-300c_den.PNG");
					img.setImage(image);
				}else{
					choiceBoxTenXe.getItems().clear();
					choiceBoxMauXe.getItems().clear();

					choiceBoxTenXe.getItems().add("SUPER-CUB");
					choiceBoxTenXe.getItems().add("Blade-110C");
					choiceBoxTenXe.setValue("SUPER-CUB");


					choiceBoxMauXe.getItems().add("Xanh duong");
					choiceBoxMauXe.setValue("Xanh duong");

					Image image = new Image("/image/SUPER-CUB_XanhDuong.PNG");
					img.setImage(image);

				}
			} 
		}); 
		choiceBoxTenXe.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() { 
			public void changed(ObservableValue ov, Number value, Number new_value) 
			{ 
				if(choiceBoxLoaiXe.getValue().contentEquals("Xe tay ga")) {
					if(listTayGa[new_value.intValue()].contentEquals("SH-300c")) {
						choiceBoxMauXe.getItems().clear();
					}else{
						choiceBoxMauXe.getItems().clear();
						String listMauTayGaVision[]= {"Xanh duong","Do","Vang"};
						choiceBoxMauXe.getItems().add("Xanh Duong");
						choiceBoxMauXe.getItems().add("Do");
						choiceBoxMauXe.getItems().add("Vang");
						choiceBoxMauXe.setValue("Do");
						Image image = new Image("/image/Vision-110C_do.PNG");
						img.setImage(image);
					}
				}else{
					if(listXeSo[new_value.intValue()].contentEquals("Blade-110C")) {
						choiceBoxMauXe.getItems().clear();
						choiceBoxMauXe.getItems().add("Den");
						choiceBoxMauXe.getItems().add("Xanh duong");
						choiceBoxMauXe.setValue("Den");
						Image image = new Image("/image/Blade-110C_den.PNG");
						img.setImage(image);
					}else {
						choiceBoxMauXe.getItems().clear();
						choiceBoxMauXe.getItems().add("Xanh duong");
						choiceBoxMauXe.setValue("Xanh duong");
						Image image = new Image("/image/SUPER-CUB_XanhDuong.PNG");
						img.setImage(image);
					}
				}
			}
		});
		choiceBoxMauXe.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() { 
			public void changed(ObservableValue ov, Number value, Number new_value) 
			{ 
				if(choiceBoxLoaiXe.getValue().contentEquals("Xe tay ga")) {
					if(choiceBoxTenXe.getValue().contentEquals("SH-300c")) {
						if(listMauTayGaSH[new_value.intValue()].contentEquals("Trang")) {
							Image image = new Image("/image/SH-300c_trang.PNG");
							img.setImage(image);
						}else {
							Image image = new Image("/image/SH-300c_den.PNG");
							img.setImage(image);
						}
					}else {
						if(listMauTayGaVision[new_value.intValue()].contentEquals("Xanh duong")) {
							Image image = new Image("/image/Vision-110C_xanhDuong.PNG");
							img.setImage(image);
						}else if(listMauTayGaVision[new_value.intValue()].contentEquals("Do")){
							Image image = new Image("/image/Vision-110C_do.PNG");
							img.setImage(image);
						}else {
							Image image = new Image("/image/Vision-110C_Vang.PNG");
							img.setImage(image);
						}
					}
				}else{
					if(choiceBoxTenXe.getValue().contentEquals("Blade-110C")) {
						if(listMauXeBlade[new_value.intValue()].contentEquals("Den")) {
							Image image = new Image("/image/Blade-110C_den.PNG");
							img.setImage(image);
						}else {
							Image image = new Image("/image/Blade-110C_XanhDuong.PNG");
							img.setImage(image);
						}
					}

				}
			}
		}); 

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
	public void btnCLickOk(ActionEvent e) {
		String maXe=txtMaXe.getText().toString();
		String donViTinh=txtDonViTinh.getText().toString();
		String moTa=txtMoTa.getText().toString();
		String nhaSX=txtNhaSX.getText().toString();
		String tenXe=choiceBoxTenXe.getValue();
		String loaiXe=choiceBoxLoaiXe.getValue();
		String mauXe=choiceBoxMauXe.getValue();
		String thongTinBaoHanh=txtThongTinBaoHanh.getText().toString();
		Xe xe=new Xe(maXe, donViTinh, moTa, nhaSX, tenXe, thongTinBaoHanh, mauXe, loaiXe);
		if(lblTitle.getText().toString().equals("Cập nhập tài khoản")) {
			if(QuanLyXe.capNhapXe(xe)==true) {
				((Node) (e.getSource())).getScene().getWindow().hide();
			}else {
				thongBaoKieuLoi(e, "cập nhập không thành công");
			}
		}else {
			if(QuanLyXe.themXe(xe)==true) {
				System.out.println(xe);
				((Node) (e.getSource())).getScene().getWindow().hide();
			}else {
				thongBaoKieuLoi(e, "thêm không thành công");
			}
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
}
