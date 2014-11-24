/*
 * @copyright (c) 2014, by Valentyn Namisnyk
 *
 * @author Valentyn Namisnyk <Valentun_Prodyser@ukr.net>
 */

package com.coffeine.virtuoso.module.user.model.entity;

import com.coffeine.virtuoso.module.model.AbstractModel;
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
 * @see com.coffeine.virtuoso.module.user.model.entity.Text
 *
 * @version 1.0
 */
public class TextTest extends AbstractModel {
    /**
     * Test field validation for entity field correct
     */
    @Test
    public void TestTextFieldsSuccess() {

        Set < ConstraintViolation < Text > > constraintViolationSet;
        //- Success -//
        //- Create entity -//
        Text textSuccess = new Text(
            new Song(
                //-Create composer-//
                new Composer(
                    new ArrayList< ComposerLocale >() {{
                        add(
                            new ComposerLocale(
                                "Test",
                                "Unit",
                                "Validation",
                                "en-US"
                            )
                        );
                    }},
                    "uk-UA"
                ),
                //- Create poet-//
                new Poet(
                    new User(
                        //- Roles -//
                        new ArrayList < Role >() {{
                            add( new Role( "POET", "Poet" ) );
                        }},
                        //- Access -//
                        new Access( "MyP@$$w0rd" ),
                        //- Emails -//
                        new Email( "myemail@virtuoso.com" ),
                        "Tester",
                        "Unit",
                        "JUnit",
                        "uk-UA"
                    ),
                    new ArrayList < PoetLocale >() {{
                        add(
                            new PoetLocale(
                                "Test",
                                "Unit",
                                "Validation",
                                "en-US"
                            )
                        );
                    }},
                    "uk-UA"
                ),
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
    public void TestSongFieldsFailure() {
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
                new Composer(
                    new ArrayList< ComposerLocale >() {{
                        add(
                            new ComposerLocale(
                                "Test",
                                "Unit",
                                "Validation",
                                "en-US"
                            )
                        );
                    }},
                    "uk-UA"
                ),
                //- Create poet-//
                new Poet(
                    new User(
                        //- Roles -//
                        new ArrayList < Role >() {{
                            add( new Role( "POET", "Poet" ) );
                        }},
                        //- Access -//
                        new Access( "MyP@$$w0rd" ),
                        //- Emails -//
                        new Email( "myemail@virtuoso.com" ),
                        "Tester",
                        "Unit",
                        "JUnit",
                        "uk-UA"
                    ),
                    new ArrayList < PoetLocale >() {{
                        add(
                            new PoetLocale(
                                "Test",
                                "Unit",
                                "Validation",
                                "en-US"
                            )
                        );
                    }},
                    "uk-UA"
                ),
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
                new Composer(
                    new ArrayList< ComposerLocale >() {{
                        add(
                            new ComposerLocale(
                                "Test",
                                "Unit",
                                "Validation",
                                "en-US"
                            )
                        );
                    }},
                    "uk-UA"
                ),
                //- Create poet-//
                new Poet(
                    new User(
                        //- Roles -//
                        new ArrayList < Role >() {{
                            add( new Role( "POET", "Poet" ) );
                        }},
                        //- Access -//
                        new Access( "MyP@$$w0rd" ),
                        //- Emails -//
                        new Email( "myemail@virtuoso.com" ),
                        "Tester",
                        "Unit",
                        "JUnit",
                        "uk-UA"
                    ),
                    new ArrayList < PoetLocale >() {{
                        add(
                            new PoetLocale(
                                "Test",
                                "Unit",
                                "Validation",
                                "en-US"
                            )
                        );
                    }},
                    "uk-UA"
                ),
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
