package edu.unam.tdi.tarea03.repo;

import edu.unam.tdi.tarea03.model.Credito;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditoRepository extends JpaRepository<Credito, Long> {}

