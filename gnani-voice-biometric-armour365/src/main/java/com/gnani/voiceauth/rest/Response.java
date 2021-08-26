package com.gnani.voiceauth.rest;

public class Response {

    String message, status;
    int decision_code, spoof, status_code;

    public Response(String message, String status, int decision_code, int spoof, int status_code) {
        this.message = message;
        this.status = status;
        this.decision_code = decision_code;
        this.spoof = spoof;
        this.status_code = status_code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getDecision_code() {
        return decision_code;
    }

    public void setDecision_code(int decision_code) {
        this.decision_code = decision_code;
    }

    public int getSpoof() {
        return spoof;
    }

    public void setSpoof(int spoof) {
        this.spoof = spoof;
    }

    public int getStatus_code() {
        return status_code;
    }

    public void setStatus_code(int status_code) {
        this.status_code = status_code;
    }

    @Override
    public String toString() {
        return "Response{" +
                "message='" + message + '\'' +
                ", status='" + status + '\'' +
                ", decision_code=" + decision_code +
                ", spoof=" + spoof +
                ", status_code=" + status_code +
                '}';
    }
}
