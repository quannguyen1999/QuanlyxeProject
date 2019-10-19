package application;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

import com.sun.javafx.application.LauncherImpl;

import controllers.KeToanController;
import dao.QuanLyAccount;
import dao.QuanLyHopDong;
import dao.QuanLyKhachHang;
import dao.QuanLyLoaiXe;
import dao.QuanLyNhanVien;
import dao.QuanLyPhieuXuat;
import dao.QuanLyXe;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Parent;
import javafx.scene.Scene;
public class Main extends Application {
	@Override
	public void init() throws Exception {
		QuanLyLoaiXe.showTatCaLoaiXe();
		QuanLyXe.showTatCaXe();
		QuanLyKhachHang.showTatCaKhachHang();
		QuanLyPhieuXuat.showTatCaPhieuXuat();
		QuanLyHopDong.showTatCaHopDong();
		QuanLyAccount.showTatCaAccount();
		QuanLyNhanVien.showTatCaNhanVien();
		QuanLyHopDong.showTatCaHopDong();
	}
	public static Stage primaryStage=null;
	public void start(Stage primaryStage) {
		try {
			Parent root=(Parent) FXMLLoader.load(getClass().getResource("/fxml/FormLogin.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.initStyle(StageStyle.UNDECORATED);
			primaryStage.setResizable(true);
			Main.primaryStage=primaryStage;
			primaryStage.getIcons().add(new Image("/image/logo.PNG"));
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	@SuppressWarnings("restriction")
	public static void main(String[] args) {
		//chú ý chạy uis trước
		LauncherImpl.launchApplication(Main.class, Loading.class, args);
	}
}
