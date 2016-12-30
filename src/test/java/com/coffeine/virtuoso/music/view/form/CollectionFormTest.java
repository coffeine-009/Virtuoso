/**
 * Copyright (c) 2014-2016 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 12/30/16 11:56 PM
 */

package com.coffeine.virtuoso.music.view.form;

import com.coffeine.virtuoso.module.model.AbstractModel;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
import javax.validation.ConstraintViolation;

import static org.junit.Assert.assertEquals;

/**
 * Tests for collection form.
 */
public class CollectionFormTest extends AbstractModel {

    @Test
    public void testRequiredFieldsSuccess() {
        //- Set of constraints after validation -//
        Set< ConstraintViolation< CollectionForm > > constraintViolationSet;

        CollectionForm form = new CollectionForm(
            "My favorite collection",
            "en-US",
            new HashSet<Long>() {{
                add( 1L );
            }}
        );

        //- Validate -//
        constraintViolationSet = validator.validate( form );

        //- Assertions -//
        assertEquals( 0, constraintViolationSet.size() );
    }
}
