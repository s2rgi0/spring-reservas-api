package com.spring.reservas.api.event;

import com.spring.reservas.api.entity.Reserva;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ReservaEventListener {

    // 🕊️ Observador 1: Simula el envío de notificaciones al cliente
    @EventListener
    public void enviarConfirmacionEmail(ReservaConfirmadaEvent event) {
        Reserva reserva = event.getReserva();
        System.out.println("==========================================================");
        System.out.println("📧 ENVIANDO EMAIL: ¡Tu reserva ha sido confirmada con éxito!");
        System.out.println("📍 Espacio: " + reserva.getEspacio().getNombre());
        System.out.println("💰 Total Pagado: $" + reserva.getPrecio());
        System.out.println("==========================================================");
    }

    // 📊 Observador 2: Podría interactuar con tu sistema de reportes o auditoría
    @EventListener
    public void registrarAuditoria(ReservaConfirmadaEvent event) {
        Long reservaId = event.getReserva().getId();
        System.out.println("🔍 [AUDITORÍA]: Registro de reserva exitoso. ID: " + reservaId);
    }
}