/*
 * @copyright (c) 2014, by Valentyn Namisnyk
 *
 * @author Valentyn Namisnyk <Valentun_Prodyser@ukr.net>
 */

package com.coffeine.virtuoso.module.user.model.entity;

/**
 * Created by valentyn on 10/28/14.
 */

import com.coffeine.virtuoso.module.model.AbstractModel;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Set;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * Tests for VideoType
 * @see com.coffeine.virtuoso.module.user.model.entity.VideoType
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
    public void testStaffTypeFieldsFailure() {

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
}
