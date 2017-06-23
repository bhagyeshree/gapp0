package gapp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "departments")
public class Department implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	private String Name;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "department")
	private List<Additional> additional;

	public Department() {
		programs = new ArrayList<Program>();
		additional = new ArrayList<Additional>();
	}

	public List<Additional> getAdditional() {
		return additional;
	}

	public void setAdditional(List<Additional> additional) {
		this.additional = additional;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	@OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
	private List<Program> programs;

	public List<Program> getPrograms() {
		return programs;
	}

	public void setPrograms(List<Program> programs) {
		this.programs = programs;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "department")
	private List<Application> applications;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Application> getApplications() {
		return applications;
	}

	public void setApplications(List<Application> applications) {
		this.applications = applications;
	}

}
