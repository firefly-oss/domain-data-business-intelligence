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

package io.fireflysoftware.data.business.intelligence.sdk.api;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * Calls {@code POST /api/v1/business-intelligence/select-company}.
 * Enrichment is not served by this service.
 */
public class SelectCompanyApi {

    private final WebClient webClient;
    private final String basePath;

    public SelectCompanyApi(String basePath) {
        this.webClient = WebClient.create();
        this.basePath = basePath == null || basePath.isBlank()
                ? ""
                : (basePath.endsWith("/") ? basePath.substring(0, basePath.length() - 1) : basePath);
    }

    public Mono<JsonNode> selectCompany(Object request) {
        if (basePath.isBlank()) {
            return Mono.error(new IllegalStateException("PROPERTY_NOT_SET"));
        }
        return webClient.post()
                .uri(basePath + "/api/v1/business-intelligence/select-company")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(JsonNode.class);
    }
}
