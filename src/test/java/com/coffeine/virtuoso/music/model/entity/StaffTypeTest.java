/**
 * Copyright (c) 2014-2015 by Coffeine Inc
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 12/7/15 10:23 PM
 */

package com.coffeine.virtuoso.music.model.entity;

import com.coffeine.virtuoso.module.model.AbstractModel;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.constraints.NotNull;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * Tests for StaffType
 * @see StaffType
 *
 * @version 1.0
 */
public class StaffTypeTest extends AbstractModel {

    /**
     * Test field validation for entity correct
     */
    @Test
    public void testStaffTypeFieldsSuccess() {

        Set<ConstraintViolation < StaffType > > constraintViolationSet;

        //- Success -//
        //- Create entity -//
        StaffType staffTypeSuccess = new StaffType(
                "CHORDS",
                "Chords",
                "Standard chords"
        );

        //- Validate -//
        constraintViolationSet = validator.validate( staffTypeSuccess );

        assertEquals( 0, constraintViolationSet.size() );
    }


    /**
     * Test field validation for entity failure
     */
    @Test
    public void testStaffTypeFieldFailure() {

        Set < ConstraintViolation < StaffType > > constraintViolationSet;

        //- Failure -//
        //- Create entity -//
        StaffType staffTypeFailure = new StaffType(
            null,
            null,
            "Standard chords"
        );

        //- Validate -//
        constraintViolationSet = validator.validate( staffTypeFailure );

        assertEquals( 4, constraintViolationSet.size() );
        for ( ConstraintViolation < StaffType > constraintViolation : constraintViolationSet ) {
            //- Property name -//
            assertTrue(
                new ArrayList < String >() {{
                    add( "code" );
                    add( "title" );
                }}.contains(
                    this.getPropertyName(
                        constraintViolation.getPropertyPath()
                    )
                )
            );
            //- Annotation type-//
            assertTrue(
                    new ArrayList<Class>() {{
                        add(NotNull.class);
                        add(NotEmpty.class);
                    }}.contains(
                            constraintViolation.getConstraintDescriptor().getAnnotation().annotationType()
                    )
            );
            //- Message-//
            assertTrue(
                new ArrayList <  String > () {{
                    add( "may not be null" );
                    add( "may not be empty" );

                }}.contains(
                    constraintViolation.getMessage()
                )
            );
        }

        //- Failure: Incorrect length-//
        //- Create entity -//
        StaffType staffTypeFailureLength = new StaffType(
            "123456789012345678901234567890123",
            "123456789012345678901234567890123",
            null
        );

        //- Validate -//
        constraintViolationSet = validator.validate( staffTypeFailureLength );

        assertEquals( 2, constraintViolationSet.size() );

        for( ConstraintViolation < StaffType > constraintViolation : constraintViolationSet ){
            //- Property name -//
            assertTrue(
                new ArrayList < String >() {{
                    add( "code" );
                    add( "title" );
                }}.contains(
                    this.getPropertyName(
                        constraintViolation.getPropertyPath()
                    )
                )
            );
            //- Annotation type -//
            assertTrue(
                new ArrayList < Class > () {{
                    add( Length.class );
                }}.contains(
                    constraintViolation.getConstraintDescriptor().getAnnotation().annotationType()
                )
            );
            //- Message -//
            assertTrue(
                new ArrayList < String >() {{
                    add( "length must be between 0 and 32" );
                }}.contains(
                    constraintViolation.getMessage()
                )
            );
        }
    }

    /*
* Test field validation for entity failure( empty )
*/
    @Test
    public void testAccessFieldEmpty() {
        Set < ConstraintViolation < StaffType > > constraintViolationSet;

        //- Failure: fields is empty-//
        //- Create entity -//
        StaffType staffTypeFailureEmpty = new StaffType(
            "",
            "",
            null
        );

        //- Validate -//
        constraintViolationSet = validator.validate( staffTypeFailureEmpty );

        assertEquals( 2, constraintViolationSet.size() );

        for( ConstraintViolation < StaffType > constraintViolation : constraintViolationSet ){
            //- Property name -//
            assertTrue(
                new ArrayList < String >() {{
                    add( "code" );
                    add( "title" );
                }}.contains(
                    this.getPropertyName(
                        constraintViolation.getPropertyPath()
                    )
                )
            );
            //- Annotation type -//
            assertTrue(
                new ArrayList < Class > () {{
                    add( NotEmpty.class );
                }}.contains(
                    constraintViolation.getConstraintDescriptor().getAnnotation().annotationType()
                )
            );
            //- Message -//
            assertTrue(
                new ArrayList < String >() {{
                    add( "may not be empty" );
                }}.contains(
                    constraintViolation.getMessage()
                )
            );
        }
    }
}
