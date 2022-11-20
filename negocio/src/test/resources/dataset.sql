insert into pelicula values ("1", "1", "Matrix", "actor1, actor2, actor3", "Pelicula de accion","url_imagen", "url_trailer");
insert into pelicula values ("2", "1", "La noche del demonio", "actor1, actor2, actor3", "Pelicula de suspenso y terror", "url_imagen", "url_trailer");
insert into pelicula values ("3", "1", "El conjuro", "actor1, actor2, actor3", "Pelicula de terror y suspenso", "url_imagen", "url_trailer");
insert into pelicula values ("4", "1", "Rapido y furioso", "actor1, actor2, actor3", "Pelicula de accion", "url_imagen", "url_trailer");

insert into foro values ("1", "pregunta", "respuesta", "1");
insert into foro values ("2", "pregunta", "respuesta", "2");
insert into foro values ("3", "pregunta", "respuesta", "3");
insert into foro values ("4", "pregunta", "respuesta", "4");
insert into foro values ("5", "pregunta", "respuesta", "5");

insert into cliente values ("1", "12345", "Ana@email.co", "1","ruta foto", "Ana Maria Cardenas", "1" );
insert into cliente values ("2", "12332", "Juan@email.co", "1","ruta foto", "Juan Esteban","2" );
insert into cliente values ("3", "12343", "Pablo@email.co", "1","ruta foto", "Pablo Andres", "3" );
insert into cliente values ("4", "12232", "Andres@email.co", "1","ruta foto", "Andres Felipe", "4" );
insert into cliente values ("5", "12465", "Diego@email.co", "1","ruta foto", "Diego Alejandro", "5" );

insert into telefono values ("12389012", "1");
insert into telefono values ("12311212", "1");
insert into telefono values ("11231232", "2");
insert into telefono values ("12389122", "2");
insert into telefono values ("12123412", "3");
insert into telefono values ("12234012", "4");
insert into telefono values ("12344012", "5");

insert into administrador values ("1","Admin1@email.co", "1231341");
insert into administrador values ("2","Admin2@email.co", "124311");
insert into administrador values ("3","Admin3@email.co", "12123311");
insert into administrador values ("4","Admin4@email.co", "1212311");

insert into administrador_teatro values ("1","Admin1@email.co", "12123123");
insert into administrador_teatro values ("2","Admin@email.co", "12311231");
insert into administrador_teatro values ("3","Admin3@email.co", "1225343123");
insert into administrador_teatro values ("4","Admin4@email.co", "123234231");

insert into ciudad values ("1", "Armenia");
insert into ciudad values ("2", "Cali");
insert into ciudad values ("3", "Medellin");
insert into ciudad values ("4", "Bogota");

insert into distribucion_sillas values ("1", "12", "esquema", "12", "144");
insert into distribucion_sillas values ("2", "12", "esquema", "12", "144");
insert into distribucion_sillas values ("3", "12", "esquema", "12", "144");
insert into distribucion_sillas values ("4", "12", "esquema", "12", "144");

insert into teatro values ("1", "Unicentro", "12312", "1", "1");
insert into teatro values ("2", "Calima", "12312", "2", "2");
insert into teatro values ("3", "Portal", "12312", "3", "3");
insert into teatro values ("4", "Centro mayor", "12312", "4", "4");

insert into sala values ("1", "sala 1", "1", "1");
insert into sala values ("2", "sala 2", "2", "2");
insert into sala values ("3", "sala 3", "3", "3");
insert into sala values ("4", "sala 4", "4", "4");

insert into funcion values ("1", 8000, "12:00", "1", "1");
insert into funcion values ("2", 8000, "12:00", "2", "2");
insert into funcion values ("3", 8000, "12:00", "3", "3");
insert into funcion values ("4", 8000, "12:00", "4", "4");

insert into compra values (1, "2001-09-12", 1, 200000, "1", "1", "1");
insert into compra values (2, "2001-09-12", 0, 300000, "2", "2", "2");
insert into compra values (3, "2001-09-12", 2, 90000, "3", "3", "3");
insert into compra values (4, "2001-09-12", 3, 100000, "4", "4", "4");

insert into confiteria values ("1", "Hamburguesa", 15000, "ruta foto");
insert into confiteria values ("2", "Perro", 10000, "ruta foto");
insert into confiteria values ("3", "Gaseosa", 5000, "ruta foto");
insert into confiteria values ("4", "Crispetas", 10000, "ruta foto");

insert into compra_confiteria values ("1", 200000, "200", "1", "3");
insert into compra_confiteria values ("2", 300000, "200", "2", "2");
insert into compra_confiteria values ("3", 250000, "200", "3", "3");
insert into compra_confiteria values ("4", 250000, "200", "4", "4");

insert into cupon values ("1", "vencido", "cupon del 15% de descuento en confiteria", 15, "2022-09-12" );
insert into cupon values ("2", "vencido", "cupon del 20% de descuento en confiteria", 20, "2022-09-12" );
insert into cupon values ("3", "vigente", "cupon del 30% de descuento en confiteria", 30, "2022-09-12" );
insert into cupon values ("4", "vigente", "cupon del 12% de descuento en confiteria", 12, "2022-09-12" );

insert into cupon_cliente values ("1", "1", "1", 1, "1");
insert into cupon_cliente values ("2", "1", "2", 2, "2");
insert into cupon_cliente values ("3", "1", "3", 3, "3");
insert into cupon_cliente values ("4", "1", "4", 4, "4");

insert into entrada values ("1", "8", "3", 1);
insert into entrada values ("2", "5", "6", 2);
insert into entrada values ("3", "4", "6", 3);
insert into entrada values ("4", "1", "5", 4);

insert into genero values ("1", "Romance");
insert into genero values ("2", "Comedia");
insert into genero values ("3", "Accion");
insert into genero values ("4", "Terror");
insert into genero values ("5", "Suspenso");

insert into genero_peliculas values ("1", "1");
insert into genero_peliculas values ("2", "2");
insert into genero_peliculas values ("3", "2");
insert into genero_peliculas values ("4", "3");
insert into genero_peliculas values ("5", "4");


insert into puntuacion values ("1", "5 estrellas", "1", "1");
insert into puntuacion values ("2", "2 estrellas", "2", "2");
insert into puntuacion values ("3", "3 estrellas", "3", "3");
insert into puntuacion values ("4", "4 estrellas", "4", "4");









