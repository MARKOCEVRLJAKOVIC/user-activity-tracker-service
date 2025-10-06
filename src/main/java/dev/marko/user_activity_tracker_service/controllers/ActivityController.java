package dev.marko.user_activity_tracker_service.controllers;

import dev.marko.user_activity_tracker_service.models.ActivityDocument;
import dev.marko.user_activity_tracker_service.services.ActivityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/activities")
@RequiredArgsConstructor
@Validated
public class ActivityController {
    private final ActivityService service;

    @PostMapping
    public ResponseEntity<ActivityDocument> ingest(@Valid @RequestBody ActivityDocument payload) {
        ActivityDocument saved = service.save(payload);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ActivityDocument>> getByUser(@PathVariable String userId) {
        return ResponseEntity.ok(service.getByUser(userId));
    }

    @GetMapping("/range")
    public ResponseEntity<List<ActivityDocument>> getRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant to) {
        return ResponseEntity.ok(service.queryRange(from, to));
    }
}