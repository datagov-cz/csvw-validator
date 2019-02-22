package com.malyvoj3.csvwvalidator.domain.metadata.properties;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UriTemplateProperty {

    private final String stringValue;
    private final String parsedValue;
    private String normalizedValue;

}
