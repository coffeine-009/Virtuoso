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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Tests for Video
 * @see com.coffeine.virtuoso.module.user.model.entity.Video
 *
 * @version 1.0
 */
public class VideoTest extends AbstractModel {

    /**
     * Test field validation for entity filled correct
     */
    @Test
    public void testVideoFieldsSuccess() {

        Set < ConstraintViolation < Video > > constraintViolationSet;
        //- Success -//
        //- Create entity -//
        Video videoSuccess = new Video(
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
            new VideoType(
                "POLKA",
                "Polka",
                "Ukrainian polka"
            ),
            "uk-UA",
            "video",
            "Happy end"
        );
        //- Validate -//
        constraintViolationSet = validator.validate( videoSuccess );

        assertEquals( 0, constraintViolationSet.size() );
    }
    /**
     * Test field validation for entity field incorrect
     */
    @Test
    public void testVideoFieldsFailure() {

        Set < ConstraintViolation < Video > > constraintViolationSet;

        //- Failure( NotNull for song, video type, ) -//
        //- Create entity -//
        Video videoFailureNotNull = new Video(
            null,
            null,
            "uk-UA",
            "video",
            "Happy end"
        );
        //- Validate -//
        constraintViolationSet = validator.validate( videoFailureNotNull);

        assertEquals( 2, constraintViolationSet.size() );
        for ( ConstraintViolation < Video > constraintViolation : constraintViolationSet ) {
            //- Property name -//
            assertTrue(
                new ArrayList < String >() {{
                    add( "song" );
                    add( "videoType" );
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

        //- Failure( for locale, title, filename ) -//
        //- Create entity -//
        Video videoFailure = new Video(
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
            new VideoType(
                "POLKA",
                "Polka",
                "Ukrainian polka"
            ),
            null,
            null,
            null
        );
        //- Validate -//
        constraintViolationSet = validator.validate( videoFailure );

        assertEquals( 6, constraintViolationSet.size() );
        for ( ConstraintViolation < Video > constraintViolation : constraintViolationSet ) {
            //- Property name -//
            assertTrue(
                new ArrayList < String >() {{
                    add( "locale" );
                    add( "title" );
                    add( "fileName" );
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

    /**
     * Test field validation for entity field incorrect
     */
    @Test
    public void testVideoFieldsFailureLength() {

        Set < ConstraintViolation < Video > > constraintViolationSet;
        //- Failure: Incorrect Length -//
        //- Create entity -//
        Video videoFailureLength = new Video(
            new Song(
                //-Create composer-//
                new Composer(
                    new ArrayList<ComposerLocale>() {{
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
                        new ArrayList<Role>() {{
                            add(new Role("POET", "Poet"));
                        }},
                        //- Access -//
                        new Access("MyP@$$w0rd"),
                        //- Emails -//
                        new Email("myemail@virtuoso.com"),
                        "Tester",
                        "Unit",
                        "JUnit",
                        "uk-UA"
                    ),
                    new ArrayList<PoetLocale>() {{
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
                new ArrayList<SongLocale>() {{
                    add(
                        new SongLocale(
                            "user",
                            "uk-UA"
                        )
                    );
                }},
                //- Create list of staff -//
                new ArrayList<Staff>() {{
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
                new ArrayList<Text>() {{
                    add(
                        new Text(
                            "uk-UA"
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
            new VideoType(
                "POLKA",
                "Polka",
                "Ukrainian polka"
            ),
            "123456",
            "123456789012345678901234567890123",
            "12345678901234567890123456789012345678901234567890123456789012345"
        );

        constraintViolationSet = validator.validate(videoFailureLength);

        assertEquals( 3, constraintViolationSet.size() );
        for ( ConstraintViolation < Video > constraintViolation : constraintViolationSet ) {
            //- Property name -//
            assertTrue(
                new ArrayList < String >() {{
                    add( "locale" );
                    add( "title" );
                    add( "fileName" );
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
            //- Message -//
            assertTrue(
                new ArrayList < String >() {{
                    add( "length must be between 0 and 5" );
                    add( "length must be between 0 and 32" );
                    add( "length must be between 0 and 64" );
                }}.contains( constraintViolation.getMessage() )
            );
        }
    }

    /*
    * Test field validation for entity failure( empty )
    */
    @Test
    public void testVideoFieldsEmpty() {
        Set < ConstraintViolation < Video > > constraintViolationSet;
        //- Failure: Empty -//
        //- Create entity -//
        Video videoFailureEmpty = new Video(
            new Song(
                //-Create composer-//
                new Composer(
                    new ArrayList<ComposerLocale>() {{
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
                        new ArrayList<Role>() {{
                            add(new Role("POET", "Poet"));
                        }},
                        //- Access -//
                        new Access("MyP@$$w0rd"),
                        //- Emails -//
                        new Email("myemail@virtuoso.com"),
                        "Tester",
                        "Unit",
                        "JUnit",
                        "uk-UA"
                    ),
                    new ArrayList<PoetLocale>() {{
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
                new ArrayList<SongLocale>() {{
                    add(
                        new SongLocale(
                            "user",
                            "uk-UA"
                        )
                    );
                }},
                //- Create list of staff -//
                new ArrayList<Staff>() {{
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
                new ArrayList<Text>() {{
                    add(
                        new Text(
                            "uk-UA"
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
            new VideoType(
                "POLKA",
                "Polka",
                "Ukrainian polka"
            ),
            "",
            "",
            ""
        );

        constraintViolationSet = validator.validate( videoFailureEmpty );

        assertEquals( 3, constraintViolationSet.size() );
        for ( ConstraintViolation < Video > constraintViolation : constraintViolationSet ) {
            //- Property name -//
            assertTrue(
                new ArrayList < String >() {{
                    add( "locale" );
                    add( "title" );
                    add( "fileName" );
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
