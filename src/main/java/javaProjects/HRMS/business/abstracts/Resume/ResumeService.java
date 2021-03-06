package javaProjects.HRMS.business.abstracts.Resume;

import java.util.List;

import javaProjects.HRMS.core.business.abstracts.BaseService;
import javaProjects.HRMS.core.utilities.results.DataResult;
import javaProjects.HRMS.core.utilities.results.Result;
import javaProjects.HRMS.entities.concretes.Resume.Resume;
import javaProjects.HRMS.entities.dtos.ResumeAddDto;

public interface ResumeService extends BaseService<Resume, Integer> {
//	DataResult<List<Resume>> getAll();
	DataResult<Resume> getByCandidateId(Long id);
	void save(Resume resume);
	
}
