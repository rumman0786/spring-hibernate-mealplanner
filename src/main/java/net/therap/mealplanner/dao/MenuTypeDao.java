package net.therap.mealplanner.dao;

import net.therap.mealplanner.entity.MenuType;

import java.util.List;

/**
 * @author rumman
 * @since 10/16/16
 */
public interface MenuTypeDao {

    void initMenuType();

    List<MenuType> findAll();

    MenuType getMenuType(String type);

    MenuType getMenuType(int menuTypeId);
}
