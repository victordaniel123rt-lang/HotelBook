package com.vdgarcia.HotelBook.repository;

import com.vdgarcia.HotelBook.entity.Habitacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HabitacionRepository extends JpaRepository<Habitacion,Long> {
}
