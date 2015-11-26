/**
 * Copyright (c) 2014-2015 by Coffeine Inc
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 * @date 11/25/15 10:41 PM
 */

package com.coffeine.virtuoso.library.validator.anotation;

import com.coffeine.virtuoso.library.validator.anotation.Implementation.EventValidatorImpl;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * The annotated element must be a date in the past.
 * Now is defined as the current time according to the virtual machine.
 * <p/>
 * The calendar used if the compared type is of type {@code Calendar}
 * is the calendar based on the current timezone and the current locale.
 * <p/>
 * Supported types are:
 * <ul>
 *     <li>{@code java.util.Date}</li>
 *     <li>{@code java.util.Calendar}</li>
 * </ul>
 * <p/>
 * {@code null} elements are considered valid.
 *
 * @author Emmanuel Bernard
 */
@Documented
@Target( { TYPE, ANNOTATION_TYPE } )
@Retention( RUNTIME )
@Constraint( validatedBy = EventValidatorImpl.class )
public @interface Event {

    String start();

    String end();

    String message() default "{javax.validation.constraints.Event.message}";

    Class< ? >[] groups() default {};

    Class< ? extends Payload >[] payload() default {};

    /**
     * Defines several {@link Event} annotations on the same element.
     *
     * @see Event
     */
    @Target( { METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER } )
    @Retention( RUNTIME )
    @Documented
    @interface List {

        Event[] value();
    }
}
