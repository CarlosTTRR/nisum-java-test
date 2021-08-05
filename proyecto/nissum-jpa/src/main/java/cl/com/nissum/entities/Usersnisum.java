package cl.com.nissum.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the USERSNISUM database table.
 * 
 */
@Entity
@NamedQuery(name="Usersnisum.findAll", query="SELECT u FROM Usersnisum u")
public class Usersnisum implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private UUID id;

	@Temporal(TemporalType.DATE)
	@Column(name="CREATED_DATE")
	private Date createdDate;

	private String email;

	private boolean isactive;

	@Temporal(TemporalType.DATE)
	@Column(name="LAST_LOGIN")
	private Date lastLogin;

	@Temporal(TemporalType.DATE)
	@Column(name="MODIFIED_DATE")
	private Date modifiedDate;

	private String name;

	private String password;

	private String token;

	//bi-directional many-to-one association to Userphone
	@OneToMany(mappedBy="usersnisum")
	private List<Userphone> userphones;

	public Usersnisum() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean getIsactive() {
		return this.isactive;
	}

	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}

	public Date getLastLogin() {
		return this.lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public Date getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public List<Userphone> getUserphones() {
		return this.userphones;
	}

	public void setUserphones(List<Userphone> userphones) {
		this.userphones = userphones;
	}

	public Userphone addUserphone(Userphone userphone) {
		getUserphones().add(userphone);
		userphone.setUsersnisum(this);

		return userphone;
	}

	public Userphone removeUserphone(Userphone userphone) {
		getUserphones().remove(userphone);
		userphone.setUsersnisum(null);

		return userphone;
	}

}