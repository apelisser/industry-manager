insert into COUNTRY (ID, NAME, ABBREV_NAME)
values ('01JM8JAY3GXTFEKFZZ6ZVHPVQA', 'Brasil', 'BR'),
       ('01JM8JBFRCJSSKG3AZYXZANF4C', 'Portugal', 'PT'),
       ('01JM8JBZK8K06X916Y21S7G7KT', 'Estados Unidos', 'EUA'),
       ('01JM8JD0EWGX1YSACBRYW5K5CF', 'França', 'FR');


insert into STATE (ID, NAME, ABBREV_NAME, COUNTRY_ID)
values ('01JM8JG7J7HVARCXCYA13Y288W', 'Paraná', 'PR', '01JM8JAY3GXTFEKFZZ6ZVHPVQA'),
       ('01JM8JGJB9AE3517NCEN7GEGCM', 'São Paulo', 'SP', '01JM8JAY3GXTFEKFZZ6ZVHPVQA'),
       ('01JM8JGY90MYVE2KHDPPFGTQSR', 'Santa Catarina', 'SC', '01JM8JAY3GXTFEKFZZ6ZVHPVQA'),
       ('01JM8JHB2FF5YDGG7EJQKYSK6W', 'Goiás', 'GO', '01JM8JAY3GXTFEKFZZ6ZVHPVQA');


insert into CITY (ID, NAME, STATE_ID)
values ('01JM8JS1AQNGQW9HCYSKY8V831', 'Cruzeiro do Sul', '01JM8JG7J7HVARCXCYA13Y288W'),
       ('01JM8JS9KRJ72W7SN627J1ZH0G', 'Paranavaí', '01JM8JG7J7HVARCXCYA13Y288W'),
       ('01JM8JTBX2FA6YJSCSBHG7892C', 'Presidente Prudente', '01JM8JGJB9AE3517NCEN7GEGCM'),
       ('01JM8JTTFEXHW1AJRCHMYGRRYR', 'São Paulo', '01JM8JGJB9AE3517NCEN7GEGCM');


insert into ORGANIZATION (ID, NAME, STATUS, DESCRIPTION)
values ('01JM8SMPC0PBS96JG5S3VEQSHS', 'Organization 01', 'ACTIVE', 'Organization 01 description'),
       ('01JM8T0QJHSKEP32YKCXFQ0P4K', 'Organization 02', 'ACTIVE', 'Organization 02 description'),
       ('01JM8T0VRRPR09AHV6J2F1PSMJ', 'Organization 03', 'ACTIVE', 'Organization 03 description');


--
-- insert into PERSON (ID, TYPE, CODE) values (1, 'CNPJ', '69856931000192');
-- insert into PERSON (ID, TYPE, CODE) values (2, 'CPF', '23362938035');
--
-- insert into COMPANY (ID, NAME, ALIAS, PERSON_ID, STATUS, OBSERVATION) values (1, 'Company 01', 'Comp01', 1, 'ACTIVE', null);
-- insert into COMPANY (ID, NAME, ALIAS, PERSON_ID, STATUS, OBSERVATION) values (2, 'Company 02', 'Comp02', 2, 'ACTIVE', 'obs02');
-- insert into COMPANY (ID, NAME, ALIAS, PERSON_ID, STATUS, OBSERVATION) values (3, 'Company 03', 'Comp03', 1, 'ACTIVE', 'obs03');
--
-- insert into DEPARTMENT (ID, NAME, DESCRIPTION, COMPANY_ID) values (1, 'Logística', null, 1);
-- insert into DEPARTMENT (ID, NAME, DESCRIPTION, COMPANY_ID) values (2, 'Produção', null, 1);
-- insert into DEPARTMENT (ID, NAME, DESCRIPTION, COMPANY_ID) values (3, 'Administrativo', null, 2);
--
-- insert into POSITION (ID, NAME, DESCRIPTION, STATUS, COMPANY_ID, SUPERIOR_ID) values (1, 'Presidente', 'Presidente geral', 'ACTIVE', 1, null);
-- insert into POSITION (ID, NAME, DESCRIPTION, STATUS, COMPANY_ID, SUPERIOR_ID) values (2, 'Diretor', 'Diretor geral', 'ACTIVE', 1, 1);
-- insert into POSITION (ID, NAME, DESCRIPTION, STATUS, COMPANY_ID, SUPERIOR_ID) values (3, 'Gerente Administrativo', 'Gerente Adm', 'ACTIVE', 1, 2);
-- insert into POSITION (ID, NAME, DESCRIPTION, STATUS, COMPANY_ID, SUPERIOR_ID) values (4, 'Gerente Industrial', 'Gerente Ind', 'ACTIVE', 1, 2);
-- insert into POSITION (ID, NAME, DESCRIPTION, STATUS, COMPANY_ID, SUPERIOR_ID) values (5, 'Gerente Qualidade', 'Gerente Qua', 'ACTIVE', 1, 2);
-- insert into POSITION (ID, NAME, DESCRIPTION, STATUS, COMPANY_ID, SUPERIOR_ID) values (6, 'Supervisor Laboratório', 'Supervisor lab', 'ACTIVE', 1, 5);
--
-- insert into EMPLOYEE (ID, NAME, EMAIL, USERNAME, PASSWORD, BADGE, POSITION_ID) values (1, 'User 01', 'user01@email.com', 'user01', '@111', '254', 1);
-- insert into EMPLOYEE (ID, NAME, EMAIL, USERNAME, PASSWORD, BADGE, POSITION_ID) values (2, 'User 02', 'user01@email.com', 'user02', '@222', '255', 2);
-- insert into EMPLOYEE (ID, NAME, EMAIL, USERNAME, PASSWORD, BADGE, POSITION_ID) values (3, 'User 03', 'user01@email.com', 'user03', '@333', '256', 3);
-- insert into EMPLOYEE (ID, NAME, EMAIL, USERNAME, PASSWORD, BADGE, POSITION_ID) values (4, 'User 04', 'user01@email.com', 'user04', '@444', '257', 6);
--
-- insert into EQUIPMENT(ID, NAME, BRAND, MODEL, SERIAL_NUMBER, DESCRIPTION, DEPARTMENT_ID) values (1, 'Maquina 01', 'GIS', 'Model-0001', 'SN111', null, 2);
-- insert into EQUIPMENT(ID, NAME, BRAND, MODEL, SERIAL_NUMBER, DESCRIPTION, DEPARTMENT_ID) values (2, 'Maquina 02', 'ARTET', 'Model-0002', 'SN222', null, 2);
--
-- insert into EQUIPMENT_PIECE (ID, NAME, CAPACITY, DESCRIPTION, EQUIPMENT_ID) values (1, 'Via-01', '16000', 'envasa 16 mil litros por hora', 1);
-- insert into EQUIPMENT_PIECE (ID, NAME, CAPACITY, DESCRIPTION, EQUIPMENT_ID) values (2, 'Via-01', '7000', 'envasa 07 mil litros por hora', 2);
--
-- insert into EVENT_TYPE (ID, NAME, ABBREVIATION, OBSERVATION, COMPANY_ID) VALUES (1, 'Parada externa à fábrica', 'pef', null, 1);
-- insert into EVENT_TYPE (ID, NAME, ABBREVIATION, OBSERVATION, COMPANY_ID) VALUES (2, 'Parada externa à linha', 'pel', null, 1);
-- insert into EVENT_TYPE (ID, NAME, ABBREVIATION, OBSERVATION, COMPANY_ID) VALUES (3, 'Parada devido a linha', 'pdl', null, 1);
--
-- insert into EVENT (ID, NAME, ABBREVIATION, OBSERVATION, COMPANY_ID, PARENT_EVENT_ID, STATUS) VALUES (1, 'Parada externa à fábrica', 'pef', null, 1, null, 'ACTIVE');
-- insert into EVENT (ID, NAME, ABBREVIATION, OBSERVATION, COMPANY_ID, PARENT_EVENT_ID, STATUS) VALUES (2, 'Parada externa à linha', 'pel', null, 1, null, 'ACTIVE');
-- insert into EVENT (ID, NAME, ABBREVIATION, OBSERVATION, COMPANY_ID, PARENT_EVENT_ID, STATUS) VALUES (3, 'Parada devido a linha', 'pdl', null, 1, null, 'ACTIVE');
-- insert into EVENT (ID, NAME, ABBREVIATION, OBSERVATION, COMPANY_ID, PARENT_EVENT_ID, STATUS) VALUES (4, 'Falta de energia', 'fen', null, 1, 1, 'ACTIVE');
-- insert into EVENT (ID, NAME, ABBREVIATION, OBSERVATION, COMPANY_ID, PARENT_EVENT_ID, STATUS) VALUES (5, 'Falta de água gelada', 'fag', null, 1, 2, 'ACTIVE');
-- insert into EVENT (ID, NAME, ABBREVIATION, OBSERVATION, COMPANY_ID, PARENT_EVENT_ID, STATUS) VALUES (6, 'Erro operacional', 'eop', null, 1, 3, 'ACTIVE');
-- insert into EVENT (ID, NAME, ABBREVIATION, OBSERVATION, COMPANY_ID, PARENT_EVENT_ID, STATUS) VALUES (7, 'Final de ciclo', 'fdc', null, 1, 3, 'ACTIVE');
-- insert into EVENT (ID, NAME, ABBREVIATION, OBSERVATION, COMPANY_ID, PARENT_EVENT_ID, STATUS) VALUES (8, 'CIP programado', 'cpr', null, 1, 7, 'ACTIVE');
--
-- insert into EQUIPMENT_DOWNTIME (ID, EQUIPMENT_ID, EVENT_ID, START_TIME, END_TIME, EMPLOYEE_ID, CREATED_AT, UPDATED_AT, OBSERVATION) values (1, 1, 7, '2024-12-23 10:00:00 +00:00', '2024-12-23 11:00:00 +00:00', 1, CURRENT_TIMESTAMP, null, 'Observação 01');
--
-- insert into EVENT_TIME (ID, EQUIPMENT_DOWNTIME_ID, EVENT_ID, TYPE, START_TIME, END_TIME, OBSERVATION) values (1, 1,8, 'PARALLEL', '2024-12-23 10:30:00 +00:00', '2024-12-23 11:00:00 +00:00', 'Observação 01');
-- insert into EVENT_TIME (ID, EQUIPMENT_DOWNTIME_ID, EVENT_ID, TYPE, START_TIME, END_TIME, OBSERVATION) values (2, 1,6, 'INTERNAL', '2024-12-23 10:10:00 +00:00', '2024-12-23 10:30:00 +00:00', 'Observação 01');