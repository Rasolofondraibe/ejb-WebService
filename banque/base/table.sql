/*
    idTransaction       datetransaction        numeroCin       entree       sortie
    transaction1        24/10/2023 00:00:00    personne1        1000         0
    transaction2        24/10/2023 00:00:00    personne1        0           500
    transaction3        24/10/2023 00:00:00    personne1        500           0
    transaction4        24/10/2023 00:00:00    personne2        2000          0
    transaction5        24/10/2023 00:00:00    personne2        500           0
    transaction6        24/10/2023 00:00:00    personne2        0            500



    numeroCin       sommeentree     sommesortie     reste
    personne1       1500            500             1000
    personne2       2500            500             2000
*/





CREATE TABLE transaction(
    idtransaction VARCHAR(100) DEFAULT 'transaction' || nextval('transactionSequence')::TEXT PRIMARY KEY,
    datetransaction timestamp DEFAULT NOW(),
    numerocin VARCHAR(100),
    entree DOUBLE PRECISION DEFAULT 0,
    sortie DOUBLE PRECISION DEFAULT 0
);

