package forms;

import java.awt.Image;
import java.sql.Timestamp;

public class FormulaireMonCompte {
	
	private String Name = "";
	private String secondName = "";
	private String Surname = "";
	private String secondSurname = "";
	private String Profession = "";
	private Timestamp dateInscription = null;
	private String Id = "";
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
	

	@SuppressWarnings("unused")
	private void validationEmail(String email) throws Exception {
		/**
		 * Valide l'adresse email saisie.
		 */
		if (email != null && !email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
			throw new Exception("Merci de saisir une adresse mail valide.");
		}
	}

	@SuppressWarnings("unused")
	private void validationMotDePasse(String motDePasse) throws Exception {
		/**
		 * Valide le mot de passe saisi.
		 */
		if (motDePasse != null) {
			if (motDePasse.length() < 3) {
				throw new Exception("Le mot de passe doit contenir au moins 3 caractères.");
			}
		} else {
			throw new Exception("Merci de saisir votre mot de passe.");
		}
	}
	
	@SuppressWarnings("unused")
	private void validationTel(String numeroTel) throws Exception {
		/**
		 * Valide le num�ro de telephone saisi
		 */
		if (numeroTel.length() < 10 || !(numeroTel.startsWith("0"))) {
			throw new Exception("Le num�ro de telephone n'est pas valide.");
		}
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public String getSurname() {
		return Surname;
	}

	public void setSurname(String surname) {
		Surname = surname;
	}

	public String getSecondSurname() {
		return secondSurname;
	}

	public void setSecondSurname(String secondSurname) {
		this.secondSurname = secondSurname;
	}

	public String getProfession() {
		return Profession;
	}

	public void setProfession(String profession) {
		Profession = profession;
	}

	public Timestamp getDateInscription() {
		return dateInscription;
	}

	public void setDateInscription(Timestamp dateInscription) {
		this.dateInscription = dateInscription;
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getTelNumber() {
		return telNumber;
	}

	public void setTelNumber(String telNumber) {
		this.telNumber = telNumber;
	}

	public String getFacebookId() {
		return facebookId;
	}

	public void setFacebookId(String facebookId) {
		this.facebookId = facebookId;
	}

	public String getTwitterId() {
		return twitterId;
	}

	public void setTwitterId(String twitterId) {
		this.twitterId = twitterId;
	}

	public String getInstagramId() {
		return instagramId;
	}

	public void setInstagramId(String instagramId) {
		this.instagramId = instagramId;
	}

	public String getLinkedinId() {
		return linkedinId;
	}

	public void setLinkedinId(String linkedinId) {
		this.linkedinId = linkedinId;
	}

	public Image getAccountPicture() {
		return accountPicture;
	}

	public void setAccountPicture(Image accountPicture) {
		this.accountPicture = accountPicture;
	}

	public Integer getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(Integer streetNumber) {
		this.streetNumber = streetNumber;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

}
