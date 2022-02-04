package com.example.sample.payload.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class LoginRequest {
	@NotBlank(message="名前は入力必須です")
	@Pattern(regexp="^[a-zA-Z0-9]+$", message="名前は半角英数で入力してください")
	private String username;

	@NotBlank(message="パスワードは入力必須です")
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
