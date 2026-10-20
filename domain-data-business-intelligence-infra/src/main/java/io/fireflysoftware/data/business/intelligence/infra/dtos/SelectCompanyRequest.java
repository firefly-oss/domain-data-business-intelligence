package io.fireflysoftware.data.business.intelligence.infra.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SelectCompanyRequest {
    private String name;
    private String city;
    private String country;
    private String address;
    private String nationalId;
    private String postCode;
}
