package javaProjects.HRMS.api.controllers.Users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javaProjects.HRMS.business.abstracts.Users.SystemEmployeeService;
import javaProjects.HRMS.core.utilities.results.DataResult;
import javaProjects.HRMS.core.utilities.results.Result;
import javaProjects.HRMS.entities.concretes.Users.SystemEmployee;

@RestController
@RequestMapping("api/systemEmployees")
@CrossOrigin
public class SystemEmployeesController {

	private final SystemEmployeeService systemEmployeeService;

	@Autowired
	public SystemEmployeesController(SystemEmployeeService systemEmployeeService) {
		super();
		this.systemEmployeeService = systemEmployeeService;
	}
	
	@GetMapping("/getAll")
	public DataResult<List<SystemEmployee>> getAll(){
		return this.systemEmployeeService.getAll();
	}
	
	@GetMapping("/getById")
	public DataResult<SystemEmployee> getById(Integer id){
		return this.systemEmployeeService.getById(id);
	}

	@PostMapping("/add")
	public Result add(@RequestBody SystemEmployee systemEmployee) {
		return this.systemEmployeeService.add(systemEmployee);
	}

	@PutMapping("/update")
	public Result update(@RequestBody SystemEmployee systemEmployee) {
		return this.systemEmployeeService.update(systemEmployee);
	}

	@DeleteMapping("/delete")
	public Result delete(@RequestBody Integer id) {
		return this.systemEmployeeService.delete(id);
	}
	
}
