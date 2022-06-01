package com.stdio.esm.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author AnhKhoa
 * @since 19/05/2022 - 11:11
 */
@Entity
@Table(name ="employee_skills")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeSkills {
    @EmbeddedId
    private EmployeeSkillsId  employeeSkillsId;

    @Column(name = "level_of_skill_id", nullable = false)
    private Integer levelOfSkill;

    @ManyToOne(fetch = FetchType.LAZY) // LAZY để tránh việc truy xuất dữ liệu không cần thiết. Lúc nào cần thì mới query
    @JsonIgnore
    @JoinColumn(name = "employee_id",referencedColumnName = "id",insertable = false,updatable = false)
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY) // LAZY để tránh việc truy xuất dữ liệu không cần thiết. Lúc nào cần thì mới query
    @JsonIgnore
    @JoinColumn(name = "skills_id",referencedColumnName = "id",insertable = false,updatable = false)
    private Skill skill;

    @Embeddable
    @Data
    public static class EmployeeSkillsId implements Serializable {

        private static final long serialVersionUID = -8886468907100754072L;

        @Column(name = "employee_id")
        private Long employeeId;

        @Column(name = "skills_id")
        private Long skillsId;
    }
}
