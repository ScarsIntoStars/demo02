package com.example.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "demo_table")
public class DemoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "studnet_name")
    private String name;

    @Column(length = 30, nullable = false, unique = true)
    private String mobile;

    @Column
    private String studentAddress;
}
