package dev.marko.user_activity_tracker_service.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "user_activities")
public class ActivityDocument {
    @Id
    private String id;

    private String userId;
    private String username;
    private String activityType;
    private String resourceId;
    private String resourceType;
    private Map<String, Object> meta;

    @CreatedDate
    @Indexed(name = "createdAt_ttl_idx")
    private Instant createdAt;

    private Map<String, Object> rawPayload;
}