package com.theniche.colivin.domain.exception;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ConstraintMessageResolver {
    private static final Map<String, String> MESSAGES = Map.of(
            "UK_HOUSE_NAME", "House name already exists, try a different name.",
            "UK_USER_EMAIL", "Email already exists, try a different one."
    );

    public String resolveMessage(String dbMessage) {
        return MESSAGES.entrySet()
                .stream()
                .filter(e -> dbMessage.contains(e.getKey()))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElse("Data integrity violation");
    }

}
