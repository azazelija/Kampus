package ru.kampus.repository.specifications;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import ru.kampus.entity.ReminderEntity;

import java.sql.Date;
import java.sql.Time;

@Component
public class ReminderSpecification {

    //yyyy-MM-dd
    public static Specification<ReminderEntity> matchDate(Date dateTime) {
        return (r, cq, cb) -> cb.equal(r.get("remindDate"), dateTime);
    }

    //HH:mm
    public static Specification<ReminderEntity> matchTime(Time dateTime) {
        return (r, cq, cb) -> cb.equal(r.get("remindTime"), dateTime);
    }
}
