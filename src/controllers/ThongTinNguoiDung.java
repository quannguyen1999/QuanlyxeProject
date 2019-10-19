package controllers;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import dao.QuanLyNhanVien;
import entities.NhanVien;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.control.Label;
public class ThongTinNguoiDung implements Initializable{
	private double xOffset = 0;
	private double yOffset = 0;
	@FXML BorderPane bd;
	@FXML Label lblHoTen;
	@FXML Label lblChucVu;
	@FXML Label lblDiaChi;
	@FXML Label lblDT;
	@FXML Label lblGioiTinh;
	@FXML Label lblUsername;
	@FXML ImageView img;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

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
	public void thietLapFormNguoiDung(NhanVien nv) {
		lblHoTen.setText(nv.getTenNV());
		lblChucVu.setText(nv.getChucVu());
		lblDiaChi.setText(nv.getDiaChi());
		lblDT.setText(nv.getDienThoai());;
		lblGioiTinh.setText(nv.getGioiTinh());
		lblUsername.setText(nv.getAccount().getUserName());
	}
	public void btnDoiMatKhau(ActionEvent e) throws IOException {
		FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/FormDoiMatKhau.fxml"));
		Parent root=loader.load();
		DoiMatKhau ctlThongTin=loader.getController();
		ctlThongTin.thietLapTenNguoiDung(lblUsername.getText().toString());
		Stage stage=new Stage();
		stage.initOwner(((Node)(e.getSource())).getScene().getWindow());
		stage.initStyle(StageStyle.UNDECORATED);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setScene(new Scene(root));
		stage.getIcons().add(new Image("/image/logo.PNG"));
		Main.primaryStage=stage;
		stage.show();
	}
	public void btnCLoseWindow(ActionEvent e) throws IOException {
		((Node)(e.getSource())).getScene().getWindow().hide();  
	}

}
