package com.HireMe;

/**
 * A Simple application to generate random CSV files and dump them into an S3
 * bucket so that Kinesis can grab them.
 */
public class Main {
    public static void main(String[] args) {
        CsvProcessor csvProcessor = new CsvProcessor();

        csvProcessor.generateCsvFile();
    }
}
