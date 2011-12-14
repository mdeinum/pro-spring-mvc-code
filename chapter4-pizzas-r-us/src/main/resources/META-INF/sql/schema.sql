CREATE TABLE pizza (
  id IDENTITY PRIMARY KEY,
  name VARCHAR(40) not null,
  description VARCHAR(255) not null,
  price DECIMAL(8,2) not null
);