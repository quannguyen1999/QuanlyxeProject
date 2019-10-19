package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Account {
	@Id 
	private String userName;
	private String password; 
	@Column(columnDefinition = "nvarchar(50)")
	private String loaiTK; 
	
	public Account(String userName, String password, String loaiTK) {
		super();
		this.userName = userName;
		this.password = password;
		this.loaiTK = loaiTK;
	}

	public Account() {
		super();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLoaiTK() {
		return loaiTK;
	}

	public void setLoaiTK(String loaiTK) {
		this.loaiTK = loaiTK;
	}

	@Override
	public String toString() {
		return "Account [userName=" + userName + ", password=" + password + ", loaiTK=" + loaiTK + "]";
	}
	

	
	
	
	
}
