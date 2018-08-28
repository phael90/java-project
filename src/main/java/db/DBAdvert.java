package db;

import models.Advert;
import models.Category;
import models.Comment;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class DBAdvert {
    private static Session session;

    public static List<Advert> getAllAdvertsByCategory(Category category){
        session = HibernateUtil.getSessionFactory().openSession();
        List<Advert> results = null;
        try{
            Criteria cr = session.createCriteria(Advert.class);
            cr.add(Restrictions.eq("category", category));
            cr.add(Restrictions.eq("archived", false));
            results = cr.list();
        }catch(HibernateException e){
            e.printStackTrace();
        }finally{
            session.close();
        }
        return results;
    }

    public static List<Advert> getAllActiveAdverts(){
        session = HibernateUtil.getSessionFactory().openSession();
        List<Advert> results = null;
        try{
            Criteria cr = session.createCriteria(Advert.class);
            cr.add(Restrictions.eq("archived", false));
            results = cr.list();
        }catch(HibernateException e){
            e.printStackTrace();
        }finally{
            session.close();
        }
        return results;
    }

    public static List<Advert> getAllArchivedAdverts(){
        session = HibernateUtil.getSessionFactory().openSession();
        List<Advert> results = null;
        try{
            Criteria cr = session.createCriteria(Advert.class);
            cr.add(Restrictions.eq("archived", true));
            results = cr.list();
        }catch(HibernateException e){
            e.printStackTrace();
        }finally{
            session.close();
        }
        return results;
    }

    public static void archiveAdvert(Advert advert){
        advert.archive();
        DBHelper.update(advert);
    }

    public static List<Advert> getAllSearchedActiveAdverts(String searchEntry){
        session = HibernateUtil.getSessionFactory().openSession();
        List<Advert> results = null;
        try{
            Criteria cr = session.createCriteria(Advert.class);
            cr.add(Restrictions.eq("archived", false));
            cr.add(Restrictions.ilike("title", searchEntry, MatchMode.ANYWHERE));
            results = cr.list();
        }catch(HibernateException e){
            e.printStackTrace();
        }finally{
            session.close();
        }
        return results;
    }

    public static List<Advert> getAllActiveAdvertsAscendingPrice(){
        session = HibernateUtil.getSessionFactory().openSession();
        List<Advert> results = null;
        try{
            Criteria cr = session.createCriteria(Advert.class);
            cr.add(Restrictions.eq("archived", false));
            cr.addOrder(Order.asc("price"));
            results = cr.list();
        }catch(HibernateException e){
            e.printStackTrace();
        }finally{
            session.close();
        }
        return results;
    }

    public static List<Advert> getAllActiveAdvertsDescendingPrice(){
        session = HibernateUtil.getSessionFactory().openSession();
        List<Advert> results = null;
        try{
            Criteria cr = session.createCriteria(Advert.class);
            cr.add(Restrictions.eq("archived", false));
            cr.addOrder(Order.desc("price"));
            results = cr.list();
        }catch(HibernateException e){
            e.printStackTrace();
        }finally{
            session.close();
        }
        return results;
    }

    public static List<Advert> getAllActiveAdvertsAscendingPriceCategory(Category category){
        session = HibernateUtil.getSessionFactory().openSession();
        List<Advert> results = null;
        try{
            Criteria cr = session.createCriteria(Advert.class);
            cr.add(Restrictions.eq("archived", false));
            cr.add(Restrictions.eq("category", category));
            cr.addOrder(Order.asc("price"));
            results = cr.list();
        }catch(HibernateException e){
            e.printStackTrace();
        }finally{
            session.close();
        }
        return results;
    }

    public static List<Advert> getAllActiveAdvertsDescendingPriceCategory(Category category){
        session = HibernateUtil.getSessionFactory().openSession();
        List<Advert> results = null;
        try{
            Criteria cr = session.createCriteria(Advert.class);
            cr.add(Restrictions.eq("archived", false));
            cr.add(Restrictions.eq("category", category));
            cr.addOrder(Order.desc("price"));
            results = cr.list();
        }catch(HibernateException e){
            e.printStackTrace();
        }finally{
            session.close();
        }
        return results;
    }

}
