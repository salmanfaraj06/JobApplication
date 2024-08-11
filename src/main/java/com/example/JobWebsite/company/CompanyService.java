package com.example.JobWebsite.company;

import java.util.List;

public interface CompanyService {
    List<Company> getAllCompanies();
    Company getCompanyById(Long id);
    void createCompany(Company company);
    Company updateCompany(Company company, Long id);
    boolean deleteCompany(Long id);
}