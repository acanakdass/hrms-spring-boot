package javaProjects.HRMS.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javaProjects.HRMS.business.abstracts.JobAdvertisementService;
import javaProjects.HRMS.core.utilities.results.DataResult;
import javaProjects.HRMS.core.utilities.results.Result;
import javaProjects.HRMS.entities.concretes.JobAdvertisement.JobAdvertisement;
import javaProjects.HRMS.entities.dtos.JobAdvertisementAddDto;

@RestController
@RequestMapping("api/jobAdvertisements")
public class JobAdvertisementsController {

	private JobAdvertisementService jobAdvertisementService;

	@Autowired
	public JobAdvertisementsController(JobAdvertisementService jobAdvertisementService) {
		super();
		this.jobAdvertisementService = jobAdvertisementService;
	}
	
	@GetMapping("/getall")
	public DataResult<List<JobAdvertisement>> getAll(){
		return this.jobAdvertisementService.getAll();
	}
	
	@GetMapping("/getallactive")
	public DataResult<List<JobAdvertisement>> getAllActive(){
		return this.jobAdvertisementService.getAllActive();
	}
	
	@GetMapping("/getallactiveandconfirmed")
	public DataResult<List<JobAdvertisement>> getAllActiveAndConfirmed(){
		return this.jobAdvertisementService.getAllActiveAndConfirmed();
	}
	
	@GetMapping("/getAllActiveByAscReleaseDate")
	public DataResult<List<JobAdvertisement>> getAllActiveByAscReleaseDate(){
		return this.jobAdvertisementService.getAllActiveByAscReleaseDate();
	}
	
	@GetMapping("/getAllActiveByDescReleaseDate")
	public DataResult<List<JobAdvertisement>> getAllActiveByDescReleaseDate(){
		return this.jobAdvertisementService.getAllActiveByDescReleaseDate();
	}
	
	@GetMapping("/getAllActiveByCompanyName")
	public DataResult<List<JobAdvertisement>> getAllActiveByCompanyName(String companyName){
		return this.jobAdvertisementService.getAllActiveByCompanyName(companyName);
	}
	
	@PostMapping("/add")
    public Result add(@RequestBody JobAdvertisementAddDto jobAdvertisementAddDto){
        return this.jobAdvertisementService.add(jobAdvertisementAddDto);
    }
	
	@GetMapping("/setActive")
    public Result unactive(int jobAdvertisementId){
        return this.jobAdvertisementService.setActive(jobAdvertisementId);
    }
	
	@GetMapping("/setPassive")
	public Result setPassive(int jobAdvertisementId){
		return this.jobAdvertisementService.setPassive(jobAdvertisementId);
	}
}