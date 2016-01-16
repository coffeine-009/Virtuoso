/**
 * Copyright (c) 2014-2015 by Coffeine Inc
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 12/12/15 10:42 AM
 */

package com.coffeine.virtuoso.music.view.form;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Form for create/update song.
 *
 * @version 1.0
 */
public class SongForm {

    @NotNull
    @Size( min = 1 )
    private List<Long> composerIds;

    @NotNull
    @Size( min = 1 )
    private List<Long> poetIds;

    @NotNull
    @NotEmpty
    @Size( min = 1 )
    private List<SongForm.SongLocaleForm> data = new ArrayList<>();//TODO: add forms

    @NotNull
    @NotEmpty
    protected List<SongForm.StaffForm> staffs = new ArrayList<>();

    @NotNull
    @NotEmpty
    private List<SongForm.TextForm> texts = new ArrayList<>();

    @NotNull
    private List<SongForm.VideoForm> videos = new ArrayList<>();

    @NotNull
    @NotEmpty
    @Length( max = 5 )
    private String locale;

    @JsonDeserialize( using = LocalDateDeserializer.class )
    private LocalDate writeDate;


    private static class SongLocaleForm {

        @NotNull
        @NotEmpty
        @Length( max = 64 )
        protected String title;

        @NotNull
        @NotEmpty
        @Length( max = 5 )
        protected String locale;


        public String getTitle() {
            return title;
        }

        public String getLocale() {
            return locale;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setLocale(String locale) {
            this.locale = locale;
        }
    }

    private class StaffForm {

        private Long staffTypeId;

        private Long styleId;


    }

    private class TextForm {
    }

    private class VideoForm {
    }

    //- SECTION :: GET -//

    public List<Long> getComposerIds() {
        return composerIds;
    }

    public List<Long> getPoetIds() {
        return poetIds;
    }


    public String getLocale() {
        return locale;
    }

    public LocalDate getWriteDate() {
        return writeDate;
    }


    //- SECTION :: SET -//

    public void setComposerIds(List<Long> composerIds) {
        this.composerIds = composerIds;
    }

    public void setPoetIds(List<Long> poetIds) {
        this.poetIds = poetIds;
    }


    public void setLocale( String locale ) {
        this.locale = locale;
    }

    public void setWriteDate( LocalDate writeDate ) {
        this.writeDate = writeDate;
    }
}
