package com.example.form;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.ScriptAssert;
import org.springframework.util.StringUtils;

import com.example.validation.Hometown;
import com.example.validation.Same;

// @ScriptAssert を使って複数フィールドを参照する入力チェックする例。 script が false のときにエラーとなるため、条件が複雑。
@ScriptAssert.List({
		@ScriptAssert(lang = "javascript", script = "_this.pet !== 'OTHER' || !org.springframework.util.StringUtils.isEmpty(_this.petOther)", message = "ペットが OTHER のときは必須です。(ScriptAssert)"),
		@ScriptAssert(lang = "javascript", script = "org.springframework.util.StringUtils.isEmpty(_this.petOther) || _this.pet === 'OTHER'", message = "ペットが OTHER のときのみ入力可能です。(ScriptAssert)") })
@Same.List({ @Same(field2 = "password", field1 = "passwordConfirm"),
		@Same(field2 = "passphrase", field1 = "passphraseConfirm") })
public class IndexForm {

	// @NotNull は NULL でないことのチェックなので、文字列の場合空文字はチェックできない
	@NotEmpty(message = "{errors.notEmpty}")
	// 文字列の長さのチェックは @Size
	@Size(min = 2, max = 10, message = "{errors.size}")
	private String name;

	@NotNull(message = "{errors.notEmpty}")
	@Range(min = 0, max = 1000)
	private Double height;

	@NotEmpty(message = "{errors.notEmpty}")
	@Pattern(regexp = "(DOG|CAT|OTHER)")
	private String pet;

	@Size(max = 10, message = "{errors.size}")
	private String petOther;

	@NotEmpty(message = "{errors.notEmpty}")
	// 独自バリデータの例。 DBアクセスをしている。
	@Hometown
	private String hometown;

	@NotEmpty(message = "{errors.notEmpty}")
	@Hometown
	private String address;

	private String password;

	private String passwordConfirm;

	private String passphrase;

	private String passphraseConfirm;

	// @AssertTrue を使って複数フィールドを参照する入力チェックする例
	@AssertTrue(message = "ペットが OTHER のときは必須です。(AssertTrue)")
	public boolean isPetOtherNotNull() {
		if ("OTHER".equals(pet)) {
			return !StringUtils.isEmpty(petOther);
		} else {
			return true;
		}
	}

	@AssertTrue(message = "ペットが OTHER のときのみ入力可能です。(AssertTrue)")
	public boolean isPetOtherNull() {
		if ("OTHER".equals(pet)) {
			return true;
		} else {
			return StringUtils.isEmpty(petOther);
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public String getPet() {
		return pet;
	}

	public void setPet(String pet) {
		this.pet = pet;
	}

	public String getPetOther() {
		return petOther;
	}

	public void setPetOther(String petOther) {
		this.petOther = petOther;
	}

	public String getHometown() {
		return hometown;
	}

	public void setHometown(String hometown) {
		this.hometown = hometown;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public String getPassphrase() {
		return passphrase;
	}

	public void setPassphrase(String passphrase) {
		this.passphrase = passphrase;
	}

	public String getPassphraseConfirm() {
		return passphraseConfirm;
	}

	public void setPassphraseConfirm(String passphraseConfirm) {
		this.passphraseConfirm = passphraseConfirm;
	}

}
