package beans;

import java.io.Serializable;
import java.sql.Timestamp;

import dao.DaoPersonImpl;
import dao.UsineDao;

import java.sql.Connection;

public class Person implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2973296045996724571L;
	private String Name = null;
	private String secondName = null;
	private String Surname = null;
	private String secondSurname = null;
	private String Profession = null;
	private Timestamp dateInscription = null;
	private Long Id;
	private String Password = null;
	private String email = null;
	private String phoneNumber = null;
	private String telNumber = null;
	private String facebookId = null;
	private String twitterId = null;
	private String instagramId = null;
	private String linkedinId = null;
	private String accountPicture = null;
	private String streetNumber = null;
	private String streetName = null;
	private String cityName = null;
	private String countryName = null;
	private String postalCode = null;
	private String Departement = null;
	private String latLng = null;
	private Integer lastConnexion = null;
	private String function = null;
	private Connection maConnexion = null;
	private ResultConnexion resultConnexion = null;
	private DaoPersonImpl daopi = new DaoPersonImpl(UsineDao.getInstance());

	protected Boolean Connexion(String Id, String Password) {
		return null;

	}

	public String getName() {
		return Name;
	}

	public void setName(String name, Boolean sqlornot) {
		Name = name;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName, Boolean sqlornot) {
		this.secondName = secondName;
	}

	public String getSurname() {
		return Surname;
	}

	public void setSurname(String surname, Boolean sqlornot) {
		Surname = surname;
	}

	public String getSecondSurname() {
		return secondSurname;
	}

	public void setSecondSurname(String secondSurname, Boolean sqlornot) {
		this.secondSurname = secondSurname;
	}

	public String getProfession() {
		return Profession;
	}

	public void setProfession(String profession, Boolean sqlornot) {
		Profession = profession;
	}

	public Long getId() {
		return Id;
	}

	public void setId(long l, Boolean sqlornot) {
		Id = l;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password, Boolean sqlornot) {
		Password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email, Boolean sqlornot) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber, Boolean sqlornot) {
		this.phoneNumber = phoneNumber;
	}

	public String getTelNumber() {
		return telNumber;
	}

	public void setTelNumber(String telNumber, Boolean sqlornot) {
		this.telNumber = telNumber;
	}

	public String getFacebookId() {
		return facebookId;
	}

	public void setFacebookId(String facebookId, Boolean sqlornot) {
		this.facebookId = facebookId;
	}

	public String getTwitterId() {
		return twitterId;
	}

	public void setTwitterId(String twitterId, Boolean sqlornot) {
		this.twitterId = twitterId;
	}

	public String getInstagramId() {
		return instagramId;
	}

	public void setInstagramId(String instagramId, Boolean sqlornot) {
		this.instagramId = instagramId;
	}

	public String getLinkedinId() {
		return linkedinId;
	}

	public void setLinkedinId(String linkedinId, Boolean sqlornot) {
		this.linkedinId = linkedinId;
	}

	public String getAccountPicture() {
		return accountPicture;
	}

	public void setAccountPicture(String accountPicture, Boolean sqlornot) {
		this.accountPicture = accountPicture;
	}

	public String getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(String streetNumber, Boolean sqlornot) {
		this.streetNumber = streetNumber;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName, Boolean sqlornot) {
		this.streetName = streetName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName, Boolean sqlornot) {
		this.cityName = cityName;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName, Boolean sqlornot) {
		this.countryName = countryName;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode, Boolean sqlornot) {
		this.postalCode = postalCode;
	}

	public Integer getLastConnexion() {
		return lastConnexion;
	}

	public void setLastConnexion(int i) {
		this.lastConnexion = i;
	}

	public Timestamp getDateInscription() {
		return dateInscription;
	}

	public void setDateInscription(Timestamp dateInscription, Boolean sqlornot) {
		this.dateInscription = dateInscription;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public DaoPersonImpl getDaopi() {
		return daopi;
	}

	public void setDaopi(DaoPersonImpl daopi) {
		this.daopi = daopi;
	}

	public Connection getMaConnexion() {
		return maConnexion;
	}

	public void setMaConnexion(Connection maConnexion) {
		this.maConnexion = maConnexion;
	}

	public ResultConnexion getResultConnexion() {
		return resultConnexion;
	}

	public void setResultConnexion(ResultConnexion resultConnexion) {
		this.resultConnexion = resultConnexion;
	}

	public String getDepartement() {
		return Departement;
	}

	public void setDepartement(String departement) {
		Departement = departement;
	}

	public String getLatLng() {
		return latLng;
	}

	public void setLatLng(String latLng) {
		this.latLng = latLng;
	}

	

}
