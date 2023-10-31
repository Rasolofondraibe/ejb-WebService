CREATE TABLE terre (
    idterre VARCHAR(100) DEFAULT 'terre' || nextval('terreSequence')::TEXT PRIMARY KEY,
    nomterre VARCHAR(100),
    numerocin VARCHAR(255)
);


CREATE TABLE coordonnee(
    idcoordonnee VARCHAR(100) DEFAULT 'coordonnee' || nextval('coordonneeSequence')::TEXT PRIMARY KEY,
    idterre VARCHAR(255),
    coordonneex VARCHAR(100),
    coordonneey VARCHAR(100),
    FOREIGN KEY(idterre) REFERENCES terre(idterre)
);