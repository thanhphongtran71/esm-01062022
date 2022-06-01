package com.stdio.esm.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Entity // Đánh dấu đây là table trong db
@Table(name ="education")
//@Data // lombok giúp generate các hàm constructor, get, set v.v.
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Education {
    @Id //Đánh dấu là primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Giúp tự động tăng
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", length = 255, nullable = false, unique = false)
    private String name;

    @Column(name = "majors", length = 255, nullable = false, unique = false)
    private String majors;

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

    @ManyToOne(fetch = FetchType.LAZY) // LAZY để tránh việc truy xuất dữ liệu không cần thiết. Lúc nào cần thì mới query
    @JsonIgnore
    @JoinColumn(name = "employee_id",referencedColumnName = "id",insertable = false,updatable = false)
    private Employee employee;
}
