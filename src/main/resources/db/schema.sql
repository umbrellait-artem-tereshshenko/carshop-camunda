
CREATE TABLE car (
  car_brand VARCHAR(255) NOT NULL,
   price BIGINT NOT NULL,
   CONSTRAINT pk_car PRIMARY KEY (car_brand)
);

CREATE TABLE car_order (
  id UUID NOT NULL,
   car VARCHAR(255),
   status VARCHAR(255),
   is_loan BOOLEAN DEFAULT FALSE NOT NULL,
   total_price BIGINT NOT NULL,
   bank_approved BOOLEAN NOT NULL,
   driver_licence_approved BOOLEAN NOT NULL,
   driver_license_number VARCHAR(255) NOT NULL,
   buyer_id VARCHAR(255),
   manager_id VARCHAR(255),
   CONSTRAINT pk_car_order PRIMARY KEY (id)
);

CREATE TABLE car_order_additional_car_parts (
  car_order_id UUID NOT NULL,
   additional_car_parts VARCHAR(255)
);

CREATE TABLE person (
  name VARCHAR(255) NOT NULL,
   password VARCHAR(255) NOT NULL,
   CONSTRAINT pk_person PRIMARY KEY (name)
);

CREATE TABLE person_roles (
  person_name VARCHAR(255) NOT NULL,
  roles VARCHAR(255)
);


CREATE TABLE request_for_approval (
  id UUID NOT NULL,
   car_order_id UUID NOT NULL,
   person_name VARCHAR(255) NOT NULL,
   approved BOOLEAN,
   CONSTRAINT pk_request_for_approval PRIMARY KEY (id)
);

ALTER TABLE car_order ADD CONSTRAINT FK_CAR_ORDER_ON_BUYER_ID FOREIGN KEY (buyer_id) REFERENCES person (name);

ALTER TABLE car_order ADD CONSTRAINT FK_CAR_ORDER_ON_MANAGER_ID FOREIGN KEY (manager_id) REFERENCES person (name);

ALTER TABLE car_order ADD CONSTRAINT FK_CAR_ORDER_ON_CAR FOREIGN KEY (car) REFERENCES car (car_brand);

ALTER TABLE request_for_approval ADD CONSTRAINT FK_REQUEST_FOR_APPROVAL_ON_CAR_ORDER FOREIGN KEY (car_order_id) REFERENCES car_order (id);

ALTER TABLE request_for_approval ADD CONSTRAINT FK_REQUEST_FOR_APPROVAL_ON_PERSON_NAME FOREIGN KEY (person_name) REFERENCES person (name);

ALTER TABLE car_order_additional_car_parts ADD CONSTRAINT fk_carorder_additionalcarparts_on_car_order FOREIGN KEY (car_order_id) REFERENCES car_order (id);

ALTER TABLE person_roles ADD CONSTRAINT fk_person_roles_on_person FOREIGN KEY (person_name) REFERENCES person (name);