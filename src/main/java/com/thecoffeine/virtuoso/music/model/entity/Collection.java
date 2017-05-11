/**
 * Copyright (c) 2014-2016 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 12/27/16 11:32 PM
 */

package com.thecoffeine.virtuoso.music.model.entity;

import com.google.common.base.MoreObjects;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

/**
 * Entity for Song Collection.
 *
 * @version 1.0
 */
@Entity
@Table( name = "songs_collection" )
@SequenceGenerator(
    name = "songs_collection_sequence",
    sequenceName = "songs_collection_sequence"
)
public class Collection implements Serializable {

    /// *** Properties  *** ///
    /**
     * Auto generated id of collection.
     */
    @Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "songs_collection_sequence"
    )
    @Column
    private Long id;

    /**
     * List of songs which should to be included into songs collection.
     */
    @NotNull
    @NotEmpty
    @ManyToMany( fetch = FetchType.EAGER )
    @JoinTable(
        name = "songs_collection_map",
        uniqueConstraints = {
            @UniqueConstraint(
                columnNames = {
                    "id_song_collection",
                    "id_song"
                }
            )
        },
        joinColumns = {
            @JoinColumn(
                name = "id_song_collection",
                nullable = false,
                updatable = true
            )
        },
        inverseJoinColumns = {
            @JoinColumn(
                name = "id_song",
                nullable = false,
                updatable = true
            )
        }
    )
    private Set<Song> songs = new HashSet<>();

    /**
     * Title of song collection.
     */
    @NotNull
    @NotEmpty
    @Length( max = 32 )
    @Column( name = "title", length = 32 )
    private String title;

    /**
     * Description of song collection.
     */
    @Column
    @Type( type = "text" )
    private String description;

    @NotNull
    @NotEmpty
    @Length( min = 2, max = 5 )
    @Column( name = "locale", length = 5 )
    private String locale;

    /**
     * Date and time of creation song collection.
     */
    @ColumnDefault( "CURRENT_TIMESTAMP" )
    @Column( insertable = false, nullable = false )
    protected OffsetDateTime creation;


    /**
     * Default constructor.
     */
    public Collection() {
    }

    /**
     * Create a new Collection.
     *
     * @param songs     List of songs.
     * @param title     Title of collection.
     * @param locale    Locale code.
     */
    public Collection(
        Set<Song> songs,
        String title,
        String locale
    ) {
        this.songs = songs;
        this.title = title;
        this.locale = locale;
    }

    //- SECTION :: GET -//
    /**
     * Get id.
     *
     * @return Id of song collection.
     */
    public Long getId() {
        return id;
    }

    /**
     * Get list of songs.
     *
     * @return Set of songs.
     */
    public Set<Song> getSongs() {
        return songs;
    }

    /**
     * Get title of song collection.
     *
     * @return Title song collection.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Get description of song collection.
     *
     * @return Description of song collection.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Get locale of song collection.
     *
     * @return Locale code of song collection.
     */
    public String getLocale() {
        return locale;
    }

    /**
     * Get time of creation soong collection.
     *
     * @return Date and time of creation.
     */
    public OffsetDateTime getCreation() {
        return creation;
    }


    //- SECTION :: SET -//
    /**
     * Set id of song collection.
     *
     * @param id    Id of song collection.
     */
    public void setId( Long id ) {
        this.id = id;
    }

    /**
     * Set list of songs for collection.
     *
     * @param songs    Set of songs.
     */
    public void setSongs( Set<Song> songs ) {
        this.songs = songs;
    }

    /**
     * Set title of song colection.
     *
     * @param title    Title of song collection.
     */
    public void setTitle( String title ) {
        this.title = title;
    }

    /**
     * Set description of song collection.
     *
     * @param description    Description of song collection.
     */
    public void setDescription( String description ) {
        this.description = description;
    }

    /**
     * Set creation time.
     *
     * @param creation    Date and time of creating collection.
     */
    public void setCreation( OffsetDateTime creation ) {
        this.creation = creation;
    }

    /**
     * Set locale of song collection.
     *
     * @param locale    Locale code.
     */
    public void setLocale( String locale ) {
        this.locale = locale;
    }

    /**
     * Hash code of song collection.
     *
     * @return Hash code.
     */
    @Override
    public int hashCode() {
        return Objects.hash( id, songs, title, description, locale, creation );
    }

    /**
     * Compare this object with another.
     *
     * @param obj    Object to compare to.
     *
     * @return true - objects are equal. false - different.
     */
    @Override
    public boolean equals( Object obj ) {
        if ( this == obj ) {
            return true;
        }
        if ( obj == null || getClass() != obj.getClass() ) {
            return false;
        }
        final Collection other = (Collection) obj;
        return Objects.equals( this.id, other.id )
            && Objects.equals( this.songs, other.songs )
            && Objects.equals( this.title, other.title )
            && Objects.equals( this.description, other.description )
            && Objects.equals( this.locale, other.locale )
            && Objects.equals( this.creation, other.creation );
    }

    /**
     * Serialize into string.
     *
     * @return String.
     */
    @Override
    public String toString() {
        return MoreObjects.toStringHelper( this )
            .add( "id", id )
            .add( "songs", songs )
            .add( "title", title )
            .add( "description", description )
            .add( "locale", locale )
            .add( "creation", creation )
            .toString();
    }
}
