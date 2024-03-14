package com.lesmonades.messaging.repositories;

import com.lesmonades.messaging.domain.Bank;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepository extends MongoRepository<Bank, String> {
}
