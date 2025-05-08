package debezium.service;

import debezium.avro.AvroProduct;
import debezium.avro.AvroUserInters;
import debezium.model.mdb.UserInteracsMDB;
import debezium.model.mdb.UserInteracsMDBPage1MDB;
import debezium.model.mdb.UserInteracsMDBPage2MDB;
import debezium.model.mdb.UserInteracsMDBPage3MDB;
import debezium.model.pq.Product;
import debezium.repository.mdb.UserInteracsPage1MDBRepository;
import debezium.repository.mdb.UserInteracsPage2MDBRepository;
import debezium.repository.mdb.UserInteracsPage3MDBRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
public class UserInteracsProcessorService {

    private ForkJoinPool commonPool = ForkJoinPool.commonPool();

    private static AtomicInteger tempCount = new AtomicInteger(0);

    @Autowired
    private UserInteracsPage1MDBRepository userInteracsPage1MDBRepository;
    @Autowired
    private UserInteracsPage2MDBRepository userInteracsPage2MDBRepository;
    @Autowired
    private UserInteracsPage3MDBRepository userInteracsPage3MDBRepository;
    @Autowired
    private LinkedBlockingQueue<ConsumerRecord<String, UserInteracsMDBPage1MDB>> userInteracsPage1MDBs;
    @Autowired
    private LinkedBlockingQueue<ConsumerRecord<String, UserInteracsMDBPage2MDB>> userInteracsPage2MDBs;
    @Autowired
    private LinkedBlockingQueue<ConsumerRecord<String, UserInteracsMDBPage3MDB>> userInteracsPage3MDBs;

    @Transactional("transactionManagerMongoDB")
    public void processUserInteracs(List<AvroUserInters> userInteracs) {
        var convertedToMDBView = commonPool.invoke(new RecordsProcessorTask(userInteracs));
        convertedToMDBView.forEach((k, v) -> {
            if (k == UserInteracsMDBPage1MDB.class) {
                userInteracsPage1MDBRepository.saveAll((List<UserInteracsMDBPage1MDB>) (List<?>) v);
            } else if (k == UserInteracsMDBPage2MDB.class) {
                userInteracsPage2MDBRepository.saveAll((List<UserInteracsMDBPage2MDB>) (List<?>) v);
            } else {
                userInteracsPage3MDBRepository.saveAll((List<UserInteracsMDBPage3MDB>) (List<?>) v);
            }
        });
    }

    public List<Product> processPSQLProducts(List<AvroProduct> products) {
//        return commonPool.invoke(new RecordsProcessorTask(products));
        return null;
    }

    public static class RecordsProcessorTask extends RecursiveTask<Map<? extends Class<? extends UserInteracsMDB>, List<UserInteracsMDB>>> {

        private List<AvroUserInters> records;
        private static final int THRESHOLD = 100;

        public RecordsProcessorTask(List<AvroUserInters> userInteracsMDBS) {
            records = userInteracsMDBS;
        }

        @Override
        protected Map<? extends Class<? extends UserInteracsMDB>, List<UserInteracsMDB>> compute() {
            if (records.size() > THRESHOLD) {
                return ForkJoinTask.invokeAll(fragmentize())
                        .stream()
                        .map(ForkJoinTask::join)
                        .flatMap(map -> map.entrySet().stream())
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                                (l1, l2) -> Stream.concat(l1.stream(), l2.stream()).collect(Collectors.toList())));
            }
            return processUserInteracs(records);
        }

        private Collection<RecordsProcessorTask> fragmentize() {
            return List.of(new RecordsProcessorTask(records.subList(0, records.size() / 2)),
                    new RecordsProcessorTask(records.subList((records.size() / 2), records.size())));
        }

        private Map<? extends Class<? extends UserInteracsMDB>, List<UserInteracsMDB>> processUserInteracs(List<AvroUserInters> userInteracs) {

            var processedUserInteracts = userInteracs.stream()
                    .map(ui -> {
                                if (ui.getPage1()) {
                                    return UserInteracsMDBPage1MDB.builder()
                                            .id(ui.getUserId())
                                            .page1(ui.getPage1())
                                            .page1_button(ui.getPage1Button())
                                            .build();
                                } else if (ui.getPage2()) {
                                    return UserInteracsMDBPage2MDB.builder()
                                            .id(ui.getUserId())
                                            .page2(ui.getPage2())
                                            .page2_button(ui.getPage2Button())
                                            .build();
                                } else {
                                    return UserInteracsMDBPage3MDB.builder()
                                            .id(ui.getUserId())
                                            .page3(ui.getPage3())
                                            .page3_button(ui.getPage3Button())
                                            .build();
                                }
                            }

                    ).collect(Collectors.groupingBy(UserInteracsMDB::getClass));
            return processedUserInteracts;
        }
    }

}
