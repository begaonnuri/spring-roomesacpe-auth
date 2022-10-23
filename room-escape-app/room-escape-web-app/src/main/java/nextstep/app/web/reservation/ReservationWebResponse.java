package nextstep.app.web.reservation;

import nextstep.core.reservation.in.ReservationResponse;

import java.time.LocalDate;
import java.time.LocalTime;

class ReservationWebResponse {
    private final Long id;
    private final Long scheduleId;
    private final LocalDate date;
    private final LocalTime time;
    private final String name;

    private ReservationWebResponse(Long id, Long scheduleId, LocalDate date, LocalTime time, String name) {
        this.id = id;
        this.scheduleId = scheduleId;
        this.date = date;
        this.time = time;
        this.name = name;
    }

    public static ReservationWebResponse from(ReservationResponse reservation) {
        return new ReservationWebResponse(reservation.getId(), reservation.getScheduleId(), reservation.getDate(), reservation.getTime(), reservation.getName());
    }

    public Long getId() {
        return id;
    }

    public Long getScheduleId() {
        return scheduleId;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public String getName() {
        return name;
    }
}
