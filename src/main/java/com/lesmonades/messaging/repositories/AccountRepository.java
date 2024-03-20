package com.lesmonades.messaging.repositories;

import com.lesmonades.messaging.domain.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends MongoRepository<Account, String> {

    Account findByNumber(int accountNumber);
}
