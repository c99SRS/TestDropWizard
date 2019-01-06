package com.opingoo.dao;

import com.opingoo.BO.User;
import com.opingoo.utils.AppLogger;
import com.opingoo.utils.GeneralConstants;
import com.opingoo.utils.HibernateCore;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public class UserDAO {

    private static AppLogger logger = AppLogger.getLogger();
    private static final String CLASSNAME = "UserDAO";


    public static void insert(User user) {
        Session session = HibernateCore.getSessionFactory().openSession();
        try {
            session.save(user);
            session.flush();
        } catch(Exception e) {
            logger.logInfo(CLASSNAME, "Exception in insert()");
            e.printStackTrace();
            throw e;
        } finally {
            session.close();
        }
    }

    public static void update(User user) {
        Session session = HibernateCore.getSessionFactory().openSession();
        try {
            session.update(user);
            session.flush();
        } catch(Exception e) {
            logger.logInfo(CLASSNAME, "Exception in update()");
            e.printStackTrace();
            throw e;
        } finally{
            session.close();
        }
    }


    public static User getUserByEmailOrMobileOrId(String emailId, String mobile, String id){

        User record = null;
        Session session = HibernateCore.getSessionFactory().openSession();
        try {
            Criteria criteria = session.createCriteria(User.class);

            if (emailId != null && !emailId.isEmpty())
                criteria.add(Restrictions.in("email",emailId));

            if (mobile != null && !mobile.isEmpty())
                criteria.add(Restrictions.in("mobile",mobile));

            if (id != null && !id.isEmpty())
                criteria.add(Restrictions.in("id",id));

            criteria.addOrder(Order.desc("createdAt"));

            if (!criteria.list().isEmpty())
                record = (User) criteria.list().get(0);
        } catch (Exception ex) {
            logger.logInfo(CLASSNAME, "Exception in PLB2BUsersDAO getUserByEmailOrMobileOrId()");
            ex.printStackTrace();
        }
        finally {
            session.close();
        }
        return record;
    }

    public static User getUserById(String id){

        User record = null;
        Session session = HibernateCore.getSessionFactory().openSession();
        try {
            Criteria criteria = session.createCriteria(User.class);

            if (id != null && !id.isEmpty())
                criteria.add(Restrictions.in("id",id));

            criteria.addOrder(Order.desc("createdAt"));

            if (!criteria.list().isEmpty())
                record = (User) criteria.list().get(0);
        } catch (Exception ex) {
            logger.logInfo(CLASSNAME, "Exception in PLB2BUsersDAO getUserByEmail()");
            ex.printStackTrace();
        }
        finally {
            session.close();
        }
        return record;
    }

    public static User getUserByEmail(String emailId){

        User record = null;
        Session session = HibernateCore.getSessionFactory().openSession();
        try {
            Criteria criteria = session.createCriteria(User.class);

            if (emailId != null && !emailId.isEmpty())
                criteria.add(Restrictions.in("email",emailId));

            criteria.addOrder(Order.desc("createdAt"));

            if (!criteria.list().isEmpty())
                record = (User) criteria.list().get(0);
        } catch (Exception ex) {
            logger.logInfo(CLASSNAME, "Exception in PLB2BUsersDAO getUserByEmail()");
            ex.printStackTrace();
        }
        finally {
            session.close();
        }
        return record;
    }

    public static User getUserByMobile(String mobile){

        User record = null;
        Session session = HibernateCore.getSessionFactory().openSession();
        try {
            Criteria criteria = session.createCriteria(User.class);

            if (mobile != null && !mobile.isEmpty())
                criteria.add(Restrictions.in("mobile",mobile));

            criteria.addOrder(Order.desc("createdAt"));

            if (!criteria.list().isEmpty())
                record = (User) criteria.list().get(0);
        } catch (Exception ex) {
            logger.logInfo(CLASSNAME, "Exception in PLB2BUsersDAO getUserByMobile()");
            ex.printStackTrace();
        }
        finally {
            session.close();
        }
        return record;
    }

    public static boolean isApprovedUser(String id) {
        User  user = getUserById(id);
        if(user != null) {
            if(user.getStatus() == GeneralConstants.ActivityStatus.ACTIVE.getValue())
                return true;
        }
        return false;
    }


}
