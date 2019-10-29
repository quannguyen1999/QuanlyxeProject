package controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.sun.javafx.application.LauncherImpl;

import application.Main;
import application.Loading;
import dao.QuanLyHopDong;
import dao.QuanLyKhachHang;
import dao.QuanLyLoaiXe;
import dao.QuanLyNhanVien;
import dao.QuanLyPhieuXuat;
import dao.QuanLyXe;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class KeToanController implements Initializable{
	String userName="";
	@FXML public BorderPane mainBd;
	@FXML Label lblLogin;
	@FXML MenuBar mnb;
	@FXML JFXButton styleQuanLyLoaiXe;
	@FXML JFXButton styleQuanLyXe;
	@FXML JFXButton styleQuanLyKhachHang;
	@FXML JFXButton styleQuanLyXuatHang;
	@FXML JFXButton styleQuanLyHopDong;
	@FXML JFXButton styleQuanLyBaoCao;

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
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		try {
			Parent root=(Parent) FXMLLoader.load(getClass().getResource("/fxml/Welcome.fxml"));
			mainBd.setCenter(root);

		} catch (IOException e) {
			// TODO Auto-generated catch block
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
			// ... user chose CANCEL or closed the dialog
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
	public void thongBaoKieuLoi(ActionEvent e, String text) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Dialog");
		alert.setHeaderText(null);
		alert.setContentText(text);
		alert.initOwner(((Node) (e.getSource())).getScene().getWindow());
		alert.showAndWait();
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

			NhanVien nv=QuanLyNhanVien.timMa2(userName);
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
				Main.primaryStage=stage;
				stage.getIcons().add(new Image("/image/logo.PNG"));
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
	public void resetColorCLickQuanLy() {
		styleQuanLyLoaiXe.setStyle("-fx-background-color: transparent");
		styleQuanLyXe.setStyle("-fx-background-color: transparent");
		styleQuanLyKhachHang.setStyle("-fx-background-color: transparent");
		styleQuanLyXuatHang.setStyle("-fx-background-color: transparent");
		styleQuanLyHopDong.setStyle("-fx-background-color: transparent");
		styleQuanLyBaoCao.setStyle("-fx-background-color: transparent");
	}
	public void btnQuanLyXe(ActionEvent e) throws IOException {
		resetColorCLickQuanLy();
		styleQuanLyXe.setStyle("-fx-background-color: rgb(23,35,51)");
		Parent root=(Parent) FXMLLoader.load(getClass().getResource("/fxml/QuanLyXe.fxml"));
		mainBd.setCenter(root);
	}
	public void btnQuanLyKhachHang(ActionEvent e) throws IOException {
		resetColorCLickQuanLy();
		styleQuanLyKhachHang.setStyle("-fx-background-color: rgb(23,35,51)");
		Parent root=(Parent) FXMLLoader.load(getClass().getResource("/fxml/QuanLyKhachHang.fxml"));
		mainBd.setCenter(root);
	}
	public void btnQuanLyXuatHang(ActionEvent e) throws IOException {
		resetColorCLickQuanLy();
		styleQuanLyXuatHang.setStyle("-fx-background-color: rgb(23,35,51)");
		Parent root=(Parent) FXMLLoader.load(getClass().getResource("/fxml/QuanLyXuatHang.fxml"));
		mainBd.setCenter(root);
	}
	public void btnQuanLyHopDong(ActionEvent e) throws IOException {
		resetColorCLickQuanLy();
		styleQuanLyHopDong.setStyle("-fx-background-color: rgb(23,35,51)");
		Parent root=(Parent) FXMLLoader.load(getClass().getResource("/fxml/QuanLyHopDong.fxml"));
		FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/QuanLyHopDong.fxml"));
		root=loader.load();
//		NhanVien nv=QuanLyNhanVien.timMa2(userName);
		QuanLyHopDongController ctlMain=loader.getController();
		ctlMain.UserName=userName;
		mainBd.setCenter(root);
	}
	public void btnBaoCaoThongKe(ActionEvent e) throws IOException {
		resetColorCLickQuanLy();
		styleQuanLyBaoCao.setStyle("-fx-background-color: rgb(23,35,51)");
		Parent root=(Parent) FXMLLoader.load(getClass().getResource("/fxml/BaoCaoThongKe.fxml"));
		mainBd.setCenter(root);
	}
	public void btnQuanLyLoaiXe(ActionEvent e) throws IOException {
		resetColorCLickQuanLy();
		styleQuanLyLoaiXe.setStyle("-fx-background-color: rgb(23,35,51)");
		Parent root=(Parent) FXMLLoader.load(getClass().getResource("/fxml/QuanLyLoaiXe.fxml"));
		mainBd.setCenter(root);
	}



	public void btnHideWindow(ActionEvent e) {
		Stage stage=(Stage) ((Node)(e.getSource())).getScene().getWindow();  
		stage.setIconified(true);
	}

}
