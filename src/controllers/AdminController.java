package controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import application.Main;
import dao.QuanLyAccount;
import dao.QuanLyNhanVien;
import entities.Account;
import entities.NhanVien;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AdminController implements Initializable{
	QuanLyNhanVien qlNV=new QuanLyNhanVien();
	QuanLyAccount qlACC=new QuanLyAccount();
	@FXML BorderPane bd;
	@FXML MenuBar mnb;
	@FXML Label lblLogin;
	@FXML JFXButton styleQuanLyTaiKhoan;
	@FXML JFXButton styleQuanLyNhanVien;
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
	public void initialize(URL arg0, ResourceBundle arg1) {
//		qlNV.showTatCaNhanVien();
//		qlACC.showTatCaAccount();
		try {
			Parent root=(Parent) FXMLLoader.load(getClass().getResource("/fxml/Welcome.fxml"));
			bd.setCenter(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void ThietLapTenNguoiDangNhap(String tenLogin) {
		lblLogin.setText(tenLogin);
	}
	public void handleExit(ActionEvent e) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setHeaderText(null);
		alert.setContentText("bạn có muốn thoát?");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			System.exit(0);
		} else {
		}
	}
	public void btnDangXuat(ActionEvent e) throws IOException {
		((Node)(e.getSource())).getScene().getWindow().hide();  
		Stage primaryStage=new Stage();
		Parent root=(Parent) FXMLLoader.load(getClass().getResource("/fxml/FormLogin.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.initStyle(StageStyle.UNDECORATED);
		Main.primaryStage=primaryStage;
		primaryStage.getIcons().add(new Image("/image/logo.PNG"));
		primaryStage.show();
	}
	public void btnCLoseWindow(ActionEvent e) throws IOException {
		((Node)(e.getSource())).getScene().getWindow().hide();  
	}
	public void resetColorCLickQuanLy() {
		styleQuanLyNhanVien.setStyle("-fx-background-color: transparent");
		styleQuanLyTaiKhoan.setStyle("-fx-background-color: transparent");
	}
	public void btnQuanLyNhanVien(ActionEvent e) throws IOException {
		resetColorCLickQuanLy();
		styleQuanLyNhanVien.setStyle("-fx-background-color: rgb(23,35,51)");
		Parent root=(Parent) FXMLLoader.load(getClass().getResource("/fxml/QuanLyNhanVien.fxml"));
		bd.setCenter(root);
	}
	public void btnQuanLyTaiKhoan(ActionEvent e) throws IOException {
		resetColorCLickQuanLy();
		styleQuanLyTaiKhoan.setStyle("-fx-background-color: rgb(23,35,51)");
		Parent root=(Parent) FXMLLoader.load(getClass().getResource("/fxml/QuanLyTaiKhoan2.fxml"));
		bd.setCenter(root);
	}
	public void btnHideWindow(ActionEvent e) {
		Stage stage=(Stage) ((Node)(e.getSource())).getScene().getWindow();  
		stage.setIconified(true);
	}
	public void btnQuayLai(ActionEvent e) {
		
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
	public void btnThongTinNguoiDung(ActionEvent e) throws IOException {
		try {

			NhanVien nv=QuanLyNhanVien.timMa2(lblLogin.getText().toString());
			if(nv!=null) {
				FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/FormUser.fxml"));
				Parent root=loader.load();
				ThongTinNguoiDung ctlThongTin=loader.getController();
				ctlThongTin.thietLapFormNguoiDung(nv);
				File currentDirFile = new File("");
				String helper = currentDirFile.getAbsolutePath();
				String begin=kiemTraChuoi(helper);
				System.out.println("file:///"+begin+"/"+nv.getHinhAnh());
				Image image = new Image("file:///"+begin+"/src/"+nv.getHinhAnh());
				ctlThongTin.img.setImage(image);
				Stage stage=new Stage();
				Stage stageCunrrent = (Stage) mnb.getScene().getWindow();
				stage.initOwner(stageCunrrent);
				stage.initStyle(StageStyle.UNDECORATED);
				stage.initModality(Modality.APPLICATION_MODAL);
				stage.setScene(new Scene(root));
				stage.getIcons().add(new Image("/image/logo.PNG"));
				Main.primaryStage=stage;
				stage.show();
			}else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information Dialog");
				alert.setHeaderText(null);
				alert.setContentText("tài khoản chưa cấp thông tin nhân viên");
				alert.initOwner((Stage) mnb.getScene().getWindow());
				alert.showAndWait();
			}

		} catch (Exception e2) {
			// TODO: handle exception
			System.out.println(e2.getMessage());
		}
	}

}
