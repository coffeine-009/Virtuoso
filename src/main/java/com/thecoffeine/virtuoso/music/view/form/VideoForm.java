/**
 * Copyright (c) 2014-2016 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 12/10/15 9:38 PM
 */

package com.thecoffeine.virtuoso.music.view.form;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * Form for create/update video.
 *
 * @version 1.0
 */
public class VideoForm {

    @NotNull
    private Long videoTypeId;

    @NotNull
    private Long songId;

    @NotNull
    @NotEmpty
    @Length( max = 5 )
    private String locale;

    @NotNull
    @NotEmpty
    @Length( max = 32 )
    private String title;

    @NotNull
    @NotEmpty
    @Length( min = 8 )
    private String description;

    @NotNull
    @NotEmpty
    @Length( max = 64 )
    private String fileName;


    //- SECTION :: GET -//
    public Long getVideoTypeId() {
        return videoTypeId;
    }

    public Long getSongId() {
        return songId;
    }

    public String getLocale() {
        return locale;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getFileName() {
        return fileName;
    }


    //- SECTION :: SET -//
    public void setVideoTypeId( Long videoTypeId ) {
        this.videoTypeId = videoTypeId;
    }

    public void setSongId( Long songId ) {
        this.songId = songId;
    }

    public void setLocale( String locale ) {
        this.locale = locale;
    }

    public void setTitle( String title ) {
        this.title = title;
    }

    public void setDescription( String description ) {
        this.description = description;
    }

    public void setFileName( String fileName ) {
        this.fileName = fileName;
    }
}
