package controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import application.Main;
import dao.QuanLyAccount;
import dao.QuanLyKhachHang;
import dao.QuanLyNhanVien;
import dao.QuanLyPhieuXuat;
import dao.QuanLyXe;
import entities.Account;
import entities.CTPhieuXuat;
import entities.KhachHang;
import entities.NhanVien;
import entities.PhieuXuat;
import entities.Xe;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
public class ThemPhieuXuat implements Initializable{
	@FXML ImageView img;
	@FXML ComboBox<String> boxMaXe;
	@FXML ComboBox<String> boxMaNV;
	@FXML ComboBox<String> boxMaKH;
	@FXML Label lblTenXe;
	@FXML Label lblMauXe;
	@FXML Label lblBH;
	@FXML Label lblLoaiXe;
	@FXML Label lblNhaSX;
	
	@FXML JFXTextField txtPX;
	@FXML JFXDatePicker txtNgayXuat;
	@FXML JFXTextField txtDonGiaXuat;
	@FXML JFXTextField txtSoLuongXuat;
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
    public void thongBaoKieuLoi(ActionEvent e, String text) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Dialog");
		alert.setHeaderText(null);
		alert.setContentText(text);
		alert.initOwner(((Node) (e.getSource())).getScene().getWindow());
		alert.showAndWait();
	}
    @FXML void btnClickOK(ActionEvent e) {
    	int mapx=Integer.parseInt(txtPX.getText().toString());
    	NhanVien nv=QuanLyNhanVien.timMa(Integer.parseInt(boxMaNV.getValue()));
    	if(nv!=null) {
    		KhachHang kh=QuanLyKhachHang.timMa(Integer.parseInt(boxMaKH.getValue()));
    		if(kh!=null) {
    			LocalDate ngayXuat=txtNgayXuat.getValue();
    			PhieuXuat px=new PhieuXuat(mapx, nv, kh, ngayXuat);
    			Xe xe=QuanLyXe.timMa(boxMaXe.getValue());
    			if(xe!=null) {
    				if(QuanLyPhieuXuat.themPhieuXuat(px)==true) {
    					int donGia=Integer.parseInt(txtDonGiaXuat.getText().toString());
    					int slXuat=Integer.parseInt(txtSoLuongXuat.getText().toString());
        				CTPhieuXuat ctPX=new CTPhieuXuat(px, xe, donGia, slXuat, 10);
        				if(QuanLyPhieuXuat.themChiTietPhieuXuat(ctPX)==true) {
        					((Node) (e.getSource())).getScene().getWindow().hide();
        				}else {
        					System.out.println("Lỗi thêm chi tiết phiếu xuất");
        				}
        			}else {
        				thongBaoKieuLoi(e, "Thêm phiếu xuất không thành công");
        			}
    			}else {
    				thongBaoKieuLoi(e,"xe không tồn tại");
    			}
    			
    		}
    	}else {
    		thongBaoKieuLoi(e, "Nhân viên không tồn tại");
    	}
    	
    	
    }
    @FXML 
    private void btnXoaRong(ActionEvent e) {
    	txtPX.setText("");
    	boxMaNV.setValue("");
    	boxMaKH.setValue("");
    	txtNgayXuat.setValue(LocalDate.of(2000, 11, 1));
    	boxMaXe.setValue("");
    	txtDonGiaXuat.setText("");
    	txtSoLuongXuat.setText("");
    	
    	lblTenXe.setText("");
		lblMauXe.setText("");
		lblBH.setText("");
		lblLoaiXe.setText("");
		lblNhaSX.setText("");
		Image image = new Image("/image/Blade-110C_den.PNG");
		img.setImage(image);
    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		//box MaNV
		boxMaNV.setEditable(true);
		ObservableList<String> items0 = FXCollections.observableArrayList();
		List<NhanVien> kh0=QuanLyNhanVien.showTatCaNhanVien();
		kh0.forEach(t->{
			items0.add(String.valueOf(t.getMaNV()));
		});
		FilteredList<String> filteredItems0 = new FilteredList<String>(items0);
		boxMaNV.getEditor().textProperty().addListener(new InputFilter(boxMaNV, filteredItems0, false));
		boxMaNV.setItems(filteredItems0);
		
		//box MaKH
		boxMaKH.setEditable(true);
		ObservableList<String> items1 = FXCollections.observableArrayList();
		List<KhachHang> kh1=QuanLyKhachHang.showTatCaKhachHang();
		kh1.forEach(t->{
			items1.add(String.valueOf(t.getMaKH()));
		});
		FilteredList<String> filteredItems1 = new FilteredList<String>(items1);
		boxMaKH.getEditor().textProperty().addListener(new InputFilter(boxMaKH, filteredItems1, false));
		boxMaKH.setItems(filteredItems1);

		
		//filter ma xe
		boxMaXe.setEditable(true);
		ObservableList<String> items = FXCollections.observableArrayList();

		List<Xe> accs=QuanLyXe.showTatCaXe();
		accs.forEach(t->{
			items.add(t.getMaXe());
		});
		FilteredList<String> filteredItems = new FilteredList<String>(items);

		boxMaXe.getEditor().textProperty().addListener(new InputFilter(boxMaXe, filteredItems, false));

		boxMaXe.setItems(filteredItems);
		
		boxMaXe.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() { 
			public void changed(ObservableValue ov, Number value, Number new_value) 
			{ 
				Xe xe=QuanLyXe.timMa(boxMaXe.getValue());
				if(xe!=null) {
					lblTenXe.setText(xe.getTenXe());
					lblMauXe.setText(xe.getMauXe());
					lblBH.setText(xe.getThongTinBaoHanh());
					lblLoaiXe.setText(xe.getLoaiXe());
					lblNhaSX.setText(xe.getNhaSX());
					if(xe.getTenXe().equals("Blade-110C")) {
						if(xe.getMauXe().length()==10) {
							Image image = new Image("/image/Blade-110C_XanhDuong.PNG");
							img.setImage(image);

						}else {
							Image image = new Image("/image/Blade-110C_den.PNG");
							img.setImage(image);
						}
					}else if(xe.getTenXe().equals("SUPER-CUB")) {
						Image image = new Image("/image/SUPER-CUB_XanhDuong.PNG");
						img.setImage(image);
					}else if(xe.getTenXe().equals("SH-300c")) {
						if(xe.getMauXe().length()==3) {
							Image image = new Image("/image/SH-300c_den.PNG");
							img.setImage(image);
						}else {
							System.out.println("false");
							Image image = new Image("/image/SH-300c_trang.PNG");
							img.setImage(image);
						}
					}else if(xe.getTenXe().equals("Vision-110C")) {
						if(xe.getMauXe().length()==2) {
							Image image = new Image("/image/Vision-110C_do.PNG");
							img.setImage(image);
						}else if(xe.getMauXe().length()==10) {
							Image image = new Image("/image/Vision-110C_xanhDuong.PNG");
							img.setImage(image);
						}else {
							Image image = new Image("/image/Vision-110C_Vang.PNG");
							img.setImage(image);
						}
					}
				}else {
					lblTenXe.setText("");
					lblMauXe.setText("");
					lblBH.setText("");
					lblLoaiXe.setText("");
					lblNhaSX.setText("");
					Image image = new Image("/image/Blade-110C_den.PNG");
					img.setImage(image);
					
				}
			}
		});
	}
	public void btnCLoseWindow(ActionEvent e) throws IOException {
		((Node) (e.getSource())).getScene().getWindow().hide();
	}

}
