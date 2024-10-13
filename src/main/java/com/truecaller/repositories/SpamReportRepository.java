package com.truecaller.repositories;

import com.truecaller.entities.SpamReport;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SpamReportRepository extends MongoRepository<SpamReport,String> {
}
