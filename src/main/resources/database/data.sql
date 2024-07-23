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

insert into COMPANY (ID, NAME, ALIAS, PERSON_ID, STATUS, OBSERVATION) values (1, 'Company 01', 'Comp01', 1, 'ACTIVE', null);
insert into COMPANY (ID, NAME, ALIAS, PERSON_ID, STATUS, OBSERVATION) values (2, 'Company 02', 'Comp02', 2, 'ACTIVE', 'obs02');
insert into COMPANY (ID, NAME, ALIAS, PERSON_ID, STATUS, OBSERVATION) values (3, 'Company 03', 'Comp03', 1, 'ACTIVE', 'obs03');

insert into DEPARTMENT (ID, NAME, DESCRIPTION, COMPANY_ID) values (1, 'Logística', null, 1);
insert into DEPARTMENT (ID, NAME, DESCRIPTION, COMPANY_ID) values (2, 'Produção', null, 1);
insert into DEPARTMENT (ID, NAME, DESCRIPTION, COMPANY_ID) values (3, 'Administrativo', null, 2);

insert into POSITION (ID, NAME, DESCRIPTION, STATUS, COMPANY_ID, SUPERIOR_ID) values (1, 'Presidente', 'Presidente geral', 'ACTIVE', 1, null);
insert into POSITION (ID, NAME, DESCRIPTION, STATUS, COMPANY_ID, SUPERIOR_ID) values (2, 'Diretor', 'Diretor geral', 'ACTIVE', 1, 1);
insert into POSITION (ID, NAME, DESCRIPTION, STATUS, COMPANY_ID, SUPERIOR_ID) values (3, 'Gerente Administrativo', 'Gerente Adm', 'ACTIVE', 1, 2);
insert into POSITION (ID, NAME, DESCRIPTION, STATUS, COMPANY_ID, SUPERIOR_ID) values (4, 'Gerente Industrial', 'Gerente Ind', 'ACTIVE', 1, 2);
insert into POSITION (ID, NAME, DESCRIPTION, STATUS, COMPANY_ID, SUPERIOR_ID) values (5, 'Gerente Qualidade', 'Gerente Qua', 'ACTIVE', 1, 2);
insert into POSITION (ID, NAME, DESCRIPTION, STATUS, COMPANY_ID, SUPERIOR_ID) values (6, 'Supervisor Laboratório', 'Supervisor lab', 'ACTIVE', 1, 5);
