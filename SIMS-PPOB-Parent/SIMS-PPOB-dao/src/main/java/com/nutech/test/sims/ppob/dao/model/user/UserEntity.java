/**
 * 
 */
package com.nutech.test.sims.ppob.dao.model.user;

import java.io.Serializable;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 */
@Data
@Entity(name = "userEntity")
@Table(name = "user_sims")
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity implements Serializable {
	
	private static final long serialVersionUID = 0l;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_user")
	private Long idUser;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "email")
	private String emailUser;
	@Column(name = "password")
	private String password;
	
}
