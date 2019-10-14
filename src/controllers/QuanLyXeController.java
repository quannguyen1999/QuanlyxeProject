package controllers;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import application.Main;
import dao.QuanLyAccount;
import dao.QuanLyXe;
import entities.Account;
import entities.Xe;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
public class QuanLyXeController implements Initializable{
	@FXML JFXButton btnThem;
	@FXML JFXButton btnXoa;
	@FXML JFXButton btnSua;
	@FXML 
	private BorderPane bd;
	private TableView<Xe> tbl_view;
	TableColumn<Xe, String> colMaXe;
	TableColumn<Xe, String> colDonViTinh;
	TableColumn<Xe, String> colMoTa;
	TableColumn<Xe, String> colNhaSX;
	TableColumn<Xe, String> colLoaiXe;
	TableColumn<Xe, String> colTenXe;
	TableColumn<Xe, String> colMauXe;
	TableColumn<Xe, String> colThongTinBaoHanh;

	@FXML 
	TextField txtMa;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tbl_view=new TableView<Xe>();
		colMaXe=new TableColumn<Xe, String>("Mã xe");
		colDonViTinh=new TableColumn<Xe, String>("Đơn vị tính");
		colMoTa=new TableColumn<Xe, String>("Mô tả");
		colNhaSX=new TableColumn<Xe, String>("Nhà sàn xuất");
		colLoaiXe=new TableColumn<Xe, String>("Loại xe");
		colTenXe=new TableColumn<Xe, String>("Tên xe");
		colThongTinBaoHanh=new TableColumn<Xe, String>("Thông tin bảo hành");
		colMauXe=new TableColumn<Xe, String>("Màu xe");


		tbl_view.getColumns().addAll(colMaXe,colDonViTinh,colMoTa,colNhaSX,colLoaiXe,colTenXe,colThongTinBaoHanh,colMauXe);

		bd.setCenter(tbl_view);

		colMaXe.setCellValueFactory(new PropertyValueFactory<>("maXe"));
		colDonViTinh.setCellValueFactory(new PropertyValueFactory<>("donViTinh"));
		colMoTa.setCellValueFactory(new PropertyValueFactory<>("moTa"));
		colNhaSX.setCellValueFactory(new PropertyValueFactory<>("nhaSX"));
		colLoaiXe.setCellValueFactory(new PropertyValueFactory<>("loaiXe"));
		colTenXe.setCellValueFactory(new PropertyValueFactory<>("tenXe"));
		colMauXe.setCellValueFactory(new PropertyValueFactory<>("mauXe"));
		colThongTinBaoHanh.setCellValueFactory(new PropertyValueFactory<>("thongTinBaoHanh"));

		UploaderDuLieuLenBang();
		
		tbl_view.setOnMouseClicked(ev->{
			int result=tbl_view.getSelectionModel().getSelectedIndex();

			if(result!=-1) {
				FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/FormThongTinXe.fxml"));

				Parent root = null;
				try {
					root = loader.load();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				ThemXe ctlMain=loader.getController();

				String maXe=tbl_view.getItems().get(result).getMaXe();
				String donViTinh=tbl_view.getItems().get(result).getDonViTinh();
				String moTa=tbl_view.getItems().get(result).getMoTa();
				String nhaSX=tbl_view.getItems().get(result).getNhaSX();
				String LoaiXe=tbl_view.getItems().get(result).getLoaiXe();
				String TenXe=tbl_view.getItems().get(result).getTenXe();
				String thongTinBaoHanh=tbl_view.getItems().get(result).getThongTinBaoHanh();
				String loaiXe=tbl_view.getItems().get(result).getLoaiXe();
				String mauXe=tbl_view.getItems().get(result).getMauXe();
				ctlMain.lblTitle.setText("Cập nhập tài khoản");
				ctlMain.txtMaXe.setText(maXe);
				ctlMain.txtMaXe.setEditable(false);
				ctlMain.txtDonViTinh.setText(donViTinh);
				ctlMain.txtMoTa.setText(moTa);
				ctlMain.txtNhaSX.setText(nhaSX);

				ctlMain.choiceBoxLoaiXe.setValue(loaiXe);
				ctlMain.choiceBoxMauXe.setValue(mauXe);
				ctlMain.choiceBoxTenXe.setValue(TenXe);
				if(loaiXe.equals("Blade-110C")) {
					if(mauXe.length()==10) {
						Image image = new Image("/image/Blade-110C_XanhDuong.PNG");
						ctlMain.img.setImage(image);

					}else {
						Image image = new Image("/image/Blade-110C_den.PNG");
						ctlMain.img.setImage(image);
					}
				}else if(TenXe.equals("SUPER-CUB")) {
					Image image = new Image("/image/SUPER-CUB_XanhDuong.PNG");
					ctlMain.img.setImage(image);
				}else if(TenXe.equals("SH-300c")) {
					if(mauXe.length()==3) {
						System.out.println("ok");
						Image image = new Image("/image/SH-300c_den.PNG");
						ctlMain.img.setImage(image);
					}else {
						System.out.println("false");
						Image image = new Image("/image/SH-300c_trang.PNG");
						ctlMain.img.setImage(image);
					}
				}else if(TenXe.equals("Vision-110C")) {
					if(mauXe.length()==2) {
						Image image = new Image("/image/Vision-110C_do.PNG");
						ctlMain.img.setImage(image);
					}else if(mauXe.length()==10) {
						Image image = new Image("/image/Vision-110C_xanhDuong.PNG");
						ctlMain.img.setImage(image);
					}else {
						Image image = new Image("/image/Vision-110C_Vang.PNG");
						ctlMain.img.setImage(image);

					}
				}
				ctlMain.txtThongTinBaoHanh.setText(thongTinBaoHanh);

				Stage stage=new Stage();
				stage.initOwner(btnThem.getScene().getWindow());
				stage.initStyle(StageStyle.UNDECORATED);
				stage.initModality(Modality.APPLICATION_MODAL);
				stage.setScene(new Scene(root));
				Main.primaryStage=stage;
				stage.show();
				stage.setOnHidden(evv->{
					handleRefersh(new ActionEvent());
				});
			}
		});

	}
	private void UploaderDuLieuLenBang(){
		List<Xe> accs=QuanLyXe.showTatCaXe();
		accs.forEach(t->{
			//			Account newAccount= new Account(t.getUserName(), t.getPassword(), t.getLoaiTK());
			tbl_view.getItems().add(t);
		});
	}
	private void handleRefersh(ActionEvent e) {
		tbl_view.getItems().clear();
		UploaderDuLieuLenBang();
	}
	public void btnSuaXe(ActionEvent e) throws IOException {
		int result=tbl_view.getSelectionModel().getSelectedIndex();

		if(result!=-1) {
			FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/FormThongTinXe.fxml"));

			Parent root=loader.load();

			ThemXe ctlMain=loader.getController();

			String maXe=tbl_view.getItems().get(result).getMaXe();
			String donViTinh=tbl_view.getItems().get(result).getDonViTinh();
			String moTa=tbl_view.getItems().get(result).getMoTa();
			String nhaSX=tbl_view.getItems().get(result).getNhaSX();
			String LoaiXe=tbl_view.getItems().get(result).getLoaiXe();
			String TenXe=tbl_view.getItems().get(result).getTenXe();
			String thongTinBaoHanh=tbl_view.getItems().get(result).getThongTinBaoHanh();
			String loaiXe=tbl_view.getItems().get(result).getLoaiXe();
			String mauXe=tbl_view.getItems().get(result).getMauXe();
			ctlMain.lblTitle.setText("Cập nhập tài khoản");
			ctlMain.txtMaXe.setText(maXe);
			ctlMain.txtMaXe.setEditable(false);
			ctlMain.txtDonViTinh.setText(donViTinh);
			ctlMain.txtMoTa.setText(moTa);
			ctlMain.txtNhaSX.setText(nhaSX);

			ctlMain.choiceBoxLoaiXe.setValue(loaiXe);
			ctlMain.choiceBoxMauXe.setValue(mauXe);
			ctlMain.choiceBoxTenXe.setValue(TenXe);
			if(loaiXe.equals("Blade-110C")) {
				if(mauXe.length()==10) {
					Image image = new Image("/image/Blade-110C_XanhDuong.PNG");
					ctlMain.img.setImage(image);

				}else {
					Image image = new Image("/image/Blade-110C_den.PNG");
					ctlMain.img.setImage(image);
				}
			}else if(TenXe.equals("SUPER-CUB")) {
				Image image = new Image("/image/SUPER-CUB_XanhDuong.PNG");
				ctlMain.img.setImage(image);
			}else if(TenXe.equals("SH-300c")) {
				if(mauXe.length()==3) {
					System.out.println("ok");
					Image image = new Image("/image/SH-300c_den.PNG");
					ctlMain.img.setImage(image);
				}else {
					System.out.println("false");
					Image image = new Image("/image/SH-300c_trang.PNG");
					ctlMain.img.setImage(image);
				}
			}else if(TenXe.equals("Vision-110C")) {
				if(mauXe.length()==2) {
					Image image = new Image("/image/Vision-110C_do.PNG");
					ctlMain.img.setImage(image);
				}else if(mauXe.length()==10) {
					Image image = new Image("/image/Vision-110C_xanhDuong.PNG");
					ctlMain.img.setImage(image);
				}else {
					Image image = new Image("/image/Vision-110C_Vang.PNG");
					ctlMain.img.setImage(image);

				}
			}
			ctlMain.txtThongTinBaoHanh.setText(thongTinBaoHanh);

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

	public void btnXoaXe(ActionEvent e) {
		int result=tbl_view.getSelectionModel().getSelectedIndex();
		if(result!=-1) {
			Alert alert = new Alert(AlertType.WARNING, "bạn có chắc muốn xóa",ButtonType.OK,ButtonType.CANCEL);
			alert.setTitle("Cảnh báo");
			Optional<ButtonType> resultx = alert.showAndWait();

			if (resultx.get() == ButtonType.OK) {
				String acc=tbl_view.getItems().get(result).getMaXe();
				if(QuanLyXe.xoaXe(acc)==true) {
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
	public void thongBaoKieuLoi(ActionEvent e, String text) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Dialog");
		alert.setHeaderText(null);
		alert.setContentText(text);
		alert.initOwner(((Node) (e.getSource())).getScene().getWindow());
		alert.showAndWait();
	}

	@FXML 
	public void btnTim(ActionEvent e) {
		String text=txtMa.getText().trim().toString();
		if (text.isEmpty()==false) {
			Xe acc=QuanLyXe.timMa(text);
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
	@FXML
	public void btnXoaRong(ActionEvent e) {
		tbl_view.getSelectionModel().clearSelection();
		txtMa.setText("");
	}
	public void btnNhapThongTinXe(ActionEvent e) throws IOException {
		try {
			FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/FormThongTinXe.fxml"));
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
