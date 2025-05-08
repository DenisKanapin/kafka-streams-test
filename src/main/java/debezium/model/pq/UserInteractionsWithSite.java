package debezium.model.pq;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "user_interactions_with_site")
public class UserInteractionsWithSite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long id;

    @Column(name = "page1", nullable = false)
    private boolean page1;
    @Column(name = "page2", nullable = false)
    private boolean page2;
    @Column(name = "page3", nullable = false)
    private boolean page3;
    @Column(name = "page1_button", nullable = false)
    private boolean page1_button;
    @Column(name = "page2_button", nullable = false)
    private boolean page2_button;
    @Column(name = "page3_button", nullable = false)
    private boolean page3_button;

}
