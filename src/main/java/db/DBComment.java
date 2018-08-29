package db;

import models.Advert;
import models.Comment;
import models.User;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class DBComment {

    private static Session session;

    public static List<Comment> getAllCommentsForAdvert(Advert advert) {
        session = HibernateUtil.getSessionFactory().openSession();
        List<Comment> results = null;
        try {
            Criteria cr = session.createCriteria(Comment.class);
            cr.add(Restrictions.eq("advert", advert));
            results = cr.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return results;
    }

    public static List<Comment> getAllCommentsByUser(User user) {
        session = HibernateUtil.getSessionFactory().openSession();
        List<Comment> results = null;
        try {
            Criteria cr = session.createCriteria(Comment.class);
            cr.add(Restrictions.eq("user", user));
            results = cr.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return results;
    }


}
