package dev.marko.user_activity_tracker_service.repositories;

import dev.marko.user_activity_tracker_service.models.ActivityDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface ActivityRepository extends MongoRepository<ActivityDocument, String> {
    List<ActivityDocument> findByUserIdOrderByCreatedAtDesc(String userId);
    List<ActivityDocument> findByUserIdAndActivityType(String userId, String activityType);
    List<ActivityDocument> findByCreatedAtBetween(Instant from, Instant to);
}
