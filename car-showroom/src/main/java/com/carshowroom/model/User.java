package com.carshowroom.model;

import jakarta.persistence.*;
import lombok.*;
import com.carshowroom.utility.CarShowroomUtil;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private String role;

    private boolean enabled = true;

    @Column(name = "created_time")
    private String createdTime;

    @Column(name = "updated_time")
    private String updatedTime;

    @PrePersist
    protected void onCreate() {
        createdTime = CarShowroomUtil.changeCurrentTimeToLocalDateFromGmtToISTInString();
        updatedTime = CarShowroomUtil.changeCurrentTimeToLocalDateFromGmtToISTInString();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedTime = CarShowroomUtil.changeCurrentTimeToLocalDateFromGmtToISTInString();
    }

}
