package dev.marko.user_activity_tracker_service.services;

import dev.marko.user_activity_tracker_service.models.ActivityDocument;
import dev.marko.user_activity_tracker_service.repositories.ActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ActivityService {
    private final ActivityRepository repo;

    public ActivityDocument save(ActivityDocument doc) {
        if (doc.getCreatedAt() == null) doc.setCreatedAt(Instant.now());
        return repo.save(doc);
    }

    public List<ActivityDocument> getByUser(String userId) {
        return repo.findByUserIdOrderByCreatedAtDesc(userId);
    }

    public List<ActivityDocument> queryRange(Instant from, Instant to) {
        return repo.findByCreatedAtBetween(from, to);
    }
}