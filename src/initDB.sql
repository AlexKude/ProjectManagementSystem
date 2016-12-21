CREATE TABLE companies
(
  ID serial NOT NULL,
  COMPANY_NAME character varying(20) NOT NULL,
  COMPANY_ADDRESS character varying(20),
  CONSTRAINT companies_pkey PRIMARY KEY (ID)
);

CREATE TABLE developers
(
  ID serial NOT NULL,
  SURNAME character varying(15) NOT NULL,
  NAME character varying(15) NOT NULL,
  FATHER_NAME character varying(15) NOT NULL,
  DATE_OF_BIRTH date NOT NULL,
  DATE_OF_JOIN date,
  ADDRESS character varying(20),
  COMPANY integer,
  CONSTRAINT developers_pkey PRIMARY KEY (ID),
  CONSTRAINT company_fkey FOREIGN KEY (COMPANY)
  REFERENCES companies (ID) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE customers
(
  ID serial NOT NULL,
  SURNAME character varying(15) NOT NULL,
  NAME character varying(15) NOT NULL,
  FATHER_NAME character varying(15) NOT NULL,
  COMPANY integer,
  CONSTRAINT customers_pkey PRIMARY KEY (ID),
  CONSTRAINT company_fkey FOREIGN KEY (COMPANY)
  REFERENCES companies (ID) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE projects
(
  ID serial NOT NULL,
  PROJECT_NAME character varying(50) NOT NULL,
  CONSTRAINT projects_pkey PRIMARY KEY (ID)
);

CREATE TABLE skills
(
  ID serial NOT NULL,
  SKILL_NAME character varying(20) NOT NULL,
  CONSTRAINT skills_pkey PRIMARY KEY (ID)
);

CREATE TABLE developers_skills
(
  developers integer NOT NULL,
  skills integer NOT NULL,
  CONSTRAINT developers_fkey FOREIGN KEY (DEVELOPERS)
  REFERENCES developers (ID) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT skills_fkey FOREIGN KEY (SKILLS)
  REFERENCES skills (ID) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE customers_projects
(
  CUSTOMER integer NOT NULL,
  PROJECT integer NOT NULL,
  CONSTRAINT customer_fkey FOREIGN KEY (CUSTOMER)
  REFERENCES customers (ID) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT projects_fkey FOREIGN KEY (PROJECT)
  REFERENCES projects (ID) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE projects_developers
(
  PROJECTS integer NOT NULL,
  DEVELOPERS integer NOT NULL,
  CONSTRAINT developers_fkey FOREIGN KEY (DEVELOPERS)
  REFERENCES developers (ID) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT projects_fkey FOREIGN KEY (PROJECTS)
  REFERENCES projects (ID) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION
)
