package mr.sy.aly.mamadou.jobapplicationapi.company;

import java.util.List;

public interface CompanyService {
    public List<Company> getAllCompanies();

    public Company getCompanyById(Long id);

    public Company addNewCompany(Company company);

    public Company updateCompanyById(Long id, Company updatedCompanyData);

    public void deleteCompanyById(Long id);
}
