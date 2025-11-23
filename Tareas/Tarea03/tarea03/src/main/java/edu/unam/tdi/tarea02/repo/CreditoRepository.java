package edu.unam.tdi.tarea02.repo;

import edu.unam.tdi.tarea02.model.Credito;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditoRepository extends JpaRepository<Credito, Long> {}

