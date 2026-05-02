package com.example.hrm.specification;

import com.example.hrm.entity.Holiday;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class HolidaySpecification {

    public static Specification<Holiday> filter(
            Integer year,
            Integer month,
            Boolean isPaid,
            Boolean isGlobal,
            String type
    ) {
        return (root, query, cb) -> {

            var predicate = cb.conjunction();

            // 👇 filter theo năm
            if (year != null) {
                LocalDate start = LocalDate.of(year, 1, 1);
                LocalDate end = LocalDate.of(year, 12, 31);

                predicate = cb.and(predicate,
                        cb.between(root.get("holidayDate"), start, end)
                );
            }

            // 👇 filter theo tháng
            if (year != null && month != null) {
                LocalDate start = LocalDate.of(year, month, 1);
                LocalDate end = start.withDayOfMonth(start.lengthOfMonth());

                predicate = cb.and(predicate,
                        cb.between(root.get("holidayDate"), start, end)
                );
            }

            // 👇 filter isPaid
            if (isPaid != null) {
                predicate = cb.and(predicate,
                        cb.equal(root.get("isPaid"), isPaid)
                );
            }

            // 👇 filter isGlobal
            if (isGlobal != null) {
                predicate = cb.and(predicate,
                        cb.equal(root.get("isGlobal"), isGlobal)
                );
            }

            // 👇 filter type
            if (type != null && !type.isEmpty()) {
                predicate = cb.and(predicate,
                        cb.like(cb.lower(root.get("type")),
                                "%" + type.toLowerCase() + "%")
                );
            }

            return predicate;
        };
    }
}