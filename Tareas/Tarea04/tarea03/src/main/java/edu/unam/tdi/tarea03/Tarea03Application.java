package edu.unam.tdi.tarea03;

import java.math.BigDecimal;
import java.time.LocalDate;

import edu.unam.tdi.tarea03.model.MovTarjeta;
import edu.unam.tdi.tarea03.repo.MovimientoTarjetaRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Tarea03Application {
    public static void main(String[] args) {
        SpringApplication.run(Tarea03Application.class, args);
    }

    @Bean
    CommandLineRunner initMovimientos(MovimientoTarjetaRepository repo) {
        return args -> {
            if (repo.count() == 0) {
                repo.save(crear("2021-01-01","enero","VISA","Peluquería","1000"));
                repo.save(crear("2021-01-02","enero","VISA","TV plasma (1 de 6)","2500"));
                repo.save(crear("2021-01-03","enero","MASTERCARD","Vajilla Kit","2500"));
                repo.save(crear("2021-01-04","enero","MASTERCARD","YouTube Premium","170"));
                repo.save(crear("2021-01-05","enero","VISA","Pasaje Londres","25000"));
            }
        };
    }

    private MovTarjeta crear(String fecha, String mes, String tarjeta,
                                    String transaccion, String gastos) {

        MovTarjeta m = new MovTarjeta();
        m.setFecha(LocalDate.parse(fecha));
        m.setMes(mes);
        m.setTarjeta(tarjeta);
        m.setTransaccion(transaccion);
        m.setGastos(new BigDecimal(gastos));
        return m;
    }

    }
