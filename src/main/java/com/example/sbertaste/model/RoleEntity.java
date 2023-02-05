package com.example.sbertaste.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "role")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "common_generator", sequenceName = "role_id_seq", allocationSize = 1)
public class RoleEntity extends CommonEntity {
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
}
