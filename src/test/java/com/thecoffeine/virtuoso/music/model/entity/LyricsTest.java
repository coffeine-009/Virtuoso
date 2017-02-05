/**
 * Copyright (c) 2014-2015 by Coffeine Inc
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 12/7/15 10:23 PM
 */

package com.thecoffeine.virtuoso.music.model.entity;

import com.thecoffeine.virtuoso.module.model.AbstractModel;
import com.thecoffeine.virtuoso.music.model.persistence.mock.PoetMock;
import com.thecoffeine.virtuoso.music.model.persistence.mock.SongMock;
import org.hibernate.validator.constraints.NotEmpty;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.constraints.NotNull;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * Tests for Lyrics
 * @see Lyrics
 *
 * @version 1.0
 */
public class LyricsTest extends AbstractModel {
    /**
     * Test field validation for entity field correct
     */
    @Test
    public void testTextFieldsSuccess() {

        Set < ConstraintViolation <Lyrics> > constraintViolationSet;
        //- Success -//
        //- Create entity -//
        Lyrics lyricsSuccess = new Lyrics(
            PoetMock.findAll(),
            SongMock.find(),
            "uk-UA",
            "Lyrics"
        );
        //- Validate -//
        constraintViolationSet = validator.validate( lyricsSuccess );

        assertEquals( 0, constraintViolationSet.size() );
    }

    /**
     * Test field validation for entity field failure
     */
    @Test
    public void testSongFieldsFailure() {
        Set < ConstraintViolation <Lyrics> > constraintViolationSet;
        //- Failure : incorrect song -//
        //- Create entity -//
        Lyrics lyricsFailureSong = new Lyrics();
            lyricsFailureSong.setLocale( "uk-UA" );

        //- Validate -//
        constraintViolationSet = validator.validate( lyricsFailureSong );

        assertEquals( 1, constraintViolationSet.size() );
        for ( ConstraintViolation <Lyrics> constraintViolation : constraintViolationSet ) {
            //- Property name -//
            assertTrue(
                new ArrayList < String >() {{
                    add( "song" );
                }}.contains(
                    this.getPropertyName(
                        constraintViolation.getPropertyPath()
                    )
                )
            );
            //- Annotation type -//
            assertTrue(
                new ArrayList < Class >() {{
                    add( NotNull.class );
                }}.contains(
                    constraintViolation.getConstraintDescriptor().getAnnotation().annotationType()
                )
            );
            //- Message -//
            assertTrue(
                new ArrayList < String >() {{
                    add( "may not be null" );
                }}.contains( constraintViolation.getMessage() )
            );
        }
        //- Failure : incorrect locale -//
        //- Create entity -//
        Lyrics lyricsFailureLocale = new Lyrics(
            PoetMock.findAll(),
            SongMock.find(),
            null,
            "Lyrics"
        );
        //- Validate -//
        constraintViolationSet = validator.validate( lyricsFailureLocale );

        assertEquals( 2, constraintViolationSet.size() );
        for ( ConstraintViolation <Lyrics> constraintViolation : constraintViolationSet ) {
            //- Property name -//
            assertTrue(
                new ArrayList < String >() {{
                    add( "locale" );
                }}.contains(
                    this.getPropertyName(
                        constraintViolation.getPropertyPath()
                    )
                )
            );
            //- Annotation type -//
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
                    add( "may not be empty" );
                }}.contains( constraintViolation.getMessage() )
            );
        }
    }

    /*
    * Test field validation for entity failure( empty )
    */
    @Test
    public void testTextFieldEmpty() {

        Set < ConstraintViolation <Lyrics> > constraintViolationSet;

        //- Failure: fields is empty-//
        //- Create entity -//
        Lyrics lyricsFailureEmpty = new Lyrics(
            PoetMock.findAll(),
            SongMock.find(),
            "",
            "Lyrics"
        );
        //- Validate -//
        constraintViolationSet = validator.validate( lyricsFailureEmpty );

        assertEquals( 1, constraintViolationSet.size() );
        for ( ConstraintViolation <Lyrics> constraintViolation : constraintViolationSet ) {
            //- Property name -//
            assertTrue(
                new ArrayList < String >() {{
                    add( "locale" );
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
            //- Message -//
            assertTrue(
                new ArrayList < String >() {{
                    add( "may not be empty" );
                }}.contains( constraintViolation.getMessage() )
            );
        }
    }
}
