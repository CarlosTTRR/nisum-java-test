package cl.com.nissum.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the USERPHONES database table.
 * 
 */
@Entity
@Table(name="USERPHONES")
@NamedQuery(name="Userphone.findAll", query="SELECT u FROM Userphone u")
public class Userphone implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private UUID id;

	private short citycode;

	private long number;

	//bi-directional many-to-one association to Usersnisum
	@ManyToOne
	@JoinColumn(name="USER_ID")
	private Usersnisum usersnisum;

	public Userphone() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public short getCitycode() {
		return this.citycode;
	}

	public void setCitycode(short citycode) {
		this.citycode = citycode;
	}

	public long getNumber() {
		return this.number;
	}

	public void setNumber(long number) {
		this.number = number;
	}

	public Usersnisum getUsersnisum() {
		return this.usersnisum;
	}

	public void setUsersnisum(Usersnisum usersnisum) {
		this.usersnisum = usersnisum;
	}

}