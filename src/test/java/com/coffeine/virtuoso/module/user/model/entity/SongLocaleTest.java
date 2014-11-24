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

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Tests for song locale
 * @see com.coffeine.virtuoso.module.user.model.entity.SongLocale
 *
 * @version 1.0
 */
public class SongLocaleTest extends AbstractModel {

    /*
    * Test field validation for entity correct
    */
    @Test
    public void testSongLocaleFieldsSuccess() {

        Set < ConstraintViolation < SongLocale > > constraintViolationSet;

        //- Success -//
        //- Create entity-//
        SongLocale songLocaleSuccess = new SongLocale(
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
