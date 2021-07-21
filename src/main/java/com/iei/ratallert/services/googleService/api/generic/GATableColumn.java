package com.iei.ratallert.services.googleService.api.generic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GATableColumn {
    private String header;
    private GAHorizontalAlignment align;
}
