package com.github.mkopylec.charon.configuration;

import java.time.Duration;

import static java.time.Duration.ofMillis;
import static org.springframework.util.Assert.isTrue;
import static org.springframework.util.Assert.notNull;

public class TimeoutConfiguration {

    private Duration connection;
    private Duration read;
    private Duration write;

    TimeoutConfiguration() {
        connection = ofMillis(200);
        read = ofMillis(1000);
        write = ofMillis(1000);
    }

    void validate() {
        notNull(connection, "No connection timeout set");
        isTrue(!connection.isNegative(), "Invalid connection timeout value: " + connection.toMillis() + " ms");
        notNull(read, "No read timeout set");
        isTrue(!read.isNegative(), "Invalid read timeout value: " + read.toMillis() + " ms");
        notNull(write, "No write timeout set");
        isTrue(!write.isNegative(), "Invalid write timeout value: " + write.toMillis() + " ms");
    }

    public Duration getConnection() {
        return connection;
    }

    void setConnection(Duration connection) {
        this.connection = connection;
    }

    public Duration getRead() {
        return read;
    }

    void setRead(Duration read) {
        this.read = read;
    }

    public Duration getWrite() {
        return write;
    }

    void setWrite(Duration write) {
        this.write = write;
    }
}
