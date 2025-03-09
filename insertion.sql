-- Insertion des utilisateurs
INSERT INTO utilisateur (id_utilisateur, age, genre, mot_de_passe, nom_utilisateur) VALUES
(2, 20, 'Homme', '$2a$10$rgrK.3iy/mq/0V5IZ9fuQedMagV5tGCw.Uf5rFvJv5RT9pmtp3MAa', 'florian'),
(3, 20, 'Homme', '$2a$10$2CBigy.y1x4DTqeQvPU57O.SJGW34.wO9mM5IRKwta7hmzlTWotf2', 'bastien');

-- Insertion des pathologies (15)
INSERT INTO pathologie (id_pathologie, nom) VALUES
(1, 'Dépression'),
(2, 'Anxiété généralisée'),
(3, 'Trouble bipolaire'),
(4, 'Stress post-traumatique'),
(5, 'Trouble obsessionnel compulsif'),
(6, 'Phobie sociale'),
(7, 'Trouble panique'),
(8, 'Burnout'),
(9, 'Schizophrénie'),
(10, 'Trouble alimentaire'),
(11, 'Insomnie chronique'),
(12, 'Dépendance alcoolique'),
(13, 'Dépendance tabagique'),
(14, 'TDAH'),
(15, 'Trouble borderline');

-- Insertion des activités (30)
INSERT INTO activite (id_activite, nom_activite, description, discipline, duree, code_postal, numero_de_rue, rue, ville, pays, pathologie_prevention) VALUES
(1, 'Yoga relaxant', 'Séance de yoga doux pour la détente', 'Yoga', 60, '75001', 5, 'Rue de la Paix', 'Paris', 'France', 'Stress'),
(2, 'Art-thérapie créative', 'Expression artistique libre', 'Art', 90, '69001', 10, 'Rue de la République', 'Lyon', 'France', 'Dépression'),
(3, 'Course en nature', 'Running en forêt avec coach', 'Sport', 45, '13001', 20, 'Avenue du Prado', 'Marseille', 'France', 'Anxiété'),
(4, 'Méditation guidée', 'Session de pleine conscience', 'Méditation', 30, '31000', 15, 'Rue Alsace-Lorraine', 'Toulouse', 'France', 'Trouble panique'),
(5, 'Cuisine thérapeutique', 'Atelier nutrition équilibrée', 'Cuisine', 120, '59000', 8, 'Rue Nationale', 'Lille', 'France', 'Trouble alimentaire'),
(6, 'Théâtre d''improvisation', 'Exercices de confiance en soi', 'Théâtre', 90, '33000', 25, 'Cours Victor Hugo', 'Bordeaux', 'France', 'Phobie sociale'),
(7, 'Jardinage sensoriel', 'Contact avec la nature', 'Jardinage', 120, '44000', 3, 'Rue Crébillon', 'Nantes', 'France', 'Dépression'),
(8, 'Boxe thérapeutique', 'Gestion de l''agressivité', 'Sport', 60, '67000', 12, 'Rue du Faubourg National', 'Strasbourg', 'France', 'Trouble borderline'),
(9, 'Écriture expressive', 'Journaling émotionnel', 'Écriture', 45, '06000', 7, 'Avenue Jean Médecin', 'Nice', 'France', 'Trouble bipolaire'),
(10, 'Aquagym douce', 'Gymnastique en piscine', 'Sport', 45, '34000', 18, 'Rue de la Loge', 'Montpellier', 'France', 'Arthrite'),
(11, 'Musico-thérapie', 'Exploration sonore', 'Musique', 60, '69002', 30, 'Rue Président Edouard Herriot', 'Lyon', 'France', 'Autisme'),
(12, 'Tai Chi en plein air', 'Mouvements fluides', 'Arts martiaux', 55, '75004', 2, 'Rue des Rosiers', 'Paris', 'France', 'Parkinson'),
(13, 'Poterie tactile', 'Modelage d''argile', 'Artisanat', 90, '13002', 14, 'Rue Saint-Ferréol', 'Marseille', 'France', 'Troubles sensoriels'),
(14, 'Randonnée contemplative', 'Marche méditative', 'Plein air', 180, '38000', 5, 'Rue Bayard', 'Grenoble', 'France', 'Burnout'),
(15, 'Sophrologie', 'Techniques de relaxation', 'Thérapie', 50, '59000', 9, 'Rue Esquermoise', 'Lille', 'France', 'Insomnie'),
(16, 'Danse-thérapie', 'Expression corporelle libre', 'Danse', 75, '31000', 22, 'Rue de Metz', 'Toulouse', 'France', 'Timidité'),
(17, 'Pilates adapté', 'Renforcement musculaire doux', 'Gymnastique', 55, '44000', 17, 'Rue Kervégan', 'Nantes', 'France', 'Lombalgie'),
(18, 'Atelier respiration', 'Maîtrise du souffle', 'Bien-être', 40, '67000', 4, 'Rue des Hallebardes', 'Strasbourg', 'France', 'Asthme'),
(19, 'Photothérapie', 'Expression visuelle', 'Photographie', 80, '06000', 11, 'Rue de France', 'Nice', 'France', 'Troubles émotionnels'),
(20, 'Yoga du rire', 'Exercices de rire spontané', 'Yoga', 50, '34000', 6, 'Rue Foch', 'Montpellier', 'France', 'Dépression'),
(21, 'Aqua-zen', 'Flottaison sensorielle', 'Bien-être', 70, '69002', 27, 'Quai Saint-Antoine', 'Lyon', 'France', 'Anxiété'),
(22, 'Atelier aromathérapie', 'Découverte des huiles essentielles', 'Santé', 65, '75005', 13, 'Rue Mouffetard', 'Paris', 'France', 'Migraine'),
(23, 'Calligraphie', 'Art de l''écriture', 'Art', 90, '13006', 9, 'Rue Paradis', 'Marseille', 'France', 'TDAH'),
(24, 'Equithérapie', 'Interaction avec les chevaux', 'Thérapie animale', 120, '38000', 2, 'Chemin des Alpins', 'Grenoble', 'France', 'Autisme'),
(25, 'Qi Gong énergétique', 'Harmonisation énergétique', 'Arts énergétiques', 60, '59000', 19, 'Rue de Béthune', 'Lille', 'France', 'Fatigue'),
(26, 'Yoga Nidra', 'Sommeil conscient', 'Yoga', 45, '31000', 8, 'Rue du Taur', 'Toulouse', 'France', 'Insomnie'),
(27, 'Cercle de parole', 'Échanges bienveillants', 'Communication', 90, '44000', 23, 'Rue de Strasbourg', 'Nantes', 'France', 'Isolement'),
(28, 'Marche afghane', 'Synchronisation souffle/pas', 'Marche', 120, '67000', 16, 'Rée du Dôme', 'Strasbourg', 'France', 'Stress'),
(29, 'Mandala-thérapie', 'Création de mandalas', 'Art-thérapie', 75, '06000', 4, 'Rue Masséna', 'Nice', 'France', 'Troubles anxieux'),
(30, 'Self-défense émotionnelle', 'Gestion des conflits', 'Arts martiaux', 85, '34000', 12, 'Avenue du Pont Juvénal', 'Montpellier', 'France', 'Phobie sociale');

-- Liens pathologies-activités
INSERT INTO pathologie_activite (id_activite, id_pathologie) VALUES
(1,2), (1,7), (2,1), (3,2), (3,8), (4,7), (5,10), 
(6,6), (7,1), (8,15), (9,3), (10,8), (11,9), (12,14), 
(13,5), (14,8), (15,11), (16,6), (17,8), (18,2), 
(19,1), (20,1), (21,2), (22,7), (23,14), (24,9), 
(25,8), (26,11), (27,6), (28,8), (29,2), (30,6);

-- Pathologies utilisateurs
INSERT INTO pathologie_utilisateur (id_utilisateur, id_pathologie) VALUES
(2,2), (2,7),
(3,1), (3,5), (3,10), (3,14);

-- Programmes thérapeutiques
INSERT INTO programme_therapeutique (id_programme_therapeutique, nom_programme_therapeutique, id_utilisateur) VALUES
(1, 'Gestion de l''anxiété', 2),
(2, 'Développement personnel', 2),
(3, 'Routine relaxante', 2),
(4, 'Équilibre émotionnel', 3),
(5, 'Contrôle des addictions', 3),
(6, 'Confiance en public', 3);

-- Associations programmes-activités
INSERT INTO programme_therapeutique_activite (id_programme_therapeutique, id_activite) VALUES
(1,1), (1,4), (1,21),
(2,3), (2,14),
(3,15),
(4,2), (4,7), (4,19), (4,20),
(5,5), (5,12), (5,23),
(6,6), (6,16), (6,27), (6,30);