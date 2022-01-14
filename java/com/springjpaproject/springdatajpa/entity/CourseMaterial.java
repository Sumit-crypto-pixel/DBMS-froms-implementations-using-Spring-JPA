package com.springjpaproject.springdatajpa.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "course")
public class CourseMaterial {
    @Id
    @SequenceGenerator(
            name = "course_material_sequence",
            sequenceName = "course_material_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
         strategy = GenerationType.SEQUENCE,
         generator = "course_material_sequence"
    )
    private Long courseMaterialId;
    private String url;

    @OneToOne ( // one-to-one mapping
           cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            optional = false // if you try to save course then must provide course-material too
    )

    @JoinColumn(
            name = "course_id",
            referencedColumnName = "courseId"
    )
    private Course course;
}
