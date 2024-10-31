INSERT
INTO
  car
  (car_brand, price)
VALUES
  ('SKODA', 20000);

INSERT
INTO
  car
  (car_brand, price)
VALUES
  ('LADA', 10000);

INSERT
INTO
  car
  (car_brand, price)
VALUES
  ('SUBARU', 500000);

INSERT
INTO
  car
  (car_brand, price)
VALUES
  ('FORD', 300000);

INSERT
INTO
  person
  (name, password)
VALUES
  ('ivan', '{bcrypt}$2a$10$pT25M9mqjLmLK5sG9M.E5OPFRNlRSLMabnNYjH5iKGzQpSZdyFBnK');

INSERT
INTO
  person
  (name, password)
VALUES
  ('boris', '{bcrypt}$2a$10$pT25M9mqjLmLK5sG9M.E5OPFRNlRSLMabnNYjH5iKGzQpSZdyFBnK');

INSERT
INTO
  person
  (name, password)
VALUES
  ('alexey', '{bcrypt}$2a$10$pT25M9mqjLmLK5sG9M.E5OPFRNlRSLMabnNYjH5iKGzQpSZdyFBnK');

INSERT
INTO
  person
  (name, password)
VALUES
  ('olga', '{bcrypt}$2a$10$pT25M9mqjLmLK5sG9M.E5OPFRNlRSLMabnNYjH5iKGzQpSZdyFBnK');

INSERT
INTO
  person
  (name, password)
VALUES
  ('prohor', '{bcrypt}$2a$10$pT25M9mqjLmLK5sG9M.E5OPFRNlRSLMabnNYjH5iKGzQpSZdyFBnK');

INSERT
INTO
  person_roles
  (person_name, roles)
VALUES
  ('ivan', 'ROLE_CUSTOMER');

INSERT
INTO
  person_roles
  (person_name, roles)
VALUES
  ('boris', 'ROLE_BANK');

INSERT
INTO
  person_roles
  (person_name, roles)
VALUES
  ('alexey', 'ROLE_BANK');


INSERT
INTO
  person_roles
  (person_name, roles)
VALUES
  ('olga', 'ROLE_SALESMAN');

INSERT
INTO
  person_roles
  (person_name, roles)
VALUES
  ('prohor', 'ROLE_SALESMAN');