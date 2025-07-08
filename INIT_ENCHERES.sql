    DROP TABLE IF EXISTS ENCHERES;
    DROP TABLE IF EXISTS RETRAITS;
	DROP TABLE IF EXISTS ARTICLES_VENDUS;
    DROP TABLE IF EXISTS CATEGORIES;
	
    DROP TABLE IF EXISTS UTILISATEURS;

    CREATE TABLE CATEGORIES (
                                no_categorie   INTEGER IDENTITY(1,1) NOT NULL,
                                libelle        VARCHAR(30) NOT NULL
    )

    ALTER TABLE CATEGORIES ADD constraint categorie_pk PRIMARY KEY (no_categorie)



    CREATE TABLE UTILISATEURS (
                                  no_utilisateur   INTEGER IDENTITY(1,1) NOT NULL,
                                  pseudo           VARCHAR(30) NOT NULL,
                                  nom              VARCHAR(30) NOT NULL,
                                  prenom           VARCHAR(30) NOT NULL,
                                  email            VARCHAR(50) NOT NULL,
                                  telephone        VARCHAR(15),
                                  rue              VARCHAR(30) NOT NULL,
                                  code_postal      VARCHAR(10) NOT NULL,
                                  ville            VARCHAR(50) NOT NULL,
                                  mot_de_passe     VARCHAR(255) NOT NULL,
                                  credit           INTEGER NOT NULL,
                                  administrateur   bit NOT NULL
    )

    ALTER TABLE UTILISATEURS ADD constraint utilisateur_pk PRIMARY KEY (no_utilisateur)


    CREATE TABLE ARTICLES_VENDUS (
                                     no_article                    INTEGER IDENTITY(1,1) NOT NULL,
                                     nom_article                   VARCHAR(30) NOT NULL,
                                     description                   VARCHAR(300) NOT NULL,
                                     date_debut_encheres           DATE NOT NULL,
                                     date_fin_encheres             DATE NOT NULL,
                                     prix_initial                  INTEGER,
                                     prix_vente                    INTEGER,
                                     no_utilisateur                INTEGER NULL,
                                     no_categorie                  INTEGER NOT NULL
    )



    ALTER TABLE ARTICLES_VENDUS ADD constraint articles_vendus_pk PRIMARY KEY (no_article)


    CREATE TABLE RETRAITS (
                              no_retrait       INTEGER NOT NULL IDENTITY,
                              no_article       INTEGER NOT NULL,
                              rue              VARCHAR(30) NOT NULL,
                              code_postal      VARCHAR(15) NOT NULL,
                              ville            VARCHAR(30) NOT NULL
    )

    ALTER TABLE RETRAITS ADD constraint retrait_pk PRIMARY KEY  (no_retrait)
    ALTER TABLE RETRAITS
        ADD CONSTRAINT retrait_article_fk FOREIGN KEY ( no_article ) REFERENCES  ARTICLES_VENDUS (no_article)
            ON DELETE CASCADE
            ON UPDATE no action

    CREATE TABLE ENCHERES(
                             no_enchere  INTEGER IDENTITY(1,1) NOT NULL,
                             date_enchere datetime NOT NULL,
                             montant_enchere INTEGER NOT NULL,
                             no_article INTEGER NULL,
                             no_utilisateur INTEGER NULL
    )

    ALTER TABLE ENCHERES ADD constraint enchere_pk PRIMARY KEY ( no_enchere)

    --1
    ALTER TABLE ARTICLES_VENDUS
        ADD CONSTRAINT ventes_utilisateur_fk FOREIGN KEY ( no_utilisateur ) REFERENCES UTILISATEURS ( no_utilisateur )
            ON DELETE SET NULL
    -- 2
    ALTER TABLE ENCHERES
        ADD CONSTRAINT encheres_no_article_fk FOREIGN KEY ( no_article ) REFERENCES ARTICLES_VENDUS ( no_article )
            ON DELETE NO ACTION

    ALTER TABLE ENCHERES
        ADD CONSTRAINT encheres_utilisateur_fk FOREIGN KEY ( no_utilisateur ) REFERENCES UTILISATEURS ( no_utilisateur )
            ON DELETE CASCADE

    ALTER TABLE ARTICLES_VENDUS
        ADD CONSTRAINT articles_vendus_categories_fk FOREIGN KEY ( no_categorie )
            REFERENCES categories ( no_categorie )
            ON DELETE NO ACTION

    INSERT INTO UTILISATEURS(
        pseudo,
        nom,
        prenom,
        email,
        telephone,
        rue,
        code_postal,
        ville,
        mot_de_passe,
        credit,
        administrateur
    )
    VALUES
		-- MOT DE PASSE = 'Test1&'
        ('yajuan', 'Hu', 'Yajuan', 'yajuan@mail.fr', '0606060606', '1 rue de Yajuan', '75000', 'Paris', '$2a$10$lO.ripC.4EutJqNEeg9S0u9OnHefPvaTw/Zme7JysNA5.lnQTknmS', 100, 0), 
        ('julien', 'Lefevre', 'Julien', 'julien@mail.fr', '0606060606', '2 rue de Julien', '75000', 'Paris', '$2a$10$lO.ripC.4EutJqNEeg9S0u9OnHefPvaTw/Zme7JysNA5.lnQTknmS', 100, 0),
        ('louis', 'Lesage', 'Louis', 'louis@mail.fr', '0606060606', '3 rue de Louis', '75000', 'Paris', '$2a$10$lO.ripC.4EutJqNEeg9S0u9OnHefPvaTw/Zme7JysNA5.lnQTknmS', 100, 0),
        ('admin', 'Admin', 'Admin', 'admin@mail.fr', '0606060606', '4 rue de la papipopapala', '75000', 'Paris', '$2a$10$lO.ripC.4EutJqNEeg9S0u9OnHefPvaTw/Zme7JysNA5.lnQTknmS', 100, 1);


	INSERT INTO CATEGORIES(
		libelle
	) VALUES
		('Informatique'),
		('Ameublement'),
		('Vêtements'),
		('Sports & Loisirs');

	INSERT INTO ARTICLES_VENDUS(
		nom_article,
		description,
		date_debut_encheres,
		date_fin_encheres,
		prix_initial,
		prix_vente,
		no_utilisateur,
		no_categorie
	) VALUES
	-- CATEGORIE INFORMATIQUE
	('PC GAMING', 'Un super PC GAMING', DATEFROMPARTS(2025, 8, 3), DATEFROMPARTS(2025, 8, 31), 1000, null, 3, 1),
	('Clavier GAMING', 'Un super clavier GAMING', DATEFROMPARTS(2025, 7, 1), DATEFROMPARTS(2025, 7, 31), 60, null, 2, 1),
	('Souris GAMING', 'Une super souris GAMING', DATEFROMPARTS(2024, 7, 3), DATEFROMPARTS(2024, 7, 31), 100, null, 1, 1),
	('Tapis de souris GAMING', 'Un super tapis de souris GAMING', DATEFROMPARTS(2025, 5, 1), DATEFROMPARTS(2025, 5, 31), 10, null, 3, 1),
	-- CATEGORIE AMEUBLEMENT
	('Chaise', 'Une magnifique chaise en bois', DATEFROMPARTS(2025, 8, 3), DATEFROMPARTS(2025, 8, 31), 30, null, 2, 2),
	('Table', 'Une magnifique Table en bois', DATEFROMPARTS(2025, 7, 1), DATEFROMPARTS(2025, 7, 31), 750, null, 1, 2),
	('Commode', 'Une magnifique commode en bois', DATEFROMPARTS(2024, 7, 3), DATEFROMPARTS(2024, 7, 31), 300, null, 3, 2),
	('Lit', 'Un magnifique lit en bois', DATEFROMPARTS(2025, 5, 1), DATEFROMPARTS(2025, 5, 31), 200, null, 2, 2),
	-- CATEGORIE VETEMENT
	('Slip en tungsten', 'Un slip en tungsten tach�', DATEFROMPARTS(2025, 8, 3), DATEFROMPARTS(2025, 8, 31), 1, null, 2, 3),
	('T-shirt', 'Un t-shirt sans tache', DATEFROMPARTS(2025, 7, 1), DATEFROMPARTS(2025, 7, 31), 5, null, 3, 3),
	('Pantalon', 'Un pantalon en cuir de vache', DATEFROMPARTS(2024, 7, 3), DATEFROMPARTS(2024, 7, 31), 20, null, 2, 3),
	('Chaussette', 'Paire de chaussette peu odorante', DATEFROMPARTS(2025, 5, 1), DATEFROMPARTS(2025, 5, 31), 2, null, 1, 3),
	-- CATEGORIE SPORT & LOISIR
	('Katana', 'Un incroyable katana presque neuf', DATEFROMPARTS(2025, 8, 3), DATEFROMPARTS(2025, 8, 31), 450, null, 3, 4),
	('Skateboard', 'Un incroyable skateboard presque neuf', DATEFROMPARTS(2025, 7, 1), DATEFROMPARTS(2025, 7, 31), 80, null, 2, 4),
	('Ballon de foot', 'Un incroyable ballon de foot presque neuf', DATEFROMPARTS(2024, 7, 3), DATEFROMPARTS(2024, 7, 31), 10, null, 1, 4),
	('Prot�ge-tibias', 'Des incroyables prot�ges-tibias presque neuf', DATEFROMPARTS(2025, 5, 1), DATEFROMPARTS(2025, 5, 31), 21, null, 3, 4)
	;

	INSERT INTO RETRAITS(
		no_article,
		rue,
		code_postal,
		ville
	) VALUES
	(1, '3 rue de la rue', '35000', 'Rennes'),
	(2, '2 rue de la rue', '35000', 'Rennes'),
	(3, '45 rue de la rue', '35000', 'Rennes'),
	(4, '6 rue de la rue', '35000', 'Rennes'),
	(5, '28 rue de la rue', '35000', 'Rennes'),
	(6, '12 rue de la rue', '35000', 'Rennes'),
	(7, '1 rue de la rue', '35000', 'Rennes'),
	(8, '8 rue de la rue', '35000', 'Rennes'),
	(9, '9 rue de la rue', '35000', 'Rennes'),
	(10, '10 rue de la rue', '35000', 'Rennes'),
	(11, '11 rue de la rue', '35000', 'Rennes'),
	(12, '12 rue de la rue', '35000', 'Rennes'),
	(13, '13 rue de la rue', '35000', 'Rennes'),
	(14, '14 rue de la rue', '35000', 'Rennes'),
	(15, '15 rue de la rue', '35000', 'Rennes'),
	(16, '16 rue de la rue', '35000', 'Rennes');

	INSERT INTO ENCHERES(
		no_article,
		no_utilisateur,
		date_enchere,
		montant_enchere
	) VALUES

	--('Clavier GAMING', 'Un super clavier GAMING', DATEFROMPARTS(2025, 7, 1), DATEFROMPARTS(2025, 7, 31), 60, null, 2, 1),
	(2, 1, DATEFROMPARTS(2025, 7, 2), 61),

	--('Souris GAMING', 'Une super souris GAMING', DATEFROMPARTS(2024, 7, 3), DATEFROMPARTS(2024, 7, 31), 100, null, 1, 1),
	(3, 2, DATEFROMPARTS(2024, 7, 7), 101),
	(3, 3, DATEFROMPARTS(2024, 7, 10), 102),
	(3, 2, DATEFROMPARTS(2024, 7, 12), 120),
	(3, 3, DATEFROMPARTS(2024, 7, 30), 121),

	--('Tapis de souris GAMING', 'Un super tapis de souris GAMING', DATEFROMPARTS(2025, 5, 1), DATEFROMPARTS(2025, 5, 31), 10, null, 3, 1),
	(4, 2, DATEFROMPARTS(2025, 5, 7), 11),
	(4, 1, DATEFROMPARTS(2025, 5, 10), 12),
	(4, 2, DATEFROMPARTS(2025, 5, 12), 13),
	--------------------------------------------------
	
	--('Table', 'Une magnifique Table en bois', DATEFROMPARTS(2025, 7, 1), DATEFROMPARTS(2025, 7, 31), 750, null, 1, 2),
	(6, 1, DATEFROMPARTS(2025, 7, 2), 751),

	--('Commode', 'Une magnifique commode en bois', DATEFROMPARTS(2024, 7, 3), DATEFROMPARTS(2024, 7, 31), 300, null, 3, 2),
	(7, 1, DATEFROMPARTS(2024, 7, 7), 301),
	(7, 2, DATEFROMPARTS(2024, 7, 10), 302),
	(7, 1, DATEFROMPARTS(2024, 7, 12), 320),
	(7, 2, DATEFROMPARTS(2024, 7, 30), 321),

	--('Lit', 'Un magnifique lit en bois', DATEFROMPARTS(2025, 5, 1), DATEFROMPARTS(2025, 5, 31), 200, null, 2, 2),
	(8, 3, DATEFROMPARTS(2025, 5, 7), 201),
	(8, 1, DATEFROMPARTS(2025, 5, 10), 202),
	(8, 3, DATEFROMPARTS(2025, 5, 12), 203),
	
	---------------------------------------------------------

	--('T-shirt', 'Un t-shirt sans tache', DATEFROMPARTS(2025, 7, 1), DATEFROMPARTS(2025, 7, 31), 5, null, 3, 3),
	(10, 1, DATEFROMPARTS(2025, 7, 2), 61),

	--('Pantalon', 'Un pantalon en cuir de vache', DATEFROMPARTS(2024, 7, 3), DATEFROMPARTS(2024, 7, 31), 20, null, 2, 3),
	(11, 1, DATEFROMPARTS(2024, 7, 7), 21),
	(11, 3, DATEFROMPARTS(2024, 7, 10), 21),
	(11, 1, DATEFROMPARTS(2024, 7, 12), 40),
	(11, 3, DATEFROMPARTS(2024, 7, 30), 41),

	--('Chaussette', 'Paire de chaussette peu odorante', DATEFROMPARTS(2025, 5, 1), DATEFROMPARTS(2025, 5, 31), 2, null, 1, 3),
	(12, 2, DATEFROMPARTS(2025, 5, 7), 3),
	(12, 3, DATEFROMPARTS(2025, 5, 10), 4),
	(12, 2, DATEFROMPARTS(2025, 5, 12), 1000),
	
	---------------------------------------------------------

	--('Skateboard', 'Un incroyable skateboard presque neuf', DATEFROMPARTS(2025, 7, 1), DATEFROMPARTS(2025, 7, 31), 80, null, 2, 4),
	(14, 1, DATEFROMPARTS(2025, 7, 2), 81),

	--('Ballon de foot', 'Un incroyable ballon de foot presque neuf', DATEFROMPARTS(2024, 7, 3), DATEFROMPARTS(2024, 7, 31), 10, null, 1, 4),
	(15, 2, DATEFROMPARTS(2024, 7, 7), 11),
	(15, 3, DATEFROMPARTS(2024, 7, 10), 12),
	(15, 2, DATEFROMPARTS(2024, 7, 12), 20),
	(15, 3, DATEFROMPARTS(2024, 7, 30), 21),

	--('Prot�ge-tibias', 'Des incroyables prot�ges-tibias presque neuf', DATEFROMPARTS(2025, 5, 1), DATEFROMPARTS(2025, 5, 31), 21, null, 3, 4)
	(16, 2, DATEFROMPARTS(2025, 5, 7), 22),
	(16, 1, DATEFROMPARTS(2025, 5, 10), 23),
	(16, 2, DATEFROMPARTS(2025, 5, 12), 30);

	-- UPDATE DES ENCHERE TERMINEE
	UPDATE ARTICLES_VENDUS SET prix_vente = (SELECT TOP 1 montant_enchere FROM ENCHERES where no_article = 3 order by montant_enchere) where no_article = 3;
	UPDATE ARTICLES_VENDUS SET prix_vente = (SELECT TOP 1 montant_enchere FROM ENCHERES where no_article = 4 order by montant_enchere) where no_article = 4;
	UPDATE ARTICLES_VENDUS SET prix_vente = (SELECT TOP 1 montant_enchere FROM ENCHERES where no_article = 7 order by montant_enchere) where no_article = 7;
	UPDATE ARTICLES_VENDUS SET prix_vente = (SELECT TOP 1 montant_enchere FROM ENCHERES where no_article = 8 order by montant_enchere) where no_article = 8;
	UPDATE ARTICLES_VENDUS SET prix_vente = (SELECT TOP 1 montant_enchere FROM ENCHERES where no_article = 11 order by montant_enchere) where no_article = 11;
	UPDATE ARTICLES_VENDUS SET prix_vente = (SELECT TOP 1 montant_enchere FROM ENCHERES where no_article = 12 order by montant_enchere) where no_article = 12;
	UPDATE ARTICLES_VENDUS SET prix_vente = (SELECT TOP 1 montant_enchere FROM ENCHERES where no_article = 15 order by montant_enchere) where no_article = 15;
	UPDATE ARTICLES_VENDUS SET prix_vente = (SELECT TOP 1 montant_enchere FROM ENCHERES where no_article = 16 order by montant_enchere) where no_article = 16;
