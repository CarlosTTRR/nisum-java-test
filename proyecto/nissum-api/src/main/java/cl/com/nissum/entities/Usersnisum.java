package cl.com.nissum.entities;

import java.io.Serializable;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.UUID;


/**
 * The persistent class for the USERSNISUM database table.
 * 
 */
@Entity
@NamedQuery(name="Usersnisum.findAll", query="SELECT u FROM Usersnisum u")
@AllArgsConstructor
@Builder
@Setter
@Getter

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
}