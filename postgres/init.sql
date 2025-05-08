CREATE TABLE IF NOT EXISTS products(
                                       PRODUCT_ID BIGINT GENERATED ALWAYS AS IDENTITY,
                                       NAME TEXT NOT NULL,
                                       DESCRIPTION TEXT NOT NULL,
                                       PRICE NUMERIC(100, 5),
    PRIMARY KEY (PRODUCT_ID)
    );

ALTER TABLE products REPLICA IDENTITY FULL;

INSERT INTO products(NAME, DESCRIPTION, PRICE)
VALUES ('Product One', 'Description One', 10.9);

CREATE PUBLICATION products_publication FOR TABLE products;


CREATE TABLE IF NOT EXISTS user_interactions_with_site(USER_ID BIGINT GENERATED ALWAYS AS IDENTITY,
    PAGE1 BOOLEAN NOT NULL,
    PAGE1_BUTTON BOOLEAN NOT NULL,
    PAGE2 BOOLEAN NOT NULL,
    PAGE2_BUTTON BOOLEAN NOT NULL,
    PAGE3 BOOLEAN NOT NULL,
    PAGE3_BUTTON BOOLEAN NOT NULL,
    PRIMARY KEY (USER_ID)

);

ALTER TABLE user_interactions_with_site REPLICA IDENTITY FULL;

INSERT INTO user_interactions_with_site(page1, page1_button, page3, page3_button, page2, page2_button)
VALUES (true, true, true, true, true, true);
/* (true, true, true, true, true, true),
 (true, true, true, true, true, true),
 (true, true, true, true, true, true),
 (true, true, true, true, true, true);*/

CREATE PUBLICATION user_interactions_publication FOR TABLE user_interactions_with_site;

SELECT pg_create_logical_replication_slot('postgres_debezium', 'pgoutput');
