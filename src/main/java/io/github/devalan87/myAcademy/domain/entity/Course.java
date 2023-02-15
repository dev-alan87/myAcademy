package io.github.devalan87.myAcademy.domain.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name = "tb_course")
@Data @AllArgsConstructor @NoArgsConstructor
public class Course {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    @Column(unique = true)
    private String code;

    private Integer hours;

    private String description;

    @Getter(AccessLevel.PRIVATE)
    @Column(columnDefinition = "boolean default false")
    private Boolean deprecated;

    @Getter(AccessLevel.PRIVATE)
    @Column(columnDefinition = "boolean default true")
    private Boolean enabled;

    public Boolean isDeprecated() {
        return deprecated;
    }
    public Boolean isEnabled() {
        return enabled;
    }

}
