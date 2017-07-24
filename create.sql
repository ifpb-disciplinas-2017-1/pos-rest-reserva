CREATE TABLE autor(
  id serial NOT NULL,
  abreviacao character varying(50),
  email character varying(80),
  nome character varying(150),
  PRIMARY KEY (id)
);

CREATE TABLE livro(
  id serial NOT NULL,
  descricao character varying(255),
  edicao character varying(50),
  titulo character varying(100),
  PRIMARY KEY (id)
);

CREATE TABLE livro_autor(
  livro_id integer NOT NULL,
  autores_id integer NOT NULL,
  PRIMARY KEY (livro_id, autores_id),
  FOREIGN KEY (autores_id) REFERENCES autor (id) ,
  FOREIGN KEY (livro_id) REFERENCES livro (id)
);

CREATE TABLE reserva(
  id serial NOT NULL,
  cliente character varying(100),
  datadareserva date,
  status character varying(15),
  PRIMARY KEY (id)
);


CREATE TABLE reserva_livro(
  reserva_id integer NOT NULL,
  livros_id integer NOT NULL,
  PRIMARY KEY (reserva_id, livros_id),
  FOREIGN KEY (livros_id) REFERENCES livro (id),
  FOREIGN KEY (reserva_id) REFERENCES reserva (id)
);



