/**
 * Copyright (c) 2014-2016 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 12/7/15 10:23 PM
 */

package com.thecoffeine.virtuoso.music.model.entity;

import com.thecoffeine.virtuoso.module.model.AbstractModel;
import com.thecoffeine.virtuoso.music.model.persistence.mock.SongMock;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.constraints.NotNull;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * Tests for Song.
 *
 * @see Song
 * @version 1.0
 */
public class SongTest extends AbstractModel {

    /**
     * Test field validation for entity field correct.
     */
    @Test
    public void testSongFieldsSuccess() {

        Set<ConstraintViolation<Song>> constraintViolationSet;

        //- Successful-//
        //- Create entity-//
        Song songSuccess = SongMock.find();
        //- Validate -//
        constraintViolationSet = validator.validate( songSuccess );

        assertEquals( 0, constraintViolationSet.size() );
    }

    /**
     * Test field validation for entity field failure.
     */
    @Test
    public void testSongFieldsFailure() {
        Set<ConstraintViolation<Song>> constraintViolationSet;

        //- Failure -//
        //- Create entity -//
        Song songFailure = new Song(
            null
        );
        //- Validate -//
        constraintViolationSet = validator.validate( songFailure );

        assertEquals( 5, constraintViolationSet.size() );
        for ( ConstraintViolation<Song> constraintViolation : constraintViolationSet ) {
            //- Property name -//
            assertTrue(
                new ArrayList<String>() {{
                    add( "data" );
                    add( "staffs" );
                    add( "lyrics" );
                    add( "locale" );
                }}.contains(
                    this.getPropertyName(
                        constraintViolation.getPropertyPath()
                    )
                )
            );
            //- Annotation type -//
            assertTrue(
                new ArrayList<Class>() {{
                    add( NotNull.class );
                    add( NotEmpty.class );
                }}.contains(
                    constraintViolation.getConstraintDescriptor().getAnnotation().annotationType()
                )
            );
            //- Message -//
            assertTrue(
                new ArrayList<String>() {{
                    add( "may not be null" );
                    add( "may not be empty" );
                }}.contains( constraintViolation.getMessage() )
            );
        }

        //- Failure : Incorrect length  -//
        //- Create entity -//
        Song songFailureLength = SongMock.find();
        //- Set locale -//
        songFailureLength.setLocale( "123456" );

        //- Validate -//
        constraintViolationSet = validator.validate( songFailureLength );

        assertEquals( 1, constraintViolationSet.size() );

        for ( ConstraintViolation<Song> constraintViolation : constraintViolationSet ) {
            //- Property name -//
            assertTrue(
                new ArrayList<String>() {{
                    add( "locale" );
                }}.contains(
                    this.getPropertyName(
                        constraintViolation.getPropertyPath()
                    )
                )
            );
            //- Annotation type -//
            assertTrue(
                new ArrayList<Class>() {{
                    add( Length.class );
                }}.contains(
                    constraintViolation.getConstraintDescriptor().getAnnotation().annotationType()
                )
            );
            //- Message -//
            assertTrue(
                new ArrayList<String>() {{
                    add( "length must be between 0 and 5" );
                }}.contains( constraintViolation.getMessage() )
            );
        }
    }

    /*
    * Test field validation for entity failure( empty )
    */
    @Test
    public void testSongFieldEmpty() {

        Set<ConstraintViolation<Song>> constraintViolationSet;

        //- Failure: fields is empty-//
        //- Create entity -//
        Song songFailureEmpty = new Song(
            //- Create list of song locale -//
            new HashSet<>(),
            //- Create list of staff -//
            new HashSet<>(),
            //- Create list of text -//
            new HashSet<>(),
            //- Create list of video -//
            new HashSet<>(),
            ""
        );
        //- Validate-//
        constraintViolationSet = validator.validate( songFailureEmpty );

        assertEquals( 4 , constraintViolationSet.size() );
        for ( ConstraintViolation<Song> constraintViolation : constraintViolationSet ) {
            //- Property name -//
            assertTrue(
                new ArrayList<String>() {{
                    add( "data" );
                    add( "staffs" );
                    add( "lyrics" );
                    add( "locale" );
                }}.contains(
                    this.getPropertyName(
                        constraintViolation.getPropertyPath()
                    )
                )
            );
            //- Annotation type -//
            assertTrue(
                new ArrayList<Class>() {{
                    add( NotEmpty.class );
                }}.contains(
                    constraintViolation.getConstraintDescriptor().getAnnotation().annotationType()
                )
            );
            //- Message -//
            assertTrue(
                new ArrayList<String>() {{
                    add( "may not be empty" );
                }}.contains( constraintViolation.getMessage() )
            );
        }
    }
}
