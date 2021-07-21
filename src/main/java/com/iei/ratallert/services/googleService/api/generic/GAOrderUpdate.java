package com.iei.ratallert.services.googleService.api.generic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GAOrderUpdate {
    private GAType type;
    private GAOrder order;
    private String updateMask;
    private GAUserNotification userNotification;
    private String reason;
}
