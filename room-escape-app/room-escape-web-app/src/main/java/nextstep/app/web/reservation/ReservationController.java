package nextstep.app.web.reservation;

import nextstep.app.web.auth.LoginMember;
import nextstep.core.reservation.in.ReservationResponse;
import nextstep.core.reservation.in.ReservationUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RequestMapping("/reservations")
@RestController
class ReservationController {
    private final ReservationUseCase useCase;

    public ReservationController(ReservationUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody ReservationCreateWebRequest request, @LoginMember Long memberId) {
        ReservationResponse reservation = useCase.create(request.to(), memberId);
        return ResponseEntity
                .created(URI.create("/reservations/" + reservation.id()))
                .build();
    }

    @GetMapping
    public ResponseEntity<List<ReservationWebResponse>> list(@RequestParam Long themeId, @RequestParam String date) {
        List<ReservationResponse> reservations = useCase.findReservations(themeId, LocalDate.parse(date));
        return ResponseEntity.ok(
                reservations.stream()
                        .map(ReservationWebResponse::from)
                        .toList()
        );
    }

    @DeleteMapping("/{reservationId}")
    public ResponseEntity<Void> delete(@PathVariable Long reservationId, @LoginMember Long memberId) {
        useCase.delete(reservationId, memberId);
        return ResponseEntity.noContent().build();
    }
}
