package com.stdio.esm.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "project_skills")
@Data
public class ProjectSkills {
    @EmbeddedId
    private ProjectSkills.ProjectSkillId projectSkillId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "project_id",referencedColumnName = "id",insertable = false,updatable = false)
    private Project project;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name="skills_id",referencedColumnName = "id",insertable = false,updatable = false)
    private Skill skill;
    @Embeddable
    @Data
    public static class ProjectSkillId implements Serializable {

        private static final long serialVersionUID = -8886468907100754072L;

        @Column(name = "project_id")
        private Long projectId;

        @Column(name = "skills_id")
        private Long skillsId;
    }
}
