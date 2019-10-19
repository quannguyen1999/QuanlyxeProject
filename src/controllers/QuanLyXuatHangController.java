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
import dao.QuanLyHopDong;
import dao.QuanLyKhachHang;
import dao.QuanLyPhieuXuat;
import dao.QuanLyXe;
import entities.Account;
import entities.CTPhieuXuat;
import entities.KhachHang;
import entities.PhieuXuat;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class QuanLyXuatHangController implements Initializable{
	private TableView<CTPhieuXuat> tbl_view;
	TableColumn<CTPhieuXuat, String> colMaPX;
	TableColumn<CTPhieuXuat, String> colMaXe;
	TableColumn<CTPhieuXuat, String> colDonGiaXuat;
	TableColumn<CTPhieuXuat, String> colSlXuat;
	TableColumn<CTPhieuXuat, String> colThue;
	TableColumn<CTPhieuXuat, String> colNgayXuat;
	TableColumn<CTPhieuXuat, String> colMaHD;
	TableColumn<CTPhieuXuat, String> colMaKH;
	TableColumn<CTPhieuXuat, String> colMaNV;

	@FXML 
	private BorderPane bd;
	@FXML JFXButton btnThem;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tbl_view=new TableView<CTPhieuXuat>();
		colMaPX=new TableColumn<CTPhieuXuat, String>("Mã phiếu xuất");
		colMaXe=new TableColumn<CTPhieuXuat, String>("Mã xe");
		colDonGiaXuat=new TableColumn<CTPhieuXuat, String>("Đơn giá xuất");
		colSlXuat=new TableColumn<CTPhieuXuat, String>("Số lượng xuất");
		colThue=new TableColumn<CTPhieuXuat, String>("Thuế");
		colNgayXuat=new TableColumn<CTPhieuXuat, String>("Ngày xuất");
		colMaHD=new TableColumn<CTPhieuXuat, String>("Mã hợp đồng");
		colMaKH=new TableColumn<CTPhieuXuat, String>("Mã khách hàng");
		colMaNV=new TableColumn<CTPhieuXuat, String>("Mã nhân viên");

		tbl_view.getColumns().addAll(colMaPX,colMaXe,colDonGiaXuat,colSlXuat,colThue,colNgayXuat,colMaHD,colMaKH,colMaNV);

		bd.setCenter(tbl_view);

		colMaPX.setCellValueFactory(cellData -> 
		new SimpleStringProperty(String.valueOf(cellData.getValue().getPhieuXuat().getMaPX())));
		colMaXe.setCellValueFactory(cellData -> 
		new SimpleStringProperty(cellData.getValue().getXe().getMaXe()));
		colDonGiaXuat.setCellValueFactory(new PropertyValueFactory<>("donGiaXuat"));
		colSlXuat.setCellValueFactory(cellData -> 
		new SimpleStringProperty(String.valueOf(cellData.getValue().getsLXuat())));
		colThue.setCellValueFactory(new PropertyValueFactory<>("thue"));
		colNgayXuat.setCellValueFactory(cellData -> 
		new SimpleStringProperty(String.valueOf(cellData.getValue().getPhieuXuat().getNgayXuat())));
		colMaHD.setCellValueFactory(cellData -> 
		new SimpleStringProperty(String.valueOf(cellData.getValue().getPhieuXuat().getHopDong().getMaHopDong())));
		colMaKH.setCellValueFactory(cellData -> 
		new SimpleStringProperty(String.valueOf(cellData.getValue().getPhieuXuat().getKhachHang().getMaKH())));
		colMaNV.setCellValueFactory(cellData -> 
		new SimpleStringProperty(String.valueOf(cellData.getValue().getPhieuXuat().getNhanVien().getMaNV())));


		UploaderDuLieuLenBang();

		tbl_view.setOnMouseClicked(e->{
			if(e.getClickCount()==2) {
				int result=tbl_view.getSelectionModel().getSelectedIndex();
				if(result!=-1) {
					FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/FormThongTinPhieuXuat.fxml"));

					Parent root = null;
					try {
						root = loader.load();
					} catch (IOException e1) {
						e1.printStackTrace();
					}

					ThemPhieuXuat ctlMain=loader.getController();

					int colMaPX=tbl_view.getItems().get(result).getPhieuXuat().getMaPX();
					int maNV=tbl_view.getItems().get(result).getPhieuXuat().getNhanVien().getMaNV();
					int maKH=tbl_view.getItems().get(result).getPhieuXuat().getKhachHang().getMaKH();
					LocalDate ngayXuat=tbl_view.getItems().get(result).getPhieuXuat().getNgayXuat();
					int maHD=tbl_view.getItems().get(result).getPhieuXuat().getHopDong().getMaHopDong();
					String maXe=tbl_view.getItems().get(result).getXe().getMaXe();
					Double donGiaXuat=tbl_view.getItems().get(result).getDonGiaXuat();
					int SoLuongXuat=tbl_view.getItems().get(result).getsLXuat();
					
					ctlMain.txtPX.setText(String.valueOf(colMaPX));
					ctlMain.boxMaNV.setValue(String.valueOf(maNV));
					ctlMain.boxMaKH.setValue(String.valueOf(maKH));
					ctlMain.txtNgayXuat.setValue(ngayXuat);
					ctlMain.boxMaHD.setValue(String.valueOf(maHD));
					ctlMain.boxMaXe.setValue(maXe);
					ctlMain.txtDonGiaXuat.setText(String.valueOf(donGiaXuat));
					ctlMain.txtSoLuongXuat.setText(String.valueOf(SoLuongXuat));
					
					ctlMain.txtPX.setEditable(false);
					ctlMain.txtNgayXuat.setEditable(false);
					ctlMain.txtDonGiaXuat.setEditable(false);
					ctlMain.boxMaHD.setEditable(false);
					ctlMain.boxMaHD.setDisable(true);
					ctlMain.boxMaXe.setDisable(true);
					ctlMain.txtSoLuongXuat.setEditable(false);
					
					Stage stage=new Stage();
					stage.initOwner(btnThem.getScene().getWindow());
					stage.setScene(new Scene(root));
					stage.initStyle(StageStyle.UNDECORATED);
					stage.initModality(Modality.APPLICATION_MODAL);
					stage.getIcons().add(new Image("/image/logo.PNG"));
					Main.primaryStage=stage;
					stage.show();
					stage.setOnHidden(evv->{
						handleRefersh(new ActionEvent());
					});
				}
			}
		});
	}
	@SuppressWarnings("unused")
	private void UploaderDuLieuLenBang(){
		List<CTPhieuXuat> accs=QuanLyPhieuXuat.showTatCaChiTietPhieuXuat();
		accs.forEach(t->{
			tbl_view.getItems().add(t);
		});
	}
	private void handleRefersh(ActionEvent e) {
		tbl_view.getItems().clear();
		UploaderDuLieuLenBang();
	}
	public void btnNhapThongTinXuatHang(ActionEvent e) throws IOException {
		try {
			FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/FormThongTinPhieuXuat.fxml"));
			Parent parent=loader.load();
			Stage stage=new Stage(StageStyle.DECORATED);
			stage.initOwner(((Node)(e.getSource())).getScene().getWindow());
			stage.initStyle(StageStyle.UNDECORATED);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(new Scene(parent));
			stage.show();
			stage.getIcons().add(new Image("/image/logo.PNG"));
			Main.primaryStage=stage;
			stage.setOnHidden(ev->{
				handleRefersh(e);
			});
		} catch (Exception e2) {
			System.out.println(e2.getMessage());
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
	public void btnXoaPhieuXuat(ActionEvent e) {
		int result=tbl_view.getSelectionModel().getSelectedIndex();
		if(result!=-1) {
			Alert alert = new Alert(AlertType.WARNING, "bạn có chắc muốn xóa",ButtonType.OK,ButtonType.CANCEL);
			alert.setTitle("Cảnh báo");
			Optional<ButtonType> resultx = alert.showAndWait();

			if (resultx.get() == ButtonType.OK) {
				int acc=tbl_view.getItems().get(result).getPhieuXuat().getMaPX();
				if(QuanLyPhieuXuat.xoaPhieuXuat(acc)==true) {
					thongBaoKieuLoi(e, "xóa thành công");
				}else{
					thongBaoKieuLoi(e, "xóa không thành công");
				};
				handleRefersh(e);
			}

		}else {
			thongBaoKieuLoi(e, "bạn chưa chọn bảng cần xóa");
		}
		
	}


}
