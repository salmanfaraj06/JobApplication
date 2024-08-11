package com.example.JobWebsite.company;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<List<Company>> getAllCompanies() {
        List<Company> companies = companyService.getAllCompanies();
        return ResponseEntity.ok(companies);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getCompanyById(@PathVariable Long id) {
        Company company = companyService.getCompanyById(id);
        if (company == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Company not found");
        }
        return ResponseEntity.ok(company);
    }

    @PostMapping
    public ResponseEntity<String> createCompany(@RequestBody Company company) {
        companyService.createCompany(company);
        return ResponseEntity.status(HttpStatus.CREATED).body("Company created successfully");

    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCompany(@PathVariable Long id, @RequestBody Company company) {
        Company updatedCompany = companyService.updateCompany(company, id);
        if (updatedCompany == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Company not found");
        }
        return ResponseEntity.ok("Company updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCompany(@PathVariable Long id) {
        boolean isDeleted = companyService.deleteCompany(id);
        if (!isDeleted) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Company not found");
        }
        return ResponseEntity.ok("Company deleted successfully");
    }
}