package dev.gojava.module.certificado.service.exporter.metadata;

import com.google.gson.Gson;
import dev.gojava.core.helper.DateHelper;
import dev.gojava.core.helper.FileHelper;
import dev.gojava.core.util.ParticipantUtil;
import dev.gojava.module.certificado.model.Certificate;
import dev.gojava.module.certificado.model.Participant;
import dev.gojava.module.certificado.model.metadata.CertificateMetadata;
import dev.gojava.module.certificado.model.metadata.JsonMetadata;
import dev.gojava.module.certificado.model.metadata.ParticipantMetadata;

import javax.enterprise.context.ApplicationScoped;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ApplicationScoped
public class JsonMetadataGenerator implements MetadataGenerator {

    @Override
    public byte[] createMetadataBytes(List<Certificate> certificateList) {
        JsonMetadata jsonMetadata = new JsonMetadata();
        // TODO - adicionar em validador primeiro
        jsonMetadata.setIntegrityKey("");
        List<CertificateMetadata> certificatesMetadata = new ArrayList<>();
        certificateList.forEach(certificate -> {
            CertificateMetadata certificateData = new CertificateMetadata();
            certificateData.setToken(certificate.getUuid());
            ParticipantMetadata participantData = new ParticipantMetadata();
            Participant participant = certificate.getParticipant();
            participantData.setIdentifier(ParticipantUtil.createIdentifier(participant));
            participantData.setName(ParticipantUtil.participantCompleteName(participant));
            certificateData.setParticipant(participantData);

            certificatesMetadata.add(certificateData);
        });
        jsonMetadata.setCertificates(certificatesMetadata);

        String json = new Gson().toJson(jsonMetadata);
        return json.getBytes();
    }

    /**
     * Cria o nome de arquivo utilizado pelo validador. Ao alterar, altere no validador.
     *
     * @param certificate model
     * @return nome de arquivo json valido
     */
    @Override
    public String getFileName(Certificate certificate) {
        Date createdDate = new Date();
        String createdDateFormat = DateHelper.format(createdDate, DateFormat.SHORT);
        String eventName = certificate.getParticipant().getEvent().getName();
        String eventExecutor = certificate.getParticipant().getEvent().getExecutor();

        eventName = FileHelper.formatToValidFileName(eventName);
        createdDateFormat = FileHelper.formatToValidFileName(createdDateFormat);

        return String.format("%s_%s_%s.json", eventName, eventExecutor, createdDateFormat);
    }
}
