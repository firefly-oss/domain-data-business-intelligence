package io.fireflysoftware.data.business.intelligence.web.controller;

import io.fireflysoftware.data.business.intelligence.infra.dtos.SelectCompanyRequest;
import io.fireflysoftware.data.business.intelligence.infra.services.OrbisService;
import io.fireflysoftware.data.business.intelligence.infra.dtos.SelectCompanyResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/v1/business-intelligence")
@RequiredArgsConstructor
@Tag(name = "Business Intelligence", description = "Business intelligence data queries")
public class BusinessIntelligenceController {

    private final OrbisService orbisService;

    @Operation(summary = "Select company", description = "Retrieve companies matching the selection criteria")
    @PostMapping("/select-company")
    public Mono<ResponseEntity<List<SelectCompanyResponse>>> selectCompany(@RequestBody SelectCompanyRequest request) {
        return orbisService.matchCompany(request)
                .map(ResponseEntity::ok);
    }
}
