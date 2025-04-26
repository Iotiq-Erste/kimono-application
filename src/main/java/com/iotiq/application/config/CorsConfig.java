package com.iotiq.application.config;

import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * CORS Configuration for the application.
 * <p>
 * To use this configuration, add the following to your application.yml:
 * <p>
 * cors:
 * allowed-origins:
 * - http://localhost:3000
 * - https://your-production-domain.com
 * allowed-methods:
 * - GET
 * - POST
 * - PUT
 * - DELETE
 * - OPTIONS
 * allowed-headers:
 * - "*"
 * allow-credentials: true
 * max-age: 3600
 */
@Setter
@Configuration
@ConfigurationProperties(prefix = "cors")
public class CorsConfig implements WebMvcConfigurer {

    /**
     * List of allowed origins. Default: <a href="http://localhost:8011">...</a>
     */
    private String[] allowedOrigins = {"http://localhost:8011"};

    /**
     * List of allowed HTTP methods. Default: GET, POST, PUT, DELETE, OPTIONS
     */
    private String[] allowedMethods = {"GET", "POST", "PUT", "DELETE", "OPTIONS"};

    /**
     * List of allowed headers. Default: all headers (*)
     */
    private String[] allowedHeaders = {"*"};

    /**
     * Whether to allow credentials. Default: true
     */
    private boolean allowCredentials = true;

    /**
     * Max age of CORS pre-flight cache in seconds. Default: 3600
     */
    private long maxAge = 3600;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(allowedOrigins)
                .allowedMethods(allowedMethods)
                .allowedHeaders(allowedHeaders)
                .allowCredentials(allowCredentials)
                .maxAge(maxAge);
    }
}
