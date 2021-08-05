package cl.com.nissum.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


/**
 * The persistent class for the USERPHONES database table.
 * 
 */
@Entity
@Table(name="USERPHONES")
@NamedQuery(name="Userphone.findAll", query="SELECT u FROM Userphone u")
@AllArgsConstructor
@Builder
@Setter
@Getter
public class Userphone implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private UUID id;

	private short citycode;
	
	private short countrycode;

	private long number;

	//bi-directional many-to-one association to Usersnisum
	@ManyToOne
	@JoinColumn(name="USER_ID")
	private Usersnisum usersnisum;

	public Userphone() {
		
	}

}