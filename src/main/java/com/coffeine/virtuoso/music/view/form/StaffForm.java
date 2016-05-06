/**
 * Copyright (c) 2014-2016 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 12/8/15 9:18 PM
 */

package com.coffeine.virtuoso.music.view.form;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * Form for input staff.
 *
 * @version 1.0
 */
public class StaffForm {

    @NotNull
    private Long songId;

    @NotNull
    private Long staffTypeId;

    @NotNull
    private Long styleId;

    @NotNull
    @NotEmpty
    @Length( max = 5 )
    private String locale;


    /// *** Properties  *** ///
    //- SECTION :: GET -//
    public Long getSongId() {
        return songId;
    }

    public Long getStaffTypeId() {
        return staffTypeId;
    }

    public Long getStyleId() {
        return styleId;
    }

    public String getLocale() {
        return locale;
    }


    //- SECTION :: SET -//
    public void setSongId( Long songId ) {
        this.songId = songId;
    }

    public void setStaffTypeId( Long staffTypeId ) {
        this.staffTypeId = staffTypeId;
    }

    public void setStyleId( Long styleId ) {
        this.styleId = styleId;
    }

    public void setLocale( String locale ) {
        this.locale = locale;
    }
}
