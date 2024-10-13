package com.truecaller.services.impls;


import com.truecaller.entities.SpamReport;
import com.truecaller.repositories.ProfileRepository;
import com.truecaller.repositories.SpamReportRepository;
import com.truecaller.services.SpamReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpamReportServiceImplements implements SpamReportService {

    @Autowired
    private SpamReportRepository repository;
    @Override
    public void saveSpamReport(SpamReport spamReport) {
        repository.save(spamReport);
    }
}
