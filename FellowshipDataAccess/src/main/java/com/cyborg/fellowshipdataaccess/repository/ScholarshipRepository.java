package com.cyborg.fellowshipdataaccess.repository;

import com.cyborg.fellowshipdataaccess.entity.Scholarship;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author saranshk04
 */
@Repository
public interface ScholarshipRepository extends MongoRepository<Scholarship, String> {
}
