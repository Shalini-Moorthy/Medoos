package com.example.medoo;

public class reportquestions {
    private String typeofissue;
    private String duration;
    private String severity;
    private String mail;

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getTypeofissue() {
        return typeofissue;
    }

    public void setTypeofissue(String typeofissue) {
        this.typeofissue = typeofissue;
    }
}
