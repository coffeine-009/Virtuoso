/**
 * Copyright (c) 2014-2016 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 4/21/16 1:59 PM
 */

package com.coffeine.virtuoso.music.controller;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Suite case for functional tests.
 */
@RunWith( Suite.class )
@Suite.SuiteClasses({
    FunctionalSongControllerTest.class, 
    FunctionalStaffControllerTest.class, 
    FunctionalStaffTypeControllerTest.class, 
    FunctionalStyleControllerTest.class,
    FunctionalVideoTypeControllerTest.class,
    FunctionalVideoControllerTest.class
})
public class FunctionalSuite {

}
