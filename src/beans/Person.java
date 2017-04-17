package beans;

import java.awt.Image;
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
	private String Name = "";
	private String secondName = "";
	private String Surname = "";
	private String secondSurname = "";
	private String Profession = "";
	private Timestamp dateInscription = null;
	private Long Id ;
	private String privateKey="";
	private String Password = "";
	private String email = "";
	private String phoneNumber = "";
	private String telNumber = "";
	private String facebookId = "";
	private String twitterId = "";
	private String instagramId = "";
	private String linkedinId = "";
	private Image accountPicture = null;
	private Integer streetNumber = 0;
	private String streetName = "";
	private String cityName = "";
	private String countryName = "";
	private String postalCode = "";
	private Integer lastConnexion = null;
	private String function="";
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

	public Image getAccountPicture() {
		return accountPicture;
	}

	public void setAccountPicture(Image accountPicture, Boolean sqlornot) {
		this.accountPicture = accountPicture;
	}

	public Integer getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(Integer streetNumber, Boolean sqlornot) {
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

	public Connection getMaConnexion() {
		return maConnexion;
	}

	public void setMaConnexion(Connection maConnexion) {
		this.maConnexion = maConnexion;
	}

	public DaoPersonImpl getDaopi() {
		return daopi;
	}

	public void setDaopi(DaoPersonImpl daopi) {
		this.daopi = daopi;
	}

	public String getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}

	public ResultConnexion getResultConnexion() {
		return resultConnexion;
	}

	public void setResultConnexion(ResultConnexion resultConnexion) {
		this.resultConnexion = resultConnexion;
	}

	@Override
	public String toString() {
		return "Person [Name=" + Name + ", secondName=" + secondName + ", Surname=" + Surname + ", secondSurname="
				+ secondSurname + ", Profession=" + Profession + ", dateInscription=" + dateInscription + ", Id=" + Id
				+ ", privateKey=" + privateKey + ", Password=" + Password + ", email=" + email + ", phoneNumber="
				+ phoneNumber + ", telNumber=" + telNumber + ", facebookId=" + facebookId + ", twitterId=" + twitterId
				+ ", instagramId=" + instagramId + ", linkedinId=" + linkedinId + ", accountPicture=" + accountPicture
				+ ", streetNumber=" + streetNumber + ", streetName=" + streetName + ", cityName=" + cityName
				+ ", countryName=" + countryName + ", postalCode=" + postalCode + ", lastConnexion=" + lastConnexion
				+ ", function=" + function + ", maConnexion=" + maConnexion + ", resultConnexion=" + resultConnexion
				+ ", daopi=" + daopi + "]";
	}

	

}
