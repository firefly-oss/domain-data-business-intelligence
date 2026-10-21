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


package io.fireflysoftware.data.business.intelligence.infra.services;

import org.fireflyframework.client.RestClient;
import io.fireflysoftware.data.business.intelligence.infra.dtos.SelectCompanyRequest;
import io.fireflysoftware.data.business.intelligence.infra.dtos.orbis.OrbisDataRequest;
import io.fireflysoftware.data.business.intelligence.infra.dtos.orbis.OrbisDataResponse;
import io.fireflysoftware.data.business.intelligence.infra.dtos.orbis.OrbisDirectorsResponse;
import io.fireflysoftware.data.business.intelligence.infra.dtos.orbis.OrbisFinancialsResponse;
import io.fireflysoftware.data.business.intelligence.infra.dtos.orbis.OrbisOwnershipResponse;
import io.fireflysoftware.data.business.intelligence.infra.mappers.OrbisMapper;
import io.fireflysoftware.data.business.intelligence.infra.dtos.SelectCompanyResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrbisServiceTest {

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private RestClient orbisRestClient;

    @Mock
    private OrbisMapper orbisMapper;

    private OrbisService orbisService;

    private static final String API_TOKEN = "test-api-token";

    @BeforeEach
    void setUp() {
        orbisService = new OrbisService(orbisRestClient, orbisMapper, API_TOKEN);
    }

    @Test
    void shouldGetCompanyData() {
        String bvdId = "testBvdId";
        String domain = "testDomain";
        OrbisDataResponse expectedResponse = new OrbisDataResponse();

        when(orbisRestClient
                .post(eq("/v1/orbis/companies/data"), eq(OrbisDataResponse.class))
                .withHeader(eq("ApiToken"), eq(API_TOKEN))
                .withHeader(eq("Domain"), eq(domain))
                .withBody(any(OrbisDataRequest.class))
                .execute())
                .thenReturn(Mono.just(expectedResponse));

        OrbisDataResponse result = orbisService.getCompanyData(bvdId, domain).block();

        assertEquals(expectedResponse, result);

        verify(orbisRestClient).post(eq("/v1/orbis/companies/data"), eq(OrbisDataResponse.class));
    }

    @Test
    void shouldGetFinancials() {
        String bvdId = "testBvdId";
        String domain = "testDomain";
        OrbisFinancialsResponse expectedResponse = new OrbisFinancialsResponse();

        when(orbisRestClient
                .post(eq("/v1/orbis/companies/data"), eq(OrbisFinancialsResponse.class))
                .withHeader(eq("ApiToken"), eq(API_TOKEN))
                .withHeader(eq("Domain"), eq(domain))
                .withBody(any(OrbisDataRequest.class))
                .execute())
                .thenReturn(Mono.just(expectedResponse));

        OrbisFinancialsResponse result = orbisService.getFinancials(bvdId, domain).block();

        assertEquals(expectedResponse, result);

        verify(orbisRestClient).post(eq("/v1/orbis/companies/data"), eq(OrbisFinancialsResponse.class));
    }

    @Test
    void shouldGetOwnership() {
        String bvdId = "testBvdId";
        String domain = "testDomain";
        OrbisOwnershipResponse expectedResponse = new OrbisOwnershipResponse();

        when(orbisRestClient
                .post(eq("/v1/orbis/companies/data"), eq(OrbisOwnershipResponse.class))
                .withHeader(eq("ApiToken"), eq(API_TOKEN))
                .withHeader(eq("Domain"), eq(domain))
                .withBody(any(OrbisDataRequest.class))
                .execute())
                .thenReturn(Mono.just(expectedResponse));

        OrbisOwnershipResponse result = orbisService.getOwnership(bvdId, domain).block();

        assertEquals(expectedResponse, result);

        verify(orbisRestClient).post(eq("/v1/orbis/companies/data"), eq(OrbisOwnershipResponse.class));
    }

    @Test
    void shouldGetDirectors() {
        String bvdId = "testBvdId";
        String domain = "testDomain";
        OrbisDirectorsResponse expectedResponse = new OrbisDirectorsResponse();

        when(orbisRestClient
                .post(eq("/v1/orbis/companies/data"), eq(OrbisDirectorsResponse.class))
                .withHeader(eq("ApiToken"), eq(API_TOKEN))
                .withHeader(eq("Domain"), eq(domain))
                .withBody(any(OrbisDataRequest.class))
                .execute())
                .thenReturn(Mono.just(expectedResponse));

        OrbisDirectorsResponse result = orbisService.getDirectors(bvdId, domain).block();

        assertEquals(expectedResponse, result);

        verify(orbisRestClient).post(eq("/v1/orbis/companies/data"), eq(OrbisDirectorsResponse.class));
    }

    @Test
    void shouldMatchCompany() {
        SelectCompanyRequest request = new SelectCompanyRequest();
        SelectCompanyResponse[] expectedArray = new SelectCompanyResponse[]{ new SelectCompanyResponse() };

        when(orbisRestClient
                .post(eq("/v1/orbis/companies/match"), eq(SelectCompanyResponse[].class))
                .withHeader(eq("ApiToken"), eq(API_TOKEN))
                .withBody(any())
                .execute())
                .thenReturn(Mono.just(expectedArray));

        List<SelectCompanyResponse> result = orbisService.matchCompany(request).block();

        assertEquals(1, result.size());
        assertEquals(expectedArray[0], result.get(0));
    }
}
