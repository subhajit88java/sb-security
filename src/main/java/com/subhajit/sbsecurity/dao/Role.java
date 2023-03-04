package com.subhajit.sbsecurity.dao;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role {
  @Id
  @SequenceGenerator(name = "role_Seq", sequenceName = "state_Seq", initialValue = 4, allocationSize = 1)
  @GeneratedValue(generator = "role_Seq")
  private Integer id;

  @Column(length = 20)
  private String name;

  public Role() {

  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}