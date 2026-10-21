/*
 * Copyright 2025 Firefly Software Foundation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package io.fireflysoftware.data.business.intelligence.infra.dtos.orbis;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrbisOwnershipResponse {

    @JsonProperty("Data")
    private List<OrbisOwnershipData> data;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class OrbisOwnershipData {

        // ── Basic ──

        @JsonProperty("NAME")
        private String name;

        @JsonProperty("CORPORATE_GROUP_SIZE_LABEL")
        private String corporateGroupSizeLabel;

        @JsonProperty("CORPORATE_ENTITY_TYPE")
        private String corporateEntityType;

        // ── Beneficial Owner status/counts ──

        @JsonProperty("BO_STATUS")
        private String boStatus;

        @JsonProperty("BO_DEFINITION_10_10_COUNT")
        private Integer boDefinition1010Count;

        @JsonProperty("BO_DEFINITION_10_50_COUNT")
        private Integer boDefinition1050Count;

        @JsonProperty("BO_DEFINITION_25_25_COUNT")
        private Integer boDefinition2525Count;

        // ── BO_ fields (List<String>) ──

        @JsonProperty("BO_NAME")
        private List<String> boName;

        @JsonProperty("BO_BVD_ID_NUMBER")
        private List<String> boBvdIdNumber;

        @JsonProperty("BO_UCI")
        private List<String> boUci;

        @JsonProperty("BO_FIRST_NAME")
        private List<String> boFirstName;

        @JsonProperty("BO_LAST_NAME")
        private List<String> boLastName;

        @JsonProperty("BO_ADDRESS")
        private List<String> boAddress;

        @JsonProperty("BO_CITY")
        private List<String> boCity;

        @JsonProperty("BO_COUNTRY_ISO_CODE")
        private List<String> boCountryIsoCode;

        @JsonProperty("BO_PHONE_NUMBER")
        private List<String> boPhoneNumber;

        @JsonProperty("BO_EMAIL")
        private List<String> boEmail;

        @JsonProperty("BO_POSSIBLE_PCT_CHANGE_DESCRIPTION")
        private List<String> boPossiblePctChangeDescription;

        @JsonProperty("BO_GRID_MATCH_INDICATOR_FORMATTED")
        private List<String> boGridMatchIndicatorFormatted;

        @JsonProperty("BO_GRID_MATCH_SANCTIONS_INDICATOR_FORMATTED")
        private List<String> boGridMatchSanctionsIndicatorFormatted;

        @JsonProperty("BO_GRID_MATCH_WATCHLIST_INDICATOR_FORMATTED")
        private List<String> boGridMatchWatchlistIndicatorFormatted;

        @JsonProperty("BO_GRID_MATCH_PEP_INDICATOR_FORMATTED")
        private List<String> boGridMatchPepIndicatorFormatted;

        @JsonProperty("BO_GRID_MATCH_MEDIA_INDICATOR_FORMATTED")
        private List<String> boGridMatchMediaIndicatorFormatted;

        // ── OUB_ fields (List<String>) ──

        @JsonProperty("OUB_NAME")
        private List<String> oubName;

        @JsonProperty("OUB_BVD_ID_NUMBER")
        private List<String> oubBvdIdNumber;

        @JsonProperty("OUB_ENTITY_TYPE")
        private List<String> oubEntityType;

        @JsonProperty("OUB_ADDRESS")
        private List<String> oubAddress;

        @JsonProperty("OUB_CITY")
        private List<String> oubCity;

        @JsonProperty("OUB_COUNTRY_ISO_CODE")
        private List<String> oubCountryIsoCode;

        @JsonProperty("OUB_PHONE_NUMBER")
        private List<String> oubPhoneNumber;

        @JsonProperty("OUB_WEBSITE")
        private List<String> oubWebsite;

        @JsonProperty("OUB_EMAIL")
        private List<String> oubEmail;

        @JsonProperty("OUB_POSSIBLE_PCT_CHANGE_DESCRIPTION")
        private List<String> oubPossiblePctChangeDescription;

        // ── BOI_ fields ──

        @JsonProperty("BOI_NAME")
        private List<String> boiName;

        @JsonProperty("BOI_BVD_ID_NUMBER")
        private List<String> boiBvdIdNumber;

        @JsonProperty("BOI_UCI")
        private List<String> boiUci;

        @JsonProperty("BOI_ENTITY_TYPE")
        private List<String> boiEntityType;

        @JsonProperty("BOI_ADDRESS")
        private List<String> boiAddress;

        @JsonProperty("BOI_CITY")
        private List<String> boiCity;

        @JsonProperty("BOI_COUNTRY_ISO_CODE")
        private List<String> boiCountryIsoCode;

        @JsonProperty("BOI_PHONE_NUMBER")
        private List<String> boiPhoneNumber;

        @JsonProperty("BOI_WEBSITE")
        private List<String> boiWebsite;

        @JsonProperty("BOI_EMAIL")
        private List<String> boiEmail;

        @JsonProperty("BOI_DIRECT_PCT")
        private List<String> boiDirectPct;

        @JsonProperty("BOI_TOTAL_PCT")
        private List<String> boiTotalPct;

        @JsonProperty("BOI_POSSIBLE_PCT_CHANGE_DESCRIPTION")
        private List<String> boiPossiblePctChangeDescription;

        // ── SH_ fields ──

        @JsonProperty("SH_COUNT")
        private Integer shCount;

        @JsonProperty("SH_NAME")
        private List<String> shName;

        @JsonProperty("SH_BVD_ID_NUMBER")
        private List<String> shBvdIdNumber;

        @JsonProperty("SH_UCI")
        private List<String> shUci;

        @JsonProperty("SH_LEI")
        private List<String> shLei;

        @JsonProperty("SH_COUNTRY_ISO_CODE")
        private List<String> shCountryIsoCode;

        @JsonProperty("SH_TERRITORY_ISO_CODE")
        private List<String> shTerritoryIsoCode;

        @JsonProperty("SH_STATE_PROVINCE")
        private List<String> shStateProvince;

        @JsonProperty("SH_CITY")
        private List<String> shCity;

        @JsonProperty("SH_ENTITY_TYPE")
        private List<String> shEntityType;

        @JsonProperty("SH_NACE_CORE_CODE")
        private List<String> shNaceCoreCode;

        @JsonProperty("SH_NACE_CORE_LABEL")
        private List<String> shNaceCoreLabel;

        @JsonProperty("SH_POSSIBLE_PCT_CHANGE_DESCRIPTION")
        private List<String> shPossiblePctChangeDescription;

        @JsonProperty("SH_DIRECT_PCT")
        private List<String> shDirectPct;

        @JsonProperty("SH_TOTAL_PCT")
        private List<String> shTotalPct;

        @JsonProperty("SH_OPRE")
        private List<Double> shOpre;

        @JsonProperty("SH_TOAS")
        private List<Double> shToas;

        @JsonProperty("SH_EMPL")
        private List<Double> shEmpl;

        @JsonProperty("SH_GRID_MATCH_INDICATOR_FORMATTED")
        private List<String> shGridMatchIndicatorFormatted;

        @JsonProperty("SH_GRID_MATCH_SANCTIONS_INDICATOR_FORMATTED")
        private List<String> shGridMatchSanctionsIndicatorFormatted;

        @JsonProperty("SH_GRID_MATCH_WATCHLIST_INDICATOR_FORMATTED")
        private List<String> shGridMatchWatchlistIndicatorFormatted;

        @JsonProperty("SH_GRID_MATCH_PEP_INDICATOR_FORMATTED")
        private List<String> shGridMatchPepIndicatorFormatted;

        @JsonProperty("SH_GRID_MATCH_MEDIA_INDICATOR_FORMATTED")
        private List<String> shGridMatchMediaIndicatorFormatted;

        // ── GUO_ fields (single values) ──

        @JsonProperty("GUO_NAME")
        private String guoName;

        @JsonProperty("GUO_FIRST_NAME")
        private String guoFirstName;

        @JsonProperty("GUO_LAST_NAME")
        private String guoLastName;

        @JsonProperty("GUO_BVD_ID_NUMBER")
        private String guoBvdIdNumber;

        @JsonProperty("GUO_UCI")
        private String guoUci;

        @JsonProperty("GUO_LEI")
        private String guoLei;

        @JsonProperty("GUO_COUNTRY_ISO_CODE")
        private String guoCountryIsoCode;

        @JsonProperty("GUO_STATE_PROVINCE")
        private String guoStateProvince;

        @JsonProperty("GUO_CITY")
        private String guoCity;

        @JsonProperty("GUO_ENTITY_TYPE")
        private String guoEntityType;

        @JsonProperty("GUO_NACE_CORE_CODE")
        private String guoNaceCoreCode;

        @JsonProperty("GUO_NACE_CORE_LABEL")
        private String guoNaceCoreLabel;

        @JsonProperty("GUO_DIRECT_PCT")
        private String guoDirectPct;

        @JsonProperty("GUO_TOTAL_PCT")
        private String guoTotalPct;

        @JsonProperty("GUO_OPRE")
        private Double guoOpre;

        @JsonProperty("GUO_TOAS")
        private Double guoToas;

        @JsonProperty("GUO_EMPL")
        private Double guoEmpl;

        @JsonProperty("GUO_GRID_MATCH_INDICATOR_FORMATTED")
        private String guoGridMatchIndicatorFormatted;

        @JsonProperty("GUO_GRID_MATCH_SANCTIONS_INDICATOR_FORMATTED")
        private String guoGridMatchSanctionsIndicatorFormatted;

        @JsonProperty("GUO_GRID_MATCH_WATCHLIST_INDICATOR_FORMATTED")
        private String guoGridMatchWatchlistIndicatorFormatted;

        @JsonProperty("GUO_GRID_MATCH_PEP_INDICATOR_FORMATTED")
        private String guoGridMatchPepIndicatorFormatted;

        @JsonProperty("GUO_GRID_MATCH_MEDIA_INDICATOR_FORMATTED")
        private String guoGridMatchMediaIndicatorFormatted;

        // ── SUB_ fields ──

        @JsonProperty("SUB_COUNT")
        private Integer subCount;

        @JsonProperty("SUB_NAME")
        private List<String> subName;

        @JsonProperty("SUB_BVD_ID_NUMBER")
        private List<String> subBvdIdNumber;

        @JsonProperty("SUB_LEI")
        private List<String> subLei;

        @JsonProperty("SUB_COUNTRY_ISO_CODE")
        private List<String> subCountryIsoCode;

        @JsonProperty("SUB_STATE_PROVINCE")
        private List<String> subStateProvince;

        @JsonProperty("SUB_CITY")
        private List<String> subCity;

        @JsonProperty("SUB_ENTITY_TYPE")
        private List<String> subEntityType;

        @JsonProperty("SUB_NACE_CORE_CODE")
        private List<String> subNaceCoreCode;

        @JsonProperty("SUB_NACE_CORE_LABEL")
        private List<String> subNaceCoreLabel;

        @JsonProperty("SUB_POSSIBLE_PCT_CHANGE_DESCRIPTION")
        private List<String> subPossiblePctChangeDescription;

        @JsonProperty("SUB_DIRECT_PCT")
        private List<String> subDirectPct;

        @JsonProperty("SUB_TOTAL_PCT")
        private List<String> subTotalPct;

        @JsonProperty("SUB_OPRE")
        private List<Double> subOpre;

        @JsonProperty("SUB_TOAS")
        private List<Double> subToas;

        @JsonProperty("SUB_EMPL")
        private List<Double> subEmpl;

        @JsonProperty("SUB_GRID_MATCH_INDICATOR_FORMATTED")
        private List<String> subGridMatchIndicatorFormatted;

        @JsonProperty("SUB_GRID_MATCH_SANCTIONS_INDICATOR_FORMATTED")
        private List<String> subGridMatchSanctionsIndicatorFormatted;

        @JsonProperty("SUB_GRID_MATCH_WATCHLIST_INDICATOR_FORMATTED")
        private List<String> subGridMatchWatchlistIndicatorFormatted;

        @JsonProperty("SUB_GRID_MATCH_PEP_INDICATOR_FORMATTED")
        private List<String> subGridMatchPepIndicatorFormatted;

        @JsonProperty("SUB_GRID_MATCH_MEDIA_INDICATOR_FORMATTED")
        private List<String> subGridMatchMediaIndicatorFormatted;
    }
}
