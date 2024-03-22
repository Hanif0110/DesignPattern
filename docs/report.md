# L3 design pattern report

- **Firstname**: Hanif
- **Lastname**: NIZAMUDDIN MOHAMED HANIF


> Add your thoughts on every TP bellow, everything is interresting but no need to right a book.
> 
> Keep it short simple and efficient:
> 
> - What you did and why
> - What helped you and why
> - What did you find difficult
> - What did not help you
> - What did you need to change
> - Anything relevant
> 
> Add a link to schemas describing your architecture (UML or not but add a legend)
> 
> Remember: it is ok to make mistakes, you will have time to spot them later.
> 
> Fill free to contact me if needed.

---
...


28/01/2024 : Le travail a été consacrée à l'amélioration du code source. Cette amélioration a été réalisée en réduisant le nombre de lignes de code, ce qui a été accompli en regroupant les lignes de code similaires ou connexes en fonctions. Cela a probablement permis de rendre le code plus lisible, plus propre, et potentiellement plus efficace en facilitant la maintenance et les mises à jour futures.

04/02/2024 : Cette période a été marquée par une tentative d'ajouter des couches (layers) supplémentaires au projet, ce qui n'a malheureusement pas abouti. L'ajout de couches aurait était envisagé dans le but d'améliorer l'architecture du projet, de séparer les préoccupations, ou d'ajouter de nouvelles fonctionnalités. Cependant, pour des raisons non précisées, cette tentative n'a pas réussi.

10/02/2024 : Le travail a inclus la séparation des classes, ce qui implique une réorganisation du code pour améliorer sa structure et sa modularité. L'abstraction des données et des services a été améliorée, ce qui suggère un effort pour rendre le système plus flexible et son architecture plus robuste en définissant clairement les interfaces entre différents composants. La création et la suppression de fichiers temporaires, l'exécution et la validation des commandes, ainsi que le diagnostic des échecs de tests, indiquent un travail sur l'optimisation des processus et sur la qualité du code. Cependant, des échecs d'assertion dans les GhostTests ont été rencontrés, signalant des problèmes spécifiques qui nécessitent une attention pour garantir la fiabilité du système.

17/02/2024 : J'ai optimisé mon application de gestion de tâches en introduisant une carte de commandes dans TodoService pour une extensibilité accrue et une meilleure validation des arguments. Le feedback utilisateur a été enrichi pour améliorer la clarté après les opérations. La sérialisation et la désérialisation JSON ont été mises en place dans TodoDataAccess pour une gestion efficace des fichiers. La classe Todo a été complétée avec des méthodes appropriées. Par ailleurs, j'ai finalisé mon rapport technique et conçu un diagramme de classe temporaire pour visualiser l'architecture du projet

22/02/2024 : Aujourd'hui, j'ai travaillé sur plusieurs aspects d'une application de gestion de tâches, avec un accent particulier sur l'amélioration et l'extension des fonctionnalités liées à la manipulation de données todo, que ce soit en format CSV ou JSON. Voici un résumé de ce que nous avons accompli ensemble :

1. **Correction et amélioration du script de chargement des données CSV :** J'ai revu le script initial pour le rendre plus robuste et conforme aux attentes, notamment en gérant mieux les exceptions et en vérifiant que les dépendances nécessaires sont correctement incluses.

2. **Extension du modèle `Todo` :** Le modèle a été amélioré pour inclure un identifiant unique et une date d'échéance optionnelle, rendant les données plus complètes et utiles pour des cas d'usage variés.

3. **Amélioration de la gestion des todos :** Que ce soit pour l'insertion, la liste, la mise à jour, ou la migration des todos, j'ai apporté des modifications significatives pour rendre le code plus modulaire, facile à maintenir et à tester.

4. **Écriture en masse des todos :** J'ai étendu les fonctionnalités d'écriture pour permettre l'écriture en masse des todos dans des fichiers, optimisant ainsi les performances et la facilité d'utilisation.

Chaque étape de ce travail a été guidée par un souci d'efficacité, de clarté, et de maintenabilité du code. Malgré ces efforts pour pousser le projet vers l'avant, je tiens à m'excuser auprès de toi Anthony pour ne pas avoir réussi à aller au bout de toutes les ambitions que nous avions pour ce projet. Sache que j'ai vraiment fait de mon mieux pour appliquer toutes les améliorations possibles et rendre l'application plus robuste et fonctionnelle. Mes excuses pour les parties que je n'ai pas pu compléter à temps. J'espère que les modifications apportées seront utiles et serviront de base solide pour la suite du développement du projet.
