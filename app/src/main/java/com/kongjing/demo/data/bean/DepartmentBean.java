package com.kongjing.demo.data.bean;

import java.util.List;

/**
 * Created by Jing on 17/10/1.
 */

public class DepartmentBean {

  private List<DataBean> data;

  public List<DataBean> getData() {
    return data;
  }

  public void setData(List<DataBean> data) {
    this.data = data;
  }

  public static class DataBean {
    /**
     * department : 1
     * name : 内科
     * id : 2
     */

    private String department;
    private String name;
    private String id;

    public String getDepartment() {
      return department;
    }

    public void setDepartment(String department) {
      this.department = department;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }
  }
}
