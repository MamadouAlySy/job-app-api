package mr.sy.aly.mamadou.jobapplicationapi.company;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/companies")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<List<Company>> index() {
        return new ResponseEntity<>(
                companyService.getAllCompanies(),
                HttpStatus.OK
        );
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity show(@PathVariable Long id) {
        Company company = companyService.getCompanyById(id);
        if (company == null)
            return new ResponseEntity<>("Company not found.", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(company, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Company> create(@RequestBody Company company) {
        return new ResponseEntity<>(
                companyService.addNewCompany(company),
                HttpStatus.CREATED
        );
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody Company company) {
        Company updatedCompany = companyService.updateCompanyById(id, company);
        if (updatedCompany == null)
            return new ResponseEntity<>("Company not found.", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(updatedCompany, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        Company company = companyService.getCompanyById(id);
        if (company == null)
            return new ResponseEntity<>("Company not found.", HttpStatus.NOT_FOUND);
        companyService.deleteCompanyById(id);
        return new ResponseEntity<>(company, HttpStatus.OK);
    }

}
