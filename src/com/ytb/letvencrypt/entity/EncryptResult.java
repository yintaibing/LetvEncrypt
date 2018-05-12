package com.ytb.letvencrypt.entity;

public class EncryptResult {
    public String output;
    public long seconds;

    public EncryptResult() {
        this("", 0L);
    }

    public EncryptResult(String output, long seconds) {
        this.output = output;
        this.seconds = seconds;
    }
}
