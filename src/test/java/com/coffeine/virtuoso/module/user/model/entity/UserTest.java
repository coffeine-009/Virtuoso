/*
 * @copyright (c) 2014, by Vitaliy Tsutsman
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 */

package com.coffeine.virtuoso.module.user.model.entity;

import com.coffeine.virtuoso.module.model.AbstractModel;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import java.util.ArrayList;
import java.util.Set;

import static junit.framework.Assert.assertEquals;

/**
 * Tests for User
 * @see com.coffeine.virtuoso.module.user.model.entity.User
 *
 * @version 1.0
 */
public class UserTest extends AbstractModel {

    /*
     * Test field validation for entity correct
     */
    @Test
    public void testStyleFieldsSuccess() {

        Set < ConstraintViolation < User > > constraintViolationSet;

        //- Success -//
        //- Create entity-//
        User userSuccess = new User(
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
        );
        //- Validate -//
        constraintViolationSet = validator.validate( userSuccess );

        assertEquals( 0, constraintViolationSet.size() );
    }
}
