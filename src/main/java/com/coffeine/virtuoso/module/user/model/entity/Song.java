///	***	User :: Model :: Entity :: Song	***	***	***	***	***	***	***	***	***	///

	/**	***	***	***	***	***	***	***	***	***	***	***	***	***	***	***	***	*
	 *																	*
	 * @copyright 2014 (c), by Coffeine
	 *
	 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
	 *
	 * @date 2014-03-25 14:26:32 :: ....-..-.. ..:..:..
	 *
	 * @address /Ukraine/Ivano-Frankivsk/Tychyny/7a
	 *																	*
	*///***	***	***	***	***	***	***	***	***	***	***	***	***	***	***	***	*

///	***	Code	***	***	***	***	***	***	***	***	***	***	***	***	***	***	***	///
package com.coffeine.virtuoso.module.user.model.entity;

import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Class for reflect table from persistence layout
 *
 * @version 1.0
 */
@SuppressWarnings( "serial" )
@Entity
@Table( name = "song" )
public class Song implements Serializable {

	///	***	Properties	***	///
	@Id
	@Column( name = "id" )
	protected Long id;

	@Column( name = "title" )
	protected String title;


	///	***	Methods		***	///

	//- SECTION :: GET -//
	public Long getId() {
		return this.id;
	}

	public String getTitle() {
		return this.title;
	}

	//- SECTION :: SET -//
	public void setId( Long id ) {
		this.id = id;
	}

	public void setTitle( String title ) {
		this.title = title;
	}
}
