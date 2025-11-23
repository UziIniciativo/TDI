package edu.unam.tdi.tarea03.repo;

import edu.unam.tdi.tarea03.model.MovTarjeta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovimientoTarjetaRepository extends JpaRepository<MovTarjeta, Long> {
}
