package dev.gojava.module.certificado.service.reader;

import dev.gojava.core.helper.DateHelper;
import dev.gojava.core.helper.StreamHelper;
import dev.gojava.module.certificado.command.ReaderCommand;
import dev.gojava.module.certificado.model.Event;
import dev.gojava.module.certificado.model.Participant;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

public class CsvParticipantReader implements ParticipantsReader {

    private static final int NAME_CSV_INDEX = 0;
    private static final int LASTNAME_CSV_INDEX = 1;
    private static final int IDENTITY_CSV_INDEX = 2;
    private static final int EVENTHOURS_CSV_INDEX = 3;
    private static final int EVENTEXECUTOR_CSV_INDEX = 4;
    private static final int EVENTNAME_CSV_INDEX = 5;
    private static final int EVENTTOPICS_CSV_INDEX = 6;
    private static final int EVENTDATESTARTED_CSV_INDEX = 7;
    private static final int EVENTDATEENDED_CSV_INDEX = 8;

    private String absoluteFilePath;

    public CsvParticipantReader(String absoluteFilePath) {
        this.absoluteFilePath = absoluteFilePath;
    }

    @Override
    public List<Participant> readParticipant(ReaderCommand command) {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File(absoluteFilePath));
            String text = StreamHelper.parseStream(inputStream);

            return buildParticipantList(text);
        } catch (IndexOutOfBoundsException | IOException e) {
            e.printStackTrace();
        } finally {
            StreamHelper.closeSafeInput(inputStream);
        }

        return null;
    }

    private List<Participant> buildParticipantList(String text) {
        List<Participant> participantList = new ArrayList<>();
        StringTokenizer stringTokenizer = new StringTokenizer(text, "\n");
        while (stringTokenizer.hasMoreTokens()) {
            String line = stringTokenizer.nextToken();
            Participant participant = buildParticipant(line);

            participantList.add(participant);
        }

        return participantList;
    }

    private Participant buildParticipant(String line) {
        Participant participant = new Participant();

        String[] colunms = line.split("[;|\t]");
        participant.setName(colunms[NAME_CSV_INDEX]);
        participant.setLastName(colunms[LASTNAME_CSV_INDEX]);
        participant.setHour(colunms[EVENTHOURS_CSV_INDEX]);
        String identity = colunms[IDENTITY_CSV_INDEX];
        if (identity.length() > 7) {
            participant.setCpf(identity);
        } else {
            participant.setRg(identity);
        }
        participant.setEvent(buildEvent(colunms));

        return participant;
    }

    private Event buildEvent(String[] colunm) {
        Event event = new Event();
        event.setName(colunm[EVENTNAME_CSV_INDEX]);
        event.setTalkerTopics(colunm[EVENTTOPICS_CSV_INDEX]);
        event.setExecutor(colunm[EVENTEXECUTOR_CSV_INDEX]);
        Date startedDate = DateHelper.parse(colunm[EVENTDATESTARTED_CSV_INDEX], DateFormat.SHORT);
        event.setDateStarted(startedDate);
        Date endedDate = DateHelper.parse(colunm[EVENTDATEENDED_CSV_INDEX], DateFormat.SHORT);
        event.setDateEnded(endedDate);

        return event;
    }
}
