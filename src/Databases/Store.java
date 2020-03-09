package Databases;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Store {
    private int position;
    private String space;
    private String group;
    private String action;
    private String buy;
    private int cost;
    private int rent_unimproved;
    private int rent_1house;
    private int rent_2house;
    private int rent_3house;
    private int rent_4house;
    private int rent_hotel;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getSpace() {
        return space;
    }

    public void setSpace(String space) {
        this.space = space;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getBuy() {
        return buy;
    }

    public void setBuy(String buy) {
        this.buy = buy;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getRent_unimproved() {
        return rent_unimproved;
    }

    public void setRent_unimproved(int rent_unimproved) {
        this.rent_unimproved = rent_unimproved;
    }

    public int getRent_1house() {
        return rent_1house;
    }

    public void setRent_1house(int rent_1house) {
        this.rent_1house = rent_1house;
    }

    public int getRent_2house() {
        return rent_2house;
    }

    public void setRent_2house(int rent_2house) {
        this.rent_2house = rent_2house;
    }

    public int getRent_3house() {
        return rent_3house;
    }

    public void setRent_3house(int rent_3house) {
        this.rent_3house = rent_3house;
    }

    public int getRent_4house() {
        return rent_4house;
    }

    public void setRent_4house(int rent_4house) {
        this.rent_4house = rent_4house;
    }

    public int getRent_hotel() {
        return rent_hotel;
    }

    public void setRent_hotel(int rent_hotel) {
        this.rent_hotel = rent_hotel;
    }

    @Override
    public String toString() {
        return "Store{" +
                "position=" + position +
                ", space='" + space + '\'' +
                ", group='" + group + '\'' +
                ", action='" + action + '\'' +
                ", buy=" + buy +
                ", cost=" + cost +
                ", rent_unimproved=" + rent_unimproved +
                ", rent_1house=" + rent_1house +
                ", rent_2house=" + rent_2house +
                ", rent_3house=" + rent_3house +
                ", rent_4house=" + rent_4house +
                ", rent_hotel=" + rent_hotel +
                '}';
    }
}



