package beans;

import java.sql.Date;

public class Connexion {
	private Long Id;
	private Long idConnected;
	private String logicAddress;
	private String personT;
	private Date loginTime;
	private Date logoutTime;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public Long getIdConnected() {
		return idConnected;
	}

	public void setIdConnected(Long idConnected) {
		this.idConnected = idConnected;
	}

	public String getLogicAddress() {
		return logicAddress;
	}

	public void setLogicAddress(String logicAddress) {
		this.logicAddress = logicAddress;
	}

	public String getPersonT() {
		return personT;
	}

	public void setPersonT(String personT) {
		this.personT = personT;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public Date getLogoutTime() {
		return logoutTime;
	}

	public void setLogoutTime(Date logoutTime) {
		this.logoutTime = logoutTime;
	}

}
