package com.test.dogapi.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BreedUpdateRequest {
    private Long id;
    private String name;
}
