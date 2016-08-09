/**
 * Copyright (c) 2014-2016 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 * @date 8/9/16 8:58 PM
 */

package com.coffeine.virtuoso.library.validator.anotation.implementation;

import com.coffeine.virtuoso.library.validator.anotation.RequiredGroup;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static org.springframework.util.Assert.isTrue;

/**
 * Implementation of RequiredGroup validator.
 *
 * @version 1.0
 * @see RequiredGroup
 */
public class RequiredGroupValidatorImpl implements ConstraintValidator<RequiredGroup, Object> {

    /**
     * Logger.
     */
    private static final Logger log = LogManager.getLogger( RequiredGroupValidatorImpl.class );


    private String[] fields;

    public void initialize( RequiredGroup constraint ) {

        this.fields = constraint.fields();
    }

    public boolean isValid( Object obj, ConstraintValidatorContext context ) {
        boolean isGroup = false;

        for ( String field : this.fields ) {
            try {
                String value = BeanUtils.getProperty( obj, field );

                if (!value.isEmpty()) isGroup = true;

                isTrue( (isGroup && !value.isEmpty()) || !isGroup );
            } catch (
                IllegalAccessException
                | IllegalArgumentException
                | InvocationTargetException
                | NoSuchMethodException e
            ) {
                //- Log exception -//
                log.warn( "Validation error.", e );

                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(
                    context.getDefaultConstraintMessageTemplate()
                )
                    .addPropertyNode( field )
                    .addConstraintViolation();

                return false;
            } catch ( NullPointerException e ) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(
                    context.getDefaultConstraintMessageTemplate()
                )
                    .addPropertyNode( field )
                    .addConstraintViolation();

                if (isGroup) return false;
            }
        }

        return true;
    }
}

