/**
 * Copyright (c) 2014-2015 by Coffeine Inc
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 12/7/15 10:23 PM
 */

package com.coffeine.virtuoso.music.model.entity;

import com.coffeine.virtuoso.module.model.AbstractModel;
import com.coffeine.virtuoso.music.model.persistence.mock.SongMock;

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
 * Tests for song locale
 * @see SongLocale
 *
 * @version 1.0
 */
public class SongLocaleTest extends AbstractModel {

    /*
    * Test field validation for entity correct
    */
    @Test
    public void testSongLocaleFieldsSuccess() {

        Set<ConstraintViolation<SongLocale>> constraintViolationSet;

        //- Success -//
        //- Create entity-//
        SongLocale songLocaleSuccess = new SongLocale(
            SongMock.find(),
            "title",
            "uk-Ua"
        );
        //- Validate -//
        constraintViolationSet = validator.validate( songLocaleSuccess );

        assertEquals( 0, constraintViolationSet.size() );
    }

    /*
* Test field validation for entity failure
*/
    @Test
    public void testSongLocaleFieldsFailure() {

        Set < ConstraintViolation < SongLocale > > constraintViolationSet;

        //- Failure -//
        //- Create entity-//
        SongLocale songLocaleFailure = new SongLocale(
            SongMock.find(),
            null,
            null
        );
        //- Validate -//
        constraintViolationSet = validator.validate( songLocaleFailure );

        assertEquals( 4, constraintViolationSet.size()) ;
        for ( ConstraintViolation < SongLocale > constraintViolation : constraintViolationSet ) {
            //- Property name -//
            assertTrue(
                new ArrayList < String >() {{
                    add("title");
                    add("locale");
                }}.contains(
                    this.getPropertyName(
                        constraintViolation.getPropertyPath()
                    )
                )
            );
            //- Annotation type -//
            assertTrue(
                new ArrayList < Class >() {{
                    add(NotNull.class);
                    add(NotEmpty.class);
                }}.contains(
                    constraintViolation.getConstraintDescriptor().getAnnotation().annotationType()
                )
            );
            //- Message -//
            assertTrue(
                new ArrayList < String >() {{
                    add("may not be null");
                    add("may not be empty");
                }}.contains(constraintViolation.getMessage())
            );
        }
        //- Failure : Incorrect song -//
        //- Create entity-//
        SongLocale songLocaleFailureSong = new SongLocale(
            null,
            "title",
            "uk-UA"
        );
        //- Validate -//
        constraintViolationSet = validator.validate( songLocaleFailureSong );

        assertEquals( 1, constraintViolationSet.size()) ;
        for ( ConstraintViolation < SongLocale > constraintViolation : constraintViolationSet ) {
            //- Property name -//
            assertTrue(
                new ArrayList < String >() {{
                    add("song");
                }}.contains(
                    this.getPropertyName(
                        constraintViolation.getPropertyPath()
                    )
                )
            );
            //- Annotation type -//
            assertTrue(
                new ArrayList < Class >() {{
                    add(NotNull.class);
                }}.contains(
                    constraintViolation.getConstraintDescriptor().getAnnotation().annotationType()
                )
            );
            //- Message -//
            assertTrue(
                new ArrayList < String >() {{
                    add("may not be null");
                }}.contains(constraintViolation.getMessage())
            );
        }
        //- Failure : Incorrect length  -//
        //- Create entity -//
        SongLocale songLocaleFailureLength = new SongLocale(
            SongMock.find(),
            "12345678901234567890123456789012345678901234567890123456789012345",
            "123456"
        );
        //- Validate -//
        constraintViolationSet = validator.validate( songLocaleFailureLength );

        assertEquals( 2, constraintViolationSet.size() );

        for ( ConstraintViolation < SongLocale > constraintViolation : constraintViolationSet ) {
            //- Property name -//
            assertTrue(
                new ArrayList < String >() {{
                    add("title");
                    add("locale");
                }}.contains(
                    this.getPropertyName(
                        constraintViolation.getPropertyPath()
                    )
                )
            );
            //- Annotation type -//
            assertTrue(
                new ArrayList< Class >() {{
                    add( Length.class );
                }}.contains(
                    constraintViolation.getConstraintDescriptor().getAnnotation().annotationType()
                )
            );
            //- Message -//
            assertTrue(
                new ArrayList<String>() {{
                    add("length must be between 0 and 5");
                    add("length must be between 0 and 64");
                }}.contains(constraintViolation.getMessage())
            );
        }
    }

    /*
    * Test field validation for entity failure( empty )
    */
    @Test
    public void testSongLocaleFieldsEmpty() {

        Set < ConstraintViolation < SongLocale > > constraintViolationSet;

        //- Failure: fields is empty-//
        //- Create entity -//
        SongLocale songLocaleFailureEmpty = new SongLocale(
            SongMock.find(),
            "",
            ""
        );
        //- Validate -//
        constraintViolationSet = validator.validate( songLocaleFailureEmpty );

        assertEquals( 2, constraintViolationSet.size() );

        for ( ConstraintViolation < SongLocale > constraintViolation : constraintViolationSet ) {
            //- Property name -//
            assertTrue(
                new ArrayList < String >() {{
                    add("title");
                    add("locale");
                }}.contains(
                    this.getPropertyName(
                        constraintViolation.getPropertyPath()
                    )
                )
            );
            //- Annotation type -//
            assertTrue(
                new ArrayList< Class >() {{
                    add( NotEmpty.class );
                }}.contains(
                    constraintViolation.getConstraintDescriptor().getAnnotation().annotationType()
                )
            );
            //- Message -//
            assertTrue(
                new ArrayList<String>() {{
                    add("may not be empty");
                }}.contains(constraintViolation.getMessage())
            );
        }
    }
}
