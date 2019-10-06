package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import application.Main;
import entities.Account;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

public class QuanLyTaiKhoanController implements Initializable{
	
	@FXML 
	private BorderPane bd;
	
	private TableView<Account> tbl_view;
	
	TableColumn<Account, String> colUserName;
	TableColumn<Account, String> colPassword;
	TableColumn<Account, String> colLoaiTK;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		tbl_view=new TableView<Account>();
		TableColumn<Account, String> colUserName=new TableColumn<Account, String>("User name");
		TableColumn<Account, String> colPassword=new TableColumn<Account, String>("Password");
		TableColumn<Account, String> colLoaiTK=new TableColumn<Account, String>("Loại tài khoản");
		
		tbl_view.getColumns().addAll(colUserName,colPassword,colLoaiTK);
		
		bd.setCenter(tbl_view);
		
		colUserName.setCellValueFactory(new PropertyValueFactory<>("userName"));
		colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
		colLoaiTK.setCellValueFactory(new PropertyValueFactory<>("loaiTK"));
		
		ObservableList<Account> list=getAccList();
		
		
		tbl_view.setItems(list);
	}
	ObservableList<Account> list = null;
	private ObservableList<Account> getAccList(){
		
		List<Account> accs=showTatCaAccount();
		accs.forEach(t->{
			Account newAccount= new Account(t.getUserName(), t.getPassword(), t.getLoaiTK());
			list=FXCollections.observableArrayList(newAccount);
		});
		return list;
//		Account accs=new Account("duytankhung123", "Khanhhoa123", "asd");
//		ObservableList<Account> list=FXCollections.observableArrayList(accs);
//		return list;
	}
	public void btnCLoseWindow(ActionEvent e) throws IOException {
		((Node)(e.getSource())).getScene().getWindow().hide();  
	}
	public static List<Account> showTatCaAccount() {
		List<Account> accs = null;
		EntityManager manager = Persistence.createEntityManagerFactory("DeAnQuanLyXeFix").createEntityManager();
		EntityTransaction transaction = null;
		try {
			transaction = manager.getTransaction();
			transaction.begin();
			accs=manager.createQuery("select s from Account s",Account.class).getResultList();
			transaction.commit();
		} catch (Exception ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
		} finally {
			manager.close();
		}
		return accs;
	}
	public void btnNhapThongTinTaiKhoan(ActionEvent e) throws IOException {
		try {
			Stage stage=(Stage) ((Node)(e.getSource())).getScene().getWindow();  
			
			Stage primaryStage=new Stage();
			Parent root=(Parent) FXMLLoader.load(getClass().getResource("/fxml/FormThongTinTaiKhoan.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.initStyle(StageStyle.UNDECORATED);
			primaryStage.setAlwaysOnTop(true);
			Main.primaryStage=primaryStage;
			primaryStage.initModality(Modality.APPLICATION_MODAL);
			primaryStage.initOwner(stage);
			primaryStage.showAndWait();
		} catch (Exception e2) {
			// TODO: handle exception
			System.out.println(e2.getMessage());
		}
	}
}
