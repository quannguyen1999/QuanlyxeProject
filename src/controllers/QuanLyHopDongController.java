package controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import application.Main;
import dao.QuanLyHopDong;
import dao.QuanLyKhachHang;
import dao.QuanLyNhanVien;
import dao.QuanLyPhieuXuat;
import dao.QuanLyXe;
import entities.HopDong;
import entities.KhachHang;
import entities.NhanVien;
import entities.PhieuXuat;
import entities.Xe;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class QuanLyHopDongController implements Initializable{
	String UserName="";
	@FXML JFXButton btnThem;
	@FXML 
	private BorderPane bd;
	private TableView<HopDong> tbl_view;
	TableColumn<HopDong, String> colMaHopDong;
	TableColumn<HopDong, String> colNgayLap;
	TableColumn<HopDong, String> colSoDTKH;
	TableColumn<HopDong, String> colTenNguoiMua;

	TableColumn<HopDong, String> colMaXe;
	TableColumn<HopDong, String> colGiaXe;
	TableColumn<HopDong, String> colTrangThai;

	@FXML 
	TextField txtMa;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		tbl_view=new TableView<HopDong>();
		colMaHopDong=new TableColumn<HopDong, String>("Mã Hợp đồng");
		colNgayLap=new TableColumn<HopDong, String>("Ngày lập");
		colSoDTKH=new TableColumn<HopDong, String>("Số điện thoại");
		colTenNguoiMua=new TableColumn<HopDong, String>("Tên người mua");
		colMaXe=new TableColumn<HopDong, String>("Mã xe");
		colGiaXe=new TableColumn<HopDong, String>("Giá xe");
		colTrangThai=new TableColumn<HopDong, String>("Trạng thái");

		tbl_view.getColumns().addAll(colMaHopDong,colNgayLap,colSoDTKH,colTenNguoiMua,colMaXe,colGiaXe,colTrangThai);

		bd.setCenter(tbl_view);

		colMaHopDong.setCellValueFactory(new PropertyValueFactory<>("maHopDong"));
		colNgayLap.setCellValueFactory(new PropertyValueFactory<>("ngayLap"));
		colSoDTKH.setCellValueFactory(new PropertyValueFactory<>("soDTNM"));
		colTenNguoiMua.setCellValueFactory(new PropertyValueFactory<>("tenNguoiMua"));


		colMaXe.setCellValueFactory(cellData -> 
		new SimpleStringProperty(cellData.getValue().getXe().getMaXe()));

		colGiaXe.setCellValueFactory(cellData -> 
		new SimpleStringProperty(String.valueOf(cellData.getValue().getXe().getDonGia())));

		colTrangThai.setCellValueFactory(new PropertyValueFactory<>("trangThai"));

		colMaHopDong.setMinWidth(120);;
		colNgayLap.setMinWidth(100);;
		colSoDTKH.setMinWidth(100);;
		colTenNguoiMua.setMinWidth(150);;
		colMaXe.setMinWidth(80);;
		colGiaXe.setMinWidth(100);;
		colTrangThai.setMinWidth(120);;

		tbl_view.setOnMouseClicked(e->{
			if(e.getClickCount()==2) {
				int result=tbl_view.getSelectionModel().getSelectedIndex();
				if(result!=-1) {
					FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/BanHopDongMuaBanXeMay.fxml"));

					Parent root = null;
					try {
						root = loader.load();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					BanHopDongBuonBanXeMayController ctlMain=loader.getController();
					int maHD=tbl_view.getItems().get(result).getMaHopDong();
					HopDong hd=QuanLyHopDong.timMaHopDong(maHD);
					ctlMain.txtMaHD.setText(" "+String.valueOf(hd.getMaHopDong()));
					ctlMain.txtNgayLap.setText(" "+String.valueOf(hd.getNgayLap().getDayOfMonth()+" "));
					ctlMain.txtThangLap.setText(" "+String.valueOf(hd.getNgayLap().getMonthValue()+" "));
					ctlMain.txtNamLap.setText(" "+String.valueOf(hd.getNgayLap().getYear()));
					ctlMain.txtTenBenBan.setText(" "+hd.getTenNguoiBan());
					ctlMain.txtCMNDBenBan.setText(" "+hd.getCMNDNB());
					ctlMain.txtDiaChiBenBan.setText(" "+hd.getNoiONB());
					ctlMain.txtTenBenMua.setText(" "+hd.getTenNguoiMua());
					ctlMain.txtCMNDBenMua.setText(" "+hd.getCMNDNM());
					ctlMain.txtDiaChiBenMua.setText(" "+hd.getNoiONM());
					ctlMain.txtNhanHieu.setText(" "+hd.getXe().getLx().getNhanhieu()+" ");
					ctlMain.txtLoaiXe.setText(" "+hd.getXe().getLx().getLoaixe()+" ");
					ctlMain.txtTenXe.setText(" "+hd.getXe().getLx().getTenxe()+" ");
					ctlMain.txtMau.setText(" "+hd.getXe().getLx().getMauson()+" ");
					ctlMain.txtGia.setText(" "+hd.getTienDatThanhToan()+" ");

					if(tbl_view.getItems().get(result).getTrangThai().equalsIgnoreCase("Chưa xác nhận")) {
						ctlMain.btnInPhieuXuat.setDisable(true);
					}




					Stage stage=new Stage();
					stage.initOwner(btnThem.getScene().getWindow());
					stage.setScene(new Scene(root));
					//					stage.initStyle(StageStyle.UNDECORATED);
					stage.initModality(Modality.APPLICATION_MODAL);
					Main.primaryStage=stage;
					stage.show();
					stage.getIcons().add(new Image("/image/logo.PNG"));
					stage.setResizable(false);
					stage.setOnHidden(evv->{
						handleRefersh(new ActionEvent());
					});
				}
			}
		});
		UploaderDuLieuLenBang();
	}
	private void UploaderDuLieuLenBang(){
		List<HopDong> accs=QuanLyHopDong.showTatCaHopDong();
		accs.forEach(t->{
			tbl_view.getItems().add(t);
		});
	}
	@FXML
	public void btnXoaRong(ActionEvent e) {
		tbl_view.getSelectionModel().clearSelection();
		txtMa.setText("");
		handleRefersh(e);
	}
	@FXML
	public void btnCapNhap(ActionEvent e) {
		int result=tbl_view.getSelectionModel().getSelectedIndex();
		if(result!=-1) {
			String kiemTraTrangThai=tbl_view.getItems().get(result).getTrangThai();
			if(kiemTraTrangThai.equalsIgnoreCase("Xác nhận")==true) {
				thongBaoKieuLoi(e, "Mã hợp đồng này đã được xác nhận");
			}else {
				Alert alert = new Alert(AlertType.WARNING, "bạn có chắc muốn thay đổi trạng thái",ButtonType.OK,ButtonType.CANCEL);
				alert.setTitle("Cảnh báo");
				Optional<ButtonType> resultx = alert.showAndWait();

				if (resultx.get() == ButtonType.OK) {
					int maHD=tbl_view.getItems().get(result).getMaHopDong();
					HopDong hd=QuanLyHopDong.timMaHopDong(maHD);
					hd.setTrangThai("Xác nhận");
					if(QuanLyHopDong.capNhapHD(hd)==true) {
						NhanVien nv=QuanLyNhanVien.timMa(Integer.parseInt(hd.getMaNV()));
						KhachHang kh=QuanLyKhachHang.timMa(Integer.parseInt(hd.getMaKH()));
						PhieuXuat px=new PhieuXuat(maHD, nv,kh, hd,hd.getNgayLap(),hd.getXe().getDonGia(),hd.getSoLuongMua(), 10);
						QuanLyPhieuXuat.themPhieuXuat(px);
						handleRefersh(e);
					}else {
						System.out.println("Lỗi");
					}
				}
			}


		}else {
			thongBaoKieuLoi(e, "bạn chưa chọn bảng");
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
	public void btnXoaHopDong(ActionEvent e) {
		int result=tbl_view.getSelectionModel().getSelectedIndex();
		if(result!=-1) {
			Alert alert = new Alert(AlertType.WARNING, "bạn có chắc muốn xóa",ButtonType.OK,ButtonType.CANCEL);
			alert.setTitle("Cảnh báo");
			Optional<ButtonType> resultx = alert.showAndWait();

			if (resultx.get() == ButtonType.OK) {
				int acc=tbl_view.getItems().get(result).getMaHopDong();
				PhieuXuat px=QuanLyPhieuXuat.timPhieuXuat(tbl_view.getItems().get(result).getMaHopDong());
				if(QuanLyPhieuXuat.xoaPX(px.getMaPX())==true) {
					if(QuanLyHopDong.xoaHopDong(acc)==true) {
						thongBaoKieuLoi(e, "xóa thành công");
						handleRefersh(e);
					}else{
						thongBaoKieuLoi(e, "xóa không thành công");
					};
				}else {
					thongBaoKieuLoi(e, "Phiếu xuất bị lỗi");
				}
			}

		}else {
			thongBaoKieuLoi(e, "bạn chưa chọn bảng cần xóa");
		}
	}
	@FXML 
	public void btnTim(ActionEvent e) {
		String text=txtMa.getText().trim().toString();
		if (text.isEmpty()==false) {
			try {
				HopDong acc=QuanLyHopDong.timMaHopDong(Integer.parseInt(text));
				if(acc!=null) {
					tbl_view.getItems().clear();
					tbl_view.getItems().add(acc);
				}else {
					tbl_view.getItems().clear();
					thongBaoKieuLoi(e, "không tìm thấy");
				}
			} catch (Exception e2) {
				// TODO: handle exception
				tbl_view.getItems().clear();
				thongBaoKieuLoi(e, "không tìm thấy");

			}

		}else {
			thongBaoKieuLoi(e, "Bạn chưa nhập tìm kiếm");
			txtMa.requestFocus();
		}

	}
	private void handleRefersh(ActionEvent e) {
		tbl_view.getItems().clear();
		UploaderDuLieuLenBang();
	}
	public void btnNhapThongTinHopDong(ActionEvent e) throws IOException {
		try {
			//			FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/FormThongTinHopDong.fxml"));
			//			Parent parent=loader.load();
			//			Stage stage=new Stage(StageStyle.DECORATED);
			//			stage.initOwner(((Node)(e.getSource())).getScene().getWindow());
			//			stage.initStyle(StageStyle.UNDECORATED);
			//			stage.initModality(Modality.APPLICATION_MODAL);
			//			stage.setScene(new Scene(parent));
			//			stage.show();
			//			stage.getIcons().add(new Image("/image/logo.PNG"));
			//			Main.primaryStage=stage;
			//			stage.setOnHidden(ev->{
			//				handleRefersh(e);
			//			});

			FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/FormThongTinHopDong.fxml"));

			Parent root=loader.load();

			ThemHopDong ctlMain=loader.getController();
			NhanVien nv=QuanLyNhanVien.timMa2(UserName);
			ctlMain.boxMaNV.setValue(String.valueOf(nv.getMaNV()));
			//			ctlMain.boxMaNV.setEditable(false);
			ctlMain.boxMaNV.setDisable(true);
			ctlMain.username=UserName;
			Stage stage=new Stage();
			stage.initOwner(((Node)(e.getSource())).getScene().getWindow());
			stage.initStyle(StageStyle.UNDECORATED);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(new Scene(root));
			Main.primaryStage=stage;
			stage.getIcons().add(new Image("/image/logo.PNG"));
			stage.setResizable(false);
			stage.show();
			stage.setOnHidden(ev->{
				handleRefersh(new ActionEvent());
			});
		} catch (Exception e2) {
			System.out.println(e2.getMessage());
		}
	}
}
