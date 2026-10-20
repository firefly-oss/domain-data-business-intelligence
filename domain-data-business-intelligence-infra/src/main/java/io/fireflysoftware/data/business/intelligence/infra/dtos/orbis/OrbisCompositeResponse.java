package io.fireflysoftware.data.business.intelligence.infra.dtos.orbis;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrbisCompositeResponse {

    private OrbisDataResponse companyData;
    private OrbisFinancialsResponse financials;
    private OrbisOwnershipResponse ownership;
    private OrbisDirectorsResponse directors;
}
