package com.iei.ratallert.services.googleService.api.generic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GAUser {
    private String locale;
    private HashMap<String, Object> params;
}
