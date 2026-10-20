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
public class OrbisDirectorsResponse {

    @JsonProperty("Data")
    private List<OrbisDirectorsData> data;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class OrbisDirectorsData {

        // ── Basic ──

        @JsonProperty("NAME")
        private String name;

        // ── Counts ──

        @JsonProperty("CPYCONTACTS_MEMBERSHIP_DIFFERENT_PERSONS_CNT")
        private Integer cpycontactsMembershipDifferentPersonsCnt;

        @JsonProperty("CPYCONTACTS_MEMBERSHIP_DIFFERENT_PERSONS_CNT_1")
        private Integer cpycontactsMembershipDifferentPersonsCnt1;

        // ── Director detail fields (List<String>) ──

        @JsonProperty("CPYCONTACTS_HEADER_FullNameOriginalLanguagePreferred")
        private List<String> cpycontactsHeaderFullNameOriginalLanguagePreferred;

        @JsonProperty("CPYCONTACTS_HEADER_IdDirector")
        private List<String> cpycontactsHeaderIdDirector;

        @JsonProperty("CPYCONTACTS_HEADER_BvdId")
        private List<String> cpycontactsHeaderBvdId;

        @JsonProperty("CPYCONTACTS_MEMBERSHIP_Function")
        private List<String> cpycontactsMembershipFunction;

        @JsonProperty("CPYCONTACTS_MEMBERSHIP_BeginningNominationDate")
        private List<String> cpycontactsMembershipBeginningNominationDate;

        @JsonProperty("CPYCONTACTS_MEMBERSHIP_EndExpirationDate")
        private List<String> cpycontactsMembershipEndExpirationDate;

        @JsonProperty("CPYCONTACTS_HEADER_GRID_MATCH_INDICATOR")
        private List<String> cpycontactsHeaderGridMatchIndicator;

        @JsonProperty("CPYCONTACTS_HEADER_GRID_MATCH_SANCTIONS_INDICATOR")
        private List<String> cpycontactsHeaderGridMatchSanctionsIndicator;

        @JsonProperty("CPYCONTACTS_HEADER_GRID_MATCH_WATCHLIST_INDICATOR")
        private List<String> cpycontactsHeaderGridMatchWatchlistIndicator;

        @JsonProperty("CPYCONTACTS_HEADER_GRID_MATCH_PEP_INDICATOR")
        private List<String> cpycontactsHeaderGridMatchPepIndicator;

        @JsonProperty("CPYCONTACTS_HEADER_GRID_MATCH_MEDIA_INDICATOR")
        private List<String> cpycontactsHeaderGridMatchMediaIndicator;

        @JsonProperty("CPYCONTACTS_HEADER_BareTitle")
        private List<String> cpycontactsHeaderBareTitle;

        @JsonProperty("CPYCONTACTS_HEADER_FirstNameOriginalLanguagePreferred")
        private List<String> cpycontactsHeaderFirstNameOriginalLanguagePreferred;

        @JsonProperty("CPYCONTACTS_HEADER_MiddleNameOriginalLanguagePreferred")
        private List<String> cpycontactsHeaderMiddleNameOriginalLanguagePreferred;

        @JsonProperty("CPYCONTACTS_HEADER_LastNameOriginalLanguagePreferred")
        private List<String> cpycontactsHeaderLastNameOriginalLanguagePreferred;

        @JsonProperty("CPYCONTACTS_HEADER_MultipleNationalitiesLabel")
        private List<String> cpycontactsHeaderMultipleNationalitiesLabel;

        @JsonProperty("CPYCONTACTS_MEMBERSHIP_WorkFullAddress")
        private List<String> cpycontactsMembershipWorkFullAddress;

        @JsonProperty("CPYCONTACTS_HEADER_CountryLabel")
        private List<String> cpycontactsHeaderCountryLabel;

        @JsonProperty("CPYCONTACTS_HEADER_Email")
        private List<String> cpycontactsHeaderEmail;

        @JsonProperty("CPYCONTACTS_MEMBERSHIP_BoardMnemonic")
        private List<String> cpycontactsMembershipBoardMnemonic;

        @JsonProperty("CPYCONTACTS_MEMBERSHIP_DepartmentFromHierCodeFall2009")
        private List<String> cpycontactsMembershipDepartmentFromHierCodeFall2009;

        @JsonProperty("CPYCONTACTS_MEMBERSHIP_LevelFromHierCodeFall2009")
        private List<String> cpycontactsMembershipLevelFromHierCodeFall2009;
    }
}
