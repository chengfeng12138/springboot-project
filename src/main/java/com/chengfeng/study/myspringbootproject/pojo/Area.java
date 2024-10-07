package com.chengfeng.study.myspringbootproject.pojo;

import java.util.Objects;

/**
 * Area class
 * 区域
 * @author chengfeng
 * @date 2022/2/20 /0020 18:33
 */
public class Area {

    public String name;
    public int id;
    public int parent_id;

    public Area() {
    }

    public Area(String name, int id, int parent_id) {
        this.name = name;
        this.id = id;
        this.parent_id = parent_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Area area = (Area) o;
        return id == area.id && parent_id == area.parent_id && Objects.equals(name, area.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id, parent_id);
    }

    @Override
    public String toString() {
        return "Area{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", parent_id=" + parent_id +
                '}';
    }
}
