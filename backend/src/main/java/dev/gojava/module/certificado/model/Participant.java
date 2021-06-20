package dev.gojava.module.certificado.model;

import java.util.Objects;

public class Participant extends BaseModel {

    private String name;
    private String lastName;
    private String rg;
    private String cpf;
    private String hour;
    private Event event;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Participant that = (Participant) o;
        return Objects.equals(name, that.name) && Objects.equals(lastName, that.lastName) && Objects.equals(rg, that.rg) && Objects.equals(cpf, that.cpf) && Objects.equals(hour,
                that.hour) && Objects.equals(event, that.event);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lastName, rg, cpf, hour, event);
    }

    @Override
    public String toString() {
        return "Participant{" + "name='" + name + '\'' + ", lastName='" + lastName + '\'' + ", rg='" + rg + '\'' + ", cpf='" + cpf + '\'' + ", hour='" + hour + '\'' + ", event="
                + event + '}';
    }
}
