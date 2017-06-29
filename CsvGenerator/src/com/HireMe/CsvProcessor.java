package com.HireMe;

import com.amazonaws.services.kinesisfirehose.AmazonKinesisFirehose;
import com.amazonaws.services.kinesisfirehose.AmazonKinesisFirehoseClientBuilder;
import com.amazonaws.services.kinesisfirehose.model.PutRecordRequest;
import com.amazonaws.services.kinesisfirehose.model.Record;
import org.joda.time.DateTime;
import java.nio.ByteBuffer;
import java.util.Random;

/**
 * Created by Leyth Gorgeis on 6/27/2017.
 */
public class CsvProcessor {
    String deliveryStreamName = "CsvFileStream";

    final AmazonKinesisFirehose fireHoseClient = AmazonKinesisFirehoseClientBuilder.defaultClient();

    public void generateCsvFile() {
        int min = 0;
        int max = 100;
        Random rand = new Random();

        for(int i = 0; i < 100; i++){
            String data = String.valueOf(i) + ",Leyth" + (rand.nextInt((max - min) + 1) + min) + "," + DateTime.now().toString() + "\n";

            streamToKinesis(data);
        }
    }

    public void streamToKinesis(String message){
        PutRecordRequest putRecordRequest = new PutRecordRequest();
        putRecordRequest.setDeliveryStreamName(deliveryStreamName);

        Record record = new Record().withData(ByteBuffer.wrap(message.getBytes()));
        putRecordRequest.setRecord(record);

        fireHoseClient.putRecord(putRecordRequest);
    }
}
