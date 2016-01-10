/**
 * Copyright (c) 2014-2015 by Coffeine Inc
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 12/7/15 10:23 PM
 */

package com.coffeine.virtuoso.music.model.entity;

import com.coffeine.virtuoso.module.model.AbstractModel;
import com.coffeine.virtuoso.music.model.persistence.mock.ComposerMock;
import com.coffeine.virtuoso.music.model.persistence.mock.PoetMock;

import org.hibernate.validator.constraints.NotEmpty;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Set;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Tests for Text
 * @see Text
 *
 * @version 1.0
 */
public class TextTest extends AbstractModel {
    /**
     * Test field validation for entity field correct
     */
    @Test
    public void testTextFieldsSuccess() {

        Set < ConstraintViolation < Text > > constraintViolationSet;
        //- Success -//
        //- Create entity -//
        Text textSuccess = new Text(
            new Song(
                //-Create composer-//
                ComposerMock.findAll(),
                //- Create poet-//
                PoetMock.findAll(),
                //- Create list of song locale -//
                new ArrayList < SongLocale >() {{
                    add(
                        new SongLocale(
                            "user",
                            "uk-UA"
                        )
                    );
                }},
                //- Create list of staff -//
                new ArrayList < Staff >() {{
                    add(
                        new Staff(
                            new StaffType(
                                "CHORDS",
                                "Chords",
                                "Standard chords"
                            ),
                            new Style(
                                "One",
                                "Two",
                                "three"
                            ),
                            "uk-UA"
                        )
                    );
                }},
                //- Create list of text -//
                new ArrayList < Text >() {{
                    add(
                        new Text(
                            "uk-UA"
                        )
                    );
                }},
                //- Create list of video -//
                new ArrayList < Video >() {{
                    add(
                        new Video(
                            new VideoType(
                                "POLKA",
                                "Polka",
                                "Ukrainian polka"
                            ),
                            "uk-UA",
                            "user",
                            "video1"
                        )
                    );
                }},
                "uk-UA"
            ),
            "uk-UA"
        );
        //- Validate -//
        constraintViolationSet = validator.validate( textSuccess );

        assertEquals( 0, constraintViolationSet.size() );
    }

    /**
     * Test field validation for entity field failure
     */
    @Test
    public void testSongFieldsFailure() {
        Set < ConstraintViolation < Text > > constraintViolationSet;
        //- Failure : incorrect song -//
        //- Create entity -//
        Text textFailureSong = new Text(
            null,
            "uk-UA"
        );
        //- Validate -//
        constraintViolationSet = validator.validate( textFailureSong );

        assertEquals( 1, constraintViolationSet.size() );
        for ( ConstraintViolation < Text > constraintViolation : constraintViolationSet ) {
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
        Text textFailureLocale = new Text(
            new Song(
                //-Create composer-//
                ComposerMock.findAll(),
                //- Create poet-//
                PoetMock.findAll(),
                //- Create list of song locale -//
                new ArrayList < SongLocale >() {{
                    add(
                        new SongLocale(
                            "user",
                            "uk-UA"
                        )
                    );
                }},
                //- Create list of staff -//
                new ArrayList < Staff >() {{
                    add(
                        new Staff(
                            new StaffType(
                                "CHORDS",
                                "Chords",
                                "Standard chords"
                            ),
                            new Style(
                                "One",
                                "Two",
                                "three"
                            ),
                            "uk-UA"
                        )
                    );
                }},
                //- Create list of text -//
                new ArrayList < Text >() {{
                    add(
                        new Text(
                            "uk-UA"
                        )
                    );
                }},
                //- Create list of video -//
                new ArrayList < Video >() {{
                    add(
                        new Video(
                            new VideoType(
                                "POLKA",
                                "Polka",
                                "Ukrainian polka"
                            ),
                            "uk-UA",
                            "user",
                            "video1"
                        )
                    );
                }},
                "uk-UA"
            ),
            null
        );
        //- Validate -//
        constraintViolationSet = validator.validate( textFailureLocale );

        assertEquals( 2, constraintViolationSet.size() );
        for ( ConstraintViolation < Text > constraintViolation : constraintViolationSet ) {
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

        Set < ConstraintViolation < Text > > constraintViolationSet;

        //- Failure: fields is empty-//
        //- Create entity -//
        Text textFailureEmpty = new Text(
            new Song(
                //-Create composer-//
                ComposerMock.findAll(),
                //- Create poet-//
                PoetMock.findAll(),
                //- Create list of song locale -//
                new ArrayList < SongLocale >() {{
                    add(
                        new SongLocale(
                            "user",
                            "uk-UA"
                        )
                    );
                }},
                //- Create list of staff -//
                new ArrayList < Staff >() {{
                    add(
                        new Staff(
                            new StaffType(
                                "CHORDS",
                                "Chords",
                                "Standard chords"
                            ),
                            new Style(
                                "One",
                                "Two",
                                "three"
                            ),
                            "uk-UA"
                        )
                    );
                }},
                //- Create list of text -//
                new ArrayList < Text >() {{
                    add(
                        new Text(
                            "uk-UA"
                        )
                    );
                }},
                //- Create list of video -//
                new ArrayList < Video >() {{
                    add(
                        new Video(
                            new VideoType(
                                "POLKA",
                                "Polka",
                                "Ukrainian polka"
                            ),
                            "uk-UA",
                            "user",
                            "video1"
                        )
                    );
                }},
                "uk-UA"
            ),
            ""
        );
        //- Validate -//
        constraintViolationSet = validator.validate( textFailureEmpty );

        assertEquals( 1, constraintViolationSet.size() );
        for ( ConstraintViolation < Text > constraintViolation : constraintViolationSet ) {
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
