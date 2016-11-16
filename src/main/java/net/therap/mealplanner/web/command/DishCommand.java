package net.therap.mealplanner.web.command;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author rumman
 * @since 11/16/16
 */

public class DishCommand implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;

    private String name;

    private String calories;

    public DishCommand() {
    }

    public DishCommand(String name, String calories) {
        this.name = name;
        this.calories = calories;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

//    @Override
//    public boolean equals(Object object) {
//
//        boolean isEqual = false;
//        if (object != null && object instanceof Dish) {
//            int dishId = ((Dish) object).getId();
//            isEqual = this.id == dishId;
//        }
//
//        return isEqual;
//    }
//
//    @Override
//    public int hashCode() {
//        return this.getId() * 17;
//    }
//
//    @Override
//    public String toString() {
//
//        return "Dish{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", calories='" + calories + '\'' + '}';
//    }
}
