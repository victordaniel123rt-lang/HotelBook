package com.vdgarcia.HotelBook.repository;

import com.vdgarcia.HotelBook.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Long>{
    Cliente findByDni(Long dni);
}
