package com.prospring.ch8.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor", date = "2023-02-15T09:45:56.239+0300")
@StaticMetamodel(Instrument.class)
public abstract class Instrument_ {

	public static volatile SetAttribute<Instrument, Singer> singers;
	public static volatile SingularAttribute<Instrument, String> instrumentId;

	public static final String SINGERS = "singers";
	public static final String INSTRUMENT_ID = "instrumentId";

}

