package com.prospring.ch8.entities;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Singer.class)
public abstract class Singer_ {

	public static volatile SingularAttribute<Singer, String> firstName;
	public static volatile SingularAttribute<Singer, String> lastName;
	public static volatile SetAttribute<Singer, Album> albums;
	public static volatile SetAttribute<Singer, Instrument> instruments;
	public static volatile SingularAttribute<Singer, Integer> id;
	public static volatile SingularAttribute<Singer, LocalDate> birthDate;
	public static volatile SingularAttribute<Singer, Integer> version;

	public static final String FIRST_NAME = "firstName";
	public static final String LAST_NAME = "lastName";
	public static final String ALBUMS = "albums";
	public static final String INSTRUMENTS = "instruments";
	public static final String ID = "id";
	public static final String BIRTH_DATE = "birthDate";
	public static final String VERSION = "version";

}

