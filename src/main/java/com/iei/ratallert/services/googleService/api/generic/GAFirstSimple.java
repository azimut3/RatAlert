package com.iei.ratallert.services.googleService.api.generic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class GAFirstSimple {
    private String speech;
    private String text;
}
