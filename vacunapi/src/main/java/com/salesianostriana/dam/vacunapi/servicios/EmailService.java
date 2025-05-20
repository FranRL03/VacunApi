package com.salesianostriana.dam.vacunapi.servicios;

import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.CreateEmailOptions;
import com.resend.services.emails.model.CreateEmailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
//@RequiredArgsConstructor
public class EmailService {

//    @Value("${resend.api.key}")
//    private final String apiKey;
//
//    Resend resend = new Resend(apiKey);

    private final Resend resend;

    public EmailService(@Value("${resend.api.key}") String apiKey) {
        this.resend = new Resend(apiKey);
    }

    public void senToEmail(String password, String email, String username) {

        String htmlContent = """
        <div style="font-family: Arial, sans-serif; padding: 20px;">
            <h2 style="color: #2E86C1;">Bienvenido a VacunApi</h2>
            <p>Hola <strong>%s</strong>,</p>
            <p>Su cuenta ha sido creada correctamente.</p>
            <p><strong>Su contraseña:</strong> %s</p>
            <p style="margin-top:20px;">Le recomendamos cambiar su contraseña tras el primer inicio de sesión.</p>
            <hr style="margin-top:30px;">
            <p style="font-size:12px; color:#888;">No responda a este correo. Para más ayuda, contacte con soporte.</p>
        </div>
        """.formatted(username, password);

        CreateEmailOptions params = CreateEmailOptions.builder()
                .from("VacunApi <onboarding@resend.dev>")
                .to(email)
                .subject("Cuenta creada en VacunApi")
                .html(htmlContent)
                .build();

        try {
            CreateEmailResponse data = resend.emails().send(params);
            System.out.println(data.getId());
        } catch (ResendException e) {
            e.printStackTrace();
        }
    }
}
