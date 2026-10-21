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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class OrbisService {

    private final RestClient orbisRestClient;
    private final OrbisMapper orbisMapper;
    private final String apiToken;

    public OrbisService(RestClient orbisRestClient,
                        OrbisMapper orbisMapper,
                        @Value("${orbis.api-token}") String apiToken) {
        this.orbisRestClient = orbisRestClient;
        this.orbisMapper = orbisMapper;
        this.apiToken = apiToken;
    }

    public Mono<List<SelectCompanyResponse>> matchCompany(SelectCompanyRequest query) {
        return orbisRestClient
                .post("/v1/orbis/companies/match", SelectCompanyResponse[].class)
                .withHeader("ApiToken", apiToken)
                .withBody(orbisMapper.toOrbisMatchRequest(query))
                .execute()
                .map(List::of);
    }

    public Mono<OrbisDataResponse> getCompanyData(String bvdId, String domain) {
        return orbisRestClient
                .post("/v1/orbis/companies/data", OrbisDataResponse.class)
                .withHeader("ApiToken", apiToken)
                .withHeader("Domain", domain)
                .withBody(OrbisDataRequest.forBvDId(bvdId))
                .execute();
    }

    public Mono<OrbisFinancialsResponse> getFinancials(String bvdId, String domain) {
        return orbisRestClient
                .post("/v1/orbis/companies/data", OrbisFinancialsResponse.class)
                .withHeader("ApiToken", apiToken)
                .withHeader("Domain", domain)
                .withBody(OrbisDataRequest.forFinancials(bvdId))
                .execute();
    }

    public Mono<OrbisOwnershipResponse> getOwnership(String bvdId, String domain) {
        return orbisRestClient
                .post("/v1/orbis/companies/data", OrbisOwnershipResponse.class)
                .withHeader("ApiToken", apiToken)
                .withHeader("Domain", domain)
                .withBody(OrbisDataRequest.forOwnership(bvdId))
                .execute();
    }

    public Mono<OrbisDirectorsResponse> getDirectors(String bvdId, String domain) {
        return orbisRestClient
                .post("/v1/orbis/companies/data", OrbisDirectorsResponse.class)
                .withHeader("ApiToken", apiToken)
                .withHeader("Domain", domain)
                .withBody(OrbisDataRequest.forDirectors(bvdId))
                .execute();
    }
}
