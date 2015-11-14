/**
 * Copyright (c) 2014-2015 by Coffeine Inc
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 11/13/15 11:04 PM
 */

package com.coffeine.virtuoso.library.validator.anotation;

import com.coffeine.virtuoso.library.validator.anotation.Implementation.InEnumImpl;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.NotNull;
import java.lang.annotation.*;

/**
 * Vaidator for check if field contains only items from enum.
 *
 * @version 1.0
 */
@Documented
@Constraint( validatedBy = InEnumImpl.class )
@Retention( RetentionPolicy.RUNTIME )
@Target( ElementType.FIELD )
@NotNull( message = "Value can not be null" )
@ReportAsSingleViolation
public @interface Exists {

    Class < ? extends Enum < ? > > enumClass();

    String message() default "Value is not valid";

    Class < ? >[] groups() default {};

    Class < ? extends Payload>[] payload() default {};
}
