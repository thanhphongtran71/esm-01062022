package com.stdio.esm.config;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author AnhKhoa
 * @since 19/05/2022 - 11:11
 */
@Component
@ConfigurationProperties(prefix = "application",ignoreUnknownFields = false)
@Getter
@Setter
public class ApplicationProperties {
    private Security security;
    @Getter
    @Setter
    public static class Security{
        private String passwordSecret;
        private long durationAccessToken;
        private long durationRefreshToken;
    }
}
