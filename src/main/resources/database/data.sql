insert into COUNTRY (ID, NAME, ABBREV_NAME) values (1, 'Brasil', 'BR');
insert into COUNTRY (ID, NAME, ABBREV_NAME) values (2, 'Portugal', 'PT');
insert into COUNTRY (ID, NAME, ABBREV_NAME) values (3, 'Estados Unidos', 'EUA');
insert into COUNTRY (ID, NAME, ABBREV_NAME) values (4, 'França', 'FR');

insert into STATE (ID, NAME, ABBREV_NAME, COUNTRY_ID) values (1, 'Paraná', 'PR', 1);
insert into STATE (ID, NAME, ABBREV_NAME, COUNTRY_ID) values (2, 'São Paulo', 'SP', 1);
insert into STATE (ID, NAME, ABBREV_NAME, COUNTRY_ID) values (3, 'Santa Catarina', 'SC', 1);
insert into STATE (ID, NAME, ABBREV_NAME, COUNTRY_ID) values (4, 'Goiás', 'GO', 1);

insert into CITY (ID, NAME, STATE_ID) values (1, 'Cruzeiro do Sul', 1);
insert into CITY (ID, NAME, STATE_ID) values (2, 'Paranavaí', 1);
insert into CITY (ID, NAME, STATE_ID) values (3, 'Presidente Prudente', 2);
insert into CITY (ID, NAME, STATE_ID) values (4, 'São Paulo', 2);

insert into PERSON (ID, TYPE, CODE) values (1, 'CNPJ', '69856931000192');
insert into PERSON (ID, TYPE, CODE) values (2, 'CPF', '23362938035');
