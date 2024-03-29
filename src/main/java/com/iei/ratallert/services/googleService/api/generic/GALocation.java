package com.iei.ratallert.services.googleService.api.generic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GALocation {
    private GALatLng coordinates;
    private String formattedAddress;
    private String zipCode;
    private String city;
    private GAPostalAddress postalAddress;
    private String name;
    private String phoneNumber;
    private String notes;
    private String placeId;
}
