package controllers;

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
import dao.QuanLyNhanVien;
import entities.Account;
import entities.NhanVien;
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

public class QuanLyNhanVienController implements Initializable{

	private TableView<NhanVien> tbl_view;
	@FXML private BorderPane bd;
	@FXML TextField txtMa;
	@FXML JFXButton btnThem;
	TableColumn<NhanVien, String> colMaNV;
	TableColumn<NhanVien, String> colChucVu;
	TableColumn<NhanVien, String> colDiaChi;
	TableColumn<NhanVien, String> colDienThoai;
	TableColumn<NhanVien, String> colGioiTinh;
	TableColumn<NhanVien, String> colLuongCoBan;
	TableColumn<NhanVien, String> colNamSinh;
	TableColumn<NhanVien, String> colTen;
	TableColumn<NhanVien, String> colCMND;

	QuanLyNhanVien qlNV=new QuanLyNhanVien();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tbl_view=new TableView<NhanVien>();
		colMaNV=new TableColumn<NhanVien, String>("Mã");
		colChucVu=new TableColumn<NhanVien, String>("Chức vụ");
		colDiaChi=new TableColumn<NhanVien, String>("Địa chỉ");
		colDienThoai=new TableColumn<NhanVien, String>("Điện thoại");
		colGioiTinh=new TableColumn<NhanVien, String>("Giới tính");
		colLuongCoBan=new TableColumn<NhanVien, String>("Lương co bản");
		colNamSinh=new TableColumn<NhanVien, String>("Năm sinh");
		colTen=new TableColumn<NhanVien, String>("Tên");
		colCMND=new TableColumn<NhanVien, String>("CMND");

		tbl_view.getColumns().addAll(colMaNV,colChucVu,colDiaChi,colDienThoai,colGioiTinh,colLuongCoBan,colNamSinh,colTen,colCMND);

		bd.setCenter(tbl_view);

		colMaNV.setCellValueFactory(new PropertyValueFactory<>("maNV"));
		colChucVu.setCellValueFactory(new PropertyValueFactory<>("chucVu"));
		colDiaChi.setCellValueFactory(new PropertyValueFactory<>("diaChi"));
		colDienThoai.setCellValueFactory(new PropertyValueFactory<>("dienThoai"));
		colGioiTinh.setCellValueFactory(new PropertyValueFactory<>("gioiTinh"));
		colLuongCoBan.setCellValueFactory(new PropertyValueFactory<>("luongCoBan"));
		colNamSinh.setCellValueFactory(new PropertyValueFactory<>("namSinh"));
		colTen.setCellValueFactory(new PropertyValueFactory<>("tenNV"));
		colCMND.setCellValueFactory(new PropertyValueFactory<>("CMND"));


		tbl_view.setOnMouseClicked(ev->{
			if(ev.getClickCount()==2) {
				int result=tbl_view.getSelectionModel().getSelectedIndex();
				if(result!=-1) {
					FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/FormThongTinNhanVien.fxml"));
	
					Parent root = null;
					try {
						root = loader.load();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	
					ThemNhanVien ctlMain=loader.getController();
	
					int colMa=tbl_view.getItems().get(result).getMaNV();
					String colTen=tbl_view.getItems().get(result).getTenNV();
					LocalDate colNamSinh=tbl_view.getItems().get(result).getNamSinh();
					String colDiaChi=tbl_view.getItems().get(result).getDiaChi();
					String colDienThoai=tbl_view.getItems().get(result).getDienThoai();
					String colCMND=tbl_view.getItems().get(result).getCMND();
					double colLuong=tbl_view.getItems().get(result).getLuongCoBan();
	
					NhanVien nv=qlNV.timMa(colMa);
					ctlMain.lblTitle.setText("Cập nhập nhân viên");
					ctlMain.txtMa.setText(String.valueOf(colMa));
					ctlMain.txtMa.setEditable(false);
					ctlMain.box.setValue(nv.getAccount().getUserName());
					ctlMain.txtTen.setText(colTen);
					ctlMain.txtNamSinh.setValue(colNamSinh);
					ctlMain.txtDiaChi.setText(colDiaChi);
					ctlMain.txtDienThoai.setText(colDienThoai);
					ctlMain.txtCMND.setText(colCMND);
					if(nv.getGioiTinh().equals("Nu")) {
						ctlMain.rdNu.setSelected(true);
					}else {
						ctlMain.rdNam.setSelected(true);
					}
					ctlMain.txtLuong.setText(String.valueOf(colLuong));
					
					File currentDirFile = new File("");
					String helper = currentDirFile.getAbsolutePath();
					String begin=kiemTraChuoi(helper);
					System.out.println("file:///"+begin+"/"+nv.getHinhAnh());
					Image image = new Image("file:///"+begin+"/src/"+nv.getHinhAnh());
					ctlMain.fileHinhCapNhap=begin+"/src/"+nv.getHinhAnh();
					ctlMain.img.setImage(image);
					
					
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

		UploaderDuLieuLenBang();
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
	private void UploaderDuLieuLenBang(){
		List<NhanVien> accs=QuanLyNhanVien.showTatCaNhanVien();
		accs.forEach(t->{
			tbl_view.getItems().add(t);
		});
	}
	private void handleRefersh(ActionEvent e) {
		tbl_view.getItems().clear();
		UploaderDuLieuLenBang();
	}
	public void btnNhapThongTinNhanVien(ActionEvent e) throws IOException {
		try {
			FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/FormThongTinNhanVien.fxml"));
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
	public void thongBaoKieuLoi(ActionEvent e, String text) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Dialog");
		alert.setHeaderText(null);
		alert.setContentText(text);
		alert.initOwner(((Node) (e.getSource())).getScene().getWindow());
		alert.showAndWait();
	}
	public void btnXoaNhanVien(ActionEvent e) {
		int result=tbl_view.getSelectionModel().getSelectedIndex();
		if(result!=-1) {
			Alert alert = new Alert(AlertType.WARNING, "bạn có chắc muốn xóa",ButtonType.OK,ButtonType.CANCEL);
			alert.setTitle("Cảnh báo");
			Optional<ButtonType> resultx = alert.showAndWait();

			if (resultx.get() == ButtonType.OK) {
				int acc=tbl_view.getItems().get(result).getMaNV();
				NhanVien nv=QuanLyNhanVien.timMa(tbl_view.getItems().get(result).getMaNV());
				if(QuanLyNhanVien.xoaNV(acc)==true) {
					File currentDirFile = new File("");
					String helper = currentDirFile.getAbsolutePath();
					String begin=kiemTraChuoi(helper);
					xoaFile(begin+"/src/"+nv.getHinhAnh());
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
	public void btnSuaTaiKhoan(ActionEvent e) throws IOException {


		int result=tbl_view.getSelectionModel().getSelectedIndex();

		if(result!=-1) {
			FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/FormThongTinNhanVien.fxml"));

			Parent root=loader.load();

			ThemNhanVien ctlMain=loader.getController();

			int colMa=tbl_view.getItems().get(result).getMaNV();
			String colTen=tbl_view.getItems().get(result).getTenNV();
			LocalDate colNamSinh=tbl_view.getItems().get(result).getNamSinh();
			String colDiaChi=tbl_view.getItems().get(result).getDiaChi();
			String colDienThoai=tbl_view.getItems().get(result).getDienThoai();
			double colLuong=tbl_view.getItems().get(result).getLuongCoBan();

			NhanVien nv=qlNV.timMa(colMa);
			ctlMain.lblTitle.setText("Cập nhập nhân viên");
			ctlMain.txtMa.setText(String.valueOf(colMa));
			ctlMain.txtMa.setEditable(false);
			ctlMain.box.setValue(nv.getAccount().getUserName());
			ctlMain.txtTen.setText(colTen);
			ctlMain.txtNamSinh.setValue(colNamSinh);
			ctlMain.txtDiaChi.setText(colDiaChi);
			ctlMain.txtDienThoai.setText(colDienThoai);
			if(nv.getGioiTinh().equals("Nu")) {
				ctlMain.rdNu.setSelected(true);
			}else {
				ctlMain.rdNam.setSelected(true);
			}
			ctlMain.txtLuong.setText(String.valueOf(colLuong));

			Stage stage=new Stage();
			stage.initStyle(StageStyle.UNDECORATED);
			stage.setScene(new Scene(root));
			Main.primaryStage=stage;
			stage.show();
			stage.getIcons().add(new Image("/image/logo.PNG"));
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

	@FXML 
	public void btnTim(ActionEvent e) {
		NhanVien acc=null;
		String text=txtMa.getText().trim().toString();
		if (text.isEmpty()==false) {
			try {
				acc=QuanLyNhanVien.timMa(Integer.parseInt(text));
			} catch (Exception e2) {
				// TODO: handle exception
				thongBaoKieuLoi(e, "yêu cầu nhập số");
			}
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
