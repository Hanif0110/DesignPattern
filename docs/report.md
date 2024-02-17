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
