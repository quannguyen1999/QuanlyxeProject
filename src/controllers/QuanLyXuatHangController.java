package controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import application.Main;
import dao.QuanLyAccount;
import dao.QuanLyKhachHang;
import dao.QuanLyPhieuXuat;
import dao.QuanLyXe;
import entities.Account;
import entities.CTPhieuXuat;
import entities.KhachHang;
import entities.PhieuXuat;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class QuanLyXuatHangController implements Initializable{
	private TableView<PhieuXuat> tbl_view;
	TableColumn<PhieuXuat, String> colMaPX;
	TableColumn<PhieuXuat, String> colNgayXuat;
	TableColumn<PhieuXuat, String> colMaKH;
	TableColumn<PhieuXuat, String> colMaNV;
	@FXML 
	private BorderPane bd;
	@FXML JFXButton btnThem;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		tbl_view=new TableView<PhieuXuat>();
		colMaPX=new TableColumn<PhieuXuat, String>("Mã phiếu xuất");
		colNgayXuat=new TableColumn<PhieuXuat, String>("Ngày xuất");
		colMaKH=new TableColumn<PhieuXuat, String>("Mã khách hàng");
		colMaNV=new TableColumn<PhieuXuat, String>("Mã nhân viên");

		tbl_view.getColumns().addAll(colMaPX,colMaKH,colMaNV,colNgayXuat);

		bd.setCenter(tbl_view);

		colMaPX.setCellValueFactory(new PropertyValueFactory<>("maPX"));
		colNgayXuat.setCellValueFactory(new PropertyValueFactory<>("ngayXuat"));
		colMaKH.setCellValueFactory(new PropertyValueFactory<>("khachHang"));
		colMaNV.setCellValueFactory(new PropertyValueFactory<>("nhanVien"));

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
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					ThemPhieuXuat ctlMain=loader.getController();

					int colMaPX=tbl_view.getItems().get(result).getMaPX();
					int maNV=tbl_view.getItems().get(result).getNhanVien().getMaNV();
					int maKH=tbl_view.getItems().get(result).getKhachHang().getMaKH();
					LocalDate ngayXuat=tbl_view.getItems().get(result).getNgayXuat();
					System.out.println(colMaPX);
					CTPhieuXuat ctPX=QuanLyPhieuXuat.timMaCTPhieuXuat(colMaPX);
					System.out.println(ctPX);
					//				ctlMain.lblTitle.setText("Cập nhập Phiếu xuất");
					//				ctlMain.txtPX.setText(String.valueOf(colMaPX));
					//				ctlMain.boxMaNV.setValue(String.valueOf(maNV));
					//				ctlMain.txtNgayXuat.setValue(ngayXuat);
					//				ctlMain.boxMaXe.setValue(ctPX.getXe().getMaXe());
					//				ctlMain.txtDonGiaXuat.setText(String.valueOf(ctPX.getDonGiaXuat()));
					//				ctlMain.txtSoLuongXuat.setText(String.valueOf(ctPX.getsLXuat()));
					//				
					//				ctlMain.lblTenXe.setText(ctPX.getXe().getTenXe());
					//				ctlMain.lblMauXe.setText(ctPX.getXe().getMauXe());
					//				ctlMain.lblBH.setText(ctPX.getXe().getThongTinBaoHanh());
					//				ctlMain.lblLoaiXe.setText(ctPX.getXe().getLoaiXe());
					//				ctlMain.lblNhaSX.setText(ctPX.getXe().getNhaSX());
					//				
					//				Stage stage=new Stage();
					//				stage.initOwner(btnThem.getScene().getWindow());
					//				stage.setScene(new Scene(root));
					//				stage.initStyle(StageStyle.UNDECORATED);
					//				stage.initModality(Modality.APPLICATION_MODAL);
					//				Main.primaryStage=stage;
					//				stage.show();
					//				stage.setOnHidden(evv->{
					//					handleRefersh(new ActionEvent());
					//				});
				}
			}
		});
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
			FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/FormThongTinPhieuXuat.fxml"));
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
			System.out.println(e2.getMessage());
		}
	}


}
