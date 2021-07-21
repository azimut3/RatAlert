package com.iei.ratallert.services.googleService.api.generic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GAStruct {
    private HashMap<String, Object> fields;
}
