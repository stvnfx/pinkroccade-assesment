-- Insert Adam and Eve
INSERT INTO Person (id, birthdate, parent1_id, parent2_id, partner_id) VALUES (1, '1980-01-01', NULL, NULL, NULL);
INSERT INTO Person (id, birthdate, parent1_id, parent2_id, partner_id) VALUES (2, '1985-05-05', NULL, NULL, 1);
UPDATE Person SET partner_id = 2 where id = 1;
-- Insert Some Kids
INSERT INTO Person (id, birthdate, parent1_id, parent2_id, partner_id) VALUES (3, '2005-03-15', 1, 2, NULL);
INSERT INTO Person (id, birthdate, parent1_id, parent2_id, partner_id) VALUES (4, '2007-07-20', 1, 2, NULL);


INSERT INTO Person (id, birthdate, parent1_id, parent2_id, partner_id) VALUES (5, '1979-03-15', 1, 2, NULL);
INSERT INTO Person (id, birthdate, parent1_id, parent2_id, partner_id) VALUES (6, '1970-07-20', 1, 2, 5);
UPDATE Person SET partner_id = 6 where id = 5;

INSERT INTO Person (id, birthdate, parent1_id, parent2_id, partner_id) VALUES (7, '2002-03-15', 5, 6, NULL);
INSERT INTO Person (id, birthdate, parent1_id, parent2_id, partner_id) VALUES (8, '2015-07-20', 5, 6, NULL);
INSERT INTO Person (id, birthdate, parent1_id, parent2_id, partner_id) VALUES (9, '2006-03-15', 5, 6, NULL);
INSERT INTO Person (id, birthdate, parent1_id, parent2_id, partner_id) VALUES (10, '2016-07-20', 5, 6, NULL);


-- Insert Names for Persons
INSERT INTO Person_Names (person_id, names) VALUES (1, 'Adam');
INSERT INTO Person_Names (person_id, names) VALUES (2, 'Eve');
INSERT INTO Person_Names (person_id, names) VALUES (3, 'John');
INSERT INTO Person_Names (person_id, names) VALUES (4, 'Amy');

INSERT INTO Person_Names (person_id, names) VALUES (5, 'Arthur');
INSERT INTO Person_Names (person_id, names) VALUES (6, 'Evelyn');

INSERT INTO Person_Names (person_id, names) VALUES (7, 'Aurora');
INSERT INTO Person_Names (person_id, names) VALUES (8, 'Charlotte');
INSERT INTO Person_Names (person_id, names) VALUES (9, 'Genevieve');
INSERT INTO Person_Names (person_id, names) VALUES (10, 'Hazel');
-- INSERT INTO Person_Names (person_id, names) VALUES (4, 'Jasper');
-- INSERT INTO Person_Names (person_id, names) VALUES (4, 'Oliver');
-- INSERT INTO Person_Names (person_id, names) VALUES (4, 'Elijah');


-- INSERT INTO Person_Names (person_id, names) VALUES (5, 'Jane');
-- INSERT INTO Person_Names (person_id, names) VALUES (6, 'Alice');
-- INSERT INTO Person_Names (person_id, names) VALUES (7, 'Bob');

-- Insert Children Relationships (ManyToMany)
INSERT INTO Person_Children (person_id, children_id) VALUES (1, 3);
INSERT INTO Person_Children (person_id, children_id) VALUES (1, 4);
INSERT INTO Person_Children (person_id, children_id) VALUES (2, 3);
INSERT INTO Person_Children (person_id, children_id) VALUES (2, 4);

INSERT INTO Person_Children (person_id, children_id) VALUES (5, 7);
INSERT INTO Person_Children (person_id, children_id) VALUES (5, 8);
INSERT INTO Person_Children (person_id, children_id) VALUES (5, 9);
INSERT INTO Person_Children (person_id, children_id) VALUES (5, 10);
INSERT INTO Person_Children (person_id, children_id) VALUES (6, 7);
INSERT INTO Person_Children (person_id, children_id) VALUES (6, 8);
INSERT INTO Person_Children (person_id, children_id) VALUES (6, 9);
INSERT INTO Person_Children (person_id, children_id) VALUES (6, 10);