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
public class OrbisFinancialsResponse {

    @JsonProperty("Data")
    private List<OrbisFinancialsData> data;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class OrbisFinancialsData {

        // ── Metadata ──

        @JsonProperty("NAME")
        private String name;

        @JsonProperty("CONSOLIDATION_CODE")
        private String consolidationCode;

        @JsonProperty("FILING_TYPE")
        private String filingType;

        @JsonProperty("CLOSING_DATE_LAST_ANNUAL_ACCOUNTS")
        private String closingDateLastAnnualAccounts;

        @JsonProperty("CLOSING_DATE_LAST_INTERIM_ACCOUNTS")
        private String closingDateLastInterimAccounts;

        @JsonProperty("YEAR_LAST_ACCOUNTS")
        private String yearLastAccounts;

        @JsonProperty("ORIGINAL_CURRENCY")
        private String originalCurrency;

        @JsonProperty("ACCOUNTING_TEMPLATE")
        private String accountingTemplate;

        @JsonProperty("NUMBER_YEARS")
        private Integer numberYears;

        @JsonProperty("LEGAL_EVENTS_DATE")
        private List<String> legalEventsDate;

        @JsonProperty("LEGAL_EVENTS_DESCRIPTION")
        private List<String> legalEventsDescription;

        // ── OPRE (Operating Revenue) ──

        @JsonProperty("OPRE")
        private Double opre;

        @JsonProperty("OPRE_1")
        private Double opre1;

        @JsonProperty("OPRE_2")
        private Double opre2;

        @JsonProperty("OPRE_3")
        private Double opre3;

        @JsonProperty("OPRE_4")
        private Double opre4;

        @JsonProperty("OPRE_5")
        private Double opre5;

        @JsonProperty("OPRE_6")
        private Double opre6;

        @JsonProperty("OPRE_7")
        private Double opre7;

        // ── PLBT (Profit/Loss Before Tax) ──

        @JsonProperty("PLBT")
        private Double plbt;

        @JsonProperty("PLBT_1")
        private Double plbt1;

        @JsonProperty("PLBT_2")
        private Double plbt2;

        @JsonProperty("PLBT_3")
        private Double plbt3;

        @JsonProperty("PLBT_4")
        private Double plbt4;

        @JsonProperty("PLBT_5")
        private Double plbt5;

        @JsonProperty("PLBT_6")
        private Double plbt6;

        @JsonProperty("PLBT_7")
        private Double plbt7;

        // ── PL (Profit/Loss) ──

        @JsonProperty("PL")
        private Double pl;

        @JsonProperty("PL_1")
        private Double pl1;

        @JsonProperty("PL_2")
        private Double pl2;

        @JsonProperty("PL_3")
        private Double pl3;

        @JsonProperty("PL_4")
        private Double pl4;

        @JsonProperty("PL_5")
        private Double pl5;

        @JsonProperty("PL_6")
        private Double pl6;

        @JsonProperty("PL_7")
        private Double pl7;

        // ── CF (Cash Flow) ──

        @JsonProperty("CF")
        private Double cf;

        @JsonProperty("CF_1")
        private Double cf1;

        @JsonProperty("CF_2")
        private Double cf2;

        @JsonProperty("CF_3")
        private Double cf3;

        @JsonProperty("CF_4")
        private Double cf4;

        @JsonProperty("CF_5")
        private Double cf5;

        @JsonProperty("CF_6")
        private Double cf6;

        @JsonProperty("CF_7")
        private Double cf7;

        // ── TOAS (Total Assets) ──

        @JsonProperty("TOAS")
        private Double toas;

        @JsonProperty("TOAS_1")
        private Double toas1;

        @JsonProperty("TOAS_2")
        private Double toas2;

        @JsonProperty("TOAS_3")
        private Double toas3;

        @JsonProperty("TOAS_4")
        private Double toas4;

        @JsonProperty("TOAS_5")
        private Double toas5;

        @JsonProperty("TOAS_6")
        private Double toas6;

        @JsonProperty("TOAS_7")
        private Double toas7;

        // ── SHFD (Shareholders Funds) ──

        @JsonProperty("SHFD")
        private Double shfd;

        @JsonProperty("SHFD_1")
        private Double shfd1;

        @JsonProperty("SHFD_2")
        private Double shfd2;

        @JsonProperty("SHFD_3")
        private Double shfd3;

        @JsonProperty("SHFD_4")
        private Double shfd4;

        @JsonProperty("SHFD_5")
        private Double shfd5;

        @JsonProperty("SHFD_6")
        private Double shfd6;

        @JsonProperty("SHFD_7")
        private Double shfd7;

        // ── EXCHANGE_RATE ──

        @JsonProperty("EXCHANGE_RATE")
        private Double exchangeRate;

        @JsonProperty("EXCHANGE_RATE_1")
        private Double exchangeRate1;

        @JsonProperty("EXCHANGE_RATE_2")
        private Double exchangeRate2;

        @JsonProperty("EXCHANGE_RATE_3")
        private Double exchangeRate3;

        @JsonProperty("EXCHANGE_RATE_4")
        private Double exchangeRate4;

        @JsonProperty("EXCHANGE_RATE_5")
        private Double exchangeRate5;

        @JsonProperty("EXCHANGE_RATE_6")
        private Double exchangeRate6;

        @JsonProperty("EXCHANGE_RATE_7")
        private Double exchangeRate7;

        // ── CURR (Current Ratio) ──

        @JsonProperty("CURR")
        private Double curr;

        @JsonProperty("CURR_1")
        private Double curr1;

        @JsonProperty("CURR_2")
        private Double curr2;

        @JsonProperty("CURR_3")
        private Double curr3;

        // ── PRMA (Profit Margin) ──

        @JsonProperty("PRMA")
        private Double prma;

        @JsonProperty("PRMA_1")
        private Double prma1;

        @JsonProperty("PRMA_2")
        private Double prma2;

        @JsonProperty("PRMA_3")
        private Double prma3;

        // ── RSHF (Return on Shareholders Funds) ──

        @JsonProperty("RSHF")
        private Double rshf;

        @JsonProperty("RSHF_1")
        private Double rshf1;

        @JsonProperty("RSHF_2")
        private Double rshf2;

        @JsonProperty("RSHF_3")
        private Double rshf3;

        // ── RCEM (Return on Capital Employed) ──

        @JsonProperty("RCEM")
        private Double rcem;

        @JsonProperty("RCEM_1")
        private Double rcem1;

        @JsonProperty("RCEM_2")
        private Double rcem2;

        @JsonProperty("RCEM_3")
        private Double rcem3;

        // ── SOLR (Solvency Ratio) ──

        @JsonProperty("SOLR")
        private Double solr;

        @JsonProperty("SOLR_1")
        private Double solr1;

        @JsonProperty("SOLR_2")
        private Double solr2;

        @JsonProperty("SOLR_3")
        private Double solr3;

        // ── EMPL (Employees) ──

        @JsonProperty("EMPL")
        private Double empl;

        @JsonProperty("EMPL_1")
        private Double empl1;

        @JsonProperty("EMPL_2")
        private Double empl2;

        @JsonProperty("EMPL_3")
        private Double empl3;

        // ── CLOSING_DATE ──

        @JsonProperty("CLOSING_DATE")
        private String closingDate;

        @JsonProperty("CLOSING_DATE_1")
        private String closingDate1;

        @JsonProperty("CLOSING_DATE_2")
        private String closingDate2;

        @JsonProperty("CLOSING_DATE_3")
        private String closingDate3;

        // ── FISCAL_YEAR ──

        @JsonProperty("FISCAL_YEAR")
        private String fiscalYear;

        @JsonProperty("FISCAL_YEAR_1")
        private String fiscalYear1;

        @JsonProperty("FISCAL_YEAR_2")
        private String fiscalYear2;

        @JsonProperty("FISCAL_YEAR_3")
        private String fiscalYear3;

        // ── QUARTER_YEAR ──

        @JsonProperty("QUARTER_YEAR")
        private String quarterYear;

        @JsonProperty("QUARTER_YEAR_1")
        private String quarterYear1;

        @JsonProperty("QUARTER_YEAR_2")
        private String quarterYear2;

        @JsonProperty("QUARTER_YEAR_3")
        private String quarterYear3;

        // ── NUMBER_MONTHS ──

        @JsonProperty("NUMBER_MONTHS")
        private String numberMonths;

        @JsonProperty("NUMBER_MONTHS_1")
        private String numberMonths1;

        @JsonProperty("NUMBER_MONTHS_2")
        private String numberMonths2;

        @JsonProperty("NUMBER_MONTHS_3")
        private String numberMonths3;

        // ── AUDIT_STATUS ──

        @JsonProperty("AUDIT_STATUS")
        private String auditStatus;

        @JsonProperty("AUDIT_STATUS_1")
        private String auditStatus1;

        @JsonProperty("AUDIT_STATUS_2")
        private String auditStatus2;

        @JsonProperty("AUDIT_STATUS_3")
        private String auditStatus3;

        // ── ACCOUNTS_STATUS ──

        @JsonProperty("ACCOUNTS_STATUS")
        private String accountsStatus;

        @JsonProperty("ACCOUNTS_STATUS_1")
        private String accountsStatus1;

        @JsonProperty("ACCOUNTS_STATUS_2")
        private String accountsStatus2;

        @JsonProperty("ACCOUNTS_STATUS_3")
        private String accountsStatus3;

        // ── ACCOUNTING_PRACTICE ──

        @JsonProperty("ACCOUNTING_PRACTICE")
        private String accountingPractice;

        @JsonProperty("ACCOUNTING_PRACTICE_1")
        private String accountingPractice1;

        @JsonProperty("ACCOUNTING_PRACTICE_2")
        private String accountingPractice2;

        @JsonProperty("ACCOUNTING_PRACTICE_3")
        private String accountingPractice3;

        // ── ACCOUNTS_SOURCE_CODE ──

        @JsonProperty("ACCOUNTS_SOURCE_CODE")
        private String accountsSourceCode;

        @JsonProperty("ACCOUNTS_SOURCE_CODE_1")
        private String accountsSourceCode1;

        @JsonProperty("ACCOUNTS_SOURCE_CODE_2")
        private String accountsSourceCode2;

        @JsonProperty("ACCOUNTS_SOURCE_CODE_3")
        private String accountsSourceCode3;

        // ── ORIGINAL_UNIT ──

        @JsonProperty("ORIGINAL_UNIT")
        private String originalUnit;

        @JsonProperty("ORIGINAL_UNIT_1")
        private String originalUnit1;

        @JsonProperty("ORIGINAL_UNIT_2")
        private String originalUnit2;

        @JsonProperty("ORIGINAL_UNIT_3")
        private String originalUnit3;

        // ── ORIGINAL_CURRENCY (periodic variants 1-4) ──

        @JsonProperty("ORIGINAL_CURRENCY_1")
        private String originalCurrency1;

        @JsonProperty("ORIGINAL_CURRENCY_2")
        private String originalCurrency2;

        @JsonProperty("ORIGINAL_CURRENCY_3")
        private String originalCurrency3;

        @JsonProperty("ORIGINAL_CURRENCY_4")
        private String originalCurrency4;
    }
}
