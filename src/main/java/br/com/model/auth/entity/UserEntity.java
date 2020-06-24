package br.com.model.auth.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;


@Data
@Entity
@Table(name = "user", schema="public")
public class UserEntity {
	
	@Id
	private Integer id;
	private String name;
	private Boolean available;
	private String email;
	private String cel;
	private String password;
	private String wallet;
	private String authority;
	private Integer indicate;

}
