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
import entities.CTHopDong;
import entities.HopDong;
import entities.KhachHang;
import entities.Xe;
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

public class QuanLyHopDongController implements Initializable{
	@FXML JFXButton btnThem;
	@FXML 
	private BorderPane bd;
	private TableView<HopDong> tbl_view;
	TableColumn<HopDong, String> colMaHopDong;
	TableColumn<HopDong, String> colNgayLap;
	TableColumn<HopDong, String> colTienDatThanhToan;
	TableColumn<HopDong, String> colTienPhaiThanhToan;

	@FXML 
	TextField txtMa;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		tbl_view=new TableView<HopDong>();
		colMaHopDong=new TableColumn<HopDong, String>("Mã Hợp đồng");
		colNgayLap=new TableColumn<HopDong, String>("Ngày lập");
		colTienDatThanhToan=new TableColumn<HopDong, String>("Tiền đặt thanh toán");
		colTienPhaiThanhToan=new TableColumn<HopDong, String>("Tiền phải thanh toán");

		tbl_view.getColumns().addAll(colMaHopDong,colNgayLap,colTienDatThanhToan,colTienPhaiThanhToan);

		bd.setCenter(tbl_view);

		colMaHopDong.setCellValueFactory(new PropertyValueFactory<>("maHopDong"));
		colNgayLap.setCellValueFactory(new PropertyValueFactory<>("ngayLap"));
		colTienDatThanhToan.setCellValueFactory(new PropertyValueFactory<>("tienDatThanhToan"));
		colTienPhaiThanhToan.setCellValueFactory(new PropertyValueFactory<>("tienPhaiThanhToan"));

		tbl_view.setOnMouseClicked(e->{
			if(e.getClickCount()==2) {
				int result=tbl_view.getSelectionModel().getSelectedIndex();
				if(result!=-1) {
					FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/FormThongTinHopDong.fxml"));

					Parent root = null;
					try {
						root = loader.load();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					ThemHopDong ctlMain=loader.getController();

					int maHD=tbl_view.getItems().get(result).getMaHopDong();
					HopDong hd=QuanLyHopDong.timMaHopDong(maHD);
					int maNV=hd.getNhanVien().getMaNV();
					String tenNV=hd.getNhanVien().getTenNV();
					LocalDate ngayLap=tbl_view.getItems().get(result).getNgayLap();
					String tienDatThanhToan=String.valueOf(tbl_view.getItems().get(result).getTienDatThanhToan());
					String tienPhaiThanhToan=String.valueOf(tbl_view.getItems().get(result).getTienPhaiThanhToan());
					CTHopDong cthd=QuanLyHopDong.timMaTheoHopDong(hd.getMaHopDong());
					String cmnd=cthd.getCMND();
					String diaChi=cthd.getDiaChiKH();
					String soDT=cthd.getSoDT();
					String tenKH=cthd.getTenKH();

					ctlMain.txtMaHD.setText(String.valueOf(maHD));
					ctlMain.txtMaHD.setEditable(false);
					ctlMain.boxMaNV.setValue(String.valueOf(maNV));
					ctlMain.boxMaNV.setDisable(true);
					ctlMain.txtTenNV.setText(tenNV);
					ctlMain.txtTenNV.setEditable(false);
					ctlMain.txtNgayLap.setValue(ngayLap);
					ctlMain.txtNgayLap.setEditable(false);
					ctlMain.txtTienDat.setText(tienDatThanhToan);
					ctlMain.txtTienDat.setEditable(false);
					ctlMain.txtTienPhai.setText(tienPhaiThanhToan);
					ctlMain.txtTienPhai.setEditable(false);
					ctlMain.txtCMND.setText(cmnd);
					ctlMain.txtCMND.setEditable(false);
					ctlMain.txtDiaChi.setText(diaChi);
					ctlMain.txtDiaChi.setEditable(false);
					ctlMain.txtSoDT.setText(soDT);
					ctlMain.txtSoDT.setEditable(false);
					ctlMain.txtTenKH.setText(tenKH);
					ctlMain.txtTenKH.setEditable(false);
					ctlMain.btnThem.setDisable(true);
					ctlMain.btnXoaRong.setDisable(true);


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
				CTHopDong hd=QuanLyHopDong.timMaTheoHopDong(acc);
				QuanLyHopDong.xoaChiTietHopDong(hd.getCMND());
				QuanLyHopDong.xoaHopDong(acc);
				thongBaoKieuLoi(e, "xóa thành công");
				handleRefersh(e);
			}

		}else {
			thongBaoKieuLoi(e, "bạn chưa chọn bảng cần xóa");
		}
	}
	@FXML 
	public void btnTim(ActionEvent e) {
		String text=txtMa.getText().trim().toString();
		if (text.isEmpty()==false) {
			HopDong acc=QuanLyHopDong.timMaHopDong(Integer.parseInt(text));
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
	private void handleRefersh(ActionEvent e) {
		tbl_view.getItems().clear();
		UploaderDuLieuLenBang();
	}
	public void btnNhapThongTinHopDong(ActionEvent e) throws IOException {
		try {
			FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/FormThongTinHopDong.fxml"));
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
