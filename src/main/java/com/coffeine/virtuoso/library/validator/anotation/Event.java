/**
 * Copyright (c) 2014-2015 by Coffeine Inc
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
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
 * The annotated class must contain two LocalDate fields.
 *
 * <p/>
 * Annotation does validation of end date for event(s).
 * The end date must be later then start date or must be null.
 * endDate = null means that event has not finished.
 * <p/>
 * Supported types are:
 * <ul>
 *     <li>{@code java.time.LocalDate}</li>
 * </ul>
 * <p/>
 * {@code null} end dates are considered valid.
 */
@Documented
@Target( { TYPE, ANNOTATION_TYPE } )
@Retention( RUNTIME )
@Constraint( validatedBy = EventValidatorImpl.class )
public @interface Event {

    /**
     * Date of start event.
     *
     * @return Field name of the start date
     */
    String start();

    /**
     * Date of the end event.
     *
     * @return Field name of the end date
     */
    String end();

    /**
     * Message for invalid values.
     *
     * @return String Error message
     */
    String message() default "{javax.validation.constraints.Event.message}";

    /**
     * Validate group of events.
     *
     * @return Event
     */
    Class< ? >[] groups() default {};

    /**
     * FIXME: Investigate if this is needed
     * @return
     */
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
