CREATE TABLE Lista (
	cod integer(9) primary key AUTO_INCREMENT,
	descripcion varchar(200)
);

CREATE TABLE Item (
	cod_lista integer(9),
        cod_item integer(9) AUTO_INCREMENT,
	descripcion varchar(200),
	cantidad integer(3),
	FOREIGN KEY (cod_lista) REFERENCES Lista(cod),
	PRIMARY KEY(
     `cod_lista`,
     `cod_item`)
);

ALTER TABLE Item ADD check_item boolean default 0;


