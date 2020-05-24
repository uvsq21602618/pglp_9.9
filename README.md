# Logiciel de dessin

Une fois le programme executé, l'utilisateur peut accéder à plusieurs fonctionnalités:

1. la creation d'une forme(carre, cercle, rectangle, triangle ou composant du dessin) qui peut contenir plusieurs formes geometriques ou composants.
Il faut saisir par exemple pour:
	-un carré:
	nomCarre = carre((1,2),3)
	-un cercle:
	nomCercle = cercle((1,2),3)
	-un rectangle:
	nomRectangle = rectangle((3,2),(4,1))
	-un triangle:
	nomTriangle = triangle((1,2),(5,2),(2,3))
	-un composant du dessin:
	nomComposant = composant du dessin
	
2. l'ajout d'une forme dans un autre composant du dessin, par exemple on peut ajouter le carre c1 dans le dessin1:
	c1 = carre((1,2),1)
	dessin1 = composant du dessin
	put(dessin1, carre)

On ne peut pas ajouter de formes dans un carré, rectangle, triangle ou cercle.

3. Supprimer une forme ou un composant du dessin en cours. Par exemple si on supprime le carre c1:
	delete(c1)

4. Supprimer tout le dessin :
	deleteall
	
5. Deplacer une forme géométrique ou un composant en particulier avec move(nom,(x,y)). Par exemple on veut deplacer le carre c1 de 5 vers la droite et de 2 vers le bas:
	move(c1,(5, -2))

6. Deplacer tout le dessin avec moveall(x,y). Par exemple on veut déplacer toutes les formes du dessin en cours de 5 vers la droite et de 2 vers le bas:
	moveall(5, -2)
	
7. Afficher les informations d'une forme géométrique ou d'un composant sur le dessin en cours avec show(nom). Par exemple les informations du carré c1:
	show(c1)
	
8. Afficher les informations de toutes les formes géométriques et composants du dessin en cours avec showall:
	showall
	
9. Sauvegarder le dessin en cours dans la base de données avec save(nomSauvegarde). Tout le contenu du dessin sera ajouter dans un nouvel élément
de type composant du dessin qui portera le nom mis en argument qui sera enregistré dans la base de données. Si on veut sauvegarder notre dessin en
cours sous le nom de "dessin" :
	save(dessin)
	
Si le dessin en cours contient c1, compo et r1, ils seront dans la base de données stockés dans les tables correspondantes ainsi que ajoutés comme elements
d'un composant appelé "dessin".

10. Récupérer un dessin sauvegardé avec get(nom).
	get(c1)
	
11. Mettre à jour un dessin de la base de données par le dessin en cours. Par exemple le dessin en cours contenant c1 remplace le dessin "dessin" qui contient r dans 
la base de données:
	update(dessin)
	
12. Effacer une forme géométrique ou un composant dans la base de données. Par exemple on veut effacer c1:
	deletebackup(c1)
	
13. Pour terminer la saisie il suffit de taper:
	quit
