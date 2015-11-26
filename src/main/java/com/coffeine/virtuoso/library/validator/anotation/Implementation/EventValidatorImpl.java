/**
 * Copyright (c) 2014-2015 by Coffeine Inc
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 11/25/15 10:40 PM
 */

package com.coffeine.virtuoso.library.validator.anotation.Implementation;

import com.coffeine.virtuoso.library.validator.anotation.Event;
import org.apache.commons.beanutils.BeanUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;

/**
 * Created by coffeine on 11/25/15.
 */
public class EventValidatorImpl implements ConstraintValidator<Event, Object> {

    /**
     * Name of field which contains start date of event
     */
    private String startDateFieldName;

    /**
     * Name of field which contains end date or null of event,
     * null means that event is not finished yet.
     */
    private String endDateFieldName;


    /**
     * Initializes the validator in preparation for
     * {@link #isValid(Object, ConstraintValidatorContext)} calls.
     * The constraint annotation for a given constraint declaration
     * is passed.
     * <p>
     * This method is guaranteed to be called before any use of this instance for
     * validation.
     *
     * @param constraintAnnotation annotation instance for a given constraint declaration
     */
    @Override
    public void initialize( Event constraintAnnotation ) {
        //- Initialization -//
        this.startDateFieldName = constraintAnnotation.start();
        this.endDateFieldName = constraintAnnotation.end();
    }

    /**
     * Implements the validation logic.
     * The state of {@code value} must not be altered.
     * <p>
     * This method can be accessed concurrently, thread-safety must be ensured
     * by the implementation.
     *
     * @param value   object to validate
     * @param context context in which the constraint is evaluated
     * @return {@code false} if {@code value} does not pass the constraint
     */
    @Override
    public boolean isValid( Object value, ConstraintValidatorContext context ) {

        boolean result = false;

        try {
            String startDate = BeanUtils.getProperty( value, this.startDateFieldName );
            String endDate = BeanUtils.getProperty( value, this.endDateFieldName );

             result = endDate == null || LocalDate.parse( startDate ).isBefore( LocalDate.parse( endDate ) );

            context.buildConstraintViolationWithTemplate( "" )
                .addPropertyNode( this.endDateFieldName );
            //TODO: put msg to last field
        } catch ( IllegalAccessException e ) {
            e.printStackTrace();
        } catch ( InvocationTargetException e ) {
            e.printStackTrace();
        } catch ( NoSuchMethodException e ) {
            e.printStackTrace();
        }
        return result;
    }
}
