CREATE DATABASE IF NOT EXISTS CinepolisBD;
USE CinepolisBD;

-- Creación de tablas

CREATE TABLE Ciudades (
    ciudad_id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL
);

CREATE TABLE Sucursales (
    sucursal_id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL UNIQUE,
    direccion VARCHAR(255) NOT NULL,
    ubicacion POINT NOT NULL,
    ciudad_id INT,
    FOREIGN KEY (ciudad_id) REFERENCES Ciudades(ciudad_id)
);

CREATE TABLE Salas (
    sala_id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    numAsientos INT NOT NULL,
    duracionLimpieza INT NOT NULL,
	estaEliminado bit(1) default 0,
    sucursal_id INT,
    FOREIGN KEY (sucursal_id) REFERENCES Sucursales(sucursal_id)
);

CREATE TABLE Clientes (
    cliente_id INT AUTO_INCREMENT PRIMARY KEY,
    nombres VARCHAR(255) NOT NULL,
	apellidoPA VARCHAR(255) NOT NULL,
    apellidoMA VARCHAR(255) NOT NULL,
    correo VARCHAR(255) NOT NULL,
    contraseña VARCHAR(255) NOT NULL,
    fechaNacimiento DATE NOT NULL,
    ubicacion POINT,
    estaEliminado bit(1) default 0,
    ciudad_id INT not null,
    FOREIGN KEY (ciudad_id) REFERENCES Ciudades(ciudad_id)
);

CREATE TABLE Mensajes (
   mensajes_id INT AUTO_INCREMENT PRIMARY KEY,
   titulo varchar(50) not null,
   contenidoTxt varchar(255) not null,
   cliente_id int not null,
   FOREIGN KEY (cliente_id) REFERENCES Clientes(cliente_id)
);

CREATE TABLE Generos (
    genero_id INT AUTO_INCREMENT PRIMARY KEY,
    nombreGenero VARCHAR(255) NOT NULL
);

CREATE TABLE Peliculas (
    pelicula_id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    sinopsis TEXT NOT NULL,
    pais VARCHAR(255) NOT NULL,
    link_Trailer VARCHAR(255),
    duracion INT NOT NULL,
    cartel varchar(255), 
    clasificacion enum ('A','B','B15','C','R') NOT NULL,
	estaEliminado bit(1) default 0
);

CREATE TABLE Funciones (
    funcion_id INT AUTO_INCREMENT PRIMARY KEY,
    precio DECIMAL(10, 2) NOT NULL,
    dia ENUM('Lunes','Martes','Miercoles','Jueves','Viernes','Sabado','Domingo') NOT NULL,
    hora_inicio TIME NOT NULL,
    hora_final TIME NOT NULL, -- Nueva columna para la hora final
    asientos_Disponibles INT NOT NULL,
    hora_final_total TIME NOT NULL,
    duracionTotal INT NOT NULL,
	estaEliminado bit(1) default 0,
    sala_id INT,
    pelicula_id INT,
    FOREIGN KEY (sala_id) REFERENCES Salas(sala_id),
    FOREIGN KEY (pelicula_id) REFERENCES Peliculas(pelicula_id)
);

CREATE TABLE Ventas (
    compra_id INT AUTO_INCREMENT PRIMARY KEY,
    fecha_compra TIMESTAMP NOT NULL default CURRENT_TIMESTAMP ,
    cantidad_Boletos INT NOT NULL,
    precioUnitario DECIMAL(10, 2),
    totalCompra DECIMAL(10, 2),
    metodoPago ENUM ("Efectivo","Tarjeta","Puntos") NOT NULL,
    cliente_id INT,
    funcion_id INT,
    FOREIGN KEY (cliente_id) REFERENCES Clientes(cliente_id),
    FOREIGN KEY (funcion_id) REFERENCES Funciones(funcion_id)
);


CREATE TABLE Pelicula_Genero (
   pelicula_genero_id INT AUTO_INCREMENT PRIMARY KEY,
    pelicula_id INT,
    genero_id INT,
    FOREIGN KEY (pelicula_id) REFERENCES Peliculas(pelicula_id),
    FOREIGN KEY (genero_id) REFERENCES Generos(genero_id)
);

CREATE TABLE Pelicula_Sucursal (
   pelicula_sucursal_id INT AUTO_INCREMENT PRIMARY KEY,
    sucursal_id INT,
    pelicula_id INT,
	fechaRegistro DATE NOT NULL default (CURDATE()),
    fechaRetiro DATE,
    FOREIGN KEY (pelicula_id) REFERENCES Peliculas(pelicula_id),
    FOREIGN KEY (sucursal_id) REFERENCES Sucursales(sucursal_id)
);

-- Inserts de tablas

-- Inserts de Ciudades
INSERT INTO Ciudades (nombre) VALUES ('Navojoa');
INSERT INTO Ciudades (nombre) VALUES ('Obregón');
INSERT INTO Ciudades (nombre) VALUES ('Hermosillo');

-- Inserts de Sucursales
INSERT INTO Sucursales (nombre, direccion, ubicacion, ciudad_id)
VALUES ('Cinépolis Sendero Obregón', 'Plaza Sendero, C. 300 85180, Franja Comercial 300, 85065, Cdad. Obregón, Son.', POINT(27.478692089159388, -109.91456675730379), (SELECT ciudad_id FROM Ciudades WHERE nombre = 'Obregón')),
('Cinépolis Bella Vista', 'Blvd. Rodolfo Elías Calles 1750, Fuentes del Bosque, 85000 Cdad. Obregón, Son.', POINT(27.488969156057266, -109.9580047508814), (SELECT ciudad_id FROM Ciudades WHERE nombre = 'Obregón')),
('Cinépolis Plaza de Hierro Navojoa', 'Blvd. Centenario 358, Reforma, 85229 Navojoa, Son.', POINT(27.09021094262184, -109.44881426288555), (SELECT ciudad_id FROM Ciudades WHERE nombre = 'Navojoa')),
('Cinépolis Galerías Mall Hermosillo', 'Av Cultura 55, Proyecto Rio Sonora Hermosillo XXI, 83270 Hermosillo, Son.', POINT(29.068465547224747, -110.95136051615307), (SELECT ciudad_id FROM Ciudades WHERE nombre = 'Hermosillo')),
('Cinépolis Hermosillo', 'Blvd. Luis Encinas J. 227-P. B, San Benito, 83190 Hermosillo, Son.', POINT(29.093991653707597, -110.96714639206756), (SELECT ciudad_id FROM Ciudades WHERE nombre = 'Hermosillo')),
('Cinépolis Patio Hermosillo', 'Periférico Ote. 1274, Perisur, 83290 Hermosillo, Son.', POINT(29.039198907378776, -110.94021202877917), (SELECT ciudad_id FROM Ciudades WHERE nombre = 'Hermosillo'));

-- Inserts de Salas
INSERT INTO Salas (nombre, numAsientos, duracionLimpieza, sucursal_id)
VALUES 
('Kids', 50, 15, (SELECT sucursal_id FROM Sucursales WHERE nombre = 'Cinépolis Sendero Obregón')),
('Sala Tradicional', 100, 10, (SELECT sucursal_id FROM Sucursales WHERE nombre = 'Cinépolis Sendero Obregón')),
('Sala Premium', 80, 15, (SELECT sucursal_id FROM Sucursales WHERE nombre = 'Cinépolis Sendero Obregón')),
('Sala VIP', 40, 20, (SELECT sucursal_id FROM Sucursales WHERE nombre = 'Cinépolis Sendero Obregón')),
('Sala ITSON', 60, 10, (SELECT sucursal_id FROM Sucursales WHERE nombre = 'Cinépolis Sendero Obregón')),

('Kids', 50, 15, (SELECT sucursal_id FROM Sucursales WHERE nombre = 'Cinépolis Bella Vista')),
('Sala Tradicional', 100, 10, (SELECT sucursal_id FROM Sucursales WHERE nombre = 'Cinépolis Bella Vista')),
('Sala Premium', 80, 15, (SELECT sucursal_id FROM Sucursales WHERE nombre = 'Cinépolis Bella Vista')),
('Sala VIP', 40, 20, (SELECT sucursal_id FROM Sucursales WHERE nombre = 'Cinépolis Bella Vista')),
('Sala ITSON', 60, 10, (SELECT sucursal_id FROM Sucursales WHERE nombre = 'Cinépolis Bella Vista')),

('Kids', 50, 15, (SELECT sucursal_id FROM Sucursales WHERE nombre = 'Cinépolis Plaza de Hierro Navojoa')),
('Sala Tradicional', 100, 10, (SELECT sucursal_id FROM Sucursales WHERE nombre = 'Cinépolis Plaza de Hierro Navojoa')),
('Sala Premium', 80, 15, (SELECT sucursal_id FROM Sucursales WHERE nombre = 'Cinépolis Plaza de Hierro Navojoa')),
('Sala VIP', 40, 20, (SELECT sucursal_id FROM Sucursales WHERE nombre = 'Cinépolis Plaza de Hierro Navojoa')),
('Sala ITSON', 60, 10, (SELECT sucursal_id FROM Sucursales WHERE nombre = 'Cinépolis Plaza de Hierro Navojoa')),

('Kids', 50, 15, (SELECT sucursal_id FROM Sucursales WHERE nombre = 'Cinépolis Galerías Mall Hermosillo')),
('Sala Tradicional', 100, 10, (SELECT sucursal_id FROM Sucursales WHERE nombre = 'Cinépolis Galerías Mall Hermosillo')),
('Sala Premium', 80, 15, (SELECT sucursal_id FROM Sucursales WHERE nombre = 'Cinépolis Galerías Mall Hermosillo')),
('Sala VIP', 40, 20, (SELECT sucursal_id FROM Sucursales WHERE nombre = 'Cinépolis Galerías Mall Hermosillo')),
('Sala ITSON', 60, 10, (SELECT sucursal_id FROM Sucursales WHERE nombre = 'Cinépolis Galerías Mall Hermosillo')),

('Kids', 50, 15, (SELECT sucursal_id FROM Sucursales WHERE nombre = 'Cinépolis Hermosillo')),
('Sala Tradicional', 100, 10, (SELECT sucursal_id FROM Sucursales WHERE nombre = 'Cinépolis Hermosillo')),
('Sala Premium', 80, 15, (SELECT sucursal_id FROM Sucursales WHERE nombre = 'Cinépolis Hermosillo')),
('Sala VIP', 40, 20, (SELECT sucursal_id FROM Sucursales WHERE nombre = 'Cinépolis Hermosillo')),
('Sala ITSON', 60, 10, (SELECT sucursal_id FROM Sucursales WHERE nombre = 'Cinépolis Hermosillo')),

('Kids', 50, 15, (SELECT sucursal_id FROM Sucursales WHERE nombre = 'Cinépolis Patio Hermosillo')),
('Sala Tradicional', 100, 10, (SELECT sucursal_id FROM Sucursales WHERE nombre = 'Cinépolis Patio Hermosillo')),
('Sala Premium', 80, 15, (SELECT sucursal_id FROM Sucursales WHERE nombre = 'Cinépolis Patio Hermosillo')),
('Sala VIP', 40, 20, (SELECT sucursal_id FROM Sucursales WHERE nombre = 'Cinépolis Patio Hermosillo')),
('Sala ITSON', 60, 10, (SELECT sucursal_id FROM Sucursales WHERE nombre = 'Cinépolis Patio Hermosillo'));

-- Inserts de Peliculas
   INSERT INTO Peliculas (titulo, sinopsis, pais, link_Trailer, duracion, cartel, clasificacion) VALUES
    ('El señor de los anillos: El retorno del Rey', 'En la última entrega de la trilogía épica de Peter Jackson, basada en la obra maestra de J.R.R. Tolkien, los miembros de la comunidad del anillo se encuentran en la cúspide de su lucha contra Sauron y sus fuerzas oscuras. Mientras Frodo y Sam se aventuran hacia el Monte del Destino para destruir el Anillo Único, la Tierra Media se ve inmersa en una guerra monumental. Las alianzas entre las diferentes razas, como hombres, elfos y enanos, son puestas a prueba mientras se enfrentan a legiones de orcos en la batalla de Minas Tirith. Con la dirección de un gran elenco, incluido Elijah Wood como Frodo, el filme explora temas de amistad, sacrificio y la lucha por la libertad. La épica conclusión lleva a los personajes a un viaje emocional que cambiará sus vidas para siempre.', 'Nueva Zelanda', 'https://www.youtube.com/watch?v=r5X-hFf6Bwo', 201, 'carteles/ESDLA3.jpg', 'A'),
    ('La La Land', 'La La Land es un musical romántico que narra la conmovedora historia de amor entre Mia, una aspirante a actriz, y Sebastian, un músico de jazz apasionado por su arte. Ambientada en Los Ángeles, la película refleja las luchas y sueños de dos jóvenes en la búsqueda de la realización personal y profesional. A medida que su romance florece, también enfrentan desafíos que ponen a prueba sus ambiciones y su relación. La cinematografía vibrante, la dirección de Damien Chazelle y la música cautivadora, incluyendo la emblemática canción "City of Stars", transportan a los espectadores a un mundo lleno de esperanza, melancolía y la lucha entre los sueños y la realidad. La La Land explora cómo el amor puede ser tanto un motor inspirador como un obstáculo, ofreciendo una visión nostálgica de Hollywood y el costo del éxito.', 'Estados Unidos', 'https://www.youtube.com/watch?v=0pdqf4P9MB8', 128, 'carteles/LaLaLand.jpg', 'B'),
    ('Parásitos', 'El aclamado thriller social de Bong Joon-ho, Parásitos, ofrece una crítica mordaz de la desigualdad de clases a través de la historia de la familia Kim, que vive en la pobreza en un sótano. Su vida cambia cuando el hijo, Ki-woo, consigue un trabajo como tutor de la hija de la adinerada familia Park. Con astucia y engaño, los Kim se infiltran en la vida de los Park, haciéndose pasar por expertos en sus respectivas profesiones. Sin embargo, la dinámica de poder entre ambas familias se vuelve tensa y oscura, revelando las profundas divisiones sociales que existen. La película, ganadora del Óscar a Mejor Película, fusiona el drama y la comedia negra para ofrecer una narrativa cautivadora, impactante y llena de giros inesperados que culminan en un clímax devastador. Parásitos no solo entretiene, sino que invita a la reflexión sobre la lucha de clases en el mundo contemporáneo.', 'Corea del Sur', 'https://www.youtube.com/watch?v=5xH0HfJHsaY', 132, 'carteles/parasitos.jpg', 'B15'),
    ('La red social', 'La Red Social, dirigida por David Fincher, es una representación dramatizada de la creación de Facebook, la plataforma de redes sociales que revolucionó la comunicación moderna. La película se centra en Mark Zuckerberg, interpretado por Jesse Eisenberg, un estudiante de Harvard que, tras una ruptura con su novia, se obsesiona con la idea de crear una red social exclusiva para estudiantes universitarios. Mientras Zuckerberg y sus amigos se embarcan en este ambicioso proyecto, se ven envueltos en conflictos legales con sus compañeros y cofundadores. La historia explora temas de amistad, traición y la búsqueda de la identidad en la era digital. A través de un guion agudo y una dirección estilizada, Fincher presenta un retrato fascinante de la ambición y la vulnerabilidad, así como las repercusiones de un invento que ha cambiado el mundo y la forma en que las personas se conectan entre sí.', 'Estados Unidos', 'https://www.youtube.com/watch?v=lB95KLmpLR4', 120, 'carteles/redSocial.jpg', 'B'),
    ('Moonlight', 'Moonlight, dirigida por Barry Jenkins, es un relato conmovedor que sigue la vida de un joven afroamericano llamado Chiron a través de tres etapas de su vida: infancia, adolescencia y adultez. Ambientada en un vecindario peligroso de Miami, la película explora la lucha de Chiron con su identidad y sexualidad mientras navega por la complejidad de su entorno. A lo largo de la historia, Chiron enfrenta el bullying, la violencia y la falta de aceptación, tanto en su hogar como en la sociedad. La relación con su madre, una mujer consumida por la adicción, y su conexión con Juan, un narcotraficante que se convierte en su figura paterna, son fundamentales para su desarrollo. Moonlight es una obra maestra visual y emocional que desafía las normas sociales y raciales, ofreciendo una perspectiva profunda y universal sobre el amor y la búsqueda de uno mismo.', 'Estados Unidos', 'https://www.youtube.com/watch?v=9NJj12tJzqc', 111, 'carteles/moonLight.jpg', 'B15'),
    ('El caballero de la noche', 'El Caballero de la Noche, dirigida por Christopher Nolan, es una épica película de superhéroes que continúa la historia de Bruce Wayne, interpretado por Christian Bale, en su lucha contra el crimen en Gotham City. Esta vez, se enfrenta a uno de sus más icónicos adversarios, el Joker, interpretado magistralmente por Heath Ledger, cuya anarquía y caos ponen a prueba los límites morales de Batman. La narrativa explora temas de justicia, moralidad y el impacto del miedo, mientras la ciudad se sumerge en el caos. La tensión aumenta a medida que Batman, junto con el fiscal de distrito Harvey Dent y el teniente Jim Gordon, intentan erradicar el crimen en Gotham, solo para descubrir que el verdadero enemigo puede estar más cerca de lo que piensan. Con impresionantes secuencias de acción y un guion inteligente, El Caballero de la Noche redefine el género de superhéroes y se convierte en un clásico moderno.', 'Estados Unidos', 'https://www.youtube.com/watch?v=EXeTwQWrcwY', 152, 'carteles/CaballeroNoche.jpg', 'B15'),
    ('La forma del agua', 'La Forma del Agua, dirigida por Guillermo del Toro, es una historia de amor única ambientada en la década de 1960 en un laboratorio gubernamental. La película sigue a Elisa, una mujer muda que trabaja como limpiadora en el laboratorio y descubre a una criatura acuática atrapada en una jaula. A medida que se desarrolla su relación, Elisa encuentra en esta criatura una conexión emocional profunda que nunca experimentó antes. La película aborda temas de aceptación, amor y la lucha contra la opresión, al tiempo que desafía las normas de la sociedad. A través de su estética visual onírica y su emotiva narrativa, del Toro crea un mundo donde la belleza y el horror coexisten. La Forma del Agua es una obra que no solo es un cuento de hadas moderno, sino una reflexión sobre la humanidad, el amor y la búsqueda de la libertad.', 'Estados Unidos', 'https://www.youtube.com/watch?v=XFYWazblaUA', 123, 'carteles/formaAgua.jpg', 'B'),
    ('El gran hotel Budapest', 'El Gran Hotel Budapest, dirigida por Wes Anderson, es una comedia visualmente estilizada que sigue las aventuras del conserje Gustave H. y su protegido, el joven Zero. Situada en un lujoso hotel europeo entre las guerras mundiales, la historia se desarrolla en un contexto de intriga y un valioso cuadro robado que desencadena una serie de eventos extravagantes. A medida que Gustave y Zero intentan limpiar su nombre tras ser acusados de asesinato, se encuentran con una serie de personajes excéntricos y situaciones absurdas que resaltan el humor característico de Anderson. Con un elenco estelar que incluye a Ralph Fiennes y Saoirse Ronan, la película es una oda al pasado y una meditación sobre el paso del tiempo, la lealtad y la amistad. El Gran Hotel Budapest es una experiencia cinematográfica encantadora que combina el humor, la nostalgia y la estética única de su director.', 'Alemania', 'https://www.youtube.com/watch?v=1Fg5iWmQjwk', 99, 'carteles/BudapestHotel.jpg', 'B15'),
    ('Origen', 'Origen, dirigida por Christopher Nolan, es un complejo thriller de ciencia ficción que explora el mundo de los sueños y la manipulación de la mente. Dom Cobb, interpretado por Leonardo DiCaprio, es un ladrón experto en el arte de la extracción, que consiste en entrar en los sueños de otros para robar sus secretos más íntimos. Sin embargo, se le ofrece una oportunidad de redención: en lugar de robar una idea, debe implantar una en la mente de su objetivo, un proceso conocido como "origen". A medida que Cobb y su equipo se sumergen en un laberinto de sueños dentro de sueños, la línea entre la realidad y la ilusión se desdibuja. Con una narrativa innovadora, efectos visuales impresionantes y una banda sonora inolvidable, Origen es una exploración fascinante de la percepción, la memoria y la naturaleza del sueño, dejando a los espectadores cuestionando la realidad misma.', 'Reino Unido', 'https://www.youtube.com/watch?v=YoHD9XEInc0', 148, 'carteles/Origen.jpg', 'B15'),
    ('12 años de esclavitud', '12 Años de Esclavitud, dirigida por Steve McQueen, es una adaptación desgarradora de la historia real de Solomon Northup, un hombre afroamericano libre que es secuestrado y vendido como esclavo en el sur de Estados Unidos. La película documenta su increíble lucha por la supervivencia y la búsqueda de la libertad en un mundo cruel y desolador. A través de su travesía, Northup, interpretado por Chiwetel Ejiofor, enfrenta la brutalidad de la esclavitud, la injusticia y la deshumanización. La actuación poderosa, junto con la dirección visceral de McQueen, crea una experiencia cinematográfica impactante que no solo narra un capítulo oscuro de la historia estadounidense, sino que también invita a la reflexión sobre la resistencia, la dignidad humana y la lucha por la libertad. 12 Años de Esclavitud es una obra esencial que ilumina el pasado y resuena en el presente.', 'Estados Unidos', 'https://www.youtube.com/watch?v=z02Ie8wKKRg', 134, 'carteles/12esclavitud.jpg', 'B15'),
    ('Whiplash', 'Whiplash, dirigida por Damien Chazelle, es un drama intenso que se adentra en el mundo de la música y la educación. La película sigue a Andrew Neiman, un joven baterista con aspiraciones de convertirse en un gran músico en una prestigiosa escuela de jazz. Sin embargo, su vida toma un giro oscuro cuando se convierte en el alumno del enigmático e implacable instructor Terence Fletcher, interpretado por J.K. Simmons. La relación entre el estudiante y el maestro se torna cada vez más tóxica y exigente, llevando a Andrew a sus límites físicos y emocionales. Whiplash es una exploración apasionante del sacrificio, la ambición y los extremos a los que uno está dispuesto a llegar por el arte. Con actuaciones electrizantes y una banda sonora cautivadora, la película examina la delgada línea entre la grandeza y la destrucción, planteando preguntas sobre el costo del éxito en el mundo de la música.', 'Estados Unidos', 'https://www.youtube.com/watch?v=7d_jQycdQGo', 106, 'carteles/whiplash.jpg', 'B15'),
    ('La llegada', 'La Llegada, dirigida por Denis Villeneuve, es una fascinante película de ciencia ficción que aborda el tema de la comunicación y la comprensión. Cuando una serie de naves espaciales misteriosas aterrizan en la Tierra, la lingüista Louise Banks, interpretada por Amy Adams, es reclutada por el gobierno de Estados Unidos para descifrar el lenguaje de los extraterrestres. A medida que trabaja para establecer una conexión con estos seres, la historia se entrelaza con su vida personal y los desafíos que enfrenta. La película explora la percepción del tiempo y cómo el lenguaje puede moldear nuestra realidad. Con una narrativa profundamente emotiva y una cinematografía impresionante, La Llegada desafía las convenciones del género y ofrece una reflexión conmovedora sobre la humanidad, el amor y la pérdida, mientras invita a los espectadores a reconsiderar su comprensión del tiempo y la comunicación.', 'Estados Unidos', 'https://www.youtube.com/watch?v=tFMo3UJ4B4g', 116, 'carteles/LaLlegada.jpg', 'B'),
    ('Birdman', 'Birdman, dirigida por Alejandro González Iñárritu, es una sátira oscura que explora la lucha interna de un actor de cine, Riggan Thomson, interpretado por Michael Keaton, quien busca redimirse al protagonizar una obra de teatro en Broadway. A medida que se adentra en el proceso creativo, Riggan enfrenta sus inseguridades, las voces de su pasado y la presión del éxito en un mundo donde la fama es efímera. La narrativa se desarrolla en un solo plano secuencia, creando una atmósfera envolvente que refleja la confusión y la intensidad de su viaje emocional. La película aborda temas como la identidad, la fama y el arte, mientras critica la superficialidad de la industria del entretenimiento. Con un elenco impresionante que incluye a Edward Norton y Emma Stone, Birdman es una obra innovadora y provocativa que desafía las expectativas del cine contemporáneo y deja una huella duradera en la mente del espectador.', 'Estados Unidos', 'https://www.youtube.com/watch?v=uJfLoE6hanc', 119, 'carteles/Birdman.jpg', 'B15'),
    ('El renacido', 'El Renacido, dirigida por Alejandro González Iñárritu, es una odisea visual basada en la historia real de Hugh Glass, un trampero que es brutalmente atacado por un oso y dejado por muerto por su equipo. La película sigue su lucha por la supervivencia en un entorno inhóspito mientras busca venganza contra aquellos que lo traicionaron. A través de paisajes deslumbrantes y una cinematografía impresionante, Iñárritu retrata la brutalidad de la naturaleza y la resistencia del espíritu humano. Leonardo DiCaprio ofrece una actuación visceral y conmovedora que le valió el Oscar a Mejor Actor. La narrativa no solo se centra en la venganza, sino que también explora la redención y la conexión con la tierra. Con un enfoque en el sufrimiento y la perseverancia, El Renacido es una experiencia cinematográfica intensa que sumerge a los espectadores en la lucha de un hombre por recuperar su humanidad en medio del desamparo.', 'Estados Unidos','https://www.youtube.com/watch?v=LoebZZ8K5N0', 156, 'carteles/Renacido.jpg', 'B15'),
    ('La vida de Pi', 'La Vida de Pi, dirigida por Ang Lee, es una adaptación visualmente asombrosa de la novela homónima que narra la historia de un joven llamado Piscine Molitor Patel, conocido como Pi. Tras un naufragio, Pi se encuentra a la deriva en el océano Pacífico con un tigre de Bengala llamado Richard Parker como único compañero. A medida que luchan por sobrevivir, la película explora temas de fe, espiritualidad y la lucha por la vida. A través de una narrativa cautivadora, La Vida de Pi invita a los espectadores a reflexionar sobre la naturaleza de la existencia y el poder de la imaginación. La cinematografía es un deleite visual, combinando la belleza del océano y la soledad del mar con una profunda conexión emocional entre Pi y Richard Parker. La historia es una fábula sobre el descubrimiento de uno mismo y la búsqueda de un propósito en un mundo incierto.', 'Estados Unidos','https://www.youtube.com/watch?v=3mMN693-F3U', 127, 'carteles/vidaPi.jpg', 'B'),
    ('Bastardos sin gloria', 'Bastardos Sin Gloria, dirigida por Quentin Tarantino, es una audaz reimaginación de la Segunda Guerra Mundial que mezcla acción, humor y violencia. La película sigue a un grupo de soldados judíos estadounidenses, conocidos como "Los Bastardos", quienes llevan a cabo una campaña de venganza brutal contra los nazis en la Francia ocupada. Bajo el mando del teniente Aldo Raine, interpretado por Brad Pitt, el grupo se embarca en misiones audaces para desmantelar el régimen nazi. Paralelamente, la historia se entrelaza con la trama de una joven judía francesa que busca venganza por la muerte de su familia. Tarantino utiliza su estilo característico para ofrecer diálogos memorables, giros inesperados y una narrativa no lineal que mantiene a los espectadores al borde de sus asientos. Bastardos Sin Gloria es una película provocativa que desafía la historia, ofreciendo una visión alternativa llena de humor negro y acción emocionante.', 'Estados Unidos', 'https://www.youtube.com/watch?v=KnrRy6kSFF0', 153, 'carteles/BastardosSinGloria.jpg', 'B15'),
    ('Interstellar', 'Interstellar, dirigida por Christopher Nolan, es una épica de ciencia ficción que desafía los límites del tiempo y el espacio. La trama sigue a Cooper, un piloto y ingeniero interpretado por Matthew McConaughey, que es reclutado para una misión intergaláctica con el fin de encontrar un nuevo hogar para la humanidad, que enfrenta la extinción en la Tierra. Junto a un equipo de astronautas, Cooper viaja a través de un agujero de gusano en busca de planetas habitables. La película explora conceptos complejos como la relatividad, el amor y la supervivencia en un contexto emocional profundo. Con efectos visuales impresionantes y una banda sonora conmovedora de Hans Zimmer, Interstellar combina la ciencia con la emoción, planteando preguntas sobre la naturaleza del tiempo y el sacrificio por el bien de la humanidad. Es una experiencia cinematográfica monumental que invita a los espectadores a reflexionar sobre su lugar en el universo.', 'Estados Unidos', 'https://www.youtube.com/watch?v=zSWdZVtXT7E', 169, 'carteles/interstellar.jpg', 'B'),
    ('Avatar 2', 'Avatar 2, dirigida por James Cameron, es la tan esperada secuela del fenómeno de taquilla Avatar, que llevó a los espectadores al mundo de Pandora. Esta nueva entrega continúa la historia de Jake Sully y Neytiri, quienes ahora deben proteger su hogar de nuevas amenazas mientras exploran las maravillas submarinas del planeta. A medida que los personajes enfrentan conflictos familiares y luchan por mantener la paz en Pandora, la película profundiza en temas de amor, lealtad y la conexión con la naturaleza. Con avances tecnológicos en efectos visuales, Avatar 2 promete ofrecer una experiencia inmersiva que traslada a los espectadores a un mundo deslumbrante lleno de vida y color. James Cameron continúa su exploración del impacto humano en el medio ambiente y la importancia de la conservación, creando una narrativa que es tanto emocionante como reflexiva. Avatar 2 es una celebración de la belleza de Pandora y la lucha por protegerlo ante fuerzas adversas.', 'Estados Unidos', 'https://www.youtube.com/watch?v=d9MyW72ELq0', 192, 'carteles/Avatar2.jpg', 'A'),
    ('Valiente', 'Valiente, dirigida por Mark Andrews y Brenda Chapman, es una encantadora película de animación de Disney y Pixar que cuenta la historia de Mérida, una joven princesa escocesa con un fuerte deseo de forjar su propio destino. En un reino donde las tradiciones son sagradas, Mérida desafía las expectativas y se embarca en una aventura para cambiar su suerte. A medida que busca la ayuda de una anciana, Mérida debe enfrentar una poderosa maldición que afecta a su familia y a su reino. La película trata temas de valentía, autonomía y la conexión entre madre e hija, mientras presenta impresionantes animaciones y una emotiva banda sonora. Valiente no solo es un relato inspirador sobre la búsqueda de identidad, sino también una reflexión sobre la importancia de la familia y el respeto a las tradiciones, todo envuelto en una historia cautivadora que resuena en todas las edades.', 'Estados Unidos', 'https://www.youtube.com/watch?v=TEHWDA_6e3M', 93, 'carteles/Valiente.jpg', 'A'),
    ('Cars 3', 'Cars 3, dirigida por Brian Fee, es la tercera entrega de la popular franquicia de Pixar que sigue las aventuras de Rayo McQueen, un auto de carreras que se enfrenta a nuevos desafíos en un mundo donde la tecnología y los nuevos competidores amenazan su legado. Tras un accidente que pone en peligro su carrera, Rayo debe encontrar una manera de demostrar que aún tiene lo necesario para competir al más alto nivel. A lo largo de su viaje, recibe ayuda de una joven y talentosa mecánica llamada Cruz Ramirez, quien lo inspira a superar sus miedos y a redescubrir su amor por las carreras. La película aborda temas de perseverancia, amistad y la importancia de adaptarse a los cambios en la vida. Con animación vibrante y un mensaje inspirador, Cars 3 celebra el espíritu del deporte y la relevancia de seguir adelante, sin importar las adversidades que se presenten en el camino.', 'Estados Unidos', 'https://www.youtube.com/watch?v=2LeOH9AGJQM', 102, 'carteles/Cars3.jpg', 'A'); 


-- Inserts de Peliculas a Sucursales
INSERT INTO Pelicula_Sucursal (sucursal_id, pelicula_id) VALUES
(1, 1), -- Cinépolis Sendero Obregón - El señor de los anillos: El retorno del Rey
(1, 2), -- Cinépolis Sendero Obregón - La La Land
(1, 3), -- Cinépolis Sendero Obregón - Parásitos
(1, 4), -- Cinépolis Sendero Obregón - La red social
(1, 5), -- Cinépolis Sendero Obregón - Moonlight
(1, 6), -- Cinépolis Sendero Obregón - El caballero de la noche
(1, 7), -- Cinépolis Sendero Obregón - La forma del agua
(1, 8), -- Cinépolis Sendero Obregón - El gran hotel Budapest
(1, 9), -- Cinépolis Sendero Obregón - Origen
(1, 10), -- Cinépolis Sendero Obregón - 12 años de esclavitud
(2, 11), -- Cinépolis Bella Vista - Whiplash
(2, 12), -- Cinépolis Bella Vista - La llegada
(2, 13), -- Cinépolis Bella Vista - Birdman
(2, 14), -- Cinépolis Bella Vista - El renacido
(2, 15), -- Cinépolis Bella Vista - La vida de Pi
(2, 16), -- Cinépolis Bella Vista - Bastardos sin gloria
(2, 17), -- Cinépolis Bella Vista - Interstellar
(2, 18), -- Cinépolis Bella Vista - Avatar 2
(2, 19), -- Cinépolis Bella Vista - Valiente
(2, 20), -- Cinépolis Bella Vista - Cars 3
(3, 1), -- Cinépolis Plaza de Hierro Navojoa - El señor de los anillos: El retorno del Rey
(3, 2), -- Cinépolis Plaza de Hierro Navojoa - La La Land
(3, 3), -- Cinépolis Plaza de Hierro Navojoa - Parásitos
(3, 4), -- Cinépolis Plaza de Hierro Navojoa - La red social
(3, 5), -- Cinépolis Plaza de Hierro Navojoa - Moonlight
(3, 6), -- Cinépolis Plaza de Hierro Navojoa - El caballero de la noche
(3, 7), -- Cinépolis Plaza de Hierro Navojoa - La forma del agua
(3, 8), -- Cinépolis Plaza de Hierro Navojoa - El gran hotel Budapest
(3, 9), -- Cinépolis Plaza de Hierro Navojoa - Origen
(3, 10), -- Cinépolis Plaza de Hierro Navojoa - 12 años de esclavitud
(3, 11), -- Cinépolis Plaza de Hierro Navojoa - Whiplash
(3, 12), -- Cinépolis Plaza de Hierro Navojoa - La llegada
(3, 13), -- Cinépolis Plaza de Hierro Navojoa - Birdman
(3, 14), -- Cinépolis Plaza de Hierro Navojoa - El renacido
(3, 15), -- Cinépolis Plaza de Hierro Navojoa - La vida de Pi
(3, 16), -- Cinépolis Plaza de Hierro Navojoa - Bastardos sin gloria
(3, 17), -- Cinépolis Plaza de Hierro Navojoa - Interstellar
(3, 18), -- Cinépolis Plaza de Hierro Navojoa - Avatar 2
(3, 19), -- Cinépolis Plaza de Hierro Navojoa - Valiente
(3, 20), -- Cinépolis Plaza de Hierro Navojoa - Cars 3
(4, 1), -- Cinépolis Galerías Mall Hermosillo - El señor de los anillos: El retorno del Rey
(4, 2), -- Cinépolis Galerías Mall Hermosillo - La La Land
(4, 3), -- Cinépolis Galerías Mall Hermosillo - Parásitos
(4, 4), -- Cinépolis Galerías Mall Hermosillo - La red social
(4, 5), -- Cinépolis Galerías Mall Hermosillo - Moonlight
(4, 6), -- Cinépolis Galerías Mall Hermosillo - El caballero de la noche
(4, 7), -- Cinépolis Galerías Mall Hermosillo - La forma del agua
(4, 8), -- Cinépolis Galerías Mall Hermosillo - El gran hotel Budapest
(4, 9), -- Cinépolis Galerías Mall Hermosillo - Origen
(4, 10), -- Cinépolis Galerías Mall Hermosillo - 12 años de esclavitud
(4, 11), -- Cinépolis Galerías Mall Hermosillo - Whiplash
(4, 12), -- Cinépolis Galerías Mall Hermosillo - La llegada
(4, 13), -- Cinépolis Galerías Mall Hermosillo - Birdman
(4, 14), -- Cinépolis Galerías Mall Hermosillo - El renacido
(4, 15), -- Cinépolis Galerías Mall Hermosillo - La vida de Pi
(4, 16), -- Cinépolis Galerías Mall Hermosillo - Bastardos sin gloria
(4, 17), -- Cinépolis Galerías Mall Hermosillo - Interstellar
(4, 18), -- Cinépolis Galerías Mall Hermosillo - Avatar 2
(4, 19), -- Cinépolis Galerías Mall Hermosillo - Valiente
(4, 20), -- Cinépolis Galerías Mall Hermosillo - Cars 3
(5, 1), -- Cinépolis Hermosillo - El señor de los anillos: El retorno del Rey
(5, 2), -- Cinépolis Hermosillo - La La Land
(5, 3), -- Cinépolis Hermosillo - Parásitos
(5, 4), -- Cinépolis Hermosillo - La red social
(5, 5), -- Cinépolis Hermosillo - Moonlight
(5, 6), -- Cinépolis Hermosillo - El caballero de la noche
(5, 7), -- Cinépolis Hermosillo - La forma del agua
(5, 8), -- Cinépolis Hermosillo - El gran hotel Budapest
(5, 9), -- Cinépolis Hermosillo - Origen
(5, 10), -- Cinépolis Hermosillo - 12 años de esclavitud
(5, 11), -- Cinépolis Hermosillo - Whiplash
(5, 12), -- Cinépolis Hermosillo - La llegada
(5, 13), -- Cinépolis Hermosillo - Birdman
(5, 14), -- Cinépolis Hermosillo - El renacido
(5, 15), -- Cinépolis Hermosillo - La vida de Pi
(5, 16), -- Cinépolis Hermosillo - Bastardos sin gloria
(5, 17), -- Cinépolis Hermosillo - Interstellar
(5, 18), -- Cinépolis Hermosillo - Avatar 2
(5, 19), -- Cinépolis Hermosillo - Valiente
(5, 20), -- Cinépolis Hermosillo - Cars 3
(6, 1), -- Cinépolis Patio Hermosillo - El señor de los anillos: El retorno del Rey
(6, 2), -- Cinépolis Patio Hermosillo - La La Land
(6, 3), -- Cinépolis Patio Hermosillo - Parásitos
(6, 4), -- Cinépolis Patio Hermosillo - La red social
(6, 5), -- Cinépolis Patio Hermosillo - Moonlight
(6, 6), -- Cinépolis Patio Hermosillo - El caballero de la noche
(6, 7), -- Cinépolis Patio Hermosillo - La forma del agua
(6, 8), -- Cinépolis Patio Hermosillo - El gran hotel Budapest
(6, 9), -- Cinépolis Patio Hermosillo - Origen
(6, 10), -- Cinépolis Patio Hermosillo - 12 años de esclavitud
(6, 11), -- Cinépolis Patio Hermosillo - Whiplash
(6, 12), -- Cinépolis Patio Hermosillo - La llegada
(6, 13), -- Cinépolis Patio Hermosillo - Birdman
(6, 14), -- Cinépolis Patio Hermosillo - El renacido
(6, 15), -- Cinépolis Patio Hermosillo - La vida de Pi
(6, 16), -- Cinépolis Patio Hermosillo - Bastardos sin gloria
(6, 17), -- Cinépolis Patio Hermosillo - Interstellar
(6, 18), -- Cinépolis Patio Hermosillo - Avatar 2
(6, 19), -- Cinépolis Patio Hermosillo - Valiente
(6, 20); -- Cinépolis Patio Hermosillo - Cars 3















select*from funciones



DELIMITER $$
CREATE PROCEDURE BuscarSucursalMasCercana(
    IN clienteID INT
)
BEGIN
    SELECT s.sucursal_id, s.nombre, s.direccion, 
           ST_DISTANCE(c.ubicacion, s.ubicacion) AS distancia
    FROM Clientes c
    CROSS JOIN Sucursales s
    WHERE c.cliente_id = clienteID
    ORDER BY distancia
    LIMIT 1;
END $$

CREATE PROCEDURE guardarPeliculaEnSucursal (
    IN p_pelicula_id INT,
    IN p_sucursal_id INT
)
BEGIN
    -- Verificar si la película existe
    DECLARE pelicula_existe INT DEFAULT 0;
    DECLARE sucursal_existe INT DEFAULT 0;

    -- Comprobar si la película existe
    SELECT COUNT(*) INTO pelicula_existe 
    FROM Peliculas 
    WHERE pelicula_id = p_pelicula_id;

    -- Comprobar si la sucursal existe
    SELECT COUNT(*) INTO sucursal_existe 
    FROM Sucursales 
    WHERE sucursal_id = p_sucursal_id;

    -- Si ambos existen, insertar en la tabla Pelicula_Sucursal
    IF pelicula_existe > 0 AND sucursal_existe > 0 THEN
        INSERT INTO Pelicula_Sucursal (sucursal_id, pelicula_id, fechaRegistro)
        VALUES (p_sucursal_id, p_pelicula_id, CURDATE());
    ELSE
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'La película o la sucursal no existen';
    END IF;
    
END $$


CREATE PROCEDURE actualizarFechaRetiro (
    IN p_pelicula_id INT,
    IN p_sucursal_id INT
)
BEGIN
    -- Actualizar la fecha de retiro a la fecha actual
    UPDATE Pelicula_Sucursal
    SET fechaRetiro = CURDATE()
    WHERE pelicula_id = p_pelicula_id AND sucursal_id = p_sucursal_id;
END $$

CREATE TRIGGER actualizar_asientos
AFTER INSERT ON Ventas
FOR EACH ROW
BEGIN
    DECLARE asientos_actuales INT;

    SELECT asientos_Disponibles
    INTO asientos_actuales
    FROM Funciones
    WHERE funcion_id = NEW.funcion_id;
    
    if asientos_actuales >= New.cantidad_Boletos then
	UPDATE Funciones
	SET asientos_Disponibles = asientos_Disponibles - NEW.cantidad_Boletos
	WHERE funcion_id = NEW.funcion_id;
    else SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Ya no hay suficientes asientos disponibles para el numero de boletos';
    END IF;
END $$

CREATE TRIGGER actualizar_precio_funcion
BEFORE INSERT ON Ventas
FOR EACH ROW
BEGIN
    -- DECLARE iva DECIMAL(10, 2) DEFAULT 0.16; -- IVA del 16%
    DECLARE costo_funcion DECIMAL(10, 2);

    -- Obtener el costo de la función correspondiente
    SELECT precio
    INTO costo_funcion
    FROM Funciones
    WHERE funcion_id = NEW.funcion_id;

    -- Calcular el precio de la función con IVA
    SET NEW.precioUnitario = costo_funcion;  
END $$

DELIMITER $$

CREATE TRIGGER calcular_precio_total
BEFORE INSERT ON Ventas
FOR EACH ROW
BEGIN
    -- Calcular el precio total
    SET NEW.totalCompra = NEW.precioUnitario * NEW.cantidad_Boletos;
END $$



CREATE EVENT reiniciar_asientos_evento
ON SCHEDULE EVERY 1 minute
STARTS CURRENT_TIMESTAMP 
DO
BEGIN
    UPDATE Funciones f
    JOIN Salas s ON f.sala_id = s.sala_id
    SET f.asientos_Disponibles = s.num_asientos
    WHERE f.hora_final < curtime();

END$$

CREATE TRIGGER before_insert_funciones
BEFORE INSERT ON Funciones
FOR EACH ROW
BEGIN
    DECLARE duracion_total INT;
    DECLARE num_asientos INT;
    DECLARE duracion_pelicula INT;

    -- Obtener la duración de la película, el tiempo de limpieza y el número de asientos
    SELECT p.duracion, s.duracionLimpieza, s.numAsientos
    INTO duracion_pelicula, duracion_total, num_asientos
    FROM Salas s
    JOIN Peliculas p ON p.pelicula_id = NEW.pelicula_id
    WHERE s.sala_id = NEW.sala_id;

    -- Calcular la duración total (duración de la película + tiempo de limpieza)
    SET duracion_total = duracion_pelicula + duracion_total;

    -- Establecer el valor de duracionTotal
    SET NEW.duracionTotal = duracion_total;

    -- Calcular hora_final
    SET NEW.hora_final = ADDTIME(NEW.hora_inicio, SEC_TO_TIME(duracion_pelicula * 60));

    -- Calcular hora_final_total
    SET NEW.hora_final_total = ADDTIME(NEW.hora_inicio, SEC_TO_TIME(duracion_total * 60));

    -- Si hora_final supera 24:00:00, restar 24:00:00
    IF NEW.hora_final >= '24:00:00' THEN
        SET NEW.hora_final = SUBTIME(NEW.hora_final, '24:00:00');
    END IF;

    -- Si hora_final_total supera 24:00:00, restar 24:00:00
    IF NEW.hora_final_total >= '24:00:00' THEN
        SET NEW.hora_final_total = SUBTIME(NEW.hora_final_total, '24:00:00');
    END IF;

    -- Inicializar asientos_Disponibles con el número de asientos de la sala
    SET NEW.asientos_Disponibles = num_asientos;
END$$

CREATE TRIGGER before_insertar_funciones
BEFORE INSERT ON Funciones
FOR EACH ROW
BEGIN
    DECLARE adjusted_hora_inicio TIME;
    DECLARE adjusted_hora_final_total TIME;
    
    -- Ajustar hora_inicio
    IF NEW.hora_inicio < '06:00:00' THEN
        SET adjusted_hora_inicio = ADDTIME(NEW.hora_inicio, '24:00:00');
    ELSE
        SET adjusted_hora_inicio = NEW.hora_inicio;
    END IF;

    -- Ajustar hora_final_total
    IF NEW.hora_final_total < '06:00:00' THEN
        SET adjusted_hora_final_total = ADDTIME(NEW.hora_final_total, '24:00:00');
    ELSE
        SET adjusted_hora_final_total = NEW.hora_final_total;
    END IF;

    -- Verificar si hay solapamiento con otras funciones
    IF EXISTS (
        SELECT 1
        FROM Funciones
        WHERE sala_id = NEW.sala_id
		AND dia = NEW.dia
		AND estaEliminado = 0 -- Solo considerar funciones no eliminadas

          AND (
              (adjusted_hora_inicio < hora_final_total AND adjusted_hora_final_total > hora_inicio)
          )
    ) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Error: Solapamiento de funciones en la misma sala y dia.';
    END IF;

END$$

DELIMITER ;

INSERT INTO Funciones (precio, dia, hora_inicio, sala_id, pelicula_id)
VALUES
(100.00, 'Lunes', '00:00:00', 1, 1),
(120.00, 'Martes', '13:00:00', 2, 2),
(150.00, 'Miércoles', '16:00:00', 3, 3),
(180.00, 'Jueves', '19:00:00',  4, 4),
(200.00, 'Viernes', '20:00:00', 5, 5),
(220.00, 'Sábado', '21:00:00', 6, 6),
(220.00, 'Sábado', '00:00:00', 6, 6),
(250.00, 'Domingo', '01:00:00', 7, 7);

insert into Ventas(cantidad_Boletos, metodoPago, cliente_id, funcion_id) Values(1,'Efectivo', 1,1);
select* from clientes;
select*from ventas;
select*from funciones;
select*from peliculas

 -- SELECT f.funcion_id, p.titulo, p.duracion, f.hora_inicio, f.hora_final_total, f.precio 
			-- FROM Funciones f 
               -- INNER JOIN Peliculas p ON f.pelicula_id = p.pelicula_id 
               -- INNER JOIN Salas s ON f.sala_id = s.sala_id 
               -- WHERE f.estaEliminado = 0
               -- AND f.dia = 'Lunes'
                -- AND s.sala_id = 1
               -- ORDER BY s.sala_id 