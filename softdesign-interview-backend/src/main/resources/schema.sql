CREATE TABLE public.books (
	   id BIGINT DEFAULT NOT NULL AUTO_INCREMENT,
	   name CHARACTER VARYING(100) NOT NULL,
	   author CHARACTER VARYING(100) NOT NULL,
	   rented BOOLEAN NOT NULL DEFAULT false,
	   PRIMARY KEY (id)
	   );

-- IMPORTANT: If using a real database I would define an appropriated index for the search endpoint query