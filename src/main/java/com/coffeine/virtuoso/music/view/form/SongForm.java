/**
 * Copyright (c) 2014-2015 by Coffeine Inc
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 12/12/15 10:42 AM
 */

package com.coffeine.virtuoso.music.view.form;

import com.coffeine.virtuoso.music.model.entity.SongLocale;
import com.coffeine.virtuoso.music.model.entity.Staff;
import com.coffeine.virtuoso.music.model.entity.Text;
import com.coffeine.virtuoso.music.model.entity.Video;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Form for create/update song.
 *
 * @version 1.0
 */
public class SongForm {

    @NotNull
    @Min( 0 )
    private Long composerId;

    @NotNull
    @Min( 0 )
    private Long poetId;

    @NotNull
    @NotEmpty
    @Size( min = 2 )
    private List<SongLocale> data = new ArrayList<>();

    @NotNull
    @NotEmpty
    protected List<Staff> staffs = new ArrayList<>();

    @NotNull
    @NotEmpty
    private List<Text> texts = new ArrayList<>();

    @NotNull
    private List<Video> videos = new ArrayList<>();

    @NotNull
    @NotEmpty
    @Length( max = 5 )
    private String locale;

    private LocalDate writeDate;


    //- SECTION :: GET -//
    public Long getComposerId() {
        return composerId;
    }

    public Long getPoetId() {
        return poetId;
    }

    public List<SongLocale> getData() {
        return data;
    }

    public List<Staff> getStaffs() {
        return staffs;
    }

    public List<Text> getTexts() {
        return texts;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public String getLocale() {
        return locale;
    }

    public LocalDate getWriteDate() {
        return writeDate;
    }


    //- SECTION :: SET -//
    public void setComposerId( Long composerId ) {
        this.composerId = composerId;
    }

    public void setPoetId( Long poetId ) {
        this.poetId = poetId;
    }

    public void setData( List<SongLocale> data ) {
        this.data = data;
    }

    public void setStaffs( List<Staff> staffs ) {
        this.staffs = staffs;
    }

    public void setTexts( List<Text> texts ) {
        this.texts = texts;
    }

    public void setVideos( List<Video> videos ) {
        this.videos = videos;
    }

    public void setLocale( String locale ) {
        this.locale = locale;
    }

    public void setWriteDate( LocalDate writeDate ) {
        this.writeDate = writeDate;
    }
}
