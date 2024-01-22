DROP TABLE IF EXISTS PRICES;

CREATE TABLE PRICES (
  id INT PRIMARY KEY,
  brand_id INT NOT NULL,
  start_date TIMESTAMP NOT NULL,
  end_date TIMESTAMP NOT NULL,
  product_id INT NOT NULL,
  priority INT NOT NULL,
  price NUMERIC NOT NULL,
  curr VARCHAR(3) NOT NULL,
  created_by VARCHAR(25) NOT NULL,
  last_modified_by VARCHAR(25) NOT NULL,
  created_date TIMESTAMP NOT NULL,
  last_modified_date TIMESTAMP NOT NULL
);

INSERT INTO PRICES (id, brand_id, start_date, end_date, product_id, priority, price, curr, created_by, last_modified_by, created_date, last_modified_date)
values (1,1,'2020-06-14T00:00:00','2020-12-31T23:59:59','35455',0,35.50,'EUR','User','User','2021-11-29T10:00:00','2021-11-29T10:00:00');
INSERT INTO PRICES (id, brand_id, start_date, end_date, product_id, priority, price, curr, created_by, last_modified_by, created_date, last_modified_date)
values (2,1,'2020-06-14T15:00:00','2020-06-14T18:30:00','35455',1,25.45,'EUR','User','User','2021-11-29T10:00:00','2021-11-29T10:00:00');
INSERT INTO PRICES (id, brand_id, start_date, end_date, product_id, priority, price, curr, created_by, last_modified_by, created_date, last_modified_date)
values (3,1,'2020-06-15T00:00:00','2020-06-15T11:00:00','35455',1,30.50,'EUR','User','User','2021-11-29T10:00:00','2021-11-29T10:00:00');
INSERT INTO PRICES (id, brand_id, start_date, end_date, product_id, priority, price, curr, created_by, last_modified_by, created_date, last_modified_date)
values (4,1,'2020-06-15T16:00:00','2020-12-31T23:59:59','35455',1,38.95,'EUR','User','User','2021-11-29T10:00:00','2021-11-29T10:00:00');