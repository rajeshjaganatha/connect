package com.thoughtfocus.leave.test;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

import com.thoughtfocus.leave.dao.QueryDAO;
import com.thoughtfocus.leave.dao.QueryDAOImpl;

@SuppressWarnings("unused")
@RunWith(MockitoJUnitRunner.class)
public class QueryDAOTest extends AbstractTransactionalDataSourceSpringContextTests  {

	@Mock
	private SessionFactory sessionFactory;

	/** class under test */
    private QueryDAO dao;
 
    public QueryDAOTest() {
        super();
        ApplicationContext ctx = super.getApplicationContext();
        dao = (QueryDAO) ctx.getBean("bookmarkDAO");
        assertNotNull(dao);
    }
 
    @Override
    protected String[] getConfigLocations() {
        return new String[] { "applicationContext.xml" };
    }

	
	/*@Override
    protected void onSetUpInTransaction() throws Exception {
		Session session = sessionFactory.getCurrentSession();
	}*/
 
 
    @Test
	public void searchBookmarksIfSessionIsNull() {
		//QueryDAOImpl b = new QueryDAOImpl();

		Mockito.when(sessionFactory.getCurrentSession()).thenReturn(null);
/*		List<LeaveType> myList = b.searchBookmarks("select * from t_tags");
		System.out.println(myList);*/
	}
    
    /*@Test
    public final void testFindAll() {
        List<Bookmark> list = dao.searchBookmarks(null);
        assertNotNull("Expected a non null list", list);
        assertTrue("Expected a non-empty list", list.size()>0);
    }
	*/


}
