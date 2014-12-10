/*
 * @copyright (c) 2014, by Valentyn Namisnyk
 *
 * @author Valentyn Namisnyk <Valentun_Prodyser@ukr.net>
 */

package com.coffeine.virtuoso.module.user.model.entity;

import com.coffeine.virtuoso.module.model.AbstractModel;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Set;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

/**
 * Tests for Style
 * @see com.coffeine.virtuoso.module.user.model.entity.Style
 *
 * @version 1.0
 */
public class StyleTest extends AbstractModel {

    /*
     * Test field validation for entity correct
     */
    @Test
    public void testStyleFieldsSuccess() {
        Set< ConstraintViolation < Style > > constraintViolationSet;

        //- Success -//
        //- Create entity-//

        Style styleSuccess = new Style(
            "One",
            "Two"
        );
        //- Validate -//
        constraintViolationSet = validator.validate( styleSuccess );

        assertEquals( 0, constraintViolationSet.size() );
    }


    /*
     * Test field validation for entity correct
     */
    @Test
    public void testStyleFieldsFailure() {
        Set < ConstraintViolation < Style> > constraintViolationSet;

        //- Failure -//
        //- Create entity-//
        Style styleFailure = new Style(
            null,
            null
        );
        //- Validate -//
        constraintViolationSet = validator.validate( styleFailure );

        assertEquals( 4, constraintViolationSet.size() );
        for( ConstraintViolation< Style > constraintViolation : constraintViolationSet ) {
            //- Property name -//
            assertTrue(
                new ArrayList< String >() {{
                    add( "code" );
                    add("title");
                }}.contains(
                    this.getPropertyName( constraintViolation.getPropertyPath())
                )
            );
            //- Annotation Type -//
            assertTrue(
                new ArrayList < Class >() {{
                    add( NotNull.class );
                    add( NotEmpty.class );
                }}.contains(
                    constraintViolation.getConstraintDescriptor().getAnnotation().annotationType()
                )
            );
            //- Message -//
            assertTrue(
                new ArrayList < String >() {{
                    add( "may not be null" );
                    add( "may not be empty");
                }}.contains(
                    constraintViolation.getMessage()
                )
            );
        }

        //- Failure: Incorrect length -//
        //- Create entity -//
        Style styleFailureLength = new Style(
            "123456789012345678901234567890123",
            "123456789012345678901234567890123"
        );
        //- Validate -//
        constraintViolationSet = validator.validate( styleFailureLength );

        assertEquals( 2, constraintViolationSet.size() );
        for( ConstraintViolation < Style > constraintViolation : constraintViolationSet ) {
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
                new ArrayList < Class >() {{
                    add( Length.class );
                }}.contains(
                    constraintViolation.getConstraintDescriptor().getAnnotation().annotationType()
                )
            );
            //-Message-//
            assertTrue(
                new ArrayList < String >() {{
                    add( "length must be between 0 and 32" );
                    add( "length must be between 0 and 32" );
                }}.contains(
                    constraintViolation.getMessage()
                )
            );
        }
    }
    /**
     * Test field validation for entity failure
     */
    @Test
    public void testStyleFieldEmpty() {

        Set < ConstraintViolation < Style > > constraintViolationSet;

        //- Failure: fields is empty-//
        //- Create entity -//
        Style styleFailureEmpty = new Style(
            "",
            ""
        );
        //- Validate -//
        constraintViolationSet = validator.validate( styleFailureEmpty );

        assertEquals( 2, constraintViolationSet.size() );
        for( ConstraintViolation < Style > constraintViolation : constraintViolationSet ) {
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
                new ArrayList < Class >() {{
                    add( NotEmpty.class );
                }}.contains(
                    constraintViolation.getConstraintDescriptor().getAnnotation().annotationType()
                )
            );
            //-Message-//h-io]p
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
