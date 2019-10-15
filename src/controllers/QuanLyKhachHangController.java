package controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import application.Main;
import dao.QuanLyAccount;
import dao.QuanLyKhachHang;
import dao.QuanLyXe;
import entities.Account;
import entities.KhachHang;
import entities.NhanVien;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class QuanLyKhachHangController implements Initializable{

	//	private int maKH;
	//	private String diaChi;
	//	private String email;
	//	private String soDT;
	//	private String tenKH;

	@FXML 
	private BorderPane bd;

	private TableView<KhachHang> tbl_view;

	TableColumn<KhachHang, String> colMaKH;
	TableColumn<KhachHang, String> colDiaChi;
	TableColumn<KhachHang, String> colEmail;
	TableColumn<KhachHang, String> colSoDT;
	TableColumn<KhachHang, String> colTenKH;

	@FXML 
	TextField txtMa;

	@FXML JFXButton btnThem;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		tbl_view=new TableView<KhachHang>();
		colMaKH=new TableColumn<KhachHang, String>("Mã KH");
		colDiaChi=new TableColumn<KhachHang, String>("Địa chỉ");
		colEmail=new TableColumn<KhachHang, String>("Email");
		colSoDT=new TableColumn<KhachHang, String>("Số điện thoại");
		colTenKH=new TableColumn<KhachHang, String>("Tên KH");

		tbl_view.getColumns().addAll(colMaKH,colDiaChi,colEmail,colSoDT,colTenKH);

		bd.setCenter(tbl_view);

		colMaKH.setCellValueFactory(new PropertyValueFactory<>("maKH"));
		colDiaChi.setCellValueFactory(new PropertyValueFactory<>("diaChi"));
		colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
		colSoDT.setCellValueFactory(new PropertyValueFactory<>("soDT"));
		colTenKH.setCellValueFactory(new PropertyValueFactory<>("tenKH"));

		UploaderDuLieuLenBang();

		tbl_view.setOnMouseClicked(e->{
			if(e.getClickCount()==2) {
				int result=tbl_view.getSelectionModel().getSelectedIndex();
				if(result!=-1) {
					FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/FormThongTinKhachHang.fxml"));

					Parent root = null;
					try {
						root = loader.load();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					ThemKhachHang ctlMain=loader.getController();

					int colMaKH=tbl_view.getItems().get(result).getMaKH();
					String diaChi=tbl_view.getItems().get(result).getDiaChi();
					String email=tbl_view.getItems().get(result).getEmail();
					String soDT=tbl_view.getItems().get(result).getSoDT();
					String tenKH=tbl_view.getItems().get(result).getTenKH();

					KhachHang nv=QuanLyKhachHang.timMa(colMaKH);
					ctlMain.lblTitle.setText("Cập nhập khách hàng");
					ctlMain.txtMa.setText(String.valueOf(colMaKH));
					ctlMain.txtDiaChi.setText(diaChi);
					ctlMain.txtEmail.setText(email);
					ctlMain.txtDienThoai.setText(soDT);
					ctlMain.txtTenKH.setText(tenKH);

					Stage stage=new Stage();
					stage.initOwner(btnThem.getScene().getWindow());
					stage.setScene(new Scene(root));
					stage.initStyle(StageStyle.UNDECORATED);
					stage.initModality(Modality.APPLICATION_MODAL);
					Main.primaryStage=stage;
					stage.show();
					stage.setOnHidden(evv->{
						handleRefersh(new ActionEvent());
					});
				}
			}
		});

	}
	ObservableList<KhachHang> list = null;
	@SuppressWarnings("unused")
	private void UploaderDuLieuLenBang(){
		List<KhachHang> accs=QuanLyKhachHang.showTatCaKhachHang();
		accs.forEach(t->{
			//			Account newAccount= new Account(t.getUserName(), t.getPassword(), t.getLoaiTK());
			tbl_view.getItems().add(t);
		});
	}
	private void handleRefersh(ActionEvent e) {
		tbl_view.getItems().clear();
		UploaderDuLieuLenBang();
	}
	public void thongBaoKieuLoi(ActionEvent e, String text) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Dialog");
		alert.setHeaderText(null);
		alert.setContentText(text);
		alert.initOwner(((Node) (e.getSource())).getScene().getWindow());
		alert.showAndWait();
	}
	public void btnXoaXe(ActionEvent e) {
		int result=tbl_view.getSelectionModel().getSelectedIndex();
		if(result!=-1) {
			Alert alert = new Alert(AlertType.WARNING, "bạn có chắc muốn xóa",ButtonType.OK,ButtonType.CANCEL);
			alert.setTitle("Cảnh báo");
			Optional<ButtonType> resultx = alert.showAndWait();

			if (resultx.get() == ButtonType.OK) {
				int acc=tbl_view.getItems().get(result).getMaKH();
				if(QuanLyKhachHang.xoaKH(acc)==true) {
					thongBaoKieuLoi(e,"xóa thành công");
					handleRefersh(e);
				}else {
					thongBaoKieuLoi(e,"lỗi");
				}
			}

		}else {
			thongBaoKieuLoi(e, "bạn chưa chọn bảng cần xóa");
		}
	}
	@FXML
	public void btnXoaRong(ActionEvent e) {
		tbl_view.getSelectionModel().clearSelection();
		txtMa.setText("");
	
	}
	@FXML 
	public void btnTim(ActionEvent e) {
		String text=txtMa.getText().trim().toString();
		if (text.isEmpty()==false) {
			KhachHang acc=QuanLyKhachHang.timMa(Integer.parseInt(text));
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
			// TODO: handle exception
			System.out.println(e2.getMessage());
		}
	}


}
