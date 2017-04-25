package beans;

public class Association {
	private Long Id;
	private Long idFirstKey;
	private Long idSecongKey;
	private Boolean trueOrFalse=false;
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public Long getIdFirstKey() {
		return idFirstKey;
	}
	public void setIdFirstKey(Long idFirstKey) {
		this.idFirstKey = idFirstKey;
	}
	public Long getIdSecongKey() {
		return idSecongKey;
	}
	public void setIdSecongKey(Long idSecongKey) {
		this.idSecongKey = idSecongKey;
	}
	public Boolean getTrueOrFalse() {
		return trueOrFalse;
	}
	public void setTrueOrFalse(Boolean trueOrFalse) {
		this.trueOrFalse = trueOrFalse;
	}

}
