package com.acme.edu;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

public class LoggerSystemTest {
    //region Fixture
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final ByteArrayOutputStream err = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(out));
        System.setErr(new PrintStream(err));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
        System.setErr(null);
    }
    //endregion

    @Test
    public void shouldLogToConsoleWhenErrorMessage() {
        //region Given
        Logger sut = new Logger(
                new MessageContentLoggerFilter(),
                new ConsoleLoggerSaver()
        );
        //endregion

        //region When
        sut.log("ERROR: test message");
        //endregion

        //region Then
        assertThat(out.toString(),
                containsString("ERROR: test message"));
        //endregion
    }
}
