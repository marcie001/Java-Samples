package com.example.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.example.service.HometownService;

/**
 * 出身地のバリデータ.
 * 
 * @author marcie
 *
 */
public class HometownValidator implements ConstraintValidator<Hometown, String> {

	private static final Logger logger = LoggerFactory.getLogger(HometownValidator.class);

	private final HometownService service;

	public HometownValidator(HometownService service) {
		this.service = service;
	}

	@Override
	public void initialize(Hometown constraintAnnotation) {
		logger.info("HometownValidator#initialize: " + this.toString());
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		logger.info("HometownValidator#isValid: " + this.toString());
		return StringUtils.isEmpty(value) || service.isValidName(value);
	}

}
