create table Purchase_transaction
(
   identifier integer not null,
   description varchar(255) not null,
   transactionDate DATE not null,
   amount DECIMAL(10,2) not null,
   primary key(identifier)
);