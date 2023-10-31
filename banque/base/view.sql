CREATE  VIEW solde_view AS
SELECT numeroCin,SUM(entree) as sommeentree,SUM(sortie) as sommesortie , (SUM(entree)-SUM(sortie)) as solde 
FROM transaction
GROUP BY numerocin
;