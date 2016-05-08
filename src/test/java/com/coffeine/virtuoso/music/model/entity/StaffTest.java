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
 * Tests for Staff
 * @see Staff
 *
 * @version 1.0
 */
public class StaffTest extends AbstractModel {
    /**
     * Test field validation for entity filled correct
     */
    @Test
    public void testStaffFieldsSuccess() {

        Set<ConstraintViolation<Staff>> constraintViolationSet;
        //- Success -//
        //- Create entity -//
        Staff staffSuccess = new Staff(
            new Song(
                //-Create composer-//
                ComposerMock.findAll(),
                //- Create poet-//
                PoetMock.findAll(),
                //- Create list of song locale -//
                new ArrayList<SongLocale>() {{
                    add(
                        new SongLocale(
                            "user",
                            "uk-UA"
                        )
                    );
                }},
                //- Create list of text -//
                new ArrayList<Text>() {{
                    add(
                        new Text(
                            "uk-UA",
                            "Lyrics"
                        )
                    );
                }},
                //- Create list of video -//
                new ArrayList<Video>() {{
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
            new StaffType(
                "CHORDS",
                "Chords",
                "Standard chords"
            ),
            new Style(
                "POLKA",
                "Polka"
            ),
            "en-US",
            "<xml>MusicXML</xml>"
        );
        //- Validate-//
        constraintViolationSet = validator.validate(staffSuccess);

        assertEquals(0, constraintViolationSet.size());
    }

    /**
     * Test field validation for entity failure
     */
    @Test
    public void testStaffFieldFailure() {

        Set<ConstraintViolation<Staff>> constraintViolationSet;

        //- Failure -//
        //- Create entity -//
        Staff staffFailure = new Staff(
            null,
            "uk-UA",
            "<xml>MusicXML</xml>"
        );
        //- Validate -//
        constraintViolationSet = validator.validate(staffFailure);

        assertEquals(3, constraintViolationSet.size());
        for (ConstraintViolation<Staff> constraintViolation : constraintViolationSet) {
            //- Property name -//
            assertTrue(
                new ArrayList<String>() {{
                    add("song");
                    add("staffType");
                    add("style");
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
                }}.contains(
                    constraintViolation.getConstraintDescriptor().getAnnotation().annotationType()
                )
            );
            //- Message-//
            assertTrue(
                new ArrayList<String>() {{
                    add("may not be null");

                }}.contains(
                    constraintViolation.getMessage()
                )
            );
        }
        //- Failure: locale -//
        //- Create entity -//
        Staff staffFailureLocale = new Staff(
            new Song(
                //-Create composer-//
                ComposerMock.findAll(),
                //- Create poet-//
                PoetMock.findAll(),
                //- Create list of song locale -//
                new ArrayList<SongLocale>() {{
                    add(
                        new SongLocale(
                            "user",
                            "uk-UA"
                        )
                    );
                }},
                //- Create list of text -//
                new ArrayList<Text>() {{
                    add(
                        new Text(
                            "uk-UA",
                            "Lyrics"
                        )
                    );
                }},
                //- Create list of video -//
                new ArrayList<Video>() {{
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
            new StaffType(
                "CHORDS",
                "Chords",
                "Standard chords"
            ),
            new Style(
                "POLKA",
                "Polka"
            ),
            null,
            "<xml>MusicXML</xml>"
        );
        //- Validate -//
        constraintViolationSet = validator.validate(staffFailureLocale);

        assertEquals(2, constraintViolationSet.size());
        for (ConstraintViolation<Staff> constraintViolation : constraintViolationSet) {
            //- Property name -//
            assertTrue(
                new ArrayList<String>() {{
                    add("locale");
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
                new ArrayList<String>() {{
                    add("may not be null");
                    add("may not be empty");

                }}.contains(
                    constraintViolation.getMessage()
                )
            );
        }
    }

    /**
     * Test field validation for entity field incorrect
     */
    @Test
    public void testStaffFieldsFailureLength() {

        Set < ConstraintViolation < Staff > > constraintViolationSet;
        //- Failure: Incorrect Length -//
        //- Create entity -//
        Staff staffFailureLength = new Staff(
            new Song(
                //-Create composer-//
                ComposerMock.findAll(),
                //- Create poet-//
                PoetMock.findAll(),
                //- Create list of song locale -//
                new ArrayList<SongLocale>() {{
                    add(
                        new SongLocale(
                            "user",
                            "uk-UA"
                        )
                    );
                }},
                //- Create list of text -//
                new ArrayList<Text>() {{
                    add(
                        new Text(
                            "uk-UA",
                            "Lyrics"
                        )
                    );
                }},
                //- Create list of video -//
                new ArrayList<Video>() {{
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
            new StaffType(
                "CHORDS",
                "Chords",
                "Standard chords"
            ),
            new Style(
                "POLKA",
                "Polka"
            ),
            "123456",
            "<xml>MusicXML</xml>"
        );
        //- Validate -//
        constraintViolationSet = validator.validate( staffFailureLength );

        assertEquals(1, constraintViolationSet.size());
        for (ConstraintViolation<Staff> constraintViolation : constraintViolationSet) {
            //- Property name -//
            assertTrue(
                new ArrayList < String >() {{
                    add("locale");
                }}.contains(
                    this.getPropertyName(
                        constraintViolation.getPropertyPath()
                    )
                )
            );
            //- Annotation type-//
            assertTrue(
                new ArrayList < Class >() {{
                    add(Length.class);
                }}.contains(
                    constraintViolation.getConstraintDescriptor().getAnnotation().annotationType()
                )
            );
            //- Message-//
            assertTrue(
                new ArrayList < String >() {{
                    add("length must be between 0 and 5");

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
    public void testVideoFieldsEmpty() {
        Set < ConstraintViolation < Staff > > constraintViolationSet;
        //- Failure: Empty -//
        //- Create entity -//
        Staff staffFailureEmpty = new Staff(
            new Song(
                //-Create composer-//
                ComposerMock.findAll(),
                //- Create poet-//
                PoetMock.findAll(),
                //- Create list of song locale -//
                new ArrayList<SongLocale>() {{
                    add(
                        new SongLocale(
                            "user",
                            "uk-UA"
                        )
                    );
                }},
                //- Create list of text -//
                new ArrayList<Text>() {{
                    add(
                        new Text(
                            "uk-UA",
                            "Lyrics"
                        )
                    );
                }},
                //- Create list of video -//
                new ArrayList<Video>() {{
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
            new StaffType(
                "CHORDS",
                "Chords",
                "Standard chords"
            ),
            new Style(
                "POLKA",
                "Polka"
            ),
            "",
            "<xml>MusicXML</xml>"
        );
        constraintViolationSet = validator.validate( staffFailureEmpty );

        assertEquals( 1, constraintViolationSet.size() );
        for (ConstraintViolation < Staff > constraintViolation : constraintViolationSet) {
            //- Property name -//
            assertTrue(
                new ArrayList < String >() {{
                    add("locale");
                }}.contains(
                    this.getPropertyName(
                        constraintViolation.getPropertyPath()
                    )
                )
            );
            //- Annotation type-//
            assertTrue(
                new ArrayList < Class >() {{
                    add( NotEmpty.class );
                }}.contains(
                    constraintViolation.getConstraintDescriptor().getAnnotation().annotationType()
                )
            );
            //- Message-//
            assertTrue(
                new ArrayList < String >() {{
                    add("may not be empty");
                }}.contains(
                    constraintViolation.getMessage()
                )
            );
        }
    }
}
