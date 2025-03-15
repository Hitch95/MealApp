# Développement d'une application de recettes avec Jetpack Compose et Clean Architecture

## Objectifs

1.  Développer une application Android consommant l'API [https://www.themealdb.com/api/json/v1/1/search.php?f=a](https://www.themealdb.com/api/json/v1/1/search.php?f=a)
2.  Appliquer les principes de la Clean Architecture.
3.  Utiliser Jetpack Compose pour construire l'interface utilisateur.
4.  Implémenter des outils modernes : Retrofit, Dagger Hilt, Coroutines, et Coil.

## Contexte

L'application doit permettre :

* D'afficher une liste de recettes obtenue depuis l'API.
* De naviguer vers un écran de détails pour chaque recette sélectionnée.

## Technologies et librairies

* **Langage** : Kotlin
* **UI** : Jetpack Compose
* **Réseau** : Retrofit
* **Injection de dépendances** : Dagger Hilt
* **Chargement d'images** : Coil
* **Architecture** : Clean Architecture
* **Gestion de l'état** : ViewModel et Coroutines

## Structure de l'application

### Fonctionnalités principales :

1.  **Écran d'accueil** : Affiche une liste de recettes (nom et image).
2.  **Écran de détails** : Montre les détails d'une recette (nom, image, instructions, etc.).
