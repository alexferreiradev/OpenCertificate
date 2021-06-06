package dev.gojava.core;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.ws.rs.core.Application;

@OpenAPIDefinition(
		info = @Info(title = "API para gerar certificados para comunidades", version = "1.0.0"),
		tags = {
				@Tag(name = "certificado", description = "Geração de certificados para eventos"),
				@Tag(name = "comunidade", description = "Geração de certificados para eventos de comunidades"),
		}
)
public class OpenCertificateApplication extends Application {
}
