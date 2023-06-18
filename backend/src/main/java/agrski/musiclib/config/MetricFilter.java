package agrski.musiclib.config;

import agrski.musiclib.services.CustomMetricService;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerMapping;

@Component
@RequiredArgsConstructor
public class MetricFilter implements Filter {

    private final CustomMetricService metricService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws java.io.IOException, ServletException {
        chain.doFilter(request, response);

        HttpServletRequest req = (HttpServletRequest) request;

        String method = req.getMethod();
        String path = (String) req.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);

        metricService.increaseCountForRequestPath(path);
        metricService.increaseCountForRequestMethod(method);
        metricService.increaseCountForRequestMethodAndPath(method, path);
    }

}
