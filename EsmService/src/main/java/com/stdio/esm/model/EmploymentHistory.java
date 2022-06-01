package com.stdio.esm.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name ="employment_history")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmploymentHistory {
    @Id //Đánh dấu là primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Giúp tự động tăng
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "job_title", length = 255, nullable = false, unique = false)
    private String jobTitle;

    @Column(name = "employer", length = 255, nullable = false, unique = false)
    private String employer;

    @Column(name = "description ", nullable = true, unique = false)
    private String description ;

    @Column(name = "employee_id", nullable = false)
    private Long employeeId;

    @CreationTimestamp
    @Column(name = "start_date",nullable = false,updatable = false)
    private Instant startDate;

    @UpdateTimestamp
    @Column(name = "end_date",nullable = false)
    private Instant endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "employee_id",referencedColumnName = "id",insertable = false,updatable = false)
    private Employee employee;
}
