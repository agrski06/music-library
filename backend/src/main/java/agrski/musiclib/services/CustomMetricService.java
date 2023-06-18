package agrski.musiclib.services;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
@Getter
public class CustomMetricService {

    private final HashMap<String, Integer> requestsPerPath;
    private final HashMap<String, Integer> requestsPerMethod;
    private final HashMap<String, Integer> requestsPerMethodAndPath;

    public CustomMetricService() {
        this.requestsPerPath = new HashMap<>();
        this.requestsPerMethod = new HashMap<>();
        this.requestsPerMethodAndPath = new HashMap<>();
    }

    public void increaseCountForRequestPath(String path) {
        if (requestsPerPath.containsKey(path)) {
            requestsPerPath.merge(path, 1, Integer::sum);
        } else {
            requestsPerPath.put(path, 1);
        }
    }

    public void increaseCountForRequestMethod(String method) {
        if (requestsPerMethod.containsKey(method)) {
            requestsPerMethod.merge(method, 1, Integer::sum);
        } else {
            requestsPerMethod.put(method, 1);
        }
    }

    public void increaseCountForRequestMethodAndPath(String method, String path) {
        String key = method + " " + path;
        if (requestsPerMethodAndPath.containsKey(key)) {
            requestsPerMethodAndPath.merge(key, 1, Integer::sum);
        } else {
            requestsPerMethodAndPath.put(key, 1);
        }
    }
}
