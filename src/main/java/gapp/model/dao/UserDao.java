package gapp.model.dao;

import java.util.List;

import gapp.model.Additional;
import gapp.model.Application;
import gapp.model.Degree;
import gapp.model.Department;
import gapp.model.Doc;
import gapp.model.Program;
import gapp.model.User;

public interface UserDao 
{

	User getUser( Long long1 );

    List<User> getUsers();

	List<Department> getDepartments();

	List<Application> getApplication();

	User getUser();

	User register(User user);

	Department getDepartment(Long id);
	
	Program getProgram(Long id);

	Department saveDepartment(Department department);

	Program saveProgram(Program program);
	
	void delete(Program program);

	Additional getAdditional(Long id);

	void deleteA(Additional additional);

	Application saveApplication(Application application);

	Application getApplication(Long id);
	
	java.io.File getFileDirectory();

	Doc saveDoc(Doc doc);

	Program getProgramByName(String name);

	void deleteDegree(Degree degree);

	Degree getDegree(Long id);

	Department getDepartmentByName(String name);

	Doc getDoc(Long id);


	
}
