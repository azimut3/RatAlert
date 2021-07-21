package com.iei.ratallert.services.googleService.api.request;

import com.iei.ratallert.services.googleService.api.generic.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GARequest {
    private GAHandler handler;
    private GAIntent intent;
    private GAScene scene;
    private GASession session;
    private GAUser user;
    private GAHome home;
    private GADevice device;
}
