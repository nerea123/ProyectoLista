CREATE TABLE Lista (
	cod integer(9) primary key,
	descripcion varchar(200)
);

CREATE TABLE Item (
	cod_lista integer(9),
	descripcion varchar(200),
	cantidad integer(3),
	FOREIGN KEY (cod_lista) REFERENCES Lista(cod)
);

