package com.extralessonsapplication.lesson;
import com.extralessonsapplication.participation.Participation;
import com.extralessonsapplication.school.SchoolEntity;
import com.extralessonsapplication.user.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.Calendar;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class LessonEntity {
    @Id @GeneratedValue
    private Long id;
    private Date date;
    private String title;
    private String comment;
    @ManyToOne
    private UserEntity teacher;
    @ManyToOne
    private SchoolEntity school;
    private Boolean isActive;
    @OneToMany(mappedBy = "lesson")
    Set<Participation> participations;

    public Boolean isDateAvailableToUpdate(){
        Calendar instance = Calendar.getInstance();
        instance.setTime(this.getDate());
        instance.add(Calendar.DAY_OF_MONTH, 15);
        java.util.Date dateToUpdate = instance.getTime();
        return(dateToUpdate.compareTo(new java.util.Date()) >= 0);

    }

}
