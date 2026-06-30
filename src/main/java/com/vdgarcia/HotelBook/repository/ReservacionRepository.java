package com.vdgarcia.HotelBook.repository;

import com.vdgarcia.HotelBook.entity.Reservacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservacionRepository extends JpaRepository<Reservacion,Long> {
    boolean existsByHabitacionIdAndFechaEntradaBeforeAndFechaSalidaAfter(
            Long habitacionId, LocalDate fechaSalida, LocalDate fechaEntrada
    );
}
