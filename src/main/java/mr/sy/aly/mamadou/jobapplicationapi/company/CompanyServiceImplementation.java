package mr.sy.aly.mamadou.jobapplicationapi.company;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImplementation implements CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyServiceImplementation(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id)
                .orElse(null);
    }

    @Override
    public Company addNewCompany(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public Company updateCompanyById(Long id, Company updatedCompanyData) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (optionalCompany.isPresent()) {
            Company company = optionalCompany.get();
            company.setName(updatedCompanyData.getName() == null ? company.getName() : updatedCompanyData.getName());
            company.setDescription(updatedCompanyData.getDescription() == null ? company.getDescription() : updatedCompanyData.getDescription());
            return companyRepository.save(company);
        }
        return null;
    }

    @Override
    public void deleteCompanyById(Long id) {
        companyRepository.deleteById(id);
    }
}
