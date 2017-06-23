package gapp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "applications")
public class Application implements Serializable{

	 private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue
    private Long id;
	
	//@ManyToOne
	@OneToOne
	private Program program;
	
	private String term;
	
	private String Submit_on;
	
	public Application()
	{
		degrees = new ArrayList<Degree>();
	}
	
	public String getSubmit_on() {
		return Submit_on;
	}

	public void setSubmit_on(String submit_on) {
		Submit_on = submit_on;
	}

	@ManyToOne
	private User applicant;
	
	@ManyToOne
	private Department department;
	
	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@OneToMany(cascade=CascadeType.ALL,mappedBy = "application")
	private List<Degree> degrees;
	
	private Double gpa;
	
	private Double tofel;
	
	private Double gre;
	
	@OneToOne
	private Doc transcript;
	
	public Doc getTranscript() {
		return transcript;
	}

	public void setTranscript(Doc transcript) {
		this.transcript = transcript;
	}

	private Status status;
	
	@OneToMany
	private List<Additional> additional;

	public List<Additional> getAdditional() {
		return additional;
	}

	public void setAdditional(List<Additional> additional) {
		this.additional = additional;
	}

	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public Program getProgram() {
		return program;
	}

	public void setProgram(Program program) {
		this.program = program;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public User getApplicant() {
		return applicant;
	}

	public void setApplicant(User applicant) {
		this.applicant = applicant;
	}

	

	public List<Degree> getDegrees() {
		return degrees;
	}

	public void setDegrees(List<Degree> degrees) {
		this.degrees = degrees;
	}

	public Double getGpa() {
		return gpa;
	}

	public void setGpa(Double gpa) {
		this.gpa = gpa;
	}

	public Double getTofel() {
		return tofel;
	}

	public void setTofel(Double tofel) {
		this.tofel = tofel;
	}

	public Double getGre() {
		return gre;
	}

	public void setGre(Double gre) {
		this.gre = gre;
	}

	

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
}
