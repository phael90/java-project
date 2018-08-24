package db;

import models.Advert;
import models.Category;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class DBAdvert {
    private static Session session;

    public static List<Advert> getAllAdvertsbyCategory(Category category){
        session = HibernateUtil.getSessionFactory().openSession();
        List<Advert> results = null;
        try{
            Criteria cr = session.createCriteria(Advert.class);
            cr.add(Restrictions.eq("category", category));
            results = cr.list();
        }catch(HibernateException e){
            e.printStackTrace();
        }finally{
            session.close();
        }
        return results;
    }

}
