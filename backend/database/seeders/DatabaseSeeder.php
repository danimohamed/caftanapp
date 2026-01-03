<?php

namespace Database\Seeders;

use Illuminate\Database\Seeder;

/**
 * Seeder principal de la base de données
 * 
 * Ce seeder appelle tous les autres seeders de l'application
 */
class DatabaseSeeder extends Seeder
{
    /**
     * Exécuter les seeders de la base de données
     * 
     * Commande pour exécuter : php artisan db:seed
     * ou : php artisan migrate:fresh --seed
     */
    public function run(): void
    {
        // Appeler le seeder des caftans
        $this->call([
            CaftanSeeder::class,
        ]);

        echo "\n🎉 Base de données remplie avec succès!\n";
        echo "📊 Vous pouvez maintenant tester l'API avec les caftans ajoutés.\n\n";
    }
}
