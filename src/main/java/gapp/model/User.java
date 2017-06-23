package gapp.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "users")
public class User implements Serializable{
	
	 private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;
    
    private String FirstName;
    
    private String LastName;
    
    private String Email;
    
    private String Password;
    
    @OneToMany(cascade=CascadeType.ALL,mappedBy = "applicant",fetch = FetchType.EAGER)
    private List<Application> applications;
    
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> roles;
    
    

	public List<Application> getApplications() {
		return applications;
	}

	public void setApplications(List<Application> applications) {
		this.applications = applications;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCitizenship() {
		return citizenship;
	}

	public void setCitizenship(String citizenship) {
		this.citizenship = citizenship;
	}

	public String getDateofbirth() {
		return dateofbirth;
	}

	public void setDateofbirth(String dateofbirth) {
		this.dateofbirth = dateofbirth;
	}

	private String phone;
    
    private String gender;
    
    private String citizenship;
    
    private String dateofbirth;
    

    
    public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public User()
    {
		roles = new HashSet<String>();
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	 public boolean isAdmin()
	    {
	        return roles.contains( "ADMIN" );
	    }

	    public boolean isStaff()
	    {
	        return roles.contains( "STAFF" );
	    }
   
	
   
}