package controllers;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;

import application.Main;
import dao.QuanLyAccount;
import dao.QuanLyLoaiXe;
import dao.QuanLyNhanVien;
import dao.QuanLyXe;
import entities.Account;
import entities.Loaixe;
import entities.NhanVien;
import entities.Xe;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
public class QuanLyXeController implements Initializable{
	@FXML JFXButton btnThem;
	@FXML JFXButton btnXoa;
	@FXML JFXButton btnSua;
	@FXML 
	private BorderPane bd;
	private TableView<Xe> tbl_view;
	TableColumn<Xe, String> colMaXe;
	TableColumn<Xe, String> colDonViTinh;
	TableColumn<Xe, String> colMoTa;
	TableColumn<Xe, String> colNhaSX;
	TableColumn<Xe, String> colLoaiXe;
	TableColumn<Xe, String> colTenXe;
	TableColumn<Xe, String> colMauXe;
	TableColumn<Xe, String> colThongTinBaoHanh;
	@FXML ChoiceBox<String> choiceBoxLoaiXe = new ChoiceBox<String>(); 
	@FXML ChoiceBox<String> choiceBoxMauXe = new ChoiceBox<String>(); 
	@FXML ChoiceBox<String> choiceBoxTenXe = new ChoiceBox<String>(); 
	@FXML JFXRadioButton rdOne;
	@FXML JFXRadioButton rdTwo;

	@FXML 
	TextField txtMa;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tbl_view=new TableView<Xe>();
		colMaXe=new TableColumn<Xe, String>("Mã xe");
		colDonViTinh=new TableColumn<Xe, String>("Đơn vị tính");
		colMoTa=new TableColumn<Xe, String>("Mô tả");
		colThongTinBaoHanh=new TableColumn<Xe, String>("Thông tin bảo hành");
		colNhaSX=new TableColumn<Xe, String>("Nhà sản xuất");
		colLoaiXe=new TableColumn<Xe, String>("Loại xe");
		colTenXe=new TableColumn<Xe, String>("Tên xe");
		colMauXe=new TableColumn<Xe, String>("Màu xe");


		tbl_view.getColumns().addAll(colMaXe,colDonViTinh,colMoTa,colNhaSX,colLoaiXe,colTenXe,colThongTinBaoHanh,colMauXe);

		bd.setCenter(tbl_view);

		colMaXe.setCellValueFactory(new PropertyValueFactory<>("maXe"));
		colDonViTinh.setCellValueFactory(new PropertyValueFactory<>("donViTinh"));
		colMoTa.setCellValueFactory(new PropertyValueFactory<>("moTa"));
		colThongTinBaoHanh.setCellValueFactory(new PropertyValueFactory<>("thongTinBaoHanh"));

		colNhaSX.setCellValueFactory(cellData -> 
		new SimpleStringProperty(cellData.getValue().getLx().getNuocSX()));
		colLoaiXe.setCellValueFactory(cellData -> 
		new SimpleStringProperty(cellData.getValue().getLx().getLoaixe()));
		colTenXe.setCellValueFactory(cellData -> 
		new SimpleStringProperty(cellData.getValue().getLx().getTenxe()));
		colMauXe.setCellValueFactory(cellData -> 
		new SimpleStringProperty(cellData.getValue().getLx().getMauson()));



		choiceBoxLoaiXe.setDisable(true);
		choiceBoxMauXe.setDisable(true);
		choiceBoxTenXe.setDisable(true);

		List<String> lx=QuanLyLoaiXe.showLoaiXe();
		lx.forEach(t->{
			choiceBoxLoaiXe.getItems().add(t);
		});
		List<String> lxTenXe=new ArrayList<String>();
		List<String> lxMauXe=new ArrayList<String>();
		//loaixe
		choiceBoxLoaiXe.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() { 
			public void changed(ObservableValue ov, Number value, Number new_value) 
			{ 
				choiceBoxTenXe.getItems().clear();
				choiceBoxMauXe.getItems().clear();
				lxMauXe.clear();
				lxTenXe.clear();
				List<String> ListTenXe=QuanLyLoaiXe.showTenXe(lx.get((int)new_value));
				ListTenXe.forEach(t->{
					choiceBoxTenXe.getItems().add(t);
					//set ngoài
					lxTenXe.add(t);
				});
				choiceBoxTenXe.setValue(ListTenXe.get(0).toString());
				List<String> listMauXe=QuanLyLoaiXe.showMauXeCuaTenXe(lx.get((int) new_value),choiceBoxTenXe.getValue().toString());
				listMauXe.forEach(t->{
					choiceBoxMauXe.getItems().add(t);
					//set ngoài 
					lxMauXe.add(t);
				});
				choiceBoxMauXe.setValue(listMauXe.get(0).toString());
			}
		});
		//tenxe
		choiceBoxTenXe.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() { 
			public void changed(ObservableValue ov, Number value, Number new_value) 
			{ 
				choiceBoxMauXe.getItems().clear();
				lxMauXe.clear();
				List<String> listMauXe=QuanLyLoaiXe.showMauXeCuaTenXe(choiceBoxLoaiXe.getValue(), lxTenXe.get((int)new_value));
				listMauXe.forEach(t->{
					choiceBoxMauXe.getItems().add(t);
				});
				choiceBoxMauXe.setValue(listMauXe.get(0).toString());
			}
		});
		//mauxe
		choiceBoxMauXe.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() { 
			public void changed(ObservableValue ov, Number value, Number new_value) 
			{ 
			}
		});


		UploaderDuLieuLenBang();

		tbl_view.setOnMouseClicked(ev->{
			if(ev.getClickCount()==2) {
				int result=tbl_view.getSelectionModel().getSelectedIndex();

				if(result!=-1) {
					FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/FormThongTinXe.fxml"));

					Parent root = null;
					try {
						root = loader.load();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					ThemXe ctlMain=loader.getController();

					String maXe=tbl_view.getItems().get(result).getMaXe();
					String donViTinh=tbl_view.getItems().get(result).getDonViTinh();
					String moTa=tbl_view.getItems().get(result).getMoTa();
					String nhaSX=tbl_view.getItems().get(result).getLx().getNuocSX();
					String LoaiXe=tbl_view.getItems().get(result).getLx().getLoaixe();
					String TenXe=tbl_view.getItems().get(result).getLx().getTenxe();
					String thongTinBaoHanh=tbl_view.getItems().get(result).getThongTinBaoHanh();
					String mauXe=tbl_view.getItems().get(result).getLx().getMauson();

					ctlMain.txtMaXe.setText(maXe);
					ctlMain.txtMaXe.setEditable(false);
					ctlMain.txtDonViTinh.setText(donViTinh);
					ctlMain.txtMoTa.setText(moTa);
					ctlMain.txtNhaSX.setText(nhaSX);
					ctlMain.txtThongTinBaoHanh.setText(thongTinBaoHanh);
					ctlMain.choiceBoxLoaiXe.setValue(LoaiXe);
					ctlMain.choiceBoxMauXe.setValue(mauXe);
					ctlMain.choiceBoxTenXe.setValue(TenXe);

					if(btnThem.isDisabled()==true) {
						System.out.println("nhân viên");
						ctlMain.lblTitle.setText("thông tin xe");
						ctlMain.txtDonViTinh.setEditable(false);
						ctlMain.txtMoTa.setEditable(false);
						ctlMain.txtNhaSX.setEditable(false);
						ctlMain.choiceBoxLoaiXe.setDisable(true);
						ctlMain.choiceBoxMauXe.setDisable(true);
						ctlMain.choiceBoxTenXe.setDisable(true);
						ctlMain.txtThongTinBaoHanh.setEditable(false);
						ctlMain.btnXoa.setDisable(true);
					}else {
						ctlMain.lblTitle.setText("Cập nhập xe");
					}
					Stage stage=new Stage();
					stage.initOwner(btnThem.getScene().getWindow());
					stage.initStyle(StageStyle.UNDECORATED);
					stage.initModality(Modality.APPLICATION_MODAL);
					stage.setScene(new Scene(root));
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
	@FXML
	private void btnClickTwo(ActionEvent e) {
		txtMa.setText("");
		txtMa.setEditable(false);
		choiceBoxLoaiXe.setDisable(false);
		choiceBoxMauXe.setDisable(false);
		choiceBoxTenXe.setDisable(false);

	}
	@FXML
	private void btnCLickOne(ActionEvent e) {
		txtMa.setText("");
		txtMa.setEditable(true);
		choiceBoxLoaiXe.setDisable(true);
		choiceBoxMauXe.setDisable(true);
		choiceBoxTenXe.setDisable(true);
	}

	private void UploaderDuLieuLenBang(){
		List<Xe> accs=QuanLyXe.showTatCaXe();
		accs.forEach(t->{
			//			Account newAccount= new Account(t.getUserName(), t.getPassword(), t.getLoaiTK());
			tbl_view.getItems().add(t);
		});
	}
	private void handleRefersh(ActionEvent e) {
		tbl_view.getItems().clear();
		UploaderDuLieuLenBang();
	}

	public void btnXoaXe(ActionEvent e) {
		int result=tbl_view.getSelectionModel().getSelectedIndex();
		if(result!=-1) {
			Alert alert = new Alert(AlertType.WARNING, "bạn có chắc muốn xóa",ButtonType.OK,ButtonType.CANCEL);
			alert.setTitle("Cảnh báo");
			Optional<ButtonType> resultx = alert.showAndWait();

			if (resultx.get() == ButtonType.OK) {
				String acc=tbl_view.getItems().get(result).getMaXe();
				if(QuanLyXe.xoaXe(acc)==true) {
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
	public void thongBaoKieuLoi(ActionEvent e, String text) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Dialog");
		alert.setHeaderText(null);
		alert.setContentText(text);
		alert.initOwner(((Node) (e.getSource())).getScene().getWindow());
		alert.showAndWait();
	}

	@FXML 
	public void btnTim(ActionEvent e) {
		if(rdOne.isSelected()==true) {
			String text=txtMa.getText().trim().toString();
			if (text.isEmpty()==false) {
				Xe acc=QuanLyXe.timMa(text);
				if(acc!=null) {
					tbl_view.getItems().clear();
					tbl_view.getItems().add(acc);
				}else {
					thongBaoKieuLoi(e, "không tìm thấy");
				}
			}else {
				handleRefersh(e);
			}
		}else {
			String loaiXe=choiceBoxLoaiXe.getValue().toString();
			String mauXe=choiceBoxMauXe.getValue().toString();
			String tenXe=choiceBoxTenXe.getValue().toString();
			System.out.println(loaiXe);
			System.out.println(mauXe);
			System.out.println(tenXe);
			List<Loaixe> listLoaiXe=QuanLyLoaiXe.timMaTraVeLoaiXe(loaiXe, tenXe, mauXe);
			List<Xe> listXe=QuanLyXe.timTheoLoai(listLoaiXe.get(0).getMaloai());
			if(listXe!=null) {

				tbl_view.getItems().clear();
				listXe.forEach(t->{
					tbl_view.getItems().add(t);
				});

			}else {
				thongBaoKieuLoi(e, "không tìm thấy");
			}
		}


	}
	@FXML
	public void btnXoaRong(ActionEvent e) {
		tbl_view.getSelectionModel().clearSelection();
		txtMa.setText("");
	}
	public void btnNhapThongTinXe(ActionEvent e) throws IOException {
		try {
			FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/FormThongTinXe.fxml"));
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

}
