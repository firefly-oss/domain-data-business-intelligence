package io.fireflysoftware.data.business.intelligence.infra.services;

import org.fireflyframework.data.event.EnrichmentEventPublisher;
import org.fireflyframework.data.model.EnrichmentRequest;
import org.fireflyframework.data.observability.JobMetricsService;
import org.fireflyframework.data.observability.JobTracingService;
import org.fireflyframework.data.resiliency.ResiliencyDecoratorService;
import io.fireflysoftware.data.business.intelligence.infra.dtos.BusinessReportDTO;
import io.fireflysoftware.data.business.intelligence.infra.dtos.BusinessReportDTO.*;
import io.fireflysoftware.data.business.intelligence.infra.dtos.orbis.OrbisCompositeResponse;
import io.fireflysoftware.data.business.intelligence.infra.dtos.orbis.OrbisDataResponse;
import io.fireflysoftware.data.business.intelligence.infra.dtos.orbis.OrbisDirectorsResponse;
import io.fireflysoftware.data.business.intelligence.infra.dtos.orbis.OrbisFinancialsResponse;
import io.fireflysoftware.data.business.intelligence.infra.dtos.orbis.OrbisOwnershipResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrbisEnricherTest {

    @Mock
    private JobTracingService tracingService;
    @Mock
    private JobMetricsService metricsService;
    @Mock
    private ResiliencyDecoratorService resiliencyService;
    @Mock
    private EnrichmentEventPublisher eventPublisher;
    @Mock
    private OrbisService orbisService;
    @Mock
    private EnrichmentRequest enrichmentRequest;

    private OrbisEnricher orbisEnricher;

    @BeforeEach
    void setUp() {
        orbisEnricher = new OrbisEnricher(tracingService, metricsService, resiliencyService, eventPublisher, orbisService);
    }

    // ── Company Profile Tests ──

    @Test
    void shouldMapCompanyProfileCorrectly() {
        OrbisCompositeResponse composite = OrbisCompositeResponse.builder()
            .companyData(buildTestCompanyData())
            .build();

        BusinessReportDTO result = orbisEnricher.mapToTarget(composite);

        assertNotNull(result);
        assertNotNull(result.getProvider());
        assertEquals("Orbis BvD", result.getProvider().getProviderName());
        assertEquals("BUSINESS_INTELLIGENCE", result.getProvider().getReportType());

        CompanyProfileDTO company = result.getCompany();
        assertNotNull(company);
        assertEquals("Acme Corp", company.getName());
        assertEquals("BVD123", company.getIdentifier());
        assertEquals("123 Main St", company.getAddress());
        assertEquals("10001", company.getPostcode());
        assertEquals("New York", company.getCity());
        assertEquals("United States", company.getCountry());
        assertEquals("US", company.getCountryIsoCode());
        assertEquals(40.7128, company.getLatitude());
        assertEquals(-74.0060, company.getLongitude());
        assertEquals("Technology services", company.getTradeDescription());
        assertEquals("Software", company.getPrimaryBusinessLine());
        assertEquals(500L, company.getOperationalSize());

        // Industry classifications
        assertNotNull(company.getIndustryClassifications());
        assertEquals(3, company.getIndustryClassifications().size());
        assertEquals("NACE2", company.getIndustryClassifications().get(0).getSystem());
        assertEquals("6201", company.getIndustryClassifications().get(0).getCode());
        assertEquals("NAICS2022", company.getIndustryClassifications().get(1).getSystem());
        assertEquals("541511", company.getIndustryClassifications().get(1).getCode());
        assertEquals("USSIC", company.getIndustryClassifications().get(2).getSystem());
        assertEquals("7371", company.getIndustryClassifications().get(2).getCode());
        assertEquals("Computer Services", company.getIndustryClassifications().get(2).getLabel());

        // Legal identifiers
        assertNotNull(company.getLegalIdentifiers());
        assertTrue(company.getLegalIdentifiers().stream().anyMatch(
            id -> "NATIONAL_ID".equals(id.getType()) && "NAT123".equals(id.getValue()) && "EIN".equals(id.getLabel())));
        assertTrue(company.getLegalIdentifiers().stream().anyMatch(
            id -> "LEI".equals(id.getType()) && "LEI123456".equals(id.getValue())));
        assertTrue(company.getLegalIdentifiers().stream().anyMatch(
            id -> "ISIN".equals(id.getType()) && "US0378331005".equals(id.getValue())));
        assertTrue(company.getLegalIdentifiers().stream().anyMatch(
            id -> "VAT".equals(id.getType()) && "VAT999".equals(id.getValue())));
        assertTrue(company.getLegalIdentifiers().stream().anyMatch(
            id -> "TIN".equals(id.getType()) && "TIN888".equals(id.getValue())));
        assertTrue(company.getLegalIdentifiers().stream().anyMatch(
            id -> "SWIFT".equals(id.getType()) && "SWIFT777".equals(id.getValue())));
        assertTrue(company.getLegalIdentifiers().stream().anyMatch(
            id -> "TRADE_REGISTER_NUMBER".equals(id.getType()) && "TRN555".equals(id.getValue())));

        // Compliance
        assertNotNull(company.getCompliance());
        assertEquals("true", company.getCompliance().getMatchIndicator());
        assertEquals("false", company.getCompliance().getSanctionsIndicator());
    }

    // ── Financial Tests ──

    @Test
    void shouldMapFinancialPeriodsCorrectly() {
        OrbisCompositeResponse composite = OrbisCompositeResponse.builder()
            .financials(buildTestFinancialsData())
            .build();

        BusinessReportDTO result = orbisEnricher.mapToTarget(composite);

        FinancialSummaryDTO fin = result.getFinancials();
        assertNotNull(fin);
        assertEquals("USD", fin.getCurrency());
        assertEquals("C1", fin.getConsolidationCode());
        assertEquals("Annual", fin.getFilingType());
        assertEquals("2024-12-31", fin.getClosingDateLastAnnual());
        assertEquals("2024", fin.getYearLastAccounts());

        assertNotNull(fin.getPeriods());
        assertEquals(2, fin.getPeriods().size());

        FinancialPeriodDTO period0 = fin.getPeriods().get(0);
        assertEquals("2024", period0.getFiscalYear());
        assertEquals("2024-12-31", period0.getClosingDate());
        assertEquals("12", period0.getNumberOfMonths());
        assertEquals(1000000.0, period0.getOperatingRevenue());
        assertEquals(200000.0, period0.getProfitBeforeTax());
        assertEquals(150000.0, period0.getProfitLoss());
        assertEquals(50000.0, period0.getCashFlow());
        assertEquals(5000000.0, period0.getTotalAssets());
        assertEquals(2000000.0, period0.getShareholdersFunds());
        assertEquals(250.0, period0.getEmployees());
        assertEquals(1.0, period0.getExchangeRate());
        assertEquals("Audited", period0.getAuditStatus());

        FinancialPeriodDTO period1 = fin.getPeriods().get(1);
        assertEquals("2023", period1.getFiscalYear());
        assertEquals(900000.0, period1.getOperatingRevenue());
    }

    @Test
    void shouldMapFinancialRatiosCorrectly() {
        OrbisCompositeResponse composite = OrbisCompositeResponse.builder()
            .financials(buildTestFinancialsData())
            .build();

        BusinessReportDTO result = orbisEnricher.mapToTarget(composite);

        FinancialSummaryDTO fin = result.getFinancials();
        assertNotNull(fin.getRatios());
        assertEquals(2, fin.getRatios().size());

        FinancialRatioDTO ratio0 = fin.getRatios().get(0);
        assertEquals("2024", ratio0.getFiscalYear());
        assertEquals(1.5, ratio0.getCurrentRatio());
        assertEquals(20.0, ratio0.getProfitMargin());
        assertEquals(7.5, ratio0.getReturnOnShareholdersFunds());
        assertEquals(12.0, ratio0.getReturnOnCapitalEmployed());
        assertEquals(40.0, ratio0.getSolvencyRatio());

        FinancialRatioDTO ratio1 = fin.getRatios().get(1);
        assertEquals("2023", ratio1.getFiscalYear());
        assertEquals(1.3, ratio1.getCurrentRatio());
    }

    @Test
    void shouldMapLegalEventsCorrectly() {
        OrbisCompositeResponse composite = OrbisCompositeResponse.builder()
            .financials(buildTestFinancialsData())
            .build();

        BusinessReportDTO result = orbisEnricher.mapToTarget(composite);

        FinancialSummaryDTO fin = result.getFinancials();
        assertNotNull(fin.getLegalEvents());
        assertEquals(2, fin.getLegalEvents().size());
        assertEquals("2024-06-15", fin.getLegalEvents().get(0).getDate());
        assertEquals("Annual filing submitted", fin.getLegalEvents().get(0).getDescription());
        assertEquals("2023-12-01", fin.getLegalEvents().get(1).getDate());
        assertEquals("Board resolution", fin.getLegalEvents().get(1).getDescription());
    }

    // ── Ownership Tests ──

    @Test
    void shouldMapShareholdersCorrectly() {
        OrbisCompositeResponse composite = OrbisCompositeResponse.builder()
            .ownership(buildTestOwnershipData())
            .build();

        BusinessReportDTO result = orbisEnricher.mapToTarget(composite);

        OwnershipStructureDTO own = result.getOwnership();
        assertNotNull(own);
        assertEquals("Large", own.getCorporateGroupSize());
        assertEquals("Industrial company", own.getCorporateEntityType());

        assertNotNull(own.getShareholders());
        assertEquals(2, own.getShareholders().size());

        StakeholderDTO sh0 = own.getShareholders().get(0);
        assertEquals("Shareholder One", sh0.getName());
        assertEquals("SH_BVD_001", sh0.getIdentifier());
        assertEquals("SH_LEI_001", sh0.getLei());
        assertEquals(51.0, sh0.getDirectPercentage());
        assertEquals(51.0, sh0.getTotalPercentage());
        assertEquals(5000000.0, sh0.getOperatingRevenue());
        assertEquals("6201", sh0.getIndustryCode());
        assertNotNull(sh0.getCompliance());
        assertEquals("Yes", sh0.getCompliance().getMatchIndicator());

        StakeholderDTO sh1 = own.getShareholders().get(1);
        assertEquals("Shareholder Two", sh1.getName());
        assertEquals(49.0, sh1.getDirectPercentage());
    }

    @Test
    void shouldMapBeneficialOwnersCorrectly() {
        OrbisCompositeResponse composite = OrbisCompositeResponse.builder()
            .ownership(buildTestOwnershipData())
            .build();

        BusinessReportDTO result = orbisEnricher.mapToTarget(composite);

        assertNotNull(result.getOwnership().getBeneficialOwners());
        assertEquals(1, result.getOwnership().getBeneficialOwners().size());

        StakeholderDTO bo = result.getOwnership().getBeneficialOwners().get(0);
        assertEquals("BO Person", bo.getName());
        assertEquals("John", bo.getFirstName());
        assertEquals("Doe", bo.getLastName());
        assertEquals("BO_BVD_001", bo.getIdentifier());
        assertEquals("US", bo.getCountryIsoCode());
        assertNotNull(bo.getCompliance());
        assertEquals("No", bo.getCompliance().getSanctionsIndicator());
    }

    @Test
    void shouldMapGlobalUltimateOwnerCorrectly() {
        OrbisCompositeResponse composite = OrbisCompositeResponse.builder()
            .ownership(buildTestOwnershipData())
            .build();

        BusinessReportDTO result = orbisEnricher.mapToTarget(composite);

        StakeholderDTO guo = result.getOwnership().getGlobalUltimateOwner();
        assertNotNull(guo);
        assertEquals("GUO Holdings Inc", guo.getName());
        assertEquals("GUO_BVD_001", guo.getIdentifier());
        assertEquals("GUO_LEI_001", guo.getLei());
        assertEquals("Corporate", guo.getEntityType());
        assertEquals("GB", guo.getCountryIsoCode());
        assertEquals(100.0, guo.getTotalPercentage());
        assertEquals(10000000.0, guo.getOperatingRevenue());
        assertEquals("7010", guo.getIndustryCode());
        assertEquals("Head offices", guo.getIndustryLabel());
        assertNotNull(guo.getCompliance());
        assertEquals("No", guo.getCompliance().getSanctionsIndicator());
    }

    @Test
    void shouldMapSubsidiariesCorrectly() {
        OrbisCompositeResponse composite = OrbisCompositeResponse.builder()
            .ownership(buildTestOwnershipData())
            .build();

        BusinessReportDTO result = orbisEnricher.mapToTarget(composite);

        assertNotNull(result.getOwnership().getSubsidiaries());
        assertEquals(1, result.getOwnership().getSubsidiaries().size());

        StakeholderDTO sub = result.getOwnership().getSubsidiaries().get(0);
        assertEquals("Subsidiary Ltd", sub.getName());
        assertEquals("SUB_BVD_001", sub.getIdentifier());
        assertEquals("DE", sub.getCountryIsoCode());
        assertEquals(100.0, sub.getDirectPercentage());
    }

    // ── Director Tests ──

    @Test
    void shouldMapDirectorsCorrectly() {
        OrbisCompositeResponse composite = OrbisCompositeResponse.builder()
            .directors(buildTestDirectorsData())
            .build();

        BusinessReportDTO result = orbisEnricher.mapToTarget(composite);

        assertNotNull(result.getDirectors());
        assertEquals(2, result.getDirectors().size());

        DirectorDTO d0 = result.getDirectors().get(0);
        assertEquals("Jane Smith", d0.getFullName());
        assertEquals("Jane", d0.getFirstName());
        assertEquals("A", d0.getMiddleName());
        assertEquals("Smith", d0.getLastName());
        assertEquals("Ms", d0.getTitle());
        assertEquals("CEO", d0.getFunction());
        assertEquals("DIR001", d0.getDirectorId());
        assertEquals("BVD_DIR_001", d0.getBvdId());
        assertEquals("American", d0.getNationality());
        assertEquals("jane@acme.com", d0.getEmail());
        assertEquals("123 Main St, NY", d0.getWorkAddress());
        assertEquals("United States", d0.getCountry());
        assertEquals("2020-01-15", d0.getStartDate());
        assertEquals("BOARD", d0.getBoard());
        assertEquals("Executive", d0.getDepartment());
        assertEquals("C-Level", d0.getLevel());
        assertNotNull(d0.getCompliance());
        assertEquals("No", d0.getCompliance().getSanctionsIndicator());

        DirectorDTO d1 = result.getDirectors().get(1);
        assertEquals("Bob Jones", d1.getFullName());
        assertEquals("CFO", d1.getFunction());
    }

    // ── Null / Empty Handling ──

    @Test
    void shouldHandleNullCompositeResponse() {
        BusinessReportDTO result = orbisEnricher.mapToTarget(null);
        assertNull(result);
    }

    @Test
    void shouldHandleEmptyDataLists() {
        OrbisCompositeResponse composite = OrbisCompositeResponse.builder()
            .companyData(OrbisDataResponse.builder().data(List.of()).build())
            .financials(OrbisFinancialsResponse.builder().data(List.of()).build())
            .ownership(OrbisOwnershipResponse.builder().data(List.of()).build())
            .directors(OrbisDirectorsResponse.builder().data(List.of()).build())
            .build();

        BusinessReportDTO result = orbisEnricher.mapToTarget(composite);

        assertNotNull(result);
        assertNotNull(result.getProvider());
        assertNull(result.getCompany());
        assertNull(result.getFinancials());
        assertNull(result.getOwnership());
        assertNull(result.getDirectors());
    }

    // ── Fetch All Endpoints Test ──

    @Test
    void shouldFetchAllFourEndpoints() {
        when(enrichmentRequest.requireParam("bvdId")).thenReturn("BVD123");
        when(enrichmentRequest.getParameters()).thenReturn(Map.of("domain", "test-domain"));

        OrbisDataResponse companyData = new OrbisDataResponse();
        OrbisFinancialsResponse financials = new OrbisFinancialsResponse();
        OrbisOwnershipResponse ownership = new OrbisOwnershipResponse();
        OrbisDirectorsResponse directors = new OrbisDirectorsResponse();

        when(orbisService.getCompanyData("BVD123", "test-domain")).thenReturn(Mono.just(companyData));
        when(orbisService.getFinancials("BVD123", "test-domain")).thenReturn(Mono.just(financials));
        when(orbisService.getOwnership("BVD123", "test-domain")).thenReturn(Mono.just(ownership));
        when(orbisService.getDirectors("BVD123", "test-domain")).thenReturn(Mono.just(directors));

        OrbisCompositeResponse result = orbisEnricher.fetchProviderData(enrichmentRequest).block();

        assertNotNull(result);
        assertEquals(companyData, result.getCompanyData());
        assertEquals(financials, result.getFinancials());
        assertEquals(ownership, result.getOwnership());
        assertEquals(directors, result.getDirectors());

        verify(orbisService).getCompanyData("BVD123", "test-domain");
        verify(orbisService).getFinancials("BVD123", "test-domain");
        verify(orbisService).getOwnership("BVD123", "test-domain");
        verify(orbisService).getDirectors("BVD123", "test-domain");
    }

    // ── Test Data Builders ──

    private OrbisDataResponse buildTestCompanyData() {
        var data = new OrbisDataResponse.OrbisCompanyData();
        data.setName("Acme Corp");
        data.setBvdIdNumber("BVD123");
        data.setAddressLine1("123 Main St");
        data.setPostcode("10001");
        data.setCity("New York");
        data.setCountry("United States");
        data.setCountryIsoCode("US");
        data.setLatitude(40.7128);
        data.setLongitude(-74.006);
        data.setNuts1("US-NY");
        data.setWorldRegion("North America");
        data.setPhoneNumber(List.of("+1-555-0100"));
        data.setEmail(List.of("info@acme.com"));
        data.setWebsite(List.of("www.acme.com"));
        data.setTradeDescriptionEn("Technology services");
        data.setOverviewPrimaryBusinessLine("Software");
        data.setProductsServices("Cloud computing");
        data.setOpgsize(500L);

        // Industry codes
        data.setNace2CoreCode("6201");
        data.setNace2MainSection("J - Information and communication");
        data.setNaics2022CoreCode("541511");
        data.setNaics2022CoreLabel("Custom Computer Programming");
        data.setUssicPrimaryCode(List.of("7371"));
        data.setUssicPrimaryLabel(List.of("Computer Services"));

        // Legal identifiers
        data.setNationalId(List.of("NAT123"));
        data.setNationalIdLabel(List.of("EIN"));
        data.setLei("LEI123456");
        data.setIsin("US0378331005");
        data.setVatNumber(List.of("VAT999"));
        data.setTin("TIN888");
        data.setSwiftCode("SWIFT777");
        data.setTradeRegisterNumber(List.of("TRN555"));

        // GRID compliance
        data.setGridMatchIndicator(true);
        data.setGridMatchSanctionsIndicator(false);
        data.setGridMatchWatchlistIndicator(false);
        data.setGridMatchPepIndicator(false);
        data.setGridMatchMediaIndicator(true);

        return OrbisDataResponse.builder().data(List.of(data)).build();
    }

    private OrbisFinancialsResponse buildTestFinancialsData() {
        var data = new OrbisFinancialsResponse.OrbisFinancialsData();
        data.setOriginalCurrency("USD");
        data.setConsolidationCode("C1");
        data.setFilingType("Annual");
        data.setClosingDateLastAnnualAccounts("2024-12-31");
        data.setYearLastAccounts("2024");
        data.setAccountingTemplate("US GAAP");

        // Period 0
        data.setOpre(1000000.0);
        data.setPlbt(200000.0);
        data.setPl(150000.0);
        data.setCf(50000.0);
        data.setToas(5000000.0);
        data.setShfd(2000000.0);
        data.setExchangeRate(1.0);
        data.setEmpl(250.0);
        data.setFiscalYear("2024");
        data.setClosingDate("2024-12-31");
        data.setNumberMonths("12");
        data.setAuditStatus("Audited");
        data.setAccountsStatus("Filed");
        data.setAccountingPractice("Local GAAP");
        data.setOriginalCurrency1("USD");
        data.setOriginalUnit("Units");

        // Period 1
        data.setOpre1(900000.0);
        data.setPlbt1(180000.0);
        data.setPl1(130000.0);
        data.setCf1(45000.0);
        data.setToas1(4500000.0);
        data.setShfd1(1800000.0);
        data.setExchangeRate1(1.0);
        data.setEmpl1(230.0);
        data.setFiscalYear1("2023");
        data.setClosingDate1("2023-12-31");
        data.setNumberMonths1("12");
        data.setAuditStatus1("Audited");
        data.setAccountsStatus1("Filed");
        data.setAccountingPractice1("Local GAAP");
        data.setOriginalCurrency2("USD");
        data.setOriginalUnit1("Units");

        // Ratios period 0
        data.setCurr(1.5);
        data.setPrma(20.0);
        data.setRshf(7.5);
        data.setRcem(12.0);
        data.setSolr(40.0);

        // Ratios period 1
        data.setCurr1(1.3);
        data.setPrma1(18.0);
        data.setRshf1(6.5);
        data.setRcem1(11.0);
        data.setSolr1(38.0);

        // Legal events
        data.setLegalEventsDate(List.of("2024-06-15", "2023-12-01"));
        data.setLegalEventsDescription(List.of("Annual filing submitted", "Board resolution"));

        return OrbisFinancialsResponse.builder().data(List.of(data)).build();
    }

    private OrbisOwnershipResponse buildTestOwnershipData() {
        var data = new OrbisOwnershipResponse.OrbisOwnershipData();
        data.setCorporateGroupSizeLabel("Large");
        data.setCorporateEntityType("Industrial company");

        // Shareholders (2)
        data.setShName(List.of("Shareholder One", "Shareholder Two"));
        data.setShBvdIdNumber(List.of("SH_BVD_001", "SH_BVD_002"));
        data.setShLei(List.of("SH_LEI_001", "SH_LEI_002"));
        data.setShUci(List.of("SH_UCI_001", "SH_UCI_002"));
        data.setShEntityType(List.of("Corporate", "Individual"));
        data.setShCountryIsoCode(List.of("US", "GB"));
        data.setShStateProvince(List.of("NY", "London"));
        data.setShCity(List.of("New York", "London"));
        data.setShDirectPct(List.of("51.0", "49.0"));
        data.setShTotalPct(List.of("51.0", "49.0"));
        data.setShOpre(List.of(5000000.0, 3000000.0));
        data.setShToas(List.of(10000000.0, 8000000.0));
        data.setShEmpl(List.of(500.0, 300.0));
        data.setShNaceCoreCode(List.of("6201", "6411"));
        data.setShNaceCoreLabel(List.of("Computer programming", "Central banking"));
        data.setShGridMatchIndicatorFormatted(List.of("Yes", "No"));
        data.setShGridMatchSanctionsIndicatorFormatted(List.of("No", "No"));
        data.setShGridMatchWatchlistIndicatorFormatted(List.of("No", "No"));
        data.setShGridMatchPepIndicatorFormatted(List.of("No", "No"));
        data.setShGridMatchMediaIndicatorFormatted(List.of("Yes", "No"));

        // Beneficial Owners (1)
        data.setBoName(List.of("BO Person"));
        data.setBoFirstName(List.of("John"));
        data.setBoLastName(List.of("Doe"));
        data.setBoBvdIdNumber(List.of("BO_BVD_001"));
        data.setBoUci(List.of("BO_UCI_001"));
        data.setBoCountryIsoCode(List.of("US"));
        data.setBoCity(List.of("Boston"));
        data.setBoAddress(List.of("456 BO St"));
        data.setBoPhoneNumber(List.of("+1-555-0200"));
        data.setBoEmail(List.of("bo@acme.com"));
        data.setBoGridMatchIndicatorFormatted(List.of("Yes"));
        data.setBoGridMatchSanctionsIndicatorFormatted(List.of("No"));
        data.setBoGridMatchWatchlistIndicatorFormatted(List.of("No"));
        data.setBoGridMatchPepIndicatorFormatted(List.of("No"));
        data.setBoGridMatchMediaIndicatorFormatted(List.of("No"));

        // GUO (single values)
        data.setGuoName("GUO Holdings Inc");
        data.setGuoBvdIdNumber("GUO_BVD_001");
        data.setGuoLei("GUO_LEI_001");
        data.setGuoUci("GUO_UCI_001");
        data.setGuoEntityType("Corporate");
        data.setGuoCountryIsoCode("GB");
        data.setGuoStateProvince("England");
        data.setGuoCity("London");
        data.setGuoDirectPct("100.0");
        data.setGuoTotalPct("100.0");
        data.setGuoOpre(10000000.0);
        data.setGuoToas(50000000.0);
        data.setGuoEmpl(2000.0);
        data.setGuoNaceCoreCode("7010");
        data.setGuoNaceCoreLabel("Head offices");
        data.setGuoGridMatchIndicatorFormatted("Yes");
        data.setGuoGridMatchSanctionsIndicatorFormatted("No");
        data.setGuoGridMatchWatchlistIndicatorFormatted("No");
        data.setGuoGridMatchPepIndicatorFormatted("No");
        data.setGuoGridMatchMediaIndicatorFormatted("No");

        // Subsidiaries (1)
        data.setSubName(List.of("Subsidiary Ltd"));
        data.setSubBvdIdNumber(List.of("SUB_BVD_001"));
        data.setSubLei(List.of("SUB_LEI_001"));
        data.setSubEntityType(List.of("Corporate"));
        data.setSubCountryIsoCode(List.of("DE"));
        data.setSubStateProvince(List.of("Bavaria"));
        data.setSubCity(List.of("Munich"));
        data.setSubDirectPct(List.of("100.0"));
        data.setSubTotalPct(List.of("100.0"));
        data.setSubOpre(List.of(2000000.0));
        data.setSubToas(List.of(5000000.0));
        data.setSubEmpl(List.of(100.0));
        data.setSubNaceCoreCode(List.of("6201"));
        data.setSubNaceCoreLabel(List.of("Computer programming"));
        data.setSubGridMatchIndicatorFormatted(List.of("No"));
        data.setSubGridMatchSanctionsIndicatorFormatted(List.of("No"));
        data.setSubGridMatchWatchlistIndicatorFormatted(List.of("No"));
        data.setSubGridMatchPepIndicatorFormatted(List.of("No"));
        data.setSubGridMatchMediaIndicatorFormatted(List.of("No"));

        return OrbisOwnershipResponse.builder().data(List.of(data)).build();
    }

    private OrbisDirectorsResponse buildTestDirectorsData() {
        var data = new OrbisDirectorsResponse.OrbisDirectorsData();
        data.setName("Acme Corp");

        data.setCpycontactsHeaderFullNameOriginalLanguagePreferred(List.of("Jane Smith", "Bob Jones"));
        data.setCpycontactsHeaderFirstNameOriginalLanguagePreferred(List.of("Jane", "Bob"));
        data.setCpycontactsHeaderMiddleNameOriginalLanguagePreferred(List.of("A", "B"));
        data.setCpycontactsHeaderLastNameOriginalLanguagePreferred(List.of("Smith", "Jones"));
        data.setCpycontactsHeaderBareTitle(List.of("Ms", "Mr"));
        data.setCpycontactsMembershipFunction(List.of("CEO", "CFO"));
        data.setCpycontactsHeaderIdDirector(List.of("DIR001", "DIR002"));
        data.setCpycontactsHeaderBvdId(List.of("BVD_DIR_001", "BVD_DIR_002"));
        data.setCpycontactsHeaderMultipleNationalitiesLabel(List.of("American", "British"));
        data.setCpycontactsHeaderEmail(List.of("jane@acme.com", "bob@acme.com"));
        data.setCpycontactsMembershipWorkFullAddress(List.of("123 Main St, NY", "456 High St, London"));
        data.setCpycontactsHeaderCountryLabel(List.of("United States", "United Kingdom"));
        data.setCpycontactsMembershipBeginningNominationDate(List.of("2020-01-15", "2021-03-01"));
        data.setCpycontactsMembershipEndExpirationDate(Arrays.asList(null, "2025-03-01"));
        data.setCpycontactsMembershipBoardMnemonic(List.of("BOARD", "BOARD"));
        data.setCpycontactsMembershipDepartmentFromHierCodeFall2009(List.of("Executive", "Finance"));
        data.setCpycontactsMembershipLevelFromHierCodeFall2009(List.of("C-Level", "C-Level"));
        data.setCpycontactsHeaderGridMatchIndicator(List.of("Yes", "No"));
        data.setCpycontactsHeaderGridMatchSanctionsIndicator(List.of("No", "No"));
        data.setCpycontactsHeaderGridMatchWatchlistIndicator(List.of("No", "No"));
        data.setCpycontactsHeaderGridMatchPepIndicator(List.of("No", "No"));
        data.setCpycontactsHeaderGridMatchMediaIndicator(List.of("No", "No"));

        return OrbisDirectorsResponse.builder().data(List.of(data)).build();
    }
}
