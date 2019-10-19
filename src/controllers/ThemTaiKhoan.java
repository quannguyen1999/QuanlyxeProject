package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXTextField;
import application.Main;
import dao.QuanLyAccount;
import entities.Account;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

public class ThemTaiKhoan implements Initializable {
	QuanLyAccount ql = new QuanLyAccount();
	@FXML
	ChoiceBox<String> choiceBox = new ChoiceBox<String>();
	@FXML
	JFXTextField txtUserName;
	@FXML
	JFXTextField txtPassword;
	private double xOffset = 0;
	private double yOffset = 0;
	@FXML
	public BorderPane mainBd;
	@FXML 
	public Label lblTitle;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		makeStageDrageable();

		choiceBox.getItems().add("Kế toán trưởng");
		choiceBox.getItems().add("Nhân viên");
		choiceBox.getItems().add("Kế toán");

		choiceBox.setValue("Nhân viên");
	}

	public void btnCLoseWindow(ActionEvent e) throws IOException {
		((Node) (e.getSource())).getScene().getWindow().hide();
	}

	public boolean kiemTraUserName(ActionEvent e,String text) {
		if (text.isEmpty() == true) {
			thongBaoKieuLoi(e, "User name không được để trống");
			txtUserName.requestFocus();
			return false;
		}
		return true;
	}

	public boolean kiemTraPassword(ActionEvent e,String text) {
		String kiemTraText=text.trim();
		if(kiemTraText.isEmpty()==false) {
			if(kiemTraText.length()>=6) {
				if(kiemTraText.matches("((?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[@#%])).{1,}")) {
					return true;
				}else {
					thongBaoKieuLoi(e, "yêu cầu mật khẩu có [A-Z] và [a-z] và [0-9] và @#%");
					return false;
				}
			}else {
				thongBaoKieuLoi(e, "Yêu cầu mật khẩu tối thiểu 6 ký tự");
				return false;
			}
		}else {
			thongBaoKieuLoi(e, "Mật khẩu chưa nhập");
			return false;
		}
		
	}
	
	public boolean kiemTraTaiKhoan(ActionEvent e,String text) {
		String kiemTraText=text.trim();
		if(kiemTraText.isEmpty()==false) {
			return true;
		}else {
			thongBaoKieuLoi(e, "tài khoản chưa nhập");
			return false;
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

	public void clearAllText() {
		txtUserName.setText("");
		txtPassword.setText("");
		choiceBox.setValue("Nhân viên");
	}
	@FXML
	public void btnHuy(ActionEvent e) {
		if(lblTitle.getText().toString().equals("Cập nhập tài khoản")) {
			txtPassword.setText("");
			choiceBox.setValue("Nhân viên");
		}else {
			clearAllText();
			
		}
	}
	
	public void btnThem(ActionEvent e) throws IOException {
		try {
			boolean result = true;
			String pass=txtPassword.getText().toString();
			String username=txtUserName.getText().toString();
			if(kiemTraUserName(e, username)==false) {
				result=false;
				txtUserName.requestFocus();
			}
			
			if(result==true) {
				if(kiemTraPassword(e,pass)==false) {
					result=false;
					txtPassword.requestFocus();
				}
			}
			if (result == true) {
				boolean resultPassword = kiemTraPassword(e,txtPassword.getText().toString());
				if (resultPassword == true) {
					Account acc = new Account(txtUserName.getText().toString(), txtPassword.getText().toString(),
							choiceBox.getValue());
					if(lblTitle.getText().toString().equals("Cập nhập tài khoản")) {
						if (ql.suaAcc(acc) == true) {
							((Node) (e.getSource())).getScene().getWindow().hide();
						} else {
							thongBaoKieuLoi(e, "sửa không thành công");
						}
					}else {
						if (ql.themAcc(acc) == true) {
							((Node) (e.getSource())).getScene().getWindow().hide();
						} else {
							thongBaoKieuLoi(e, "thêm không thành công");
						}
					}

				} else {
					txtPassword.requestFocus();
				}
			}
		} catch (Exception e2) {
			System.out.println(e2.getMessage());
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
