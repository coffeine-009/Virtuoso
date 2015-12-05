/// *** Error :: Model :: Entity :: ValidationError *** *** *** *** *** *** ///

    /** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *
     *                                                                  *
     * @copyright 2014 (c), by Coffeine
     *
     * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
     *
     * @date 2014-07-25 15:40:00 :: 2014-07-25 15:44:14
     *
     * @address /Ukraine/Ivano-Frankivsk/Tychyny/7a
     *                                                                  *
    *///*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *

/// *** Code    *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ///
package com.coffeine.virtuoso.error.model.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Object for describe input error
 *
 * @version 1.0
 */
public class ValidationError {

    /// *** Properties  *** ///
    /**
     * List of errors/field
     */
    protected List < Field > fieldErrors;


    //***    Methods     *** ///
    /**
     * Default constructor
     */
    public ValidationError() {
        this.fieldErrors = new ArrayList<>();
    }

    /**
     * Add new fieldErrors
     *
     * @param field Name of field
     * @param message Message about error for this field
     */
    public void addFieldError( String field, String message ) {
        fieldErrors.add(
            new Field(
                field,
                message
            )
        );
    }


    //-  SECTION :: GET -//
    /**
     * Get field erros
     *
     * @return List<Field> List of errors/fields
     */
    public List < Field > getFieldErrors() {
        return fieldErrors;
    }


    //- SECTION :: SET -//
    /**
     * Set field error
     *
     * @param fieldErrors List of errors/fields
     */
    public void setFieldErrors( List < Field > fieldErrors ) {
        this.fieldErrors = fieldErrors;
    }
}
