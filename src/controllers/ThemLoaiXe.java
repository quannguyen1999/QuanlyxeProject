package controllers;

import java.awt.ImageCapabilities;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextField;

import application.Main;
import dao.QuanLyAccount;
import dao.QuanLyLoaiXe;
import entities.Account;
import entities.Loaixe;
import entities.NhanVien;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class ThemLoaiXe implements Initializable{
	private double xOffset = 0;
	private double yOffset = 0;
	@FXML Label lblTitle;
	@FXML ComboBox<String> chkNSX;
	@FXML ComboBox<String> chkMauXe;
	@FXML public BorderPane mainBd;
	@FXML ImageView img;
	@FXML JFXTextField txtMaLoai;
	@FXML JFXTextField txtLoaiXe;
	@FXML JFXTextField txtNhanHieu;
	@FXML JFXTextField txtTenXe;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		chkNSX.setEditable(true);
		chkMauXe.setEditable(true);
		loadDuLieuNuocSanXuat();
		loadDuLieuMauXe();

	}
	String fileHinhCapNhap="";
	String fileHinh="";
	public void btnChonHinh(ActionEvent e) {
		FileChooser fc = new FileChooser();
		fc.getExtensionFilters().add(new ExtensionFilter("PNG Files", "*.PNG"));
		List<File> f=fc.showOpenMultipleDialog(null);
		for(File file:f) {
			fileHinh=file.getAbsolutePath();
			Image image = new Image("file:///"+file.getAbsolutePath());
			img.setImage(image);
		}
	}
	public  void loadDuLieuMauXe() {
		ObservableList<String> items = FXCollections.observableArrayList();

		List<String> listMauXe= Arrays.asList("Xanh dương","Đỏ","Vàng","Đen","Trắng","Hồng","Tím");

		listMauXe.forEach(t->{
			items.add(t);
		});

		FilteredList<String> filteredItems = new FilteredList<String>(items);

		chkMauXe.getEditor().textProperty().addListener(new InputFilter(chkMauXe, filteredItems, false));

		chkMauXe.setItems(filteredItems);
	}
	public  void loadDuLieuNuocSanXuat() {
		ObservableList<String> items = FXCollections.observableArrayList();

		String[] locales = Locale.getISOCountries();

		for (String countryCode : locales) {
			Locale obj = new Locale("", countryCode);
			items.add(obj.getDisplayCountry());
		}

		FilteredList<String> filteredItems = new FilteredList<String>(items);

		chkNSX.getEditor().textProperty().addListener(new InputFilter(chkNSX, filteredItems, false));

		chkNSX.setItems(filteredItems);


	}
	private void makeStageDrageable() {
		mainBd.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				xOffset = event.getSceneX();
				yOffset = event.getSceneY();
			}
		});
		mainBd.setOnMouseDragged(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {

				Main.primaryStage.setX(event.getScreenX() - xOffset);
				Main.primaryStage.setY(event.getScreenY() - yOffset);
				Main.primaryStage.setOpacity(0.7f);
			}
		});
		mainBd.setOnDragDone((e) -> {
			Main.primaryStage.setOpacity(1.0f);
		});
		mainBd.setOnMouseReleased((e) -> {
			Main.primaryStage.setOpacity(1.0f);
		});

	}
	public void btnCLoseWindow(ActionEvent e) throws IOException {
		((Node) (e.getSource())).getScene().getWindow().hide();
	}
	public void btnXoaRong(ActionEvent e) {
		txtMaLoai.setText("");;
		txtLoaiXe.setText("");;
		txtNhanHieu.setText("");;
		txtTenXe.setText("");;
		fileHinh="";
		chkMauXe.setValue("");
		chkNSX.setValue("");
		img.setImage(new Image("/image/honda-power-products-vector-logo-small.png"));
	}
	public void thongBaoKieuLoi(ActionEvent e, String text) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Dialog");
		alert.setHeaderText(null);
		alert.setContentText(text);
		alert.initOwner(((Node) (e.getSource())).getScene().getWindow());
		alert.showAndWait();
	}
	private static void copyFileUsingStream(File source, File dest) throws IOException {
		InputStream is = null;
		OutputStream os = null;
		try {
			is = new FileInputStream(source);
			os = new FileOutputStream(dest);
			byte[] buffer = new byte[1024];
			int length;
			while ((length = is.read(buffer)) > 0) {
				os.write(buffer, 0, length);
			}
		}finally {
			is.close();
			os.close();
		}
	}
	public void clickOk(ActionEvent e) throws IOException {
		String maloai=txtMaLoai.getText().toString();
		String loaixe=txtLoaiXe.getText().toString();
		String tenxe=txtTenXe.getText().toString();
		String mauson=chkMauXe.getValue();
		String nuocSX=chkNSX.getValue();
		String nhanhieu=txtNhanHieu.getText().toString();
		Loaixe lx=new Loaixe(maloai, loaixe, tenxe, mauson, nuocSX, "image/"+maloai+".PNG", nhanhieu);
		//		System.out.println("alo:"+fileHinh);
		if(maloai.isEmpty()==false
				&& loaixe.isEmpty()==false
				&& tenxe.isEmpty()==false 
				&& mauson.isEmpty()==false
				&& nuocSX.isEmpty()==false 
				&& nhanhieu.isEmpty()==false) {
			if(lblTitle.getText().toString().equals("Cập nhập loại xe")) {
				if(fileHinhCapNhap.contentEquals(fileHinh)) {
				}else if(fileHinh.isEmpty()==false){
					copyFileUsingStream(new File(fileHinh),new File("src/image/"+maloai+".PNG"));
				}
				Loaixe lxe=new Loaixe(maloai, loaixe, tenxe, mauson, nuocSX, "image/"+maloai+".PNG", nhanhieu);
				List<String> kiemTraTonTai=QuanLyLoaiXe.timMa(lxe.getLoaixe(), lxe.getTenxe(), lxe.getMauson());
				if(kiemTraTonTai.toString().toString()=="[]") {
					if(QuanLyLoaiXe.suaLoaiXe(lxe)==true) {
						((Node) (e.getSource())).getScene().getWindow().hide();
					}else {
						thongBaoKieuLoi(e, "sửa không thành công");
					}
				}else {
					thongBaoKieuLoi(e, "xe đã tồn tại");
				}
				
			}else {
				if(fileHinh.isEmpty()==false) {
					Loaixe lxe=new Loaixe(maloai, loaixe, tenxe, mauson, nuocSX, "image/"+maloai+".PNG", nhanhieu);
					if(lxe!=null) {
						List<String> kiemTraTonTai=QuanLyLoaiXe.timMa(lxe.getLoaixe(), lxe.getTenxe(), lxe.getMauson());
						if(kiemTraTonTai.toString().toString()=="[]") {
							if(QuanLyLoaiXe.themXe(lxe)==true) {
								copyFileUsingStream(new File(fileHinh),new File("src/image/"+maloai+".PNG"));
								((Node) (e.getSource())).getScene().getWindow().hide();
							}else {
								thongBaoKieuLoi(e, "thêm không thành công");
							}
						}else {
							thongBaoKieuLoi(e, "xe đã tồn tại");
						}
					}
				}else {
					thongBaoKieuLoi(e, "vui lòng chọn hình");

				}
			}
		}else {
			thongBaoKieuLoi(e, "yêu cầu nhập đầy đủ và hợp lệ");
		}

	}
}
