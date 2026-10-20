package io.fireflysoftware.data.business.intelligence.infra.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Provider-agnostic business intelligence report DTO.
 * Unifies firmographic, financial, ownership, and director data
 * from any business data provider (e.g. Orbis BvD).
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BusinessReportDTO {

    private ProviderInfoDTO provider;
    private CompanyProfileDTO company;
    private FinancialSummaryDTO financials;
    private OwnershipStructureDTO ownership;
    private List<DirectorDTO> directors;

    // ── Provider Metadata ──

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProviderInfoDTO {
        private String providerName;
        private String reportType;
        private String reportDate;
    }

    // ── Company Profile (firmographics) ──

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CompanyProfileDTO {
        private String name;
        private String identifier;
        private String address;
        private String postcode;
        private String city;
        private String country;
        private String countryIsoCode;
        private Double latitude;
        private Double longitude;
        private String region;
        private String worldRegion;
        private List<String> phones;
        private List<String> emails;
        private List<String> websites;
        private String tradeDescription;
        private String primaryBusinessLine;
        private String productsServices;
        private Long operationalSize;
        private List<IndustryCodeDTO> industryClassifications;
        private List<LegalIdentifierDTO> legalIdentifiers;
        private ComplianceScreeningDTO compliance;
    }

    // ── Industry Classification ──

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class IndustryCodeDTO {
        private String system;
        private String code;
        private String label;
    }

    // ── Legal Identifier ──

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LegalIdentifierDTO {
        private String type;
        private String value;
        private String label;
    }

    // ── Compliance / GRID Screening ──

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ComplianceScreeningDTO {
        private String matchIndicator;
        private String sanctionsIndicator;
        private String watchlistIndicator;
        private String pepIndicator;
        private String mediaIndicator;
    }

    // ── Financial Summary ──

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FinancialSummaryDTO {
        private String currency;
        private String consolidationCode;
        private String filingType;
        private String closingDateLastAnnual;
        private String yearLastAccounts;
        private String accountingTemplate;
        private List<FinancialPeriodDTO> periods;
        private List<FinancialRatioDTO> ratios;
        private List<LegalEventDTO> legalEvents;
    }

    // ── Single Financial Period ──

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FinancialPeriodDTO {
        private String fiscalYear;
        private String closingDate;
        private String numberOfMonths;
        private Double operatingRevenue;
        private Double profitBeforeTax;
        private Double profitLoss;
        private Double cashFlow;
        private Double totalAssets;
        private Double shareholdersFunds;
        private Double employees;
        private Double exchangeRate;
        private String currency;
        private String unit;
        private String auditStatus;
        private String accountsStatus;
        private String accountingPractice;
    }

    // ── Single Financial Ratio Period ──

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FinancialRatioDTO {
        private String fiscalYear;
        private Double currentRatio;
        private Double profitMargin;
        private Double returnOnShareholdersFunds;
        private Double returnOnCapitalEmployed;
        private Double solvencyRatio;
    }

    // ── Legal Event ──

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LegalEventDTO {
        private String date;
        private String description;
    }

    // ── Ownership Structure ──

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OwnershipStructureDTO {
        private String corporateGroupSize;
        private String corporateEntityType;
        private StakeholderDTO globalUltimateOwner;
        private List<StakeholderDTO> shareholders;
        private List<StakeholderDTO> beneficialOwners;
        private List<StakeholderDTO> subsidiaries;
    }

    // ── Stakeholder (shareholder, BO, subsidiary, or GUO) ──

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class StakeholderDTO {
        private String name;
        private String firstName;
        private String lastName;
        private String identifier;
        private String lei;
        private String uci;
        private String entityType;
        private String countryIsoCode;
        private String stateProvince;
        private String city;
        private String address;
        private String phone;
        private String email;
        private String website;
        private Double directPercentage;
        private Double totalPercentage;
        private Double operatingRevenue;
        private Double totalAssets;
        private Double employees;
        private String industryCode;
        private String industryLabel;
        private ComplianceScreeningDTO compliance;
    }

    // ── Director / Contact ──

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DirectorDTO {
        private String fullName;
        private String firstName;
        private String middleName;
        private String lastName;
        private String title;
        private String function;
        private String directorId;
        private String bvdId;
        private String nationality;
        private String email;
        private String workAddress;
        private String country;
        private String startDate;
        private String endDate;
        private String board;
        private String department;
        private String level;
        private ComplianceScreeningDTO compliance;
    }
}
