package controllers;


import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import application.Main;
import dao.QuanLyAccount;
import dao.QuanLyLoaiXe;
import dao.QuanLyNhanVien;
import dao.QuanLyPhieuXuat;
import entities.Account;
import entities.Loaixe;
import entities.NhanVien;
import entities.PhieuXuat;
import entities.Xe;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class QuanLyLoaiXeController implements Initializable{
	@FXML 
	TextField txtMa;
	@FXML 
	private BorderPane bd;
	@FXML JFXButton btnThem;
	private TableView<Loaixe> tbl_view;
	TableColumn<Loaixe, String> colMaLoai;
	TableColumn<Loaixe, String> colLoaiXe;
	TableColumn<Loaixe, String> colMauSon;
	TableColumn<Loaixe, String> colNhanHieu;
	TableColumn<Loaixe, String> colNuocSanXuat;
	TableColumn<Loaixe, String> colTenXe;
	public void initialize(URL arg0, ResourceBundle arg1) {
		tbl_view=new TableView<Loaixe>();
		colMaLoai=new TableColumn<Loaixe, String>("Mã loại");
		colLoaiXe=new TableColumn<Loaixe, String>("Loại xe");
		colMauSon=new TableColumn<Loaixe, String>("Màu xe");
		colNhanHieu=new TableColumn<Loaixe, String>("Nhẵn hiệu");
		colNuocSanXuat=new TableColumn<Loaixe, String>("Nước sản xuất");
		colTenXe=new TableColumn<Loaixe, String>("Tên xe");
		

		tbl_view.getColumns().addAll(colMaLoai,colLoaiXe,colMauSon,colNhanHieu,colNuocSanXuat,colTenXe);
		

		bd.setCenter(tbl_view);
		

		colMaLoai.setCellValueFactory(new PropertyValueFactory<>("maloai"));
		colLoaiXe.setCellValueFactory(new PropertyValueFactory<>("loaixe"));
		colMauSon.setCellValueFactory(new PropertyValueFactory<>("mauson"));
		colNhanHieu.setCellValueFactory(new PropertyValueFactory<>("nhanhieu"));
		colNuocSanXuat.setCellValueFactory(new PropertyValueFactory<>("nuocSX"));
		colTenXe.setCellValueFactory(new PropertyValueFactory<>("tenxe"));
		
		UploaderDuLieuLenBang();
		
		tbl_view.setOnMouseClicked(ev->{
			if(ev.getClickCount()==2) {
				int result=tbl_view.getSelectionModel().getSelectedIndex();
				if(result!=-1) {
					FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/FormThongTinLoaiXe.fxml"));
	
					Parent root = null;
					try {
						root = loader.load();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	
					ThemLoaiXe ctlMain=loader.getController();
	
					String maloai=tbl_view.getItems().get(result).getMaloai();
					String loaixe=tbl_view.getItems().get(result).getLoaixe();
					String mauson=tbl_view.getItems().get(result).getMauson();
					String nhanhieu=tbl_view.getItems().get(result).getNhanhieu();
					String nuocSX=tbl_view.getItems().get(result).getNuocSX();
					String tenxe=tbl_view.getItems().get(result).getTenxe();
					
	
					Loaixe xe=QuanLyLoaiXe.timMa(maloai);
					ctlMain.lblTitle.setText("Cập nhập loại xe");
					ctlMain.txtMaLoai.setText(maloai);
					ctlMain.txtMaLoai.setEditable(false);
					ctlMain.txtLoaiXe.setText(loaixe);
					ctlMain.chkMauXe.setValue(mauson);
					ctlMain.txtNhanHieu.setText(nhanhieu);
					ctlMain.txtTenXe.setText(tenxe);
					ctlMain.chkNSX.setValue(nuocSX);
					File currentDirFile = new File("");
					String helper = currentDirFile.getAbsolutePath();
					String begin=kiemTraChuoi(helper);
					System.out.println("file:///"+begin+"/"+xe.getHinhanh());
					Image image = new Image("file:///"+begin+"/src/"+xe.getHinhanh());
					ctlMain.fileHinhCapNhap=begin+"/src/"+xe.getHinhanh();
					ctlMain.img.setImage(image);
					
					Stage stage=new Stage();
					stage.initOwner(btnThem.getScene().getWindow());
					stage.setScene(new Scene(root));
					stage.initStyle(StageStyle.UNDECORATED);
					stage.initModality(Modality.APPLICATION_MODAL);
					Main.primaryStage=stage;
					stage.getIcons().add(new Image("/image/logo.PNG"));
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
		List<Loaixe> accs=QuanLyLoaiXe.showTatCaLoaiXe();
		accs.forEach(t->{
			tbl_view.getItems().add(t);
		});
	}
	@FXML
	private void btnXoaRong(ActionEvent e) {
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
	private static String kiemTraChuoi(String text) {
		String newTextResult="";
		for(int i=0;i<=text.length()-1;i++) {
			if((int)text.charAt(i)==92) {
				newTextResult+="/";
			}else {
				newTextResult+=text.charAt(i);
			}
		}
		return newTextResult;
	}
	private void handleRefersh(ActionEvent e) {
		tbl_view.getItems().clear();
		UploaderDuLieuLenBang();
	}
	public void btnXoaLoaiXe(ActionEvent e) {
		int result=tbl_view.getSelectionModel().getSelectedIndex();
		if(result!=-1) {
			Alert alert = new Alert(AlertType.WARNING, "bạn có chắc muốn xóa",ButtonType.OK,ButtonType.CANCEL);
			alert.setTitle("Cảnh báo");
			Optional<ButtonType> resultx = alert.showAndWait();

			if (resultx.get() == ButtonType.OK) {
				String acc=tbl_view.getItems().get(result).getMaloai();
				Loaixe xe=QuanLyLoaiXe.timMa(acc);
				if(QuanLyLoaiXe.xoaLoaiXe(acc)==true) {
					File currentDirFile = new File("");
					String helper = currentDirFile.getAbsolutePath();
					String begin=kiemTraChuoi(helper);
					xoaFile(begin+"/src/"+xe.getHinhanh());
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
	private static void xoaFile(String File) {
		System.out.println(File);
        File file = new File(File); 
          
        if(file.delete()) 
        { 
            System.out.println("File deleted successfully"); 
        } 
        else
        { 
            System.out.println("Failed to delete the file"); 
        } 
	}
	public void btnNhapThongTinLoaiXe(ActionEvent e) throws IOException {
		try {
			FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/FormThongTinLoaiXe.fxml"));
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
			// TODO: handle exception
			System.out.println(e2.getMessage());
		}
	}
	@FXML 
	public void btnTim(ActionEvent e) {
		String text=txtMa.getText().trim().toString();
		if (text.isEmpty()==false) {
			Loaixe acc=QuanLyLoaiXe.timMa(text);
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
	
}
