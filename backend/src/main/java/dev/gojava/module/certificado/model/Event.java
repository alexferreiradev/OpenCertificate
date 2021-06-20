package dev.gojava.module.certificado.model;

import java.util.Date;
import java.util.Objects;

public class Event extends BaseModel {

    private String name;
    private String executor;
    private String talkerTopics;
    private Date dateStarted;
    private Date dateEnded;

    public String getTalkerTopics() {
        return talkerTopics;
    }

    public void setTalkerTopics(String description) {
        this.talkerTopics = description;
    }

    public Date getDateStarted() {
        return dateStarted;
    }

    public void setDateStarted(Date dateStarted) {
        this.dateStarted = dateStarted;
    }

    public Date getDateEnded() {
        return dateEnded;
    }

    public void setDateEnded(Date dateEnded) {
        this.dateEnded = dateEnded;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExecutor() {
        return executor;
    }

    public void setExecutor(String executor) {
        this.executor = executor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(name, event.name) && Objects.equals(executor, event.executor) && Objects.equals(talkerTopics, event.talkerTopics) && Objects.equals(dateStarted,
                event.dateStarted) && Objects.equals(dateEnded, event.dateEnded);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, executor, talkerTopics, dateStarted, dateEnded);
    }

    @Override
    public String toString() {
        return "Event{" + "name='" + name + '\'' + ", executor='" + executor + '\'' + ", talkerTopics='" + talkerTopics + '\'' + ", dateStarted=" + dateStarted + ", dateEnded="
                + dateEnded + '}';
    }
}
