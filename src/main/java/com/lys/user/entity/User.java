package com.lys.user.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="users")
@Getter @Setter @ToString
public class User {
    @Id // this field maps to the primary key in the table
    @GeneratedValue(strategy = GenerationType.IDENTITY) // values of id column will be generated
    // automatically by the database
    private Integer id;

    @Column(unique = true, length = 45) // max length is 45
    @NotNull
    private String email;

    @Column(length = 15)
    @NotNull
    private String password;

    @Column(length = 45, name = "first_name") // we can specify the name, default is field name, show in database
    @NotNull
    private String firstName;

    @Column(length = 45, name = "last_name")
    @NotNull
    private String lastName;

    private boolean enabled;
}
