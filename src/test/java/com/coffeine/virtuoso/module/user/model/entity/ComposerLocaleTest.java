/**
 * @copyright (c) 2014, by Valentyn Namisnyk
 *
 * @author Valentyn Namisnyk <Valentun_Prodyser@ukr.net>
 */

package com.coffeine.virtuoso.module.user.model.entity;

import com.coffeine.virtuoso.module.model.AbstractModel;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.constraints.NotNull;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Tests for ComposerLocale
 * @see com.coffeine.virtuoso.module.user.model.entity.ComposerLocale
 *
 * @version 1.0
 */
public class ComposerLocaleTest extends AbstractModel {

    /*
     * Test field validation for entity correct
     */
    @Test
    public void testComposerLocaleFieldSuccess() {

        Set < ConstraintViolation < ComposerLocale > > constraintViolationSet;

        //- Success -//
        //- Create entity-//
        ComposerLocale composerLocaleSuccess = new ComposerLocale(
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
            "Test",
            "Unit",
            "Validation",
            "en-US"

        );
        //- Validate -//
        constraintViolationSet = validator.validate( composerLocaleSuccess );

        assertEquals(0, constraintViolationSet.size());
    }

    /*
 * Test field validation for entity failure
 */
    @Test
    public void testComposerLocaleFieldsFailure() {

        Set < ConstraintViolation < ComposerLocale > > constraintViolationSet;

        //- Failure -//
        //- Create entity-//
        ComposerLocale composerLocaleFailure = new ComposerLocale(
            new Composer(
                new ArrayList < ComposerLocale >() {{
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
            null,
            "Unit",
            "Validation",
            null
        );
        //- Validate -//
        constraintViolationSet = validator.validate(composerLocaleFailure);

        assertEquals( 4, constraintViolationSet.size()) ;
        for (ConstraintViolation < ComposerLocale > constraintViolation : constraintViolationSet ) {
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
        ComposerLocale composerLocaleFailureComposer = new ComposerLocale(
            //-composer -//
            null,
            "Tester",
            "Unit",
            "JUnit",
            "uk-UA"
        );

        constraintViolationSet = validator.validate(composerLocaleFailureComposer);

        assertEquals( 1, constraintViolationSet.size() );
        for (ConstraintViolation<ComposerLocale> constraintViolation : constraintViolationSet ) {
            //- Property name -//
            assertTrue(
                new ArrayList < String >() {{
                    add("composer");
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
        ComposerLocale composerLocaleFailureLength = new ComposerLocale(
            new Composer(
                new ArrayList < ComposerLocale >() {{
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
            "12345678901234567",
            "12345678901234567",
            "123456789012345678901234567890123",
            "123456"
        );

        //- Validate -//
        constraintViolationSet = validator.validate(composerLocaleFailureLength);

        assertEquals(4, constraintViolationSet.size());

        for ( ConstraintViolation < ComposerLocale > constraintViolation : constraintViolationSet ) {
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
    public void testComposerLocaleFieldEmpty() {
        Set < ConstraintViolation < ComposerLocale > > constraintViolationSet;

        //- Failure: fields is empty-//
        //- Create entity -//
        ComposerLocale composerLocaleFailureEmpty = new ComposerLocale(
            new Composer(
                new ArrayList < ComposerLocale >() {{
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
            "",
            "admin",
            "user",
            ""
        );

        //- Validate -//
        constraintViolationSet = validator.validate( composerLocaleFailureEmpty );

        assertEquals( 2, constraintViolationSet.size() );

        for ( ConstraintViolation < ComposerLocale > constraintViolation : constraintViolationSet ) {
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
