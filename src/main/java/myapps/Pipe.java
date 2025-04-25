package myapps;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.state.StateSerdes;

import java.util.Properties;
import java.util.concurrent.CountDownLatch;

public class Pipe {

    public static void main(String[] args) {
        // Streams execution configurations
        Properties prop = new Properties();

        prop.put(StreamsConfig.APPLICATION_ID_CONFIG, "streams-pipe");
        prop.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");

        prop.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        prop.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());

        // Computational logic of Streams app as topology
        final StreamsBuilder builder = new StreamsBuilder(); //topology builder

        KStream<String, String> source = builder.stream("streams-plaintext-input");
        source.to("streams-pipe-output");

        Topology topology = builder.build();

        System.out.print(topology.describe());

        final KafkaStreams streams = new KafkaStreams(topology, prop); //Streams client

        final CountDownLatch latch = new CountDownLatch(1);

        Runtime.getRuntime()
                .addShutdownHook(new Thread("streams-shutdown-hook") {
                    @Override
                    public void run() {
                        streams.close();
                        latch.countDown();
                    }
                });

        try{
            streams.start();
            latch.await();
        } catch (Throwable w) {
            System.exit(1);
        }
        System.exit(0);
    }
}
