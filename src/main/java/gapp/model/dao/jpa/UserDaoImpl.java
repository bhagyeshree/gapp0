package gapp.model.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import gapp.model.Additional;
import gapp.model.Application;
import gapp.model.Degree;
import gapp.model.Department;
import gapp.model.Doc;
import gapp.model.Program;
import gapp.model.User;
import gapp.model.dao.UserDao;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    /*@Override
    public User getUser1( Integer id )
    {
        return entityManager.find( User.class, id );
    }*/

    @Override
    public List<User> getUsers()
    {
        return entityManager.createQuery( "from User order by id", User.class )
            .getResultList();
    }
    
    @Override
    public User getUser(){
		return entityManager.createQuery( "select s from User s WHERE s.FirstName = 'student1'", User.class )
	            .getSingleResult();
	}
    
    @Override
    public List<Department> getDepartments()
    {
        return entityManager.createQuery( "from Department order by id", Department.class )
            .getResultList();
    }
    
    @Override
	public List<Application> getApplication() {
		return entityManager.createQuery( "select s from Application s WHERE s.term = 'FALL 2016' AND s.department.Name = 'Accounting Department'", Application.class )
	            .getResultList();
	}

	@Override
	public User getUser(Long id) {
		// TODO Auto-generated method stub
		return entityManager.find( User.class, id );
	}
	
	@Override
	public Doc getDoc(Long id) {
		// TODO Auto-generated method stub
		return entityManager.find( Doc.class, id );
	}

	@Override
	public Department getDepartment(Long id) {
		// TODO Auto-generated method stub
		return entityManager.find( Department.class, id );
	}

	
	@Transactional
	@Override
	public User register(User user) {
		// TODO Auto-generated method stub
		return entityManager.merge(user);
	}
	
	@Override
    @Transactional
    public Department saveDepartment(Department department)
    {
        return entityManager.merge( department  );
    }
	
	@Override
    @Transactional
    public Program saveProgram(Program program)
    {
        return entityManager.merge( program );
    }
	
	
	@Override
    @Transactional
    public Doc saveDoc(Doc doc)
    {
        return entityManager.merge( doc);
    }
	
	@Override
    @Transactional
    public void delete(Program program)
    {
        entityManager.remove( program );
    }
	
	@Override
    @Transactional
    public void deleteDegree(Degree degree)
    {
        entityManager.remove( degree);
    }

	@Override
	public Program getProgram(Long id) {
		// TODO Auto-generated method stub
		return entityManager.find( Program.class, id );
	}
	
	@Override
	public Degree getDegree(Long id) {
		// TODO Auto-generated method stub
		return entityManager.find( Degree.class, id );
	}

	@Override
	public Additional getAdditional(Long id) {
		// TODO Auto-generated method stub
		return entityManager.find( Additional.class, id );
	}

	@Override
	 @Transactional
	public void deleteA(Additional additional) {
		// TODO Auto-generated method stub
		entityManager.remove( additional );
	}
	
	@Override
    @Transactional
    public Application saveApplication(Application application)
    {
        return entityManager.merge( application );
    }
	
	@Override
	public Application getApplication(Long id) {
		// TODO Auto-generated method stub
		return entityManager.find( Application.class, id );
	}
	
	 @Autowired
	    private ServletContext context;
	    
		@Override
		public java.io.File getFileDirectory() {
			String path = context.getRealPath("/WEB-INF/files");
	    	return new java.io.File(path);
		}
		
		@Override
	    public Program getProgramByName( String name )
	    {
	        String query = "from Program program where name = :name";

	        List<Program> p = entityManager.createQuery( query, Program.class )
	            .setParameter( "name", name )
	            .getResultList();
	        return p.size() == 0 ? null : p.get( 0 );
	    }

		@Override
	    public Department getDepartmentByName( String name )
	    {
	        String query = "from Department department where name = :name";

	        List<Department> p = entityManager.createQuery( query, Department.class )
	            .setParameter( "name", name )
	            .getResultList();
	        return p.size() == 0 ? null : p.get( 0 );
	    }


}