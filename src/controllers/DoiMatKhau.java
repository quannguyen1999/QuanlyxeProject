package controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import application.Main;
import dao.QuanLyAccount;
import dao.QuanLyNhanVien;
import entities.Account;
import entities.NhanVien;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
public class DoiMatKhau implements Initializable{
	@FXML Label lblUsername;
	@FXML JFXPasswordField txtPassOld;
	@FXML JFXPasswordField txtPassNew;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}
	public void thongBaoKieuLoi(ActionEvent e, String text) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Dialog");
		alert.setHeaderText(null);
		alert.setContentText(text);
		alert.initOwner(((Node) (e.getSource())).getScene().getWindow());
		alert.showAndWait();
	}
	public void btnThayDoi(ActionEvent e) {
		try {
			String passOld=txtPassOld.getText().toString();
			String passNew=txtPassNew.getText().toString();
			NhanVien nv=QuanLyNhanVien.timMa2(lblUsername.getText().toString());
			Account acc=QuanLyAccount.timMa(nv.getAccount().getUserName());

			if(acc.getPassword().equals(passOld)) {
				if(passNew.isEmpty()==false) {
					Account acc2=new Account(lblUsername.getText().toString(), passNew, nv.getChucVu());
					if(QuanLyAccount.suaAcc(acc2)==true) {
						thongBaoKieuLoi(e, "Sửa thành công");
						txtPassOld.setText("");
						txtPassNew.setText("");
					}else{
						thongBaoKieuLoi(e, "Sửa không thành công");
					};
					
				}else {
					thongBaoKieuLoi(e, "không được để rỗng");
					txtPassNew.requestFocus();
				}
			}else {
				thongBaoKieuLoi(e, "mật khẩu cũ không chính xác");
			}
		} catch (Exception e2) {
			// TODO: handle exception
			System.out.println(e2.getMessage());
		}

	}
	public void btnXoaRong(ActionEvent e) {
		txtPassOld.setText("");
		txtPassNew.setText("");
	}
	public void thietLapTenNguoiDung(String userName) {
		lblUsername.setText(userName);
	}

	public void btnQuayLai(ActionEvent e) throws IOException {
		((Node)(e.getSource())).getScene().getWindow().hide();  
		FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/FormUser.fxml"));
		Parent root=loader.load();
		ThongTinNguoiDung ctlThongTin=loader.getController();
		NhanVien nv=QuanLyNhanVien.timMa2(lblUsername.getText().toString());
		ctlThongTin.thietLapFormNguoiDung(nv);
		Stage stage=new Stage();
		stage.initStyle(StageStyle.UNDECORATED);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setScene(new Scene(root));
		Main.primaryStage=stage;
		stage.show();
	}

}
