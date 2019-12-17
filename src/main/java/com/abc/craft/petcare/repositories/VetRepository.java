package com.abc.craft.petcare.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.abc.craft.petcare.document.Veterinarian;

/**
 * The VetRepository interface is to create the connector between the
 * Veterinarian data model and veterinarian collection present in MongoDB.This
 * extends MongoRepository which already contains generic methods like save,
 * delete and helps to perform find queries.
 *
 * @author Devesh Sumeet
 * @version 1.0
 * @since 2019-12-12
 */
@Repository
public interface VetRepository extends MongoRepository<Veterinarian, String> {
}
