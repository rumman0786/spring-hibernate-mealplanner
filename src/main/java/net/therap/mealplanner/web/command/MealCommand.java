package net.therap.mealplanner.web.command;

import net.therap.mealplanner.entity.Dish;
import net.therap.mealplanner.entity.MenuType;

import java.io.Serializable;
import java.util.List;

/**
 * @author rumman
 * @since 11/17/16
 */
public class MealCommand implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;

    private String name;

    private MenuType menuType;

    private List<Dish> dishList;

    private String day;

    public MealCommand() {
    }

    public MealCommand(MenuType menuType, String name, String day) {
        this.menuType = menuType;
        this.name = name;
        this.day = day;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MenuType getMenuType() {
        return menuType;
    }

    public void setMenuType(MenuType menuType) {
        this.menuType = menuType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public List<Dish> getDishList() {
        return dishList;
    }

    public void setDishList(List<Dish> dishSet) {
        this.dishList = dishSet;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    @Override
    public String toString() {

        return "id=" + id +
                ", menuType='" + menuType + '\'' +
                ", name='" + name + '\'' +
                ", dishes='" + dishList+ '\'' +
                ", day='" + day + '\'';
    }
}