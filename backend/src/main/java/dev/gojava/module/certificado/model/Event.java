package dev.gojava.module.certificado.model;

import org.apache.commons.beanutils.converters.DateConverter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.time.Instant;
import java.util.Date;
import java.util.Objects;

@Entity
public class Event extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String name;
    @Column
    private String executor;
    @Column(name = "talker_topics")
    private String talkerTopics;
    @Column(name = "date_started")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateStarted;
    @Column(name = "date_ended")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateEnded;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
        return Objects.equals(id, event.id) && Objects.equals(name, event.name) && Objects.equals(executor, event.executor) && Objects.equals(talkerTopics, event.talkerTopics)
                && Objects.equals(dateStarted, event.dateStarted) && Objects.equals(dateEnded, event.dateEnded);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, executor, talkerTopics, dateStarted, dateEnded);
    }

    @Override
    public String toString() {
        return "Event{" + "id=" + id + ", name='" + name + '\'' + ", executor='" + executor + '\'' + ", talkerTopics='" + talkerTopics + '\'' + ", dateStarted=" + dateStarted
                + ", dateEnded=" + dateEnded + '}';
    }
}
