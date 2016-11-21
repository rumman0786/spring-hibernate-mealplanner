package net.therap.mealplanner.dao;

import net.therap.mealplanner.entity.MenuType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author rumman
 * @since 10/26/16
 */
@Repository
public class MenuTypeDaoImpl implements MenuTypeDao {

    private String[] menuTypes = {"BREAKFAST", "LUNCH", "DINNER"};

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @SuppressWarnings("unchecked")
    public void initMenuType() {
        List<MenuType> menuTypeList = entityManager.createQuery("from MenuType").getResultList();

        if (menuTypeList.size() == 0) {
            insertMenuType(menuTypes);
        }
    }

    @Transactional
    public boolean insertMenuType(String[] menuTypes) {

        for (String menuTypeString : menuTypes) {
            MenuType menuType = new MenuType(menuTypeString);
            entityManager.persist(menuType);
        }

        return true;
    }

    @Override
    @SuppressWarnings("unchecked")
    @Transactional
    public List<MenuType> findAll() {
        return entityManager.createQuery("from MenuType").getResultList();
    }

    @Override
    public MenuType getMenuType(String type) {
        List<MenuType> menuTypeList = findAll();

        for (MenuType menuType : menuTypeList) {
            if (menuType.getCategory().equals(type)) {
                return menuType;
            }
        }

        return null;
    }

    @Override
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
