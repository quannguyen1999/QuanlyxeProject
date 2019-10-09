package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;


import application.Main;
import dao.QuanLyAccount;
import entities.Account;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class QuanLyTaiKhoanController implements Initializable{
	QuanLyAccount ql=new QuanLyAccount();
	@FXML 
	private BorderPane bd;

	private TableView<Account> tbl_view;

	@FXML 
	TextField txtMa;

	TableColumn<Account, String> colUserName;
	TableColumn<Account, String> colPassword;
	TableColumn<Account, String> colLoaiTK;
	public void ThietLapTenMa(String result) {
		txtMa.setText(result);
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tbl_view=new TableView<Account>();
		TableColumn<Account, String> colUserName=new TableColumn<Account, String>("User name");
		TableColumn<Account, String> colPassword=new TableColumn<Account, String>("Password");
		TableColumn<Account, String> colLoaiTK=new TableColumn<Account, String>("Loại tài khoản");

		tbl_view.getColumns().addAll(colUserName,colPassword,colLoaiTK);

		bd.setCenter(tbl_view);

		colUserName.setCellValueFactory(new PropertyValueFactory<>("userName"));
		colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
		colLoaiTK.setCellValueFactory(new PropertyValueFactory<>("loaiTK"));

		UploaderDuLieuLenBang();
	}

	ObservableList<Account> list = null;
	@SuppressWarnings("unused")
	private void UploaderDuLieuLenBang(){
		List<Account> accs=QuanLyAccount.showTatCaAccount();
		accs.forEach(t->{
			//			Account newAccount= new Account(t.getUserName(), t.getPassword(), t.getLoaiTK());
			tbl_view.getItems().add(t);
		});
	}

	private ObservableList<Account> refershhAccList(){
		Account acc=new Account("hope", "success", "asd");
		list=FXCollections.observableArrayList(acc);
		return list;
	}
	private void handleRefersh(ActionEvent e) {
		tbl_view.getItems().clear();
		UploaderDuLieuLenBang();
	}
	public void btnCLoseWindow(ActionEvent e) throws IOException {
		((Node)(e.getSource())).getScene().getWindow().hide();  
	}
	public void btnNhapThongTinTaiKhoan(ActionEvent e){
		try {
			FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/FormThongTinTaiKhoan.fxml"));
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
			// TODO: handle exception
			System.out.println(e2.getMessage());
		}
	}
	public void thongBaoKieuLoi(ActionEvent e,String text) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Dialog");
		alert.setHeaderText(null);
		alert.setContentText(text);
		alert.initOwner(((Node)(e.getSource())).getScene().getWindow());
		alert.showAndWait();
	}
	public void btnXoaTaiKhoan(ActionEvent e) {
		int result=tbl_view.getSelectionModel().getSelectedIndex();
		if(result!=-1) {
			Alert alert = new Alert(AlertType.WARNING, "bạn có chắc muốn xóa",ButtonType.OK,ButtonType.CANCEL);
			alert.setTitle("Cảnh báo");
			Optional<ButtonType> resultx = alert.showAndWait();

			if (resultx.get() == ButtonType.OK) {
				String acc=tbl_view.getItems().get(result).getUserName();
				if(ql.xoaAcc(acc)==true) {
					thongBaoKieuLoi(e,"xóa thành công");
					handleRefersh(e);
				}else {
					thongBaoKieuLoi(e,"lỗi");
				}
			}

		}else {
			thongBaoKieuLoi(e, "bạn chưa chọn bảng cần xóa");
		}
		//		String acc=tbl_view.getItems().get(result).getUserName();
		//		if(ql.xoaAcc(acc)==true) {
		//			thongBaoKieuLoi(e,"xóa thành công");
		//			handleRefersh(e);
		//		}else {
		//			thongBaoKieuLoi(e,"lỗi");
		//		}
	}
	@FXML 
	public void btnTim(ActionEvent e) {
		String text=txtMa.getText().trim().toString();
		if (text.isEmpty()==false) {
			Account acc=QuanLyAccount.timMa(text);
			if(acc!=null) {
				tbl_view.getItems().clear();
				tbl_view.getItems().add(acc);
			}else {
				thongBaoKieuLoi(e, "không tìm thấy");
			}
		}else {
			handleRefersh(e);
		}

	}
	public void btnSuaTaiKhoan(ActionEvent e) throws IOException {
		int result=tbl_view.getSelectionModel().getSelectedIndex();

		if(result!=-1) {
			FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/FormThongTinTaiKhoan.fxml"));

			Parent root=loader.load();

			ThemTaiKhoan ctlMain=loader.getController();

			String userName=tbl_view.getItems().get(result).getUserName();
			String password=tbl_view.getItems().get(result).getPassword();
			String loaiTK=tbl_view.getItems().get(result).getLoaiTK();
			ctlMain.txtUserName.setText(userName);
			ctlMain.txtPassword.setText(password);
			ctlMain.choiceBox.setValue(loaiTK);
			ctlMain.lblTitle.setText("Cập nhập tài khoản");
			ctlMain.txtUserName.setEditable(false);

			Stage stage=new Stage();
			stage.initOwner(((Node)(e.getSource())).getScene().getWindow());
			stage.initStyle(StageStyle.UNDECORATED);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(new Scene(root));
			Main.primaryStage=stage;
			stage.show();
			stage.setOnHidden(ev->{
				handleRefersh(new ActionEvent());
			});
		}else {
			thongBaoKieuLoi(e,"Bạn chưa chọn bảng cần sửa");
		}

	}
	@FXML
	public void btnHuy(ActionEvent e) {
		tbl_view.getSelectionModel().clearSelection();
		txtMa.setText("");
	}

}
