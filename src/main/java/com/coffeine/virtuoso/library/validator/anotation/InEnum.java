/**
 * Copyright (c) 2014-2015 by Coffeine Inc
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 12/13/15 2:27 PM
 */

package com.coffeine.virtuoso.library.validator.anotation;

import com.coffeine.virtuoso.library.validator.anotation.Implementation.InEnumValidatorImpl;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.NotNull;

/**
 * Validator for check if field contains only items from enum.
 *
 * @version 1.0
 */
@Documented
@Constraint( validatedBy = InEnumValidatorImpl.class )
@Retention( RetentionPolicy.RUNTIME )
@Target( ElementType.FIELD )
@NotNull( message = "Value can not be null" )
@ReportAsSingleViolation
public @interface InEnum {

    Class<? extends Enum<?>> enumClass();

    String message() default "Value is not valid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
