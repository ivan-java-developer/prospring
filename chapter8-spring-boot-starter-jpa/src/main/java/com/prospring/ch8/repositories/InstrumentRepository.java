package com.prospring.ch8.repositories;

import com.prospring.ch8.entities.Instrument;
import org.springframework.data.repository.CrudRepository;

public interface InstrumentRepository extends CrudRepository<Instrument, Integer> {
}
