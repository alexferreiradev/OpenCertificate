package dev.gojava.module.certificado.dto.mapper;

import dev.gojava.core.mapper.mapstruct.MapStructComponentModel;
import dev.gojava.module.certificado.dto.CertificateDTO;
import dev.gojava.module.certificado.model.Certificate;
import org.mapstruct.Mapper;

@Mapper(componentModel = MapStructComponentModel.CDI)
public interface CertificadoMapper {

    CertificateDTO toDTO(Certificate certificate);

}
