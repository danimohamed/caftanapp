# 👗 API de Location de Caftans Marocains

## 📋 Description

Application backend simple développée avec **Laravel** et **MySQL** pour gérer la location de caftans marocains traditionnels. Projet de niveau étudiant (BTS/Université).

## 🎯 Fonctionnalités

- ✅ Afficher la liste de tous les caftans disponibles
- ✅ Afficher les détails d'un caftan spécifique
- ✅ Créer une réservation de location
- ✅ Validation des données
- ✅ Base de données avec 10 exemples de caftans marocains

## 🛠️ Technologies Utilisées

- **Backend** : Laravel 10.x
- **Base de données** : MySQL 8.0
- **PHP** : 8.1+
- **Language** : PHP avec commentaires en français

## 📁 Structure du Projet

```
backend/
├── app/
│   ├── Http/
│   │   └── Controllers/
│   │       ├── CaftanController.php      # Gestion des caftans
│   │       └── RentalController.php      # Gestion des locations
│   └── Models/
│       ├── Caftan.php                    # Modèle Caftan
│       └── Rental.php                    # Modèle Rental
├── config/
│   └── database.php                      # Configuration DB
├── database/
│   ├── migrations/
│   │   ├── 2024_01_01_000001_create_caftans_table.php
│   │   └── 2024_01_01_000002_create_rentals_table.php
│   └── seeders/
│       ├── CaftanSeeder.php              # Données de test
│       └── DatabaseSeeder.php
├── routes/
│   └── api.php                           # Routes API
├── .env.example                          # Configuration exemple
├── composer.json                         # Dépendances PHP
└── README.md                             # Ce fichier
```

## 🚀 Installation

### Prérequis

- PHP 8.1 ou supérieur
- Composer
- MySQL 8.0
- XAMPP ou Laragon (recommandé pour les étudiants)

### Étapes d'Installation

1. **Cloner ou télécharger le projet**
```bash
cd backend
```

2. **Installer les dépendances Laravel**
```bash
composer install
```

3. **Configurer le fichier .env**
```bash
# Copier le fichier .env.example
copy .env.example .env

# Modifier les paramètres de la base de données dans .env :
DB_CONNECTION=mysql
DB_HOST=127.0.0.1
DB_PORT=3306
DB_DATABASE=caftan_rental
DB_USERNAME=root
DB_PASSWORD=
```

4. **Créer la base de données**
```sql
-- Dans phpMyAdmin ou MySQL :
CREATE DATABASE caftan_rental;
```

5. **Générer la clé de l'application**
```bash
php artisan key:generate
```

6. **Exécuter les migrations**
```bash
php artisan migrate
```

7. **Remplir la base de données avec des exemples**
```bash
php artisan db:seed
```

8. **Démarrer le serveur**
```bash
php artisan serve
```

L'API sera accessible sur : `http://localhost:8000`

## 📡 Routes API

### 1. Obtenir tous les caftans
```http
GET /api/caftans
```

**Réponse :**
```json
{
  "success": true,
  "message": "Liste des caftans récupérée avec succès",
  "data": [
    {
      "id": 1,
      "name": "Caftan Royal Fassi",
      "size": "M",
      "price": "500.00",
      "image_url": "https://...",
      "availability": true,
      "created_at": "2024-01-01T00:00:00.000000Z",
      "updated_at": "2024-01-01T00:00:00.000000Z"
    }
  ]
}
```

### 2. Obtenir un caftan spécifique
```http
GET /api/caftans/{id}
```

**Exemple :** `GET /api/caftans/1`

**Réponse :**
```json
{
  "success": true,
  "message": "Caftan récupéré avec succès",
  "data": {
    "id": 1,
    "name": "Caftan Royal Fassi",
    "size": "M",
    "price": "500.00",
    "image_url": "https://...",
    "availability": true
  }
}
```

### 3. Créer une location
```http
POST /api/rent
Content-Type: application/json
```

**Body :**
```json
{
  "customer_name": "Fatima Zahra",
  "caftan_id": 1,
  "start_date": "2024-12-15",
  "end_date": "2024-12-18"
}
```

**Réponse :**
```json
{
  "success": true,
  "message": "Location créée avec succès",
  "data": {
    "rental": {
      "id": 1,
      "customer_name": "Fatima Zahra",
      "caftan_id": 1,
      "start_date": "2024-12-15",
      "end_date": "2024-12-18",
      "total_price": "2000.00"
    },
    "caftan": {
      "id": 1,
      "name": "Caftan Royal Fassi",
      "availability": false
    }
  }
}
```

### 4. Test de l'API
```http
GET /api/test
```

## 🗄️ Structure de la Base de Données

### Table `caftans`
| Colonne       | Type         | Description                    |
|---------------|--------------|--------------------------------|
| id            | bigint       | Clé primaire auto-incrémentée  |
| name          | varchar(255) | Nom du caftan                  |
| size          | varchar(255) | Taille (S, M, L, XL)          |
| price         | decimal(8,2) | Prix par jour (MAD)           |
| image_url     | varchar(255) | URL de l'image                 |
| availability  | boolean      | Disponibilité                  |
| created_at    | timestamp    | Date de création               |
| updated_at    | timestamp    | Date de modification           |

### Table `rentals`
| Colonne       | Type         | Description                    |
|---------------|--------------|--------------------------------|
| id            | bigint       | Clé primaire auto-incrémentée  |
| customer_name | varchar(255) | Nom du client                  |
| caftan_id     | bigint       | ID du caftan (clé étrangère)  |
| start_date    | date         | Date de début                  |
| end_date      | date         | Date de fin                    |
| total_price   | decimal(10,2)| Prix total calculé             |
| created_at    | timestamp    | Date de création               |
| updated_at    | timestamp    | Date de modification           |

## 🧪 Tester l'API

### Avec Postman
1. Importer les routes dans Postman
2. Tester chaque endpoint avec les exemples ci-dessus

### Avec curl
```bash
# Liste des caftans
curl http://localhost:8000/api/caftans

# Un caftan spécifique
curl http://localhost:8000/api/caftans/1

# Créer une location
curl -X POST http://localhost:8000/api/rent \
  -H "Content-Type: application/json" \
  -d '{"customer_name":"Fatima","caftan_id":1,"start_date":"2024-12-15","end_date":"2024-12-18"}'
```

## 📚 Exemples de Caftans Disponibles

Le seeder ajoute 10 caftans marocains traditionnels :

1. **Caftan Royal Fassi** - 500 MAD/jour
2. **Caftan Marocain Brodé Or** - 650 MAD/jour
3. **Caftan Velours Bleu Roi** - 450 MAD/jour
4. **Caftan Takchita Rouge** - 800 MAD/jour
5. **Caftan Soie Vert Émeraude** - 550 MAD/jour
6. **Caftan Blanc Perles** - 700 MAD/jour
7. **Caftan Doré Mariée** - 900 MAD/jour
8. **Caftan Rose Poudré** - 480 MAD/jour
9. **Caftan Noir Diamants** - 750 MAD/jour
10. **Caftan Turquoise Traditionnel** - 520 MAD/jour

## ⚠️ Validation

### Règles de Validation pour POST /api/rent

- `customer_name` : Requis, texte, max 255 caractères
- `caftan_id` : Requis, doit exister dans la table caftans
- `start_date` : Requis, format date, pas avant aujourd'hui
- `end_date` : Requis, format date, après start_date

## 🎓 Notes pour les Étudiants

### Concepts Laravel Utilisés

1. **Migrations** : Créer et modifier la structure de la base de données
2. **Models (Eloquent)** : Interagir avec la base de données facilement
3. **Controllers** : Gérer la logique métier
4. **Routes API** : Définir les endpoints
5. **Validation** : Vérifier les données entrantes
6. **Seeders** : Remplir la base avec des données de test
7. **Relations** : belongsTo et hasMany entre Caftan et Rental

### Commandes Laravel Utiles

```bash
# Voir toutes les routes
php artisan route:list

# Réinitialiser la base de données
php artisan migrate:fresh --seed

# Créer un nouveau contrôleur
php artisan make:controller NomController

# Créer un nouveau modèle avec migration
php artisan make:model NomModele -m
```

## 🐛 Dépannage

### Erreur "Class not found"
```bash
composer dump-autoload
```

### Erreur de connexion à la base de données
- Vérifier que MySQL est démarré (XAMPP/Laragon)
- Vérifier les paramètres dans le fichier `.env`
- Vérifier que la base de données `caftan_rental` existe

### Erreur "No application encryption key"
```bash
php artisan key:generate
```

## 👨‍💻 Auteur

Projet étudiant - API de Location de Caftans Marocains

## 📝 Licence

Projet éducatif - Libre d'utilisation pour l'apprentissage
