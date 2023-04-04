package com.prospring.ch8.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor", date = "2023-02-15T09:45:56.274+0300")
@StaticMetamodel(SingerAudit.class)
public abstract class SingerAudit_ {

	public static volatile SingularAttribute<SingerAudit, String> firstName;
	public static volatile SingularAttribute<SingerAudit, String> lastName;
	public static volatile SingularAttribute<SingerAudit, LocalDateTime> createdDate;
	public static volatile SingularAttribute<SingerAudit, String> createdBy;
	public static volatile SingularAttribute<SingerAudit, LocalDateTime> lastModifiedDate;
	public static volatile SingularAttribute<SingerAudit, String> lastModifiedBy;
	public static volatile SingularAttribute<SingerAudit, Integer> id;
	public static volatile SingularAttribute<SingerAudit, LocalDate> birthDate;
	public static volatile SingularAttribute<SingerAudit, Integer> version;

	public static final String FIRST_NAME = "firstName";
	public static final String LAST_NAME = "lastName";
	public static final String CREATED_DATE = "createdDate";
	public static final String CREATED_BY = "createdBy";
	public static final String LAST_MODIFIED_DATE = "lastModifiedDate";
	public static final String LAST_MODIFIED_BY = "lastModifiedBy";
	public static final String ID = "id";
	public static final String BIRTH_DATE = "birthDate";
	public static final String VERSION = "version";

}

