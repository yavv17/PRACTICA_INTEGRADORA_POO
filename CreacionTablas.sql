CREATE TABLE personajes (
    id VARCHAR(10) PRIMARY KEY,
    nombre VARCHAR(30) NOT NULL,
    tipo_clase VARCHAR(20) NOT NULL,
    vida INT NOT NULL,
    vida_maxima INT NOT NULL,
    fuerza INT NOT NULL,
    poder INT NOT NULL,
    defensa INT NOT NULL,
    arma VARCHAR(40) NOT NULL,
    ruta_imagen VARCHAR(255),
    atributos_extra VARCHAR(255)
);


CREATE TABLE historial_batallas (
    id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    id_ganador VARCHAR(10) NOT NULL,
    nombre_ganador VARCHAR(30) NOT NULL,
    nombre_perdedor VARCHAR(30) NOT NULL,
    turnos_totales INT NOT NULL,
    resumen_combate VARCHAR(500),
    fecha_registro DATETIME NOT NULL
);