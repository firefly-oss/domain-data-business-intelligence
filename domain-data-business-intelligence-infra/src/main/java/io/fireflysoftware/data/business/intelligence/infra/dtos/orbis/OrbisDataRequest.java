package io.fireflysoftware.data.business.intelligence.infra.dtos.orbis;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrbisDataRequest {
    @JsonProperty("WHERE")
    private List<Map<String, List<String>>> where;
    @JsonProperty("SELECT")
    private List<Map<String, Object>> select;

    // ──────────────────────────────────────────────────────────────────────
    // Ownership filter constants
    // ──────────────────────────────────────────────────────────────────────

    private static final String GUO_FILTERS =
            "Filter.Name=GlobalUltimateOwners;"
                    + "GlobalUltimateOwners.RemoveVessels=1;"
                    + "GlobalUltimateOwners.IsBvDLiensNote131=1;"
                    + "GlobalUltimateOwners.ControlShareholders=0;"
                    + "GlobalUltimateOwners.UltimatesIASOnlyEqU=1;"
                    + "GlobalUltimateOwners.UseBranchHeadQuarter=1;"
                    + "GlobalUltimateOwners.ArchivedDatesFilter=0;"
                    + "GlobalUltimateOwners.IsBvDLiensNote53=1;"
                    + "GlobalUltimateOwners.Ultimates=0;"
                    + "GlobalUltimateOwners.ListedIASDefinitionOnly=0;"
                    + "GlobalUltimateOwners.QuotedShareholders=0;"
                    + "GlobalUltimateOwners.UltimatesIASOnlyDiffU=1;";

    private static final String BO_FILTERS =
            "Filter.Name=BeneficialOwnersFilter;"
                    + "BeneficialOwnersFilter.MinPercentBOFirstLevel=1000;"
                    + "BeneficialOwnersFilter.MinPercentBOHigherLevel=5001;"
                    + "BeneficialOwnersFilter.MinPercentBOLastLevelIndividual=1000;"
                    + "BeneficialOwnersFilter.AcceptNaPercentageAtLastLevelIndividual=True;"
                    + "BeneficialOwnersFilter.EjectIndividualsEvenIfAllWOUntilIndividualAndMinPercentBOFirstLevelIsOK=False;"
                    + "BeneficialOwnersFilter.KeepOnlyOnePathForEachBO_OUB=True;"
                    + "BeneficialOwnersFilter.BOFromRegisterOnly=False;";

    private static final String BO_10_10_FILTERS =
            "Filter.Name=BeneficialOwnersFilterDefinition10_10;"
                    + "BeneficialOwnersFilterDefinition10_10.MinPercentBOFirstLevel=1000;"
                    + "BeneficialOwnersFilterDefinition10_10.MinPercentBOHigherLevel=1000;"
                    + "BeneficialOwnersFilterDefinition10_10.MinPercentBOLastLevelIndividual=1000;"
                    + "BeneficialOwnersFilterDefinition10_10.AcceptNaPercentageAtLastLevelIndividual=True;"
                    + "BeneficialOwnersFilterDefinition10_10.EjectIndividualsEvenIfAllWOUntilIndividualAndMinPercentBOFirstLevelIsOK=False;"
                    + "BeneficialOwnersFilterDefinition10_10.KeepOnlyOnePathForEachBO_OUB=True;"
                    + "BeneficialOwnersFilterDefinition10_10.BOFromRegisterOnly=False;";

    private static final String BO_10_50_FILTERS =
            "Filter.Name=BeneficialOwnersFilterDefinition10_50;"
                    + "BeneficialOwnersFilterDefinition10_50.MinPercentBOFirstLevel=1000;"
                    + "BeneficialOwnersFilterDefinition10_50.MinPercentBOHigherLevel=5000;"
                    + "BeneficialOwnersFilterDefinition10_50.MinPercentBOLastLevelIndividual=1000;"
                    + "BeneficialOwnersFilterDefinition10_50.AcceptNaPercentageAtLastLevelIndividual=True;"
                    + "BeneficialOwnersFilterDefinition10_50.EjectIndividualsEvenIfAllWOUntilIndividualAndMinPercentBOFirstLevelIsOK=False;"
                    + "BeneficialOwnersFilterDefinition10_50.KeepOnlyOnePathForEachBO_OUB=True;"
                    + "BeneficialOwnersFilterDefinition10_50.BOFromRegisterOnly=False;";

    private static final String BO_25_25_FILTERS =
            "Filter.Name=BeneficialOwnersFilterDefinition25_25;"
                    + "BeneficialOwnersFilterDefinition25_25.MinPercentBOFirstLevel=2500;"
                    + "BeneficialOwnersFilterDefinition25_25.MinPercentBOHigherLevel=2500;"
                    + "BeneficialOwnersFilterDefinition25_25.MinPercentBOLastLevelIndividual=2500;"
                    + "BeneficialOwnersFilterDefinition25_25.AcceptNaPercentageAtLastLevelIndividual=True;"
                    + "BeneficialOwnersFilterDefinition25_25.EjectIndividualsEvenIfAllWOUntilIndividualAndMinPercentBOFirstLevelIsOK=False;"
                    + "BeneficialOwnersFilterDefinition25_25.KeepOnlyOnePathForEachBO_OUB=True;"
                    + "BeneficialOwnersFilterDefinition25_25.BOFromRegisterOnly=False;";

    private static final String SH_FILTERS =
            "Filter.Name=Shareholders;"
                    + "Shareholders.RemoveVessels=0;"
                    + "Shareholders.RemoveBranches=0;"
                    + "Shareholders.AlsoSelectNotListedShareholders=1;"
                    + "Shareholders.ArchivedDatesFilter=0;"
                    + "Shareholders.RecursionLevel=1;";

    private static final String SUB_FILTERS =
            "Filter.Name=Subsidiaries;"
                    + "Subsidiaries.RemoveVessels=0;"
                    + "Subsidiaries.RemoveBranches=1;"
                    + "Subsidiaries.ControlShareholders=0;"
                    + "Subsidiaries.UltimatesIASOnlyEqU=1;"
                    + "Subsidiaries.QuotedShareholders=0;"
                    + "Subsidiaries.UltimatesIASOnlyDiffU=1;"
                    + "Subsidiaries.Ultimates=0;"
                    + "Subsidiaries.ListedIASDefinitionOnly=0;"
                    + "Subsidiaries.IsBvDLiensNote53=1;"
                    + "Subsidiaries.RecursionLevel=1;";

    // ──────────────────────────────────────────────────────────────────────
    // Directors / Contacts filter constants
    // ──────────────────────────────────────────────────────────────────────

    private static final String CONTACTS_FILTERS =
            "Filter.Name=ContactsFilter;"
                    + "ContactsFilter.IfHomeOnlyReturnCountry=1;"
                    + "ContactsFilter.SourcesToExcludeQueryString=99B|59B|69B|70B|0|278;"
                    + "ContactsFilter.HierarchicCodeToExcludeQueryString=3|4;"
                    + "ContactsFilter.HierarchicCodeQueryString=0|1|2;"
                    + "ContactsFilter.HierarchicCodeToExcludeCountQueryString=3|4;"
                    + "ContactsFilter.HierarchicCodeCountQueryString=0|1|2";

    private static final String CONTACTS_CURRENTS_FILTERS =
            "Filter.Name=ContactsFilter;"
                    + "ContactsFilter.IfHomeOnlyReturnCountry=1;"
                    + "ContactsFilter.Currents=True;"
                    + "ContactsFilter.CurrentPreviousQueryString=0;"
                    + "ContactsFilter.SourcesToExcludeQueryString=99B|59B|69B|70B|0|278;"
                    + "ContactsFilter.HierarchicCodeToExcludeQueryString=3|4;"
                    + "ContactsFilter.HierarchicCodeQueryString=0|1|2;"
                    + "ContactsFilter.HierarchicCodeToExcludeCountQueryString=3|4;"
                    + "ContactsFilter.HierarchicCodeCountQueryString=0|1|2";

    private static final String CONTACTS_WORK_ADDRESS_FILTERS =
            "Filter.Name=ContactsFilter;"
                    + "ContactsFilter.IfHomeOnlyReturnCountry=1;"
                    + "ContactsFilter.PriorityToWorkAddress=1;"
                    + "ContactsFilter.SourcesToExcludeQueryString=99B|59B|69B|70B|0|278;"
                    + "ContactsFilter.HierarchicCodeToExcludeQueryString=3|4;"
                    + "ContactsFilter.HierarchicCodeQueryString=0|1|2";

    private static final String CONTACTS_BASE_FILTERS =
            "Filter.Name=ContactsFilter;"
                    + "ContactsFilter.IfHomeOnlyReturnCountry=1;"
                    + "ContactsFilter.SourcesToExcludeQueryString=99B|59B|69B|70B|0|278;"
                    + "ContactsFilter.HierarchicCodeToExcludeQueryString=3|4;"
                    + "ContactsFilter.HierarchicCodeQueryString=0|1|2";

    // ──────────────────────────────────────────────────────────────────────
    // Private helper methods for building SELECT entries
    // ──────────────────────────────────────────────────────────────────────

    private static Map<String, Object> simple(String field) {
        return Map.of(field, Map.of("AS", field));
    }

    private static Map<String, Object> limited(String field, int limit) {
        return Map.of(field, Map.of("LIMIT", limit, "AS", field));
    }

    private static Map<String, Object> limited(String field, int limit, String alias) {
        return Map.of(field, Map.of("LIMIT", limit, "AS", alias));
    }

    private static Map<String, Object> financial(String field, String currency, int unit, String year, String alias) {
        return Map.of(field, Map.of("CURRENCY", currency, "UNIT", unit, "INDEXORYEAR", year, "AS", alias));
    }

    private static Map<String, Object> ratio(String field, int unit, String year, String alias) {
        return Map.of(field, Map.of("UNIT", unit, "INDEXORYEAR", year, "AS", alias));
    }

    private static Map<String, Object> indexed(String field, String year, String alias) {
        return Map.of(field, Map.of("INDEXORYEAR", year, "AS", alias));
    }

    private static Map<String, Object> filtered(String field, String filters, String alias) {
        return Map.of(field, Map.of("FILTERS", filters, "AS", alias));
    }

    private static Map<String, Object> filteredLimited(String field, String filters, int limit, String alias) {
        Map<String, Object> inner = new LinkedHashMap<>();
        inner.put("FILTERS", filters);
        inner.put("LIMIT", limit);
        inner.put("AS", alias);
        return Map.of(field, inner);
    }

    private static Map<String, Object> filteredFinancial(String field, String filters, String currency, int unit, String alias) {
        Map<String, Object> inner = new LinkedHashMap<>();
        inner.put("FILTERS", filters);
        inner.put("CURRENCY", currency);
        inner.put("UNIT", unit);
        inner.put("AS", alias);
        return Map.of(field, inner);
    }

    @SafeVarargs
    private static List<Map<String, Object>> buildSelectList(Map<String, Object>... entries) {
        return new ArrayList<>(Arrays.asList(entries));
    }

    // ──────────────────────────────────────────────────────────────────────
    // Factory: Firmographics & GRID
    // ──────────────────────────────────────────────────────────────────────

    public static OrbisDataRequest forBvDId(String bvdId) {
        return OrbisDataRequest.builder()
                .where(List.of(Map.of("BvDID", List.of(bvdId))))
                .select(buildSelectList(
                        simple("NAME"),
                        simple("BVD_ID_NUMBER"),
                        simple("ADDRESS_LINE1"),
                        simple("POSTCODE"),
                        simple("CITY"),
                        simple("COUNTRY"),
                        simple("COUNTRY_ISO_CODE"),
                        simple("LATITUDE"),
                        simple("LONGITUDE"),
                        limited("COUNTRY_REGION", 1),
                        limited("COUNTRY_REGION_TYPE", 1),
                        simple("NUTS1"),
                        simple("WORLD_REGION"),
                        simple("US_STATE"),
                        simple("COUNTY"),
                        simple("ADDRESS_TYPE"),
                        limited("PHONE_NUMBER", 1),
                        limited("WEBSITE", 1),
                        limited("EMAIL", 1),
                        simple("TRADE_DESCRIPTION_EN"),
                        simple("PRODUCTS_SERVICES"),
                        limited("INDUSTRY_PRIMARY_CODE", 1),
                        simple("NACE2_MAIN_SECTION"),
                        simple("NACE2_CORE_CODE"),
                        limited("NACE2_PRIMARY_CODE", 1),
                        simple("NAICS2022_CORE_CODE"),
                        simple("NAICS2022_CORE_LABEL"),
                        limited("USSIC_PRIMARY_CODE", 1),
                        limited("USSIC_PRIMARY_LABEL", 1),
                        simple("OPGSIZE"),
                        simple("OVERVIEW_PRIMARY_BUSINESS_LINE"),
                        simple("LEI_STATUS"),
                        simple("LEI"),
                        limited("NATIONAL_ID", 1),
                        limited("NATIONAL_ID_LABEL", 1),
                        limited("NATIONAL_ID_TYPE", 1),
                        limited("VAT_NUMBER", 1),
                        limited("TRADE_REGISTER_NUMBER", 1),
                        simple("TIN"),
                        simple("SWIFT_CODE"),
                        simple("ISIN"),
                        simple("GRID_MATCH_INDICATOR"),
                        simple("GRID_MATCH_SANCTIONS_INDICATOR"),
                        simple("GRID_MATCH_WATCHLIST_INDICATOR"),
                        simple("GRID_MATCH_PEP_INDICATOR"),
                        simple("GRID_MATCH_MEDIA_INDICATOR")
                ))
                .build();
    }

    // ──────────────────────────────────────────────────────────────────────
    // Factory: Financials
    // ──────────────────────────────────────────────────────────────────────

    public static OrbisDataRequest forFinancials(String bvdId) {
        return OrbisDataRequest.builder()
                .where(List.of(Map.of("BvDID", List.of(bvdId))))
                .select(buildSelectList(
                        // Basic info
                        simple("NAME"),
                        simple("CONSOLIDATION_CODE"),
                        simple("FILING_TYPE"),
                        simple("CLOSING_DATE_LAST_ANNUAL_ACCOUNTS"),
                        simple("CLOSING_DATE_LAST_INTERIM_ACCOUNTS"),
                        simple("YEAR_LAST_ACCOUNTS"),
                        indexed("ORIGINAL_CURRENCY", "0", "ORIGINAL_CURRENCY"),
                        simple("ACCOUNTING_TEMPLATE"),
                        simple("NUMBER_YEARS"),
                        limited("LEGAL_EVENTS_DATE", 1),
                        limited("LEGAL_EVENTS_DESCRIPTION", 1),

                        // OPRE - Operating Revenue (4 years, RECORD + EUR)
                        financial("OPRE", "RECORD", 0, "0", "OPRE"),
                        financial("OPRE", "EUR", 0, "0", "OPRE_1"),
                        financial("OPRE", "RECORD", 0, "1", "OPRE_2"),
                        financial("OPRE", "EUR", 0, "1", "OPRE_3"),
                        financial("OPRE", "RECORD", 0, "2", "OPRE_4"),
                        financial("OPRE", "EUR", 0, "2", "OPRE_5"),
                        financial("OPRE", "RECORD", 0, "3", "OPRE_6"),
                        financial("OPRE", "EUR", 0, "3", "OPRE_7"),

                        // PLBT - Profit/Loss Before Tax (4 years, RECORD + EUR)
                        financial("PLBT", "RECORD", 0, "0", "PLBT"),
                        financial("PLBT", "EUR", 0, "0", "PLBT_1"),
                        financial("PLBT", "RECORD", 0, "1", "PLBT_2"),
                        financial("PLBT", "EUR", 0, "1", "PLBT_3"),
                        financial("PLBT", "RECORD", 0, "2", "PLBT_4"),
                        financial("PLBT", "EUR", 0, "2", "PLBT_5"),
                        financial("PLBT", "RECORD", 0, "3", "PLBT_6"),
                        financial("PLBT", "EUR", 0, "3", "PLBT_7"),

                        // PL - Profit/Loss (4 years, RECORD + EUR)
                        financial("PL", "RECORD", 0, "0", "PL"),
                        financial("PL", "EUR", 0, "0", "PL_1"),
                        financial("PL", "RECORD", 0, "1", "PL_2"),
                        financial("PL", "EUR", 0, "1", "PL_3"),
                        financial("PL", "RECORD", 0, "2", "PL_4"),
                        financial("PL", "EUR", 0, "2", "PL_5"),
                        financial("PL", "RECORD", 0, "3", "PL_6"),
                        financial("PL", "EUR", 0, "3", "PL_7"),

                        // CF - Cash Flow (4 years, RECORD + EUR)
                        financial("CF", "RECORD", 0, "0", "CF"),
                        financial("CF", "EUR", 0, "0", "CF_1"),
                        financial("CF", "RECORD", 0, "1", "CF_2"),
                        financial("CF", "EUR", 0, "1", "CF_3"),
                        financial("CF", "RECORD", 0, "2", "CF_4"),
                        financial("CF", "EUR", 0, "2", "CF_5"),
                        financial("CF", "RECORD", 0, "3", "CF_6"),
                        financial("CF", "EUR", 0, "3", "CF_7"),

                        // TOAS - Total Assets (4 years, RECORD + EUR)
                        financial("TOAS", "RECORD", 0, "0", "TOAS"),
                        financial("TOAS", "EUR", 0, "0", "TOAS_1"),
                        financial("TOAS", "RECORD", 0, "1", "TOAS_2"),
                        financial("TOAS", "EUR", 0, "1", "TOAS_3"),
                        financial("TOAS", "RECORD", 0, "2", "TOAS_4"),
                        financial("TOAS", "EUR", 0, "2", "TOAS_5"),
                        financial("TOAS", "RECORD", 0, "3", "TOAS_6"),
                        financial("TOAS", "EUR", 0, "3", "TOAS_7"),

                        // SHFD - Shareholders Funds (4 years, RECORD + EUR)
                        financial("SHFD", "RECORD", 0, "0", "SHFD"),
                        financial("SHFD", "EUR", 0, "0", "SHFD_1"),
                        financial("SHFD", "RECORD", 0, "1", "SHFD_2"),
                        financial("SHFD", "EUR", 0, "1", "SHFD_3"),
                        financial("SHFD", "RECORD", 0, "2", "SHFD_4"),
                        financial("SHFD", "EUR", 0, "2", "SHFD_5"),
                        financial("SHFD", "RECORD", 0, "3", "SHFD_6"),
                        financial("SHFD", "EUR", 0, "3", "SHFD_7"),

                        // EXCHANGE_RATE (4 years, RECORD + EUR)
                        financial("EXCHANGE_RATE", "RECORD", 0, "0", "EXCHANGE_RATE"),
                        financial("EXCHANGE_RATE", "EUR", 0, "0", "EXCHANGE_RATE_1"),
                        financial("EXCHANGE_RATE", "RECORD", 0, "1", "EXCHANGE_RATE_2"),
                        financial("EXCHANGE_RATE", "EUR", 0, "1", "EXCHANGE_RATE_3"),
                        financial("EXCHANGE_RATE", "RECORD", 0, "2", "EXCHANGE_RATE_4"),
                        financial("EXCHANGE_RATE", "EUR", 0, "2", "EXCHANGE_RATE_5"),
                        financial("EXCHANGE_RATE", "RECORD", 0, "3", "EXCHANGE_RATE_6"),
                        financial("EXCHANGE_RATE", "EUR", 0, "3", "EXCHANGE_RATE_7"),

                        // CURR - Current Ratio (4 years)
                        ratio("CURR", 0, "0", "CURR"),
                        ratio("CURR", 0, "1", "CURR_1"),
                        ratio("CURR", 0, "2", "CURR_2"),
                        ratio("CURR", 0, "3", "CURR_3"),

                        // PRMA - Profit Margin (4 years)
                        ratio("PRMA", 0, "0", "PRMA"),
                        ratio("PRMA", 0, "1", "PRMA_1"),
                        ratio("PRMA", 0, "2", "PRMA_2"),
                        ratio("PRMA", 0, "3", "PRMA_3"),

                        // RSHF - Return on Shareholders Funds (4 years)
                        ratio("RSHF", 0, "0", "RSHF"),
                        ratio("RSHF", 0, "1", "RSHF_1"),
                        ratio("RSHF", 0, "2", "RSHF_2"),
                        ratio("RSHF", 0, "3", "RSHF_3"),

                        // RCEM - Return on Capital Employed (4 years)
                        ratio("RCEM", 0, "0", "RCEM"),
                        ratio("RCEM", 0, "1", "RCEM_1"),
                        ratio("RCEM", 0, "2", "RCEM_2"),
                        ratio("RCEM", 0, "3", "RCEM_3"),

                        // SOLR - Solvency Ratio (4 years)
                        ratio("SOLR", 0, "0", "SOLR"),
                        ratio("SOLR", 0, "1", "SOLR_1"),
                        ratio("SOLR", 0, "2", "SOLR_2"),
                        ratio("SOLR", 0, "3", "SOLR_3"),

                        // EMPL - Employees (4 years)
                        ratio("EMPL", 0, "0", "EMPL"),
                        ratio("EMPL", 0, "1", "EMPL_1"),
                        ratio("EMPL", 0, "2", "EMPL_2"),
                        ratio("EMPL", 0, "3", "EMPL_3"),

                        // CLOSING_DATE (4 years)
                        indexed("CLOSING_DATE", "0", "CLOSING_DATE"),
                        indexed("CLOSING_DATE", "1", "CLOSING_DATE_1"),
                        indexed("CLOSING_DATE", "2", "CLOSING_DATE_2"),
                        indexed("CLOSING_DATE", "3", "CLOSING_DATE_3"),

                        // FISCAL_YEAR (4 years)
                        indexed("FISCAL_YEAR", "0", "FISCAL_YEAR"),
                        indexed("FISCAL_YEAR", "1", "FISCAL_YEAR_1"),
                        indexed("FISCAL_YEAR", "2", "FISCAL_YEAR_2"),
                        indexed("FISCAL_YEAR", "3", "FISCAL_YEAR_3"),

                        // QUARTER_YEAR (4 years)
                        indexed("QUARTER_YEAR", "0", "QUARTER_YEAR"),
                        indexed("QUARTER_YEAR", "1", "QUARTER_YEAR_1"),
                        indexed("QUARTER_YEAR", "2", "QUARTER_YEAR_2"),
                        indexed("QUARTER_YEAR", "3", "QUARTER_YEAR_3"),

                        // NUMBER_MONTHS (4 years)
                        indexed("NUMBER_MONTHS", "0", "NUMBER_MONTHS"),
                        indexed("NUMBER_MONTHS", "1", "NUMBER_MONTHS_1"),
                        indexed("NUMBER_MONTHS", "2", "NUMBER_MONTHS_2"),
                        indexed("NUMBER_MONTHS", "3", "NUMBER_MONTHS_3"),

                        // AUDIT_STATUS (4 years)
                        indexed("AUDIT_STATUS", "0", "AUDIT_STATUS"),
                        indexed("AUDIT_STATUS", "1", "AUDIT_STATUS_1"),
                        indexed("AUDIT_STATUS", "2", "AUDIT_STATUS_2"),
                        indexed("AUDIT_STATUS", "3", "AUDIT_STATUS_3"),

                        // ACCOUNTS_STATUS (4 years)
                        indexed("ACCOUNTS_STATUS", "0", "ACCOUNTS_STATUS"),
                        indexed("ACCOUNTS_STATUS", "1", "ACCOUNTS_STATUS_1"),
                        indexed("ACCOUNTS_STATUS", "2", "ACCOUNTS_STATUS_2"),
                        indexed("ACCOUNTS_STATUS", "3", "ACCOUNTS_STATUS_3"),

                        // ACCOUNTING_PRACTICE (4 years)
                        indexed("ACCOUNTING_PRACTICE", "0", "ACCOUNTING_PRACTICE"),
                        indexed("ACCOUNTING_PRACTICE", "1", "ACCOUNTING_PRACTICE_1"),
                        indexed("ACCOUNTING_PRACTICE", "2", "ACCOUNTING_PRACTICE_2"),
                        indexed("ACCOUNTING_PRACTICE", "3", "ACCOUNTING_PRACTICE_3"),

                        // ACCOUNTS_SOURCE_CODE (4 years)
                        indexed("ACCOUNTS_SOURCE_CODE", "0", "ACCOUNTS_SOURCE_CODE"),
                        indexed("ACCOUNTS_SOURCE_CODE", "1", "ACCOUNTS_SOURCE_CODE_1"),
                        indexed("ACCOUNTS_SOURCE_CODE", "2", "ACCOUNTS_SOURCE_CODE_2"),
                        indexed("ACCOUNTS_SOURCE_CODE", "3", "ACCOUNTS_SOURCE_CODE_3"),

                        // ORIGINAL_UNIT (4 years)
                        indexed("ORIGINAL_UNIT", "0", "ORIGINAL_UNIT"),
                        indexed("ORIGINAL_UNIT", "1", "ORIGINAL_UNIT_1"),
                        indexed("ORIGINAL_UNIT", "2", "ORIGINAL_UNIT_2"),
                        indexed("ORIGINAL_UNIT", "3", "ORIGINAL_UNIT_3"),

                        // ORIGINAL_CURRENCY (4 years, indexed by year)
                        indexed("ORIGINAL_CURRENCY", "0", "ORIGINAL_CURRENCY_1"),
                        indexed("ORIGINAL_CURRENCY", "1", "ORIGINAL_CURRENCY_2"),
                        indexed("ORIGINAL_CURRENCY", "2", "ORIGINAL_CURRENCY_3"),
                        indexed("ORIGINAL_CURRENCY", "3", "ORIGINAL_CURRENCY_4")
                ))
                .build();
    }

    // ──────────────────────────────────────────────────────────────────────
    // Factory: Ownership
    // ──────────────────────────────────────────────────────────────────────

    public static OrbisDataRequest forOwnership(String bvdId) {
        return OrbisDataRequest.builder()
                .where(List.of(Map.of("BvDID", List.of(bvdId))))
                .select(buildSelectList(
                        // Basic
                        simple("NAME"),
                        filtered("CORPORATE_GROUP_SIZE_LABEL", GUO_FILTERS, "CORPORATE_GROUP_SIZE_LABEL"),
                        filtered("CORPORATE_ENTITY_TYPE", GUO_FILTERS, "CORPORATE_ENTITY_TYPE"),
                        filtered("BO_STATUS", BO_FILTERS, "BO_STATUS"),
                        filtered("BO_DEFINITION_10_10_COUNT", BO_10_10_FILTERS, "BO_DEFINITION_10_10_COUNT"),
                        filtered("BO_DEFINITION_10_50_COUNT", BO_10_50_FILTERS, "BO_DEFINITION_10_50_COUNT"),
                        filtered("BO_DEFINITION_25_25_COUNT", BO_25_25_FILTERS, "BO_DEFINITION_25_25_COUNT"),

                        // BO fields (Beneficial Owners)
                        filtered("BO_NAME", BO_FILTERS, "BO_NAME"),
                        filtered("BO_BVD_ID_NUMBER", BO_FILTERS, "BO_BVD_ID_NUMBER"),
                        filtered("BO_UCI", BO_FILTERS, "BO_UCI"),
                        filtered("BO_FIRST_NAME", BO_FILTERS, "BO_FIRST_NAME"),
                        filtered("BO_LAST_NAME", BO_FILTERS, "BO_LAST_NAME"),
                        filtered("BO_ADDRESS", BO_FILTERS, "BO_ADDRESS"),
                        filtered("BO_CITY", BO_FILTERS, "BO_CITY"),
                        filtered("BO_COUNTRY_ISO_CODE", BO_FILTERS, "BO_COUNTRY_ISO_CODE"),
                        filtered("BO_PHONE_NUMBER", BO_FILTERS, "BO_PHONE_NUMBER"),
                        filtered("BO_EMAIL", BO_FILTERS, "BO_EMAIL"),
                        filtered("BO_POSSIBLE_PCT_CHANGE_DESCRIPTION", BO_FILTERS, "BO_POSSIBLE_PCT_CHANGE_DESCRIPTION"),
                        filtered("BO_GRID_MATCH_INDICATOR_FORMATTED", BO_FILTERS, "BO_GRID_MATCH_INDICATOR_FORMATTED"),
                        filtered("BO_GRID_MATCH_SANCTIONS_INDICATOR_FORMATTED", BO_FILTERS, "BO_GRID_MATCH_SANCTIONS_INDICATOR_FORMATTED"),
                        filtered("BO_GRID_MATCH_WATCHLIST_INDICATOR_FORMATTED", BO_FILTERS, "BO_GRID_MATCH_WATCHLIST_INDICATOR_FORMATTED"),
                        filtered("BO_GRID_MATCH_PEP_INDICATOR_FORMATTED", BO_FILTERS, "BO_GRID_MATCH_PEP_INDICATOR_FORMATTED"),
                        filtered("BO_GRID_MATCH_MEDIA_INDICATOR_FORMATTED", BO_FILTERS, "BO_GRID_MATCH_MEDIA_INDICATOR_FORMATTED"),

                        // OUB fields (Ownership Ultimate Beneficial)
                        filtered("OUB_NAME", BO_FILTERS, "OUB_NAME"),
                        filtered("OUB_BVD_ID_NUMBER", BO_FILTERS, "OUB_BVD_ID_NUMBER"),
                        filtered("OUB_ENTITY_TYPE", BO_FILTERS, "OUB_ENTITY_TYPE"),
                        filteredLimited("OUB_ADDRESS", BO_FILTERS, 1, "OUB_ADDRESS"),
                        filtered("OUB_CITY", BO_FILTERS, "OUB_CITY"),
                        filtered("OUB_COUNTRY_ISO_CODE", BO_FILTERS, "OUB_COUNTRY_ISO_CODE"),
                        filtered("OUB_PHONE_NUMBER", BO_FILTERS, "OUB_PHONE_NUMBER"),
                        filtered("OUB_WEBSITE", BO_FILTERS, "OUB_WEBSITE"),
                        filtered("OUB_EMAIL", BO_FILTERS, "OUB_EMAIL"),
                        filtered("OUB_POSSIBLE_PCT_CHANGE_DESCRIPTION", BO_FILTERS, "OUB_POSSIBLE_PCT_CHANGE_DESCRIPTION"),

                        // BOI fields (Beneficial Owner Intermediaries)
                        filtered("BOI_NAME", BO_FILTERS, "BOI_NAME"),
                        filtered("BOI_BVD_ID_NUMBER", BO_FILTERS, "BOI_BVD_ID_NUMBER"),
                        filtered("BOI_UCI", BO_FILTERS, "BOI_UCI"),
                        filtered("BOI_ENTITY_TYPE", BO_FILTERS, "BOI_ENTITY_TYPE"),
                        filtered("BOI_ADDRESS", BO_FILTERS, "BOI_ADDRESS"),
                        filtered("BOI_CITY", BO_FILTERS, "BOI_CITY"),
                        filtered("BOI_COUNTRY_ISO_CODE", BO_FILTERS, "BOI_COUNTRY_ISO_CODE"),
                        filtered("BOI_PHONE_NUMBER", BO_FILTERS, "BOI_PHONE_NUMBER"),
                        filtered("BOI_WEBSITE", BO_FILTERS, "BOI_WEBSITE"),
                        filtered("BOI_EMAIL", BO_FILTERS, "BOI_EMAIL"),
                        filtered("BOI_DIRECT_PCT", BO_FILTERS, "BOI_DIRECT_PCT"),
                        filtered("BOI_TOTAL_PCT", BO_FILTERS, "BOI_TOTAL_PCT"),
                        filtered("BOI_POSSIBLE_PCT_CHANGE_DESCRIPTION", BO_FILTERS, "BOI_POSSIBLE_PCT_CHANGE_DESCRIPTION"),

                        // SH fields (Shareholders)
                        filtered("SH_COUNT", SH_FILTERS, "SH_COUNT"),
                        filtered("SH_NAME", SH_FILTERS, "SH_NAME"),
                        filtered("SH_BVD_ID_NUMBER", SH_FILTERS, "SH_BVD_ID_NUMBER"),
                        filtered("SH_UCI", SH_FILTERS, "SH_UCI"),
                        filtered("SH_LEI", SH_FILTERS, "SH_LEI"),
                        filtered("SH_COUNTRY_ISO_CODE", SH_FILTERS, "SH_COUNTRY_ISO_CODE"),
                        simple("SH_TERRITORY_ISO_CODE"),
                        filtered("SH_STATE_PROVINCE", SH_FILTERS, "SH_STATE_PROVINCE"),
                        filtered("SH_CITY", SH_FILTERS, "SH_CITY"),
                        filtered("SH_ENTITY_TYPE", SH_FILTERS, "SH_ENTITY_TYPE"),
                        filtered("SH_NACE_CORE_CODE", SH_FILTERS, "SH_NACE_CORE_CODE"),
                        filtered("SH_NACE_CORE_LABEL", SH_FILTERS, "SH_NACE_CORE_LABEL"),
                        filtered("SH_DIRECT_PCT", SH_FILTERS, "SH_DIRECT_PCT"),
                        filtered("SH_TOTAL_PCT", SH_FILTERS, "SH_TOTAL_PCT"),
                        filtered("SH_POSSIBLE_PCT_CHANGE_DESCRIPTION", SH_FILTERS, "SH_POSSIBLE_PCT_CHANGE_DESCRIPTION"),
                        filteredFinancial("SH_OPRE", SH_FILTERS, "SESSION", 6, "SH_OPRE"),
                        filteredFinancial("SH_TOAS", SH_FILTERS, "SESSION", 6, "SH_TOAS"),
                        filteredFinancial("SH_EMPL", SH_FILTERS, "SESSION", 0, "SH_EMPL"),
                        filtered("SH_GRID_MATCH_INDICATOR_FORMATTED", SH_FILTERS, "SH_GRID_MATCH_INDICATOR_FORMATTED"),
                        filtered("SH_GRID_MATCH_SANCTIONS_INDICATOR_FORMATTED", SH_FILTERS, "SH_GRID_MATCH_SANCTIONS_INDICATOR_FORMATTED"),
                        filtered("SH_GRID_MATCH_WATCHLIST_INDICATOR_FORMATTED", SH_FILTERS, "SH_GRID_MATCH_WATCHLIST_INDICATOR_FORMATTED"),
                        filtered("SH_GRID_MATCH_PEP_INDICATOR_FORMATTED", SH_FILTERS, "SH_GRID_MATCH_PEP_INDICATOR_FORMATTED"),
                        filtered("SH_GRID_MATCH_MEDIA_INDICATOR_FORMATTED", SH_FILTERS, "SH_GRID_MATCH_MEDIA_INDICATOR_FORMATTED"),

                        // GUO fields (Global Ultimate Owner)
                        filtered("GUO_NAME", GUO_FILTERS, "GUO_NAME"),
                        filtered("GUO_FIRST_NAME", GUO_FILTERS, "GUO_FIRST_NAME"),
                        filtered("GUO_LAST_NAME", GUO_FILTERS, "GUO_LAST_NAME"),
                        filtered("GUO_BVD_ID_NUMBER", GUO_FILTERS, "GUO_BVD_ID_NUMBER"),
                        filtered("GUO_UCI", GUO_FILTERS, "GUO_UCI"),
                        filtered("GUO_LEI", GUO_FILTERS, "GUO_LEI"),
                        filtered("GUO_COUNTRY_ISO_CODE", GUO_FILTERS, "GUO_COUNTRY_ISO_CODE"),
                        filtered("GUO_STATE_PROVINCE", GUO_FILTERS, "GUO_STATE_PROVINCE"),
                        filtered("GUO_CITY", GUO_FILTERS, "GUO_CITY"),
                        filtered("GUO_ENTITY_TYPE", GUO_FILTERS, "GUO_ENTITY_TYPE"),
                        filtered("GUO_NACE_CORE_CODE", GUO_FILTERS, "GUO_NACE_CORE_CODE"),
                        filtered("GUO_NACE_CORE_LABEL", GUO_FILTERS, "GUO_NACE_CORE_LABEL"),
                        filtered("GUO_DIRECT_PCT", GUO_FILTERS, "GUO_DIRECT_PCT"),
                        filtered("GUO_TOTAL_PCT", GUO_FILTERS, "GUO_TOTAL_PCT"),
                        filteredFinancial("GUO_OPRE", GUO_FILTERS, "SESSION", 6, "GUO_OPRE"),
                        filteredFinancial("GUO_TOAS", GUO_FILTERS, "SESSION", 6, "GUO_TOAS"),
                        filteredFinancial("GUO_EMPL", GUO_FILTERS, "SESSION", 0, "GUO_EMPL"),
                        filtered("GUO_GRID_MATCH_INDICATOR_FORMATTED", GUO_FILTERS, "GUO_GRID_MATCH_INDICATOR_FORMATTED"),
                        filtered("GUO_GRID_MATCH_SANCTIONS_INDICATOR_FORMATTED", GUO_FILTERS, "GUO_GRID_MATCH_SANCTIONS_INDICATOR_FORMATTED"),
                        filtered("GUO_GRID_MATCH_WATCHLIST_INDICATOR_FORMATTED", GUO_FILTERS, "GUO_GRID_MATCH_WATCHLIST_INDICATOR_FORMATTED"),
                        filtered("GUO_GRID_MATCH_PEP_INDICATOR_FORMATTED", GUO_FILTERS, "GUO_GRID_MATCH_PEP_INDICATOR_FORMATTED"),
                        filtered("GUO_GRID_MATCH_MEDIA_INDICATOR_FORMATTED", GUO_FILTERS, "GUO_GRID_MATCH_MEDIA_INDICATOR_FORMATTED"),

                        // SUB fields (Subsidiaries)
                        filtered("SUB_COUNT", SUB_FILTERS, "SUB_COUNT"),
                        filtered("SUB_NAME", SUB_FILTERS, "SUB_NAME"),
                        filtered("SUB_BVD_ID_NUMBER", SUB_FILTERS, "SUB_BVD_ID_NUMBER"),
                        filtered("SUB_LEI", SUB_FILTERS, "SUB_LEI"),
                        filtered("SUB_COUNTRY_ISO_CODE", SUB_FILTERS, "SUB_COUNTRY_ISO_CODE"),
                        filtered("SUB_STATE_PROVINCE", SUB_FILTERS, "SUB_STATE_PROVINCE"),
                        filtered("SUB_CITY", SUB_FILTERS, "SUB_CITY"),
                        filtered("SUB_ENTITY_TYPE", SUB_FILTERS, "SUB_ENTITY_TYPE"),
                        filtered("SUB_NACE_CORE_CODE", SUB_FILTERS, "SUB_NACE_CORE_CODE"),
                        filtered("SUB_NACE_CORE_LABEL", SUB_FILTERS, "SUB_NACE_CORE_LABEL"),
                        filtered("SUB_DIRECT_PCT", SUB_FILTERS, "SUB_DIRECT_PCT"),
                        filtered("SUB_TOTAL_PCT", SUB_FILTERS, "SUB_TOTAL_PCT"),
                        filtered("SUB_POSSIBLE_PCT_CHANGE_DESCRIPTION", SUB_FILTERS, "SUB_POSSIBLE_PCT_CHANGE_DESCRIPTION"),
                        filteredFinancial("SUB_OPRE", SUB_FILTERS, "SESSION", 6, "SUB_OPRE"),
                        filteredFinancial("SUB_TOAS", SUB_FILTERS, "SESSION", 6, "SUB_TOAS"),
                        filteredFinancial("SUB_EMPL", SUB_FILTERS, "SESSION", 0, "SUB_EMPL"),
                        filtered("SUB_GRID_MATCH_INDICATOR_FORMATTED", SUB_FILTERS, "SUB_GRID_MATCH_INDICATOR_FORMATTED"),
                        filtered("SUB_GRID_MATCH_SANCTIONS_INDICATOR_FORMATTED", SUB_FILTERS, "SUB_GRID_MATCH_SANCTIONS_INDICATOR_FORMATTED"),
                        filtered("SUB_GRID_MATCH_WATCHLIST_INDICATOR_FORMATTED", SUB_FILTERS, "SUB_GRID_MATCH_WATCHLIST_INDICATOR_FORMATTED"),
                        filtered("SUB_GRID_MATCH_PEP_INDICATOR_FORMATTED", SUB_FILTERS, "SUB_GRID_MATCH_PEP_INDICATOR_FORMATTED"),
                        filtered("SUB_GRID_MATCH_MEDIA_INDICATOR_FORMATTED", SUB_FILTERS, "SUB_GRID_MATCH_MEDIA_INDICATOR_FORMATTED")
                ))
                .build();
    }

    // ──────────────────────────────────────────────────────────────────────
    // Factory: Directors / Contacts
    // ──────────────────────────────────────────────────────────────────────

    public static OrbisDataRequest forDirectors(String bvdId) {
        return OrbisDataRequest.builder()
                .where(List.of(Map.of("BvDID", List.of(bvdId))))
                .select(buildSelectList(
                        simple("NAME"),

                        // Count fields
                        filtered("CPYCONTACTS_MEMBERSHIP_DIFFERENT_PERSONS_CNT", CONTACTS_FILTERS, "CPYCONTACTS_MEMBERSHIP_DIFFERENT_PERSONS_CNT"),
                        filtered("CPYCONTACTS_MEMBERSHIP_DIFFERENT_PERSONS_CNT", CONTACTS_CURRENTS_FILTERS, "CPYCONTACTS_MEMBERSHIP_DIFFERENT_PERSONS_CNT_1"),

                        // Contact detail fields (CONTACTS_BASE_FILTERS)
                        filtered("CPYCONTACTS_HEADER_FullNameOriginalLanguagePreferred", CONTACTS_BASE_FILTERS, "CPYCONTACTS_HEADER_FullNameOriginalLanguagePreferred"),
                        filtered("CPYCONTACTS_HEADER_IdDirector", CONTACTS_BASE_FILTERS, "CPYCONTACTS_HEADER_IdDirector"),
                        filtered("CPYCONTACTS_HEADER_BvdId", CONTACTS_BASE_FILTERS, "CPYCONTACTS_HEADER_BvdId"),
                        filtered("CPYCONTACTS_MEMBERSHIP_Function", CONTACTS_BASE_FILTERS, "CPYCONTACTS_MEMBERSHIP_Function"),
                        filtered("CPYCONTACTS_MEMBERSHIP_BeginningNominationDate", CONTACTS_BASE_FILTERS, "CPYCONTACTS_MEMBERSHIP_BeginningNominationDate"),
                        filtered("CPYCONTACTS_MEMBERSHIP_EndExpirationDate", CONTACTS_BASE_FILTERS, "CPYCONTACTS_MEMBERSHIP_EndExpirationDate"),
                        filtered("CPYCONTACTS_HEADER_GRID_MATCH_INDICATOR", CONTACTS_BASE_FILTERS, "CPYCONTACTS_HEADER_GRID_MATCH_INDICATOR"),
                        filtered("CPYCONTACTS_HEADER_GRID_MATCH_SANCTIONS_INDICATOR", CONTACTS_BASE_FILTERS, "CPYCONTACTS_HEADER_GRID_MATCH_SANCTIONS_INDICATOR"),
                        filtered("CPYCONTACTS_HEADER_GRID_MATCH_WATCHLIST_INDICATOR", CONTACTS_BASE_FILTERS, "CPYCONTACTS_HEADER_GRID_MATCH_WATCHLIST_INDICATOR"),
                        filtered("CPYCONTACTS_HEADER_GRID_MATCH_PEP_INDICATOR", CONTACTS_BASE_FILTERS, "CPYCONTACTS_HEADER_GRID_MATCH_PEP_INDICATOR"),
                        filtered("CPYCONTACTS_HEADER_GRID_MATCH_MEDIA_INDICATOR", CONTACTS_BASE_FILTERS, "CPYCONTACTS_HEADER_GRID_MATCH_MEDIA_INDICATOR"),
                        filtered("CPYCONTACTS_HEADER_BareTitle", CONTACTS_BASE_FILTERS, "CPYCONTACTS_HEADER_BareTitle"),
                        filtered("CPYCONTACTS_HEADER_FirstNameOriginalLanguagePreferred", CONTACTS_BASE_FILTERS, "CPYCONTACTS_HEADER_FirstNameOriginalLanguagePreferred"),
                        filtered("CPYCONTACTS_HEADER_MiddleNameOriginalLanguagePreferred", CONTACTS_BASE_FILTERS, "CPYCONTACTS_HEADER_MiddleNameOriginalLanguagePreferred"),
                        filtered("CPYCONTACTS_HEADER_LastNameOriginalLanguagePreferred", CONTACTS_BASE_FILTERS, "CPYCONTACTS_HEADER_LastNameOriginalLanguagePreferred"),
                        filtered("CPYCONTACTS_HEADER_MultipleNationalitiesLabel", CONTACTS_BASE_FILTERS, "CPYCONTACTS_HEADER_MultipleNationalitiesLabel"),
                        filtered("CPYCONTACTS_MEMBERSHIP_BoardMnemonic", CONTACTS_BASE_FILTERS, "CPYCONTACTS_MEMBERSHIP_BoardMnemonic"),
                        filtered("CPYCONTACTS_MEMBERSHIP_DepartmentFromHierCodeFall2009", CONTACTS_BASE_FILTERS, "CPYCONTACTS_MEMBERSHIP_DepartmentFromHierCodeFall2009"),
                        filtered("CPYCONTACTS_MEMBERSHIP_LevelFromHierCodeFall2009", CONTACTS_BASE_FILTERS, "CPYCONTACTS_MEMBERSHIP_LevelFromHierCodeFall2009"),

                        // Work address fields (CONTACTS_WORK_ADDRESS_FILTERS)
                        filtered("CPYCONTACTS_MEMBERSHIP_WorkFullAddress", CONTACTS_WORK_ADDRESS_FILTERS, "CPYCONTACTS_MEMBERSHIP_WorkFullAddress"),
                        filtered("CPYCONTACTS_HEADER_CountryLabel", CONTACTS_WORK_ADDRESS_FILTERS, "CPYCONTACTS_HEADER_CountryLabel"),
                        filtered("CPYCONTACTS_HEADER_Email", CONTACTS_WORK_ADDRESS_FILTERS, "CPYCONTACTS_HEADER_Email")
                ))
                .build();
    }
}
