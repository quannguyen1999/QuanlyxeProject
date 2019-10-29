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
		txtNamSinh.setEditable(false);
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
	public boolean kiemTraMaNhanVien(ActionEvent e,String ma) {
		System.out.println("đã vào mã");
		String MaKT=ma.trim();
		if(MaKT.isEmpty()==false) {
			if(MaKT.length()==5) {
				if(MaKT.matches("[0-9]+")==true) {
					return true;
				}else {
					thongBaoKieuLoi(e, "Mã nhân viên chỉ nhập số");
					return false;
				}
			}else {
				thongBaoKieuLoi(e, "Ký tự mã nhân viên phải lớn hơn 5");
				return false;
			}
		}else {
			System.out.println("mã nhân trống");
			thongBaoKieuLoi(e, "Mã nhân viên không được để trống");
			return false;
		}

	}
	public boolean kiemTraTenNhanVien(ActionEvent e,String ma) {
		String MaKT=ma.trim();
		if(MaKT.isEmpty()==false) {
			if(MaKT.matches("^[A-Za-z\\sÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ]+$")==true) {
				return true;
			}else {
				thongBaoKieuLoi(e, "Tên nhân viên không được nhập ký tự đặc biệt");
				return false;
			}
		}else {
			thongBaoKieuLoi(e, "Tên nhân viên không được để trống");
			return false;
		}
	}
	public boolean kiemTraCMND(ActionEvent e,String ma) {
		String MaKT=ma.trim();
		if(MaKT.isEmpty()==false) {
			if(MaKT.length()==9) {
				if(MaKT.matches("[0-9]{9}+")==true) {
					return true;
				}else {
					thongBaoKieuLoi(e, "Mã nhân viên chỉ nhập số");
					return false;
				}
			}else {
				thongBaoKieuLoi(e, "chứng minh nhân dân phải 9 số");
				return false;
			}
		}else {
			thongBaoKieuLoi(e, "chứng minh nhân dân không được để trống");
			return false;
		}
	}
	public boolean kiemTraDiaChi(ActionEvent e,String ma) {
		String MaKT=ma.trim();
		if(MaKT.isEmpty()==false) {
			if(MaKT.matches("^[A-Za-z/,\\sÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ]+$")==true) {
				return true;
			}else {
				thongBaoKieuLoi(e, "địa chỉ không được nhập ký tự đặc biệt");
				return false;
			}
		}else {
			thongBaoKieuLoi(e, "địa chỉ không được để trống");
			return false;
		}
	}
	public boolean kiemTraDienThoai(ActionEvent e,String ma) {
		String MaKT=ma.trim();
		if(MaKT.isEmpty()==false) {
			if(MaKT.length()==10) {
				if(MaKT.matches("[0-9]{10}+")==true) {
					return true;
				}else {
					thongBaoKieuLoi(e, "số điện thoại chỉ nhập số");
					return false;
				}
			}else {
				thongBaoKieuLoi(e, "số điện thoại phải 10 số");
				return false;
			}
		}else {
			thongBaoKieuLoi(e, "Số điện thoại không được để trống");
			return false;
		}
	}
	public boolean kiemTraLuong(ActionEvent e,String ma) {
		String MaKT=ma.trim();
		if(MaKT.isEmpty()==false) {
			if(MaKT.matches("[0-9]+")==true) {
				return true;
			}else {
				thongBaoKieuLoi(e, "tiền lương chỉ nhập số");
				return false;
			}
		}else {
			thongBaoKieuLoi(e, "lương không được để trống");
			return false;
		}
	}
	public boolean kiemTraUserName(ActionEvent e,String ma) {
		String MaKT=ma.trim();
		if(MaKT.isEmpty()==false) {
			return true;
		}else {
			thongBaoKieuLoi(e, "username không được để trống");
			return false;
		}
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
			boolean ktChuoi=true;
			String textMa=txtMa.getText().toString();
			String textTen=txtTen.getText().toString();
			String textDiaChi= txtDiaChi.getText().toString();
			String textDienThoai=txtDienThoai.getText().toString();
			String textLuong=txtLuong.getText().toString();
			String textCMND=txtCMND.getText().toString();

			if(kiemTraMaNhanVien(e, textMa)==false) {
				ktChuoi=false;
				txtMa.requestFocus();
			}
			if(ktChuoi==true) {
				if(box.getValue()==null) {
					thongBaoKieuLoi(e, "Bạn chưa nhập mã");
					ktChuoi=false;
				}
			}

			if(ktChuoi==true) {
				if(kiemTraTenNhanVien(e, textTen)==false) {
					ktChuoi=false;
					txtTen.requestFocus();
				}
			}

			if(ktChuoi==true) {
				if(txtNamSinh.getValue()==null) {
					thongBaoKieuLoi(e, "Bạn chưa nhập ngày sinh");
					ktChuoi=false;
				}
			}


			if(ktChuoi==true) {
				if(kiemTraCMND(e, textCMND)==false) {
					ktChuoi=false;
					txtCMND.requestFocus();
				}
			}

			if(ktChuoi==true) {
				if(kiemTraDiaChi(e, textDiaChi)==false) {
					ktChuoi=false;
				}
			}

			if(ktChuoi==true) {
				if(kiemTraDienThoai(e, textDienThoai)==false) {
					ktChuoi=false;
					txtDienThoai.requestFocus();
				}
			}

			if(ktChuoi==true) {
				if(kiemTraLuong(e, textLuong)==false) {
					ktChuoi=false;
					txtLuong.requestFocus();
				}
			}
			if(ktChuoi==true) {
				Account acc=ql.timMa(box.getValue());
				if(acc!=null) {
					if(textMa.isEmpty()==false
							&& textTen.isEmpty()==false
							&& textDiaChi.isEmpty()==false
							&& textDienThoai.isEmpty()==false 
							&& textLuong.isEmpty()==false
							&& textCMND.isEmpty()==false
							) {
						NhanVien nv=null;
						if(lblTitle.getText().toString().equals("Cập nhập nhân viên")) {
							if(fileHinhCapNhap.contentEquals(fileHinh)) {
							}else if(fileHinh.isEmpty()==false){
								copyFileUsingStream(new File(fileHinh),new File("src/image/"+textMa+".PNG"));
							}
							if(rdNam.isSelected()==true) {
								nv=new NhanVien(Integer.parseInt(textMa), acc.getLoaiTK(), textDiaChi,textDienThoai,rdNam.getText().toString(),Double.parseDouble(textLuong),txtNamSinh.getValue(),textTen,acc,"image/"+textMa+".PNG",textCMND);
							}else {
								nv=new NhanVien(Integer.parseInt(textMa), acc.getLoaiTK(), textDiaChi,textDienThoai,"Nữ",Double.parseDouble(textLuong),txtNamSinh.getValue(),textTen,acc,"image/"+textMa+".PNG",textCMND);
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
										box.requestFocus();
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
					box.requestFocus();
				}
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
