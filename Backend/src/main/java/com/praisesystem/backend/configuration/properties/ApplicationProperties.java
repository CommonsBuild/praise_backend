package com.praisesystem.backend.configuration.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Data
@Validated
@Component
@ConfigurationProperties(prefix = "praise-system")
public class ApplicationProperties {

    List<String> adminAddresses;

    String frontendUrl;
}
