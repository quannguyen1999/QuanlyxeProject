package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;

import application.Main;
import dao.QuanLyAccount;
import dao.QuanLyNhanVien;
import entities.Account;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class ThemNhanVien implements Initializable{
	private double xOffset = 0;
	private double yOffset = 0;
	QuanLyAccount ql=new QuanLyAccount();
	QuanLyNhanVien qlNV=new QuanLyNhanVien();
	@FXML public BorderPane mainBd;
	@FXML JFXTextField txtMa;
	@FXML JFXTextField txtTen;
	@FXML JFXDatePicker txtNamSinh;
	@FXML JFXTextField txtDiaChi;
	@FXML ComboBox<String> box;
	@FXML JFXTextField txtDienThoai;
	@FXML JFXTextField txtLuong;
	@FXML JFXTextField txtCMND;
	@FXML JFXRadioButton rdNam;
	@FXML JFXRadioButton rdNu;
	@FXML Label lblTitle;
	@FXML ImageView img;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		makeStageDrageable();
		box.setEditable(true);
		loadDuLieu();
	}

	public  void loadDuLieu() {
		ObservableList<String> items = FXCollections.observableArrayList();

		List<Account> accs=QuanLyAccount.showTatCaAccount();
		accs.forEach(t->{
			Account newAccount= new Account(t.getUserName(), t.getPassword(), t.getLoaiTK());
			items.add(newAccount.getUserName());
		});
		FilteredList<String> filteredItems = new FilteredList<String>(items);

		box.getEditor().textProperty().addListener(new InputFilter(box, filteredItems, false));

		box.setItems(filteredItems);

		txtNamSinh.setEditable(false);

	}

	public void btnCLoseWindow(ActionEvent e) throws IOException {
		((Node)(e.getSource())).getScene().getWindow().hide();  
	}
	public void thongBaoKieuLoi(ActionEvent e, String text) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Dialog");
		alert.setHeaderText(null);
		alert.setContentText(text);
		alert.initOwner(((Node) (e.getSource())).getScene().getWindow());
		alert.showAndWait();
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
	public void btnThem(ActionEvent e) throws IOException {
		try {
			Account acc=ql.timMa(box.getValue());
			if(acc!=null) {
				String textMa=txtMa.getText().toString();
				String textTen=txtTen.getText().toString();
				String textNamSinh=txtNamSinh.getValue().toString();
				String textDiaChi= txtDiaChi.getText().toString();
				String textDienThoai=txtDienThoai.getText().toString();
				String textLuong=txtLuong.getText().toString();
				String textCMND=txtCMND.getText().toString();
				if(textMa.isEmpty()==false
						&& textTen.isEmpty()==false
						&& textNamSinh.isEmpty()==false 
						&& textDiaChi.isEmpty()==false
						&& textDienThoai.isEmpty()==false 
						&& textLuong.isEmpty()==false
						&& textCMND.isEmpty()==false
						) {
					NhanVien nv=null;
					if(lblTitle.getText().toString().equals("Cập nhập nhân viên")) {
						System.out.println(fileHinh);
						System.out.println(fileHinhCapNhap);
						if(fileHinhCapNhap.contentEquals(fileHinh)) {
						}else if(fileHinh.isEmpty()==false){
							copyFileUsingStream(new File(fileHinh),new File("src/image/"+textMa+".PNG"));
						}
						if(rdNam.isSelected()==true) {
							nv=new NhanVien(Integer.parseInt(textMa), acc.getLoaiTK(), textDiaChi,textDienThoai,rdNam.getText().toString(),Double.parseDouble(textLuong),txtNamSinh.getValue(),textTen,acc,"image/"+textMa+".PNG",textCMND);
						}else {
							nv=new NhanVien(Integer.parseInt(textMa), acc.getLoaiTK(), textDiaChi,textDienThoai,"Nu",Double.parseDouble(textLuong),txtNamSinh.getValue(),textTen,acc,"image/"+textMa+".PNG",textCMND);
						}
						if(qlNV.suaNV(nv)==true) {
							((Node) (e.getSource())).getScene().getWindow().hide();
						}else {
							thongBaoKieuLoi(e, "sửa không thành công");
						}
					}else {
						if(fileHinh.isEmpty()==false) {
							if(rdNam.isSelected()==true) {
								nv=new NhanVien(Integer.parseInt(textMa), acc.getLoaiTK(), textDiaChi,textDienThoai,rdNam.getText().toString(),Double.parseDouble(textLuong),txtNamSinh.getValue(),textTen,acc,"image/"+textMa+".PNG",textCMND);
							}else {
								nv=new NhanVien(Integer.parseInt(textMa), acc.getLoaiTK(), textDiaChi,textDienThoai,"Nu",Double.parseDouble(textLuong),txtNamSinh.getValue(),textTen,acc,"image/"+textMa+".PNG",textCMND);
							}
							if(nv!=null) {
								int result=qlNV.themNV(nv);
								if(result==-1) {
									thongBaoKieuLoi(e, "usename đã có người sử dụng");
								}else if(result==0) {
									thongBaoKieuLoi(e, "bị trùng mã");
								}else {
									copyFileUsingStream(new File(fileHinh),new File("src/image/"+textMa+".PNG"));
									((Node) (e.getSource())).getScene().getWindow().hide();
								}
							}else {
								thongBaoKieuLoi(e, "thêm không thành công");
							}
						}else {
							thongBaoKieuLoi(e, "vui lòng chọn hình");
						}
					}
				}else {
					thongBaoKieuLoi(e, "yêu cầu nhập đầy đủ và hợp lệ");
				}
			}else {
				thongBaoKieuLoi(e, "username không tồn tại");
			}
		} catch (Exception e2) {
			System.out.println(e2.getMessage());
		}
	}

	@FXML
	public void btnXoaRong(ActionEvent e) {
		if(lblTitle.getText().toString().equals("Cập nhập nhân viên")==false) {
			txtMa.setText("");
		}
		txtTen.setText("");
		box.setValue("");
		txtNamSinh.setValue(LocalDate.of(2000,1,1));
		txtDiaChi.setText("");
		txtDienThoai.setText("");
		txtLuong.setText("");
		txtCMND.setText("");
		rdNam.setSelected(true);
		//		
		//		try {
		//			copyFileUsingStream(new File(fileHinh),new File("src/image/xeMoi.png"));
		//		} catch (IOException e1) {
		//			System.out.println(e1.getMessage());
		//			// TODO Auto-generated catch block
		//		}
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
}
