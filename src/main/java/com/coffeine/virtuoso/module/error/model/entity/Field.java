/// *** Error :: Model :: Entity :: Field   *** *** *** *** *** *** *** *** ///

/** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *
 *                                                                  *
 * @copyright 2014 (c), by Coffeine
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 2014-07-25 15:35:28 :: 2014-07-25 15:39:08
 *
 * @address /Ukraine/Ivano-Frankivsk/Tychyny/7a
 *                                                                  *
 *///*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *

/// *** Code    *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ///
package com.coffeine.virtuoso.module.error.model.entity;

/**
 * @version 1.0.
 */
public class Field {

    /// *** Properties  *** ///
    /**
     * Field, which contain error
     */
    protected String field;

    /**
     * Message of error
     */
    protected String message;


    /// *** Methods     *** ///
    /**
     * Constructor, init error
     *
     * @param field
     * @param message
     */
    public Field(
        String field,
        String message
    ) {
        this.field = field;
        this.message = message;
    }

    //- SECTION :: GET -//

    /**
     * Get field
     *
     * @return String
     */
    public String getField() {
        return field;
    }

    /**
     * Get message
     *
     * @return String
     */
    public String getMessage() {
        return message;
    }


    //- SECTION :: SET -//
    /**
     * Set field
     *
     * @param field
     */
    public void setField( String field ) {
        this.field = field;
    }

    /**
     * Set message
     *
     * @param message
     */
    public void setMessage( String message ) {
        this.message = message;
    }
}
