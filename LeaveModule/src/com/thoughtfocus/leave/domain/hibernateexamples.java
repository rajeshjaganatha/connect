package com.thoughtfocus.leave.domain;
 
import java.util.Arrays;
import java.util.List;
 
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
 
import hibernate.model.Employee;
import hibernate.util.HibernateUtil;
 
public class HibernateCriteriaExamples {
 

         
        //Join example for selecting few columns
        criteria = session.createCriteria(Employee.class, "employee");
        criteria.setFetchMode("employee.address", FetchMode.JOIN);
        criteria.createAlias("employee.address", "address"); // inner join by default
 
        ProjectionList columns = Projections.projectionList()
                        .add(Projections.property("name"))
                        .add(Projections.property("address.city"));
        criteria.setProjection(columns);
 
        List<Object[]> list = criteria.list();
        for(Object[] arr : list){
            System.out.println(Arrays.toString(arr));
        }
         
         
        // Rollback transaction to avoid messing test data
        tx.commit();
        // closing hibernate resources
        sessionFactory.close();
    }
 
}