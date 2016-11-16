package net.therap.mealplanner.dao;

import net.therap.mealplanner.entity.MenuType;
import net.therap.mealplanner.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by rumman on 10/18/16.
 */
@Repository
public class MenuTypeDaoImpl implements MenuTypeDao {
    String[] menuTypes = {"BREAKFAST", "LUNCH"};

    @Override
    @SuppressWarnings("unchecked")
    public void initMenuType() {
        SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        List<MenuType> menuTypeList = session.createCriteria(MenuType.class).list();
        tx.commit();
        session.close();

        if (menuTypeList.size() == 0) {
            insertMenuType(menuTypes);
        }
    }

    public boolean insertMenuType(String[] menuTypes) {
        SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
        for (String menuTypeString : menuTypes) {
            Session session = sessionFactory.openSession();
            MenuType menuType = new MenuType(menuTypeString);
            session.beginTransaction();
            session.save(menuType);
            session.getTransaction().commit();
            System.out.println("MenuType =" + menuType.getCategory());
            session.close();
        }
        return true;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<MenuType> findAll() {
        SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        List<MenuType> menuTypeList = session.createCriteria(MenuType.class).list();
        tx.commit();
        session.close();
        return menuTypeList;
    }

    public MenuType getMenuType(String type) {
        List<MenuType> menuTypeList = findAll();
        for (MenuType menuType : menuTypeList) {
            if (menuType.getCategory().equals(type)) {
                return menuType;
            }
        }
        return null;
    }

    public MenuType getMenuType(int menuTypeId) {
        List<MenuType> menuTypeList = findAll();
        for (MenuType menuType : menuTypeList) {
            if (menuType.getId() == menuTypeId) {
                return menuType;
            }
        }
        return null;
    }
}
