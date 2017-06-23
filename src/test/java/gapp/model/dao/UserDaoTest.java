package gapp.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;

@Test(groups = "UserDaoTest")
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class UserDaoTest extends AbstractTransactionalTestNGSpringContextTests
{

//	@Autowired
//    UserDao userDao;
//
//   
//    
//    @Test
//    public void getDepartments()
//    {
//        assert userDao.getDepartments().size() == 2;
//    }
//    
//    @Test
//    public void getApplication()
//    {
//        assert userDao.getApplication().size() == 1;
//    }
//    
//    @Test
//    public void getUser()
//    {
//        assert userDao.getUser().getApplications().size() == 1;
//    }
	
}
