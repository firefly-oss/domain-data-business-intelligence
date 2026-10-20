package io.fireflysoftware.data.business.intelligence.infra.services;

import org.fireflyframework.data.enrichment.EnricherMetadata;
import org.fireflyframework.data.event.EnrichmentEventPublisher;
import org.fireflyframework.data.model.EnrichmentRequest;
import org.fireflyframework.data.observability.JobMetricsService;
import org.fireflyframework.data.observability.JobTracingService;
import org.fireflyframework.data.resiliency.ResiliencyDecoratorService;
import org.fireflyframework.data.service.DataEnricher;
import io.fireflysoftware.data.business.intelligence.infra.dtos.BusinessReportDTO;
import io.fireflysoftware.data.business.intelligence.infra.dtos.BusinessReportDTO.*;
import io.fireflysoftware.data.business.intelligence.infra.dtos.orbis.OrbisCompositeResponse;
import io.fireflysoftware.data.business.intelligence.infra.dtos.orbis.OrbisDataResponse;
import io.fireflysoftware.data.business.intelligence.infra.dtos.orbis.OrbisDirectorsResponse;
import io.fireflysoftware.data.business.intelligence.infra.dtos.orbis.OrbisFinancialsResponse;
import io.fireflysoftware.data.business.intelligence.infra.dtos.orbis.OrbisOwnershipResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Component
@EnricherMetadata(
    providerName = "Orbis BvD",
    tenantId = "efdd318a-d07a-45a6-bfbf-a9922f696c6f",
    type = "company-details",
    priority = 100
)
public class OrbisEnricher extends DataEnricher<BusinessReportDTO, OrbisCompositeResponse, BusinessReportDTO> {

    private final OrbisService orbisService;

    public OrbisEnricher(
            JobTracingService tracingService,
            JobMetricsService metricsService,
            ResiliencyDecoratorService resiliencyService,
            EnrichmentEventPublisher eventPublisher,
            OrbisService orbisService) {
        super(tracingService, metricsService, resiliencyService, eventPublisher, BusinessReportDTO.class);
        this.orbisService = orbisService;
    }

    @Override
    protected Mono<OrbisCompositeResponse> fetchProviderData(EnrichmentRequest request) {
        String bvdId = (String) request.requireParam("bvdId");
        String domain = (String) request.getParameters().get("domain");
        return Mono.zip(
            orbisService.getCompanyData(bvdId, domain),
            orbisService.getFinancials(bvdId, domain),
            orbisService.getOwnership(bvdId, domain),
            orbisService.getDirectors(bvdId, domain)
        ).map(tuple -> OrbisCompositeResponse.builder()
            .companyData(tuple.getT1())
            .financials(tuple.getT2())
            .ownership(tuple.getT3())
            .directors(tuple.getT4())
            .build());
    }

    @Override
    protected BusinessReportDTO mapToTarget(OrbisCompositeResponse response) {
        if (response == null) return null;
        return BusinessReportDTO.builder()
            .provider(buildProviderInfo())
            .company(mapCompanyProfile(response.getCompanyData()))
            .financials(mapFinancialSummary(response.getFinancials()))
            .ownership(mapOwnershipStructure(response.getOwnership()))
            .directors(mapDirectors(response.getDirectors()))
            .build();
    }

    // ── Provider Info ──

    private ProviderInfoDTO buildProviderInfo() {
        return ProviderInfoDTO.builder()
            .providerName("Orbis BvD")
            .reportType("BUSINESS_INTELLIGENCE")
            .build();
    }

    // ── Company Profile ──

    private CompanyProfileDTO mapCompanyProfile(OrbisDataResponse response) {
        if (response == null || response.getData() == null || response.getData().isEmpty()) {
            return null;
        }
        var data = response.getData().get(0);

        return CompanyProfileDTO.builder()
            .name(data.getName())
            .identifier(data.getBvdIdNumber())
            .address(data.getAddressLine1())
            .postcode(data.getPostcode())
            .city(data.getCity())
            .country(data.getCountry())
            .countryIsoCode(data.getCountryIsoCode())
            .latitude(data.getLatitude())
            .longitude(data.getLongitude())
            .region(data.getNuts1())
            .worldRegion(data.getWorldRegion())
            .phones(data.getPhoneNumber())
            .emails(data.getEmail())
            .websites(data.getWebsite())
            .tradeDescription(data.getTradeDescriptionEn())
            .primaryBusinessLine(data.getOverviewPrimaryBusinessLine())
            .productsServices(data.getProductsServices())
            .operationalSize(data.getOpgsize())
            .industryClassifications(buildIndustryClassifications(data))
            .legalIdentifiers(buildLegalIdentifiers(data))
            .compliance(buildCompanyCompliance(data))
            .build();
    }

    private List<IndustryCodeDTO> buildIndustryClassifications(OrbisDataResponse.OrbisCompanyData data) {
        List<IndustryCodeDTO> classifications = new ArrayList<>();

        if (data.getNace2CoreCode() != null) {
            classifications.add(IndustryCodeDTO.builder()
                .system("NACE2").code(data.getNace2CoreCode()).label(data.getNace2MainSection()).build());
        }
        if (data.getNace2PrimaryCode() != null) {
            for (String code : data.getNace2PrimaryCode()) {
                classifications.add(IndustryCodeDTO.builder()
                    .system("NACE2_PRIMARY").code(code).build());
            }
        }
        if (data.getNaics2022CoreCode() != null) {
            classifications.add(IndustryCodeDTO.builder()
                .system("NAICS2022").code(data.getNaics2022CoreCode()).label(data.getNaics2022CoreLabel()).build());
        }
        if (data.getUssicPrimaryCode() != null) {
            for (int i = 0; i < data.getUssicPrimaryCode().size(); i++) {
                classifications.add(IndustryCodeDTO.builder()
                    .system("USSIC")
                    .code(data.getUssicPrimaryCode().get(i))
                    .label(safeGet(data.getUssicPrimaryLabel(), i))
                    .build());
            }
        }

        return classifications.isEmpty() ? null : classifications;
    }

    private List<LegalIdentifierDTO> buildLegalIdentifiers(OrbisDataResponse.OrbisCompanyData data) {
        List<LegalIdentifierDTO> identifiers = new ArrayList<>();

        if (data.getNationalId() != null) {
            for (int i = 0; i < data.getNationalId().size(); i++) {
                identifiers.add(LegalIdentifierDTO.builder()
                    .type("NATIONAL_ID")
                    .value(data.getNationalId().get(i))
                    .label(safeGet(data.getNationalIdLabel(), i))
                    .build());
            }
        }
        if (data.getVatNumber() != null) {
            for (String vat : data.getVatNumber()) {
                identifiers.add(LegalIdentifierDTO.builder().type("VAT").value(vat).build());
            }
        }
        if (data.getLei() != null) {
            identifiers.add(LegalIdentifierDTO.builder().type("LEI").value(data.getLei()).build());
        }
        if (data.getIsin() != null) {
            identifiers.add(LegalIdentifierDTO.builder().type("ISIN").value(data.getIsin()).build());
        }
        if (data.getTin() != null) {
            identifiers.add(LegalIdentifierDTO.builder().type("TIN").value(data.getTin()).build());
        }
        if (data.getSwiftCode() != null) {
            identifiers.add(LegalIdentifierDTO.builder().type("SWIFT").value(data.getSwiftCode()).build());
        }
        if (data.getTradeRegisterNumber() != null) {
            for (String trn : data.getTradeRegisterNumber()) {
                identifiers.add(LegalIdentifierDTO.builder().type("TRADE_REGISTER_NUMBER").value(trn).build());
            }
        }

        return identifiers.isEmpty() ? null : identifiers;
    }

    private ComplianceScreeningDTO buildCompanyCompliance(OrbisDataResponse.OrbisCompanyData data) {
        if (data.getGridMatchIndicator() == null && data.getGridMatchSanctionsIndicator() == null
                && data.getGridMatchWatchlistIndicator() == null && data.getGridMatchPepIndicator() == null
                && data.getGridMatchMediaIndicator() == null) {
            return null;
        }
        return ComplianceScreeningDTO.builder()
            .matchIndicator(booleanToString(data.getGridMatchIndicator()))
            .sanctionsIndicator(booleanToString(data.getGridMatchSanctionsIndicator()))
            .watchlistIndicator(booleanToString(data.getGridMatchWatchlistIndicator()))
            .pepIndicator(booleanToString(data.getGridMatchPepIndicator()))
            .mediaIndicator(booleanToString(data.getGridMatchMediaIndicator()))
            .build();
    }

    // ── Financial Summary ──

    private FinancialSummaryDTO mapFinancialSummary(OrbisFinancialsResponse response) {
        if (response == null || response.getData() == null || response.getData().isEmpty()) {
            return null;
        }
        var data = response.getData().get(0);

        return FinancialSummaryDTO.builder()
            .currency(data.getOriginalCurrency())
            .consolidationCode(data.getConsolidationCode())
            .filingType(data.getFilingType())
            .closingDateLastAnnual(data.getClosingDateLastAnnualAccounts())
            .yearLastAccounts(data.getYearLastAccounts())
            .accountingTemplate(data.getAccountingTemplate())
            .periods(mapFinancialPeriods(data))
            .ratios(mapFinancialRatios(data))
            .legalEvents(mapLegalEvents(data))
            .build();
    }

    private List<FinancialPeriodDTO> mapFinancialPeriods(OrbisFinancialsResponse.OrbisFinancialsData data) {
        List<FinancialPeriodDTO> periods = new ArrayList<>();

        // Period 0 (current)
        addPeriodIfPresent(periods, data.getOpre(), data.getPlbt(), data.getPl(), data.getCf(),
            data.getToas(), data.getShfd(), data.getExchangeRate(), data.getEmpl(),
            data.getFiscalYear(), data.getClosingDate(), data.getNumberMonths(),
            data.getAuditStatus(), data.getAccountsStatus(), data.getAccountingPractice(),
            data.getOriginalCurrency1(), data.getOriginalUnit());

        // Period 1
        addPeriodIfPresent(periods, data.getOpre1(), data.getPlbt1(), data.getPl1(), data.getCf1(),
            data.getToas1(), data.getShfd1(), data.getExchangeRate1(), data.getEmpl1(),
            data.getFiscalYear1(), data.getClosingDate1(), data.getNumberMonths1(),
            data.getAuditStatus1(), data.getAccountsStatus1(), data.getAccountingPractice1(),
            data.getOriginalCurrency2(), data.getOriginalUnit1());

        // Period 2
        addPeriodIfPresent(periods, data.getOpre2(), data.getPlbt2(), data.getPl2(), data.getCf2(),
            data.getToas2(), data.getShfd2(), data.getExchangeRate2(), data.getEmpl2(),
            data.getFiscalYear2(), data.getClosingDate2(), data.getNumberMonths2(),
            data.getAuditStatus2(), data.getAccountsStatus2(), data.getAccountingPractice2(),
            data.getOriginalCurrency3(), data.getOriginalUnit2());

        // Period 3
        addPeriodIfPresent(periods, data.getOpre3(), data.getPlbt3(), data.getPl3(), data.getCf3(),
            data.getToas3(), data.getShfd3(), data.getExchangeRate3(), data.getEmpl3(),
            data.getFiscalYear3(), data.getClosingDate3(), data.getNumberMonths3(),
            data.getAuditStatus3(), data.getAccountsStatus3(), data.getAccountingPractice3(),
            data.getOriginalCurrency4(), data.getOriginalUnit3());

        // Periods 4-7 (only core financial fields available, no metadata/employees/ratios)
        addPeriodIfPresent(periods, data.getOpre4(), data.getPlbt4(), data.getPl4(), data.getCf4(),
            data.getToas4(), data.getShfd4(), data.getExchangeRate4(), null,
            null, null, null, null, null, null, null, null);

        addPeriodIfPresent(periods, data.getOpre5(), data.getPlbt5(), data.getPl5(), data.getCf5(),
            data.getToas5(), data.getShfd5(), data.getExchangeRate5(), null,
            null, null, null, null, null, null, null, null);

        addPeriodIfPresent(periods, data.getOpre6(), data.getPlbt6(), data.getPl6(), data.getCf6(),
            data.getToas6(), data.getShfd6(), data.getExchangeRate6(), null,
            null, null, null, null, null, null, null, null);

        addPeriodIfPresent(periods, data.getOpre7(), data.getPlbt7(), data.getPl7(), data.getCf7(),
            data.getToas7(), data.getShfd7(), data.getExchangeRate7(), null,
            null, null, null, null, null, null, null, null);

        return periods.isEmpty() ? null : periods;
    }

    private void addPeriodIfPresent(List<FinancialPeriodDTO> periods,
            Double opre, Double plbt, Double pl, Double cf,
            Double toas, Double shfd, Double exchangeRate, Double empl,
            String fiscalYear, String closingDate, String numberOfMonths,
            String auditStatus, String accountsStatus, String accountingPractice,
            String currency, String unit) {

        if (opre == null && plbt == null && pl == null && cf == null
                && toas == null && shfd == null) {
            return;
        }

        periods.add(FinancialPeriodDTO.builder()
            .fiscalYear(fiscalYear)
            .closingDate(closingDate)
            .numberOfMonths(numberOfMonths)
            .operatingRevenue(opre)
            .profitBeforeTax(plbt)
            .profitLoss(pl)
            .cashFlow(cf)
            .totalAssets(toas)
            .shareholdersFunds(shfd)
            .employees(empl)
            .exchangeRate(exchangeRate)
            .currency(currency)
            .unit(unit)
            .auditStatus(auditStatus)
            .accountsStatus(accountsStatus)
            .accountingPractice(accountingPractice)
            .build());
    }

    private List<FinancialRatioDTO> mapFinancialRatios(OrbisFinancialsResponse.OrbisFinancialsData data) {
        List<FinancialRatioDTO> ratios = new ArrayList<>();

        addRatioIfPresent(ratios, data.getFiscalYear(),
            data.getCurr(), data.getPrma(), data.getRshf(), data.getRcem(), data.getSolr());
        addRatioIfPresent(ratios, data.getFiscalYear1(),
            data.getCurr1(), data.getPrma1(), data.getRshf1(), data.getRcem1(), data.getSolr1());
        addRatioIfPresent(ratios, data.getFiscalYear2(),
            data.getCurr2(), data.getPrma2(), data.getRshf2(), data.getRcem2(), data.getSolr2());
        addRatioIfPresent(ratios, data.getFiscalYear3(),
            data.getCurr3(), data.getPrma3(), data.getRshf3(), data.getRcem3(), data.getSolr3());

        return ratios.isEmpty() ? null : ratios;
    }

    private void addRatioIfPresent(List<FinancialRatioDTO> ratios, String fiscalYear,
            Double curr, Double prma, Double rshf, Double rcem, Double solr) {
        if (curr == null && prma == null && rshf == null && rcem == null && solr == null) {
            return;
        }
        ratios.add(FinancialRatioDTO.builder()
            .fiscalYear(fiscalYear)
            .currentRatio(curr)
            .profitMargin(prma)
            .returnOnShareholdersFunds(rshf)
            .returnOnCapitalEmployed(rcem)
            .solvencyRatio(solr)
            .build());
    }

    private List<LegalEventDTO> mapLegalEvents(OrbisFinancialsResponse.OrbisFinancialsData data) {
        if (data.getLegalEventsDate() == null || data.getLegalEventsDate().isEmpty()) {
            return null;
        }
        List<LegalEventDTO> events = new ArrayList<>();
        for (int i = 0; i < data.getLegalEventsDate().size(); i++) {
            events.add(LegalEventDTO.builder()
                .date(data.getLegalEventsDate().get(i))
                .description(safeGet(data.getLegalEventsDescription(), i))
                .build());
        }
        return events;
    }

    // ── Ownership Structure ──

    private OwnershipStructureDTO mapOwnershipStructure(OrbisOwnershipResponse response) {
        if (response == null || response.getData() == null || response.getData().isEmpty()) {
            return null;
        }
        var data = response.getData().get(0);

        return OwnershipStructureDTO.builder()
            .corporateGroupSize(data.getCorporateGroupSizeLabel())
            .corporateEntityType(data.getCorporateEntityType())
            .globalUltimateOwner(mapGlobalUltimateOwner(data))
            .shareholders(mapShareholders(data))
            .beneficialOwners(mapBeneficialOwners(data))
            .subsidiaries(mapSubsidiaries(data))
            .build();
    }

    private StakeholderDTO mapGlobalUltimateOwner(OrbisOwnershipResponse.OrbisOwnershipData data) {
        if (data.getGuoName() == null) {
            return null;
        }
        return StakeholderDTO.builder()
            .name(data.getGuoName())
            .firstName(data.getGuoFirstName())
            .lastName(data.getGuoLastName())
            .identifier(data.getGuoBvdIdNumber())
            .lei(data.getGuoLei())
            .uci(data.getGuoUci())
            .entityType(data.getGuoEntityType())
            .countryIsoCode(data.getGuoCountryIsoCode())
            .stateProvince(data.getGuoStateProvince())
            .city(data.getGuoCity())
            .directPercentage(parseDouble(data.getGuoDirectPct()))
            .totalPercentage(parseDouble(data.getGuoTotalPct()))
            .operatingRevenue(data.getGuoOpre())
            .totalAssets(data.getGuoToas())
            .employees(data.getGuoEmpl())
            .industryCode(data.getGuoNaceCoreCode())
            .industryLabel(data.getGuoNaceCoreLabel())
            .compliance(ComplianceScreeningDTO.builder()
                .matchIndicator(data.getGuoGridMatchIndicatorFormatted())
                .sanctionsIndicator(data.getGuoGridMatchSanctionsIndicatorFormatted())
                .watchlistIndicator(data.getGuoGridMatchWatchlistIndicatorFormatted())
                .pepIndicator(data.getGuoGridMatchPepIndicatorFormatted())
                .mediaIndicator(data.getGuoGridMatchMediaIndicatorFormatted())
                .build())
            .build();
    }

    private List<StakeholderDTO> mapShareholders(OrbisOwnershipResponse.OrbisOwnershipData data) {
        if (data.getShName() == null || data.getShName().isEmpty()) {
            return null;
        }
        List<StakeholderDTO> shareholders = new ArrayList<>();
        for (int i = 0; i < data.getShName().size(); i++) {
            shareholders.add(StakeholderDTO.builder()
                .name(data.getShName().get(i))
                .identifier(safeGet(data.getShBvdIdNumber(), i))
                .lei(safeGet(data.getShLei(), i))
                .uci(safeGet(data.getShUci(), i))
                .entityType(safeGet(data.getShEntityType(), i))
                .countryIsoCode(safeGet(data.getShCountryIsoCode(), i))
                .stateProvince(safeGet(data.getShStateProvince(), i))
                .city(safeGet(data.getShCity(), i))
                .directPercentage(parseDouble(safeGet(data.getShDirectPct(), i)))
                .totalPercentage(parseDouble(safeGet(data.getShTotalPct(), i)))
                .operatingRevenue(safeGet(data.getShOpre(), i))
                .totalAssets(safeGet(data.getShToas(), i))
                .employees(safeGet(data.getShEmpl(), i))
                .industryCode(safeGet(data.getShNaceCoreCode(), i))
                .industryLabel(safeGet(data.getShNaceCoreLabel(), i))
                .compliance(ComplianceScreeningDTO.builder()
                    .matchIndicator(safeGet(data.getShGridMatchIndicatorFormatted(), i))
                    .sanctionsIndicator(safeGet(data.getShGridMatchSanctionsIndicatorFormatted(), i))
                    .watchlistIndicator(safeGet(data.getShGridMatchWatchlistIndicatorFormatted(), i))
                    .pepIndicator(safeGet(data.getShGridMatchPepIndicatorFormatted(), i))
                    .mediaIndicator(safeGet(data.getShGridMatchMediaIndicatorFormatted(), i))
                    .build())
                .build());
        }
        return shareholders;
    }

    private List<StakeholderDTO> mapBeneficialOwners(OrbisOwnershipResponse.OrbisOwnershipData data) {
        if (data.getBoName() == null || data.getBoName().isEmpty()) {
            return null;
        }
        List<StakeholderDTO> owners = new ArrayList<>();
        for (int i = 0; i < data.getBoName().size(); i++) {
            owners.add(StakeholderDTO.builder()
                .name(data.getBoName().get(i))
                .firstName(safeGet(data.getBoFirstName(), i))
                .lastName(safeGet(data.getBoLastName(), i))
                .identifier(safeGet(data.getBoBvdIdNumber(), i))
                .uci(safeGet(data.getBoUci(), i))
                .countryIsoCode(safeGet(data.getBoCountryIsoCode(), i))
                .city(safeGet(data.getBoCity(), i))
                .address(safeGet(data.getBoAddress(), i))
                .phone(safeGet(data.getBoPhoneNumber(), i))
                .email(safeGet(data.getBoEmail(), i))
                .compliance(ComplianceScreeningDTO.builder()
                    .matchIndicator(safeGet(data.getBoGridMatchIndicatorFormatted(), i))
                    .sanctionsIndicator(safeGet(data.getBoGridMatchSanctionsIndicatorFormatted(), i))
                    .watchlistIndicator(safeGet(data.getBoGridMatchWatchlistIndicatorFormatted(), i))
                    .pepIndicator(safeGet(data.getBoGridMatchPepIndicatorFormatted(), i))
                    .mediaIndicator(safeGet(data.getBoGridMatchMediaIndicatorFormatted(), i))
                    .build())
                .build());
        }
        return owners;
    }

    private List<StakeholderDTO> mapSubsidiaries(OrbisOwnershipResponse.OrbisOwnershipData data) {
        if (data.getSubName() == null || data.getSubName().isEmpty()) {
            return null;
        }
        List<StakeholderDTO> subsidiaries = new ArrayList<>();
        for (int i = 0; i < data.getSubName().size(); i++) {
            subsidiaries.add(StakeholderDTO.builder()
                .name(data.getSubName().get(i))
                .identifier(safeGet(data.getSubBvdIdNumber(), i))
                .lei(safeGet(data.getSubLei(), i))
                .entityType(safeGet(data.getSubEntityType(), i))
                .countryIsoCode(safeGet(data.getSubCountryIsoCode(), i))
                .stateProvince(safeGet(data.getSubStateProvince(), i))
                .city(safeGet(data.getSubCity(), i))
                .directPercentage(parseDouble(safeGet(data.getSubDirectPct(), i)))
                .totalPercentage(parseDouble(safeGet(data.getSubTotalPct(), i)))
                .operatingRevenue(safeGet(data.getSubOpre(), i))
                .totalAssets(safeGet(data.getSubToas(), i))
                .employees(safeGet(data.getSubEmpl(), i))
                .industryCode(safeGet(data.getSubNaceCoreCode(), i))
                .industryLabel(safeGet(data.getSubNaceCoreLabel(), i))
                .compliance(ComplianceScreeningDTO.builder()
                    .matchIndicator(safeGet(data.getSubGridMatchIndicatorFormatted(), i))
                    .sanctionsIndicator(safeGet(data.getSubGridMatchSanctionsIndicatorFormatted(), i))
                    .watchlistIndicator(safeGet(data.getSubGridMatchWatchlistIndicatorFormatted(), i))
                    .pepIndicator(safeGet(data.getSubGridMatchPepIndicatorFormatted(), i))
                    .mediaIndicator(safeGet(data.getSubGridMatchMediaIndicatorFormatted(), i))
                    .build())
                .build());
        }
        return subsidiaries;
    }

    // ── Directors ──

    private List<DirectorDTO> mapDirectors(OrbisDirectorsResponse response) {
        if (response == null || response.getData() == null || response.getData().isEmpty()) {
            return null;
        }
        var data = response.getData().get(0);
        var names = data.getCpycontactsHeaderFullNameOriginalLanguagePreferred();
        if (names == null || names.isEmpty()) {
            return null;
        }

        List<DirectorDTO> directors = new ArrayList<>();
        for (int i = 0; i < names.size(); i++) {
            directors.add(DirectorDTO.builder()
                .fullName(names.get(i))
                .firstName(safeGet(data.getCpycontactsHeaderFirstNameOriginalLanguagePreferred(), i))
                .middleName(safeGet(data.getCpycontactsHeaderMiddleNameOriginalLanguagePreferred(), i))
                .lastName(safeGet(data.getCpycontactsHeaderLastNameOriginalLanguagePreferred(), i))
                .title(safeGet(data.getCpycontactsHeaderBareTitle(), i))
                .function(safeGet(data.getCpycontactsMembershipFunction(), i))
                .directorId(safeGet(data.getCpycontactsHeaderIdDirector(), i))
                .bvdId(safeGet(data.getCpycontactsHeaderBvdId(), i))
                .nationality(safeGet(data.getCpycontactsHeaderMultipleNationalitiesLabel(), i))
                .email(safeGet(data.getCpycontactsHeaderEmail(), i))
                .workAddress(safeGet(data.getCpycontactsMembershipWorkFullAddress(), i))
                .country(safeGet(data.getCpycontactsHeaderCountryLabel(), i))
                .startDate(safeGet(data.getCpycontactsMembershipBeginningNominationDate(), i))
                .endDate(safeGet(data.getCpycontactsMembershipEndExpirationDate(), i))
                .board(safeGet(data.getCpycontactsMembershipBoardMnemonic(), i))
                .department(safeGet(data.getCpycontactsMembershipDepartmentFromHierCodeFall2009(), i))
                .level(safeGet(data.getCpycontactsMembershipLevelFromHierCodeFall2009(), i))
                .compliance(ComplianceScreeningDTO.builder()
                    .matchIndicator(safeGet(data.getCpycontactsHeaderGridMatchIndicator(), i))
                    .sanctionsIndicator(safeGet(data.getCpycontactsHeaderGridMatchSanctionsIndicator(), i))
                    .watchlistIndicator(safeGet(data.getCpycontactsHeaderGridMatchWatchlistIndicator(), i))
                    .pepIndicator(safeGet(data.getCpycontactsHeaderGridMatchPepIndicator(), i))
                    .mediaIndicator(safeGet(data.getCpycontactsHeaderGridMatchMediaIndicator(), i))
                    .build())
                .build());
        }
        return directors;
    }

    // ── Utility helpers ──

    private <T> T safeGet(List<T> list, int index) {
        if (list == null || index >= list.size()) return null;
        return list.get(index);
    }

    private Double parseDouble(String value) {
        if (value == null || value.isBlank()) return null;
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private String booleanToString(Boolean value) {
        return value == null ? null : String.valueOf(value);
    }
}
