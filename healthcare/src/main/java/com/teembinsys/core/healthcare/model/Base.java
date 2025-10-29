package com.teembinsys.core.healthcare.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.teembinsys.core.healthcare.constant.UserTypes;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;


@Getter
@Setter
@MappedSuperclass
public class Base implements Serializable {

	@Column(name = "user_name", length = 30, nullable = false)
	private String userName;

	@Column(name = "password", length = 30, nullable = false)
	private String password;

	@Column(name = "date_of_birth", nullable = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate dateOfBirth;

	@Column(name = "age", nullable = false)
	private Integer age;

	@Column(name = "gender", length = 10, nullable = false)
	private String gender;

	@Column(name = "email", length = 30)
	private String email;

	@Column(name = "address", length = 100)
	private String address;

	@Column(name = "zip_code")
	private Integer zipCode;

	@Column(name = "district", length = 30)
	private String district;

	@Column(name = "state", length = 30)
	private String state;

	@Column(name = "country", length = 20)
	private String country;

	@Column(name = "identification", length = 64, unique = true)
	private String identification;


}
