/*
 * @copyright (c) 2014, by Valentyn Namisnyk
 *
 * @author Valentyn Namisnyk <Valentun_Prodyser@ukr.net>
 */

package com.coffeine.virtuoso.module.user.model.entity;

import com.coffeine.virtuoso.module.model.AbstractModel;
import junit.framework.Assert;
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
 * Tests for Song
 *
 * @see com.coffeine.virtuoso.module.user.model.entity.Song
 * @version 1.0
 */
public class SongTest extends AbstractModel {
    /**
     * Test field validation for entity field correct
     */
    @Test
    public void TestSongFieldsSuccess() {

        Set < ConstraintViolation < Song > > constraintViolationSet;

        //- Successful-//
        //- Create entity-//
        Song songSuccess = new Song(
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
        );
        //- Validate -//
        constraintViolationSet = validator.validate( songSuccess );

        assertEquals( 0, constraintViolationSet.size() );
    }

    /**
     * Test field validation for entity field failure
     */
    @Test
    public void TestSongFieldsFailure() {
        Set < ConstraintViolation < Song > > constraintViolationSet;

        //- Failure -//
        //- Create entity -//
        Song songFailure = new Song(
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
            null
        );
        //- Validate -//
        constraintViolationSet = validator.validate( songFailure );

        assertEquals( 8, constraintViolationSet.size() );
        for ( ConstraintViolation < Song > constraintViolation : constraintViolationSet ) {
            //- Property name -//
            assertTrue(
                new ArrayList < String >() {{
                    add("data");
                    add("staffs");
                    add("texts");
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

        //- Failure: poet and composer are null-//
        //- Create entity -//
        Song songFailureNull = new Song(
            null,
            null,
            new ArrayList < SongLocale >() {{
                add(
                    new SongLocale(
                        "user",
                        "uk-UA"
                    )
                );
            }},
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
            new ArrayList < Text >() {{
                add(
                    new Text(
                        "uk-UA"
                    )
                );
            }},
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
        );
        //- Validate-//
        constraintViolationSet = validator.validate( songFailureNull );

        Assert.assertEquals( 2, constraintViolationSet.size() );
        for( ConstraintViolation < Song > constraintViolation : constraintViolationSet ) {
            //- Property name -//
            assertTrue(
                new ArrayList < String >() {{
                    add("composer");
                    add("poet");
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
        //- Failure : Incorrect length  -//
        //- Create entity -//
        Song songFailureLength = new Song(
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
            "123456"
        );
        //- Validate -//
        constraintViolationSet = validator.validate( songFailureLength );

        assertEquals( 1, constraintViolationSet.size() );

        for ( ConstraintViolation < Song > constraintViolation : constraintViolationSet ) {
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
                    add( Length.class );
                }}.contains(
                    constraintViolation.getConstraintDescriptor().getAnnotation().annotationType()
                )
            );
            //- Message -//
            assertTrue(
                new ArrayList < String >() {{
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

        Set < ConstraintViolation < Song > > constraintViolationSet;

        //- Failure: fields is empty-//
        //- Create entity -//
        Song songFailureEmpty = new Song(
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
            new ArrayList < SongLocale >(),
            //- Create list of staff -//
            new ArrayList < Staff >(),
            //- Create list of text -//
            new ArrayList < Text >(),
            //- Create list of video -//
            new ArrayList < Video >(),
            ""
        );
        //- Validate-//
        constraintViolationSet = validator.validate( songFailureEmpty );

        assertEquals( 4 , constraintViolationSet.size() );
        for ( ConstraintViolation < Song > constraintViolation : constraintViolationSet ) {
            //- Property name -//
            assertTrue(
                new ArrayList < String >() {{
                    add( "data" );
                    add( "staffs");
                    add( "texts");
                    add( "locale");
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
