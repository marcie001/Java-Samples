package com.example.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;

/**
 * 2つのフィールドの値が同じであることをチェックするバリデータ.
 * 
 * @author marcie
 *
 */
public class SameValidator implements ConstraintValidator<Same, Object> {

	private static final Logger logger = LoggerFactory.getLogger(SameValidator.class);

	private String field1;

	private String field2;

	private String message;

	@Override
	public void initialize(Same constraintAnnotation) {
		logger.info("SameValidator#initialize: " + this.toString());

		field1 = constraintAnnotation.field1();
		field2 = constraintAnnotation.field2();
		message = constraintAnnotation.message();
	}

	@Override
	public boolean isValid(Object object, ConstraintValidatorContext context) {
		logger.info("SameValidator#isValid: " + this.toString());

		context.disableDefaultConstraintViolation();
		context.buildConstraintViolationWithTemplate(message).addPropertyNode(field1).addConstraintViolation()
				.buildConstraintViolationWithTemplate(message).addPropertyNode(field2).addConstraintViolation();

		BeanWrapper bean = PropertyAccessorFactory.forBeanPropertyAccess(object);
		Object v1 = bean.getPropertyValue(field1);
		Object v2 = bean.getPropertyValue(field2);
		if (v1 == v2) {
			return true;
		}
		if (v1 == null) {
			return false;
		}
		return v1.equals(v2);
	}

}
