package db;

import models.Comment;
import models.Rating;
import models.User;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class DBRating {

    private static Session session;

    public static List<Rating> getAllRatingsReceivedByUser(User user){
        session = HibernateUtil.getSessionFactory().openSession();
        List<Rating> results = null;
        try {
            Criteria cr = session.createCriteria(Rating.class);
            cr.add(Restrictions.eq("ratee", user));
            results = cr.list();
        } catch(HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return results;
    }

    public static List<Rating> getAllRatingsMadeByUser(User user){
        session = HibernateUtil.getSessionFactory().openSession();
        List<Rating> results = null;
        try {
            Criteria cr = session.createCriteria(Rating.class);
            cr.add(Restrictions.eq("rater", user));
            results = cr.list();
        } catch(HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return results;
    }

}
