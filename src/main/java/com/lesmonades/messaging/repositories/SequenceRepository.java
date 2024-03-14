package com.lesmonades.messaging.repositories;

import com.lesmonades.messaging.domain.Sequence;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SequenceRepository extends MongoRepository<Sequence, String> {
    Sequence findByName(String name);
}
