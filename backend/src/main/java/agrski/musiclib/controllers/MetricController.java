package agrski.musiclib.controllers;

import agrski.musiclib.services.CustomMetricService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/metrics")
public class MetricController {

    private final CustomMetricService metricService;

    @GetMapping("/paths")
    public ResponseEntity<HashMap<String, Integer>> getMetricsForPaths() {
        return ResponseEntity.ok(metricService.getRequestsPerPath());
    }

    @GetMapping("/methods")
    public ResponseEntity<HashMap<String, Integer>> getMetricsForMethods() {
        return ResponseEntity.ok(metricService.getRequestsPerMethod());
    }

    @GetMapping("/both")
    public ResponseEntity<HashMap<String, Integer>> getMetricsForMethodsAndPaths() {
        return ResponseEntity.ok(metricService.getRequestsPerMethodAndPath());
    }

    @GetMapping("/total")
    public ResponseEntity<Integer> totalNumberOfRequests() {
        return ResponseEntity.ok(
                metricService.getRequestsPerPath().values()
                        .stream().reduce(Integer::sum)
                        .orElse(0)
        );
    }

}
