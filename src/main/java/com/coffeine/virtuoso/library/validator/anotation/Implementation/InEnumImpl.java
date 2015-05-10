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
package com.coffeine.virtuoso.library.validator.anotation.Implementation;

import com.coffeine.virtuoso.library.validator.anotation.InEnum;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 */
public class InEnumImpl implements ConstraintValidator <InEnum, List < String > > {
    /// *** Properties  *** ///
    /**
     * List of available values
     */
    private List < String > valueList = null;


    /// *** Methods     *** ///

    /**
     * Validate input
     *
     * @param values
     * @param context
     * @return boolean true - input is valid, false - not valid
     */
    @Override
    public boolean isValid(
        List < String > values,
        ConstraintValidatorContext context
    ) {
        for ( String value : values ) {
            if ( !valueList.contains( value.toUpperCase()) ) {
                return false;
            }
        }

        return true;
    }

    /**
     * Initialization
     *
     * @param constraintAnnotation
     */
    @Override
    public void initialize( InEnum constraintAnnotation ) {

        valueList = new ArrayList <>();

        Class < ? extends Enum < ? > > enumClass = constraintAnnotation.enumClass();

        @SuppressWarnings( "rawtypes" )
        Enum[] enumValArr = enumClass.getEnumConstants();

        for( @SuppressWarnings( "rawtypes" )Enum enumVal : enumValArr ) {
            valueList.add( enumVal.toString().toUpperCase() );
        }

    }
}
