package com.stdio.esm.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity // Đánh dấu đây là table trong db
@Table(name = "employees_projects")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeesProjects {
    @EmbeddedId
    private EmployeesProjects.EmployeesProjectsId employeesProjectsId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "employee_id",referencedColumnName = "id",insertable = false,updatable = false)
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name="project_id",referencedColumnName = "id",insertable = false,updatable = false)
    private Project project;


    @Embeddable
    @Data
    public static class EmployeesProjectsId implements Serializable {

        private static final long serialVersionUID = -8886468907100754072L;

        @Column(name = "employee_id")
        private Long employeeId;

        @Column(name = "project_id")
        private Long projectId;
    }
}
