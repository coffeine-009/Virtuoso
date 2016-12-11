/**
 * Copyright (c) 2014-2016 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 12/11/16 12:57 PM
 */

package com.coffeine.virtuoso.music.view.form;

import com.coffeine.virtuoso.module.model.AbstractModel;

import org.junit.Test;

import java.util.Set;
import javax.validation.ConstraintViolation;

import static org.junit.Assert.assertEquals;

/**
 * Tests fo composer form.
 */
public class ComposerFormTest extends AbstractModel {

    @Test
    public void testRequiredFieldsSuccess() {
        //- Set of constraints after validation -//
        Set< ConstraintViolation< ComposerForm > > constraintViolationSet;

        ComposerForm form = new ComposerForm();

        //- Validate -//
        constraintViolationSet = validator.validate( form );

        //- Assertions -//
        assertEquals( 3, constraintViolationSet.size() );
        //FIXME: make impl
    }
}
