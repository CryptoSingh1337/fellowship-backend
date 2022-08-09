package com.cyborg.fellowshipscheduler.jobs;

import com.cyborg.fellowshipdataaccess.entity.Scholarship;
import com.cyborg.fellowshipdataaccess.repository.ScholarshipRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

/**
 * @author saranshk04
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class CleanupJob implements Runnable {

    private final ScholarshipRepository scholarshipRepository;
    @Value("${scholarships.expiration}")
    private Long expirationInDays;

    private void removeAllExpiredScholarships() {
        List<Scholarship> scholarships = scholarshipRepository.findAll();
        List<String> scholarshipsToBeRemove = new ArrayList<>();

        for (Scholarship scholarship : scholarships) {
            boolean isExpired = scholarship.getCreatedAt().plusDays(expirationInDays)
                    .isBefore(LocalDateTime.now(ZoneOffset.UTC));
            if (isExpired)
                scholarshipsToBeRemove.add(scholarship.getId());
        }

        if (!scholarshipsToBeRemove.isEmpty())
            scholarshipRepository.deleteAllById(scholarshipsToBeRemove);
        else
            log.info("No scholarships for cleanup");
    }

    @Override
    public void run() {
        removeAllExpiredScholarships();
    }
}
