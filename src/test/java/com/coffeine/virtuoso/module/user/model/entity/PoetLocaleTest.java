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
 * Tests for PoetLocale
 * @see com.coffeine.virtuoso.module.user.model.entity.PoetLocale
 *
 * @version 1.0
 */
public class PoetLocaleTest extends AbstractModel {

    /*
    * Test field validation for entity correct
    */
    @Test
    public void testPoetLocaleFieldSuccess() {

        Set < ConstraintViolation < PoetLocale > > constraintViolationSet;

        //- Success -//
        //- Create entity-//
        PoetLocale poetLocaleSuccess = new PoetLocale(
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
            "Test",
            "Unit",
            "Validation",
            "en-US"

        );
        //- Validate -//
        constraintViolationSet = validator.validate( poetLocaleSuccess );

        assertEquals(0, constraintViolationSet.size());
    }

    /*
 * Test field validation for entity failure
 */
    @Test
    public void testPoetLocaleFieldsFailure() {

        Set < ConstraintViolation < PoetLocale > > constraintViolationSet;

        //- Failure -//
        //- Create entity-//
        PoetLocale poetLocaleFailure = new PoetLocale(
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
            null,
            "Unit",
            "Validation",
            null
        );

        //- Validate -//
        constraintViolationSet = validator.validate( poetLocaleFailure );

        assertEquals( 4, constraintViolationSet.size()) ;
        for ( ConstraintViolation < PoetLocale > constraintViolation : constraintViolationSet ) {
            //- Property name -//
            assertTrue(
                new ArrayList<String>() {{
                    add("firstName");
                    add("locale");
                }}.contains(
                    this.getPropertyName(
                        constraintViolation.getPropertyPath()
                    )
                )
            );
            //- Annotation type -//
            assertTrue(
                new ArrayList<Class>() {{
                    add(NotNull.class);
                    add(NotEmpty.class);
                }}.contains(
                    constraintViolation.getConstraintDescriptor().getAnnotation().annotationType()
                )
            );
            //- Message -//
            assertTrue(
                new ArrayList<String>() {{
                    add("may not be null");
                    add("may not be empty");
                }}.contains(constraintViolation.getMessage())
            );
        }
        //- Failure: Incorrect composer -//
        //- Create entity-//
        PoetLocale poetLocaleFailurePoet = new PoetLocale(
            //-composer -//
            null,
            "Tester",
            "Unit",
            "JUnit",
            "uk-UA"
        );

        constraintViolationSet = validator.validate( poetLocaleFailurePoet );

        Assert.assertEquals(1, constraintViolationSet.size());
        for (ConstraintViolation < PoetLocale> constraintViolation : constraintViolationSet ) {
            //- Property name -//
            assertTrue(
                new ArrayList < String >() {{
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

        //- Failure: Incorrect length -//
        //- Create entity -//
        PoetLocale poetLocaleFailureLength = new PoetLocale(
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
            "12345678901234567",
            "12345678901234567",
            "123456789012345678901234567890123",
            "123456"
        );

        //- Validate -//
        constraintViolationSet = validator.validate( poetLocaleFailureLength );

        assertEquals( 4, constraintViolationSet.size() );

        for ( ConstraintViolation < PoetLocale > constraintViolation : constraintViolationSet ) {
            //- Property name -//
            assertTrue(
                new ArrayList < String >() {{
                    add("firstName");
                    add("lastName");
                    add("middleName");
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
                    add("length must be between 0 and 16");
                    add("length must be between 0 and 32");
                }}.contains(constraintViolation.getMessage())
            );
        }
    }

    /*
    * Test field validation for entity failure( empty )
    */
    @Test
    public void testPoetLocaleFieldEmpty() {

        Set < ConstraintViolation < PoetLocale> > constraintViolationSet;

        //- Failure: fields is empty-//
        //- Create entity -//
        PoetLocale poetLocaleFailureEmpty = new PoetLocale(
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
            "",
            "admin",
            "user",
            ""
        );

        //- Validate -//
        constraintViolationSet = validator.validate( poetLocaleFailureEmpty );

        assertEquals( 2, constraintViolationSet.size() );

        for ( ConstraintViolation < PoetLocale > constraintViolation : constraintViolationSet ) {
            //- Property name -//
            assertTrue(
                new ArrayList < String >() {{
                    add("firstName");
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
