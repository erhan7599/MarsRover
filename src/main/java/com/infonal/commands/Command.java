package com.infonal.commands;

public abstract class Command {

    private String success;
    private String error;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String pSuccess) {
        success = pSuccess;
    }

    public String getError() {
        return error;
    }

    public void setError(String pError) {
        error = pError;
    }
}
