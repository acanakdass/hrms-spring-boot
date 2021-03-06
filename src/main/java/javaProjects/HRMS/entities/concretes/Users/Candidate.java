package javaProjects.HRMS.entities.concretes.Users;



import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javaProjects.HRMS.core.entities.User;
import javaProjects.HRMS.entities.concretes.Application;
import javaProjects.HRMS.entities.concretes.Resume.Resume;
import javaProjects.HRMS.entities.concretes.Verification.VerificationCodeCandidate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Collection;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "candidates")
public class Candidate extends User{

	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "identity_number")
	private String identityNumber;
	
	@Column(name = "birth_year")
	private int birthYear;
	
//	@ApiParam(hidden = true)
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "resume_id")
	private Resume resume;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "verification_id")
	private VerificationCodeCandidate verificationCodeCandidate;

	@OneToMany(mappedBy = "candidate")
	@JsonIgnore
	private Collection<Application> applications;
	
}