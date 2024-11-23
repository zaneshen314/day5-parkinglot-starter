package com.parkinglot;

import org.junit.jupiter.api.BeforeEach;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;

public class SuperSmartParkingBoyTest {

    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    @BeforeEach
    public void setup() {
        System.setOut(new PrintStream(outContent));
    }


    private String systemOut() {
        return outContent.toString();
    }
}
