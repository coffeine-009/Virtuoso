/**
 * Copyright (c) 2014-2016 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 12/11/16 1:01 PM
 */

package com.thecoffeine.virtuoso.music.view.form;

import com.thecoffeine.virtuoso.module.model.AbstractModel;
import org.junit.Test;

import java.util.Set;
import javax.validation.ConstraintViolation;

import static org.junit.Assert.assertEquals;

/**
 * Tests fo composer form.
 */
public class PoetFormTest extends AbstractModel {

    @Test
    public void testRequiredFieldsSuccess() {
        //- Set of constraints after validation -//
        Set< ConstraintViolation< PoetForm > > constraintViolationSet;

        PoetForm form = new PoetForm();

        //- Validate -//
        constraintViolationSet = validator.validate( form );

        //- Assertions -//
        assertEquals( 3, constraintViolationSet.size() );
        //FIXME: make impl
    }
}
