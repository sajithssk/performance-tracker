package com.hr.performance.controller;

import com.hr.performance.dto.CycleSummaryDTO;
import com.hr.performance.service.CycleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cycles")
public class CycleController {

    @Autowired
    private CycleService cycleService;

    @GetMapping("/{id}/summary")
    public ResponseEntity<CycleSummaryDTO> getCycleSummary(@PathVariable Long id) {
        return ResponseEntity.ok(cycleService.getCycleSummary(id));
    }
}