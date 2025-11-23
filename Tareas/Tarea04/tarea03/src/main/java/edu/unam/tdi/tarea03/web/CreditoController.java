package edu.unam.tdi.tarea03.web;

import edu.unam.tdi.tarea03.model.Credito;
import edu.unam.tdi.tarea03.model.MovTarjeta;
import edu.unam.tdi.tarea03.repo.CreditoRepository;
import edu.unam.tdi.tarea03.repo.MovimientoTarjetaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Controller
public class CreditoController {

    private final CreditoRepository creditoRepo;
    private final MovimientoTarjetaRepository movRepo;

    public CreditoController(CreditoRepository creditoRepo,MovimientoTarjetaRepository movRepo) {
        this.creditoRepo = creditoRepo;
        this.movRepo = movRepo;
    }

    // Muestra el formulario del crédito + la tabla de movimientos
    @GetMapping("/")
    public String formulario(Model model) {
        model.addAttribute("credito", new Credito());
        model.addAttribute("movimientos", movRepo.findAll());
        return "index"; // templates/index.html
    }

    // Recibe el crédito + los pagos, guarda todo y muestra la vista de resultado
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("credito") Credito credito, @RequestParam Map<String, String> params, Model model) {

        // 1) Guardar el crédito
        Credito creditoGuardado = creditoRepo.save(credito);

        // 2) Actualizar los pagos de cada movimiento
        List<MovTarjeta> movimientos = movRepo.findAll();

        for (int i = 0; i < movimientos.size(); i++) {
            // nombres de los campos que vamos a poner en el HTML
            String idStr   = params.get("pagos[" + i + "].id");
            String pagoStr = params.get("pagos[" + i + "].monto");

            if (idStr != null) {
                Long id = Long.valueOf(idStr);
                movRepo.findById(id).ifPresent(mov -> {
                    if (pagoStr != null && !pagoStr.isBlank()) {
                        mov.setPagos(new BigDecimal(pagoStr));
                    } else {
                        mov.setPagos(null); // sin pago
                    }
                    movRepo.save(mov);
                });
            }
        }

        // 3) Cargar de nuevo los movimientos ya actualizados
        List<MovTarjeta> movimientosActualizados = movRepo.findAll();

        model.addAttribute("credito", creditoGuardado);
        model.addAttribute("movimientos", movimientosActualizados);

        return "resultado"; // templates/resultado.html
    }
}
