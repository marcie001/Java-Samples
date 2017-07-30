package com.example.validation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * field1 と field2 の値が同じであることをチェックするバリデーションのアノテーション.
 * 
 * @author marcie
 *
 */
@Documented
@Constraint(validatedBy = SameValidator.class)
@Target({ TYPE })
@Retention(RUNTIME)
public @interface Same {
	String message() default "{errors.same}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	String field1();

	String field2();

	@Target({ TYPE })
	@Retention(RUNTIME)
	@Documented
	public @interface List {
		Same[] value();
	}
}
