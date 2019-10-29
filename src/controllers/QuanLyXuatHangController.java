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
import dao.QuanLyNhanVien;
import dao.QuanLyPhieuXuat;
import dao.QuanLyXe;
import entities.Account;
import entities.HopDong;
import entities.KhachHang;
import entities.NhanVien;
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
import javafx.scene.control.Label;
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

public class QuanLyXuatHangController implements Initializable{
	@FXML TextField txtMaPX;
	
	@FXML 
	private BorderPane bd;
	@FXML JFXButton btnThem;
	private TableView<PhieuXuat> tbl_view;
	TableColumn<PhieuXuat, String> colMaPX;
	TableColumn<PhieuXuat, String> colMaNV;
	TableColumn<PhieuXuat, String> colMaKH;
	TableColumn<PhieuXuat, String> colMaHD;
	TableColumn<PhieuXuat, String> colNgayXuat;
	TableColumn<PhieuXuat, String> colDonGiaXuat;
	TableColumn<PhieuXuat, String> colSLX;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tbl_view=new TableView<PhieuXuat>();
		colMaPX=new TableColumn<PhieuXuat, String>("Mã phiếu xuất");
		colMaNV=new TableColumn<PhieuXuat, String>("Mã nhân viên");
		colMaKH=new TableColumn<PhieuXuat, String>("Mã khách hàng");
		colMaHD=new TableColumn<PhieuXuat, String>("Mã hợp đồng");
		colNgayXuat=new TableColumn<PhieuXuat, String>("Ngày xuất");
		colDonGiaXuat=new TableColumn<PhieuXuat, String>("Đơn giá xuất");
		colSLX=new TableColumn<PhieuXuat, String>("Số lượng xuất");

		tbl_view.getColumns().addAll(colMaPX,colMaNV,colMaKH,colMaHD,colNgayXuat,colDonGiaXuat,colSLX);

		bd.setCenter(tbl_view);

		colMaPX.setCellValueFactory(new PropertyValueFactory<>("maPX"));
		
		colMaNV.setCellValueFactory(cellData -> 
		new SimpleStringProperty(String.valueOf(cellData.getValue().getNhanVien().getMaNV())));
		
		colMaKH.setCellValueFactory(cellData -> 
		new SimpleStringProperty(String.valueOf(cellData.getValue().getKhachHang().getMaKH())));
		
		colMaHD.setCellValueFactory(cellData -> 
		new SimpleStringProperty(String.valueOf(cellData.getValue().getHopDong().getMaHopDong())));
		
		colNgayXuat.setCellValueFactory(new PropertyValueFactory<>("ngayXuat"));
		
		colDonGiaXuat.setCellValueFactory(cellData -> 
		new SimpleStringProperty(String.format("%.0f", cellData.getValue().getDonGiaXuat())));
		
//		colSpeedProgress.setCellValueFactory(new PropertyValueFactory<TableDisplayData, Double>("progressBar"));
//		colDonGiaXuat.setCellFactory(ProgressBarTableCell.<TableDisplayData> forTableColumn());
		
		colSLX.setCellValueFactory(cellData -> 
		new SimpleStringProperty(String.valueOf(cellData.getValue().getsLXuat())));
		
		tbl_view.setOnMouseClicked(e->{
			if(e.getClickCount()==2) {
				int result=tbl_view.getSelectionModel().getSelectedIndex();
				if(result!=-1) {
					FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/BanPhieuXuatXeMay.fxml"));

					Parent root = null;
					try {
						root = loader.load();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					BanPhieuXuatXeMay ctlMain=loader.getController();
					ctlMain.txtNgay.setText(" "+String.valueOf(tbl_view.getItems().get(result).getNgayXuat().getDayOfMonth()));
					ctlMain.txtThang.setText(" "+String.valueOf(tbl_view.getItems().get(result).getNgayXuat().getMonthValue()));
					ctlMain.txtNam.setText(" "+String.valueOf(tbl_view.getItems().get(result).getNgayXuat().getYear()));
					
					ctlMain.txtHoTenNV.setText(" "+tbl_view.getItems().get(result).getNhanVien().getTenNV());
					ctlMain.txtHoTenKH.setText(" "+tbl_view.getItems().get(result).getKhachHang().getTenKH());
					ctlMain.txtSDT.setText(" "+tbl_view.getItems().get(result).getKhachHang().getSoDT());
					ctlMain.txtDiaChi.setText(" "+tbl_view.getItems().get(result).getKhachHang().getDiaChi());
					
					PhieuXuat x=tbl_view.getItems().get(result);
					
					ctlMain.tbl_view.getItems().add(x);
					double resultx=x.getsLXuat()*x.getDonGiaXuat();
					ctlMain.tblTT.getItems().add(String.format("%.0f",resultx));
					
					Stage stage=new Stage();
					stage.initOwner(btnThem.getScene().getWindow());
					stage.setScene(new Scene(root));
//					stage.initStyle(StageStyle.UNDECORATED);
					stage.setResizable(false);
					stage.initModality(Modality.APPLICATION_MODAL);
					Main.primaryStage=stage;
					stage.show();
					stage.getIcons().add(new Image("/image/logo.PNG"));
					stage.setOnHidden(evv->{
						handleRefersh(new ActionEvent());
					});
				}
			}
		});
		
		UploaderDuLieuLenBang();
	}
	@SuppressWarnings("unused")
	private void UploaderDuLieuLenBang(){
		List<PhieuXuat> accs=QuanLyPhieuXuat.showTatCaPhieuXuat();
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
		} catch (Exception e2) {
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
		
	}
	
	@FXML
	public void btnXoaRong(ActionEvent e) {
		tbl_view.getSelectionModel().clearSelection();
		txtMaPX.setText("");
		handleRefersh(e);
	}
	
	@FXML
	public void btnTim(ActionEvent e) {
		String text=txtMaPX.getText().trim().toString();
		if (text.isEmpty()==false) {
			PhieuXuat acc=null;
			try {
				acc=QuanLyPhieuXuat.timPhieuXuat(Integer.parseInt(text));
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
			txtMaPX.requestFocus();
		}
	}
	
	


}
