package javaProjects.HRMS.business.concretes.Users;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javaProjects.HRMS.business.abstracts.CandidateService;
import javaProjects.HRMS.core.adapters.concretes.MernisServiceAdapter;
import javaProjects.HRMS.core.business.concretes.BaseManager;
import javaProjects.HRMS.core.constants.Messages;
import javaProjects.HRMS.core.utilities.results.DataResult;
import javaProjects.HRMS.core.utilities.results.ErrorDataResult;
import javaProjects.HRMS.core.utilities.results.ErrorResult;
import javaProjects.HRMS.core.utilities.results.Result;
import javaProjects.HRMS.core.utilities.results.SuccessDataResult;
import javaProjects.HRMS.core.utilities.results.SuccessResult;
import javaProjects.HRMS.dataAccess.abstracts.CandidateDao;
import javaProjects.HRMS.dataAccess.abstracts.ResumeDao;
import javaProjects.HRMS.entities.concretes.Resume.Resume;
import javaProjects.HRMS.entities.concretes.Users.Candidate;
import javaProjects.HRMS.entities.concretes.Users.Employer;
import javaProjects.HRMS.entities.concretes.Verification.VerificationCodeCandidate;
import javaProjects.HRMS.entities.concretes.Verification.VerificationCodeEmployer;

@Service
public class CandidateManager extends BaseManager<CandidateDao, Candidate, Integer> implements CandidateService {

	private CandidateDao candidateDao;
	
	@Autowired
	public CandidateManager(CandidateDao candidateDao) {
		super(candidateDao, "Candidate");
		this.candidateDao=candidateDao;
	}


	@Override
	public DataResult<List<Candidate>> getAll() {
		
		List<Candidate> candidates = this.candidateDao.findAll();
		if(candidates.isEmpty()) {
			return new ErrorDataResult<>("Aday Bulunamadı");
		}
		return new SuccessDataResult<List<Candidate>>(candidates,"Adaylar Listelendi");
	}

	@Override
	public Result add(Candidate candidate) {

		if (CheckIfIdentityNumberExists(candidate.getIdentityNumber())) {
			
			if (CheckIfEmailExists(candidate.getEmail())) {
				if (IdentifyUserWithMernis(candidate)) {
					SetVerification(candidate);
					this.candidateDao.save(candidate);
					return new SuccessResult("Kullanıcı bilgileri mernis ile doğrulandı ve sisteme eklendi");
				} else {
					return new ErrorResult("Mernis kimlik bilgilerini doğrulayamadı");
				}
			}else {
				return new ErrorResult("Email Zaten Mevcut");
			}
		} else {
			return new ErrorResult("Tc Kimlik Numarası Sistemde Mevcut");
		}
	}
	

	@Override
	public DataResult<Candidate> getById(int id) {
		Optional<Candidate> candidate = this.candidateDao.findById(id);
		if (candidate.isEmpty()) {
			return new ErrorDataResult<Candidate>(Messages.notFound("Candidate"));
		}
		return new SuccessDataResult<Candidate>(candidate.get(),Messages.complete("Candidate"));	}
	

	@Override
	public DataResult<Candidate> getByEmail(String email) {
		Candidate candidate = this.candidateDao.getByEmail(email);
		if (candidate == null) {
			return new ErrorDataResult<Candidate>("Email'e ait kullanıcı bulunamadı");
		}
		return new SuccessDataResult<Candidate>(candidate, "Kullanıcı Bulundu");
	}

	@Override
	public DataResult<Candidate> getByIdentityNumber(String identityNumber) {
		Candidate candidate = this.candidateDao.getByIdentityNumber(identityNumber);
		if (candidate == null) {
			return new ErrorDataResult<Candidate>("Kullanıcı Bulunamadı");
		}
		return new SuccessDataResult<Candidate>(candidate, "Aday Listelendi");
	}
	
	@Override
	public void save(Candidate candidate) {
		this.candidateDao.save(candidate);
	}

		
	
	
//Business Rules methods
	private boolean CheckIfIdentityNumberExists(String identityNumber) {
		if (this.getByIdentityNumber(identityNumber).getData() == null) {
			return true;
		}
		return false;
	}

	private boolean CheckIfEmailExists(String email) {
		if (this.getByEmail(email).isSuccess()) {
			return false;
		}
		return true;
	}

	private boolean IdentifyUserWithMernis(Candidate candidate){
		MernisServiceAdapter mernisService = new MernisServiceAdapter();
		boolean mernisResult = mernisService.checkIfRealPerson(candidate);
		if (mernisResult) {
			return true;
		}
		return false;
	}

	private Candidate SetVerification(Candidate candidate) {
		UUID uuid = UUID.randomUUID();
		VerificationCodeCandidate verificationCodeCandidate =new VerificationCodeCandidate();
		verificationCodeCandidate.setVerified(false);
		verificationCodeCandidate.setCode(uuid.toString());
		candidate.setVerificationCodeCandidate(verificationCodeCandidate);
		return candidate;
	}

}