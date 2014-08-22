/// *** Validator :: Anotation :: InEnum    *** *** *** *** *** *** *** *** ///

/** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *
 *                                                                  *
 * @copyright 2014 (c), by Coffeine
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 2014-08-12 15:26:32 :: 2014-08-12 20:00:00
 *
 * @address /Ukraine/Ivano-Frankivsk/Tychyny/7a
 *                                                                  *
 *///*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *

/// *** Code    *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ///
package com.coffeine.virtuoso.library.validator.anotation;

import com.coffeine.virtuoso.library.validator.anotation.Implementation.InEnumImpl;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.NotNull;
import java.lang.annotation.*;

/**
 * @version 1.0
 */
@Documented
@Constraint( validatedBy = InEnumImpl.class )
@Retention( RetentionPolicy.RUNTIME )
@Target( ElementType.FIELD )
@NotNull( message = "Value can not be null" )
@ReportAsSingleViolation
public @interface InEnum {

    Class < ? extends Enum < ? > > enumClass();

    String message() default "Value is not valid";

    Class < ? >[] groups() default {};

    Class < ? extends Payload >[] payload() default {};
}
