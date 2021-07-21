package com.iei.ratallert.services.googleService.api.generic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GADevice {
    private List<String> capabilities;
}
