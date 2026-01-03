<?php

namespace Database\Seeders;

use Illuminate\Database\Seeder;
use App\Models\Caftan;

/**
 * Seeder pour remplir la base de données avec des exemples de caftans marocains
 * 
 * Ce seeder ajoute 10 caftans traditionnels marocains avec des images
 */
class CaftanSeeder extends Seeder
{
    /**
     * Exécuter le seeder
     * 
     * Commande pour exécuter : php artisan db:seed --class=CaftanSeeder
     */
    public function run(): void
    {
        // Delete all existing caftans first (disable foreign key checks)
        \DB::statement('SET FOREIGN_KEY_CHECKS=0;');
        Caftan::truncate();
        \DB::statement('SET FOREIGN_KEY_CHECKS=1;');
        
        // Tableau de 20 caftans marocains traditionnels
        // Using local images from the image folder
        // Using 10.0.2.2 for Android emulator to access localhost
        $caftans = [
            [
                'name' => 'Caftan Royal Fassi',
                'size' => 'M',
                'price' => 500.00,
                'image_url' => 'http://10.0.2.2:8000/image/01D2C2AC-2397-460A-B8C0-8904EFF78968.jpeg',
                'availability' => true
            ],
            [
                'name' => 'Caftan Marocain Brodé Or',
                'size' => 'L',
                'price' => 650.00,
                'image_url' => 'http://10.0.2.2:8000/image/0CFF82F6-C75A-406E-8585-78C3DEEC40F4.jpeg',
                'availability' => true
            ],
            [
                'name' => 'Caftan Velours Bleu Roi',
                'size' => 'S',
                'price' => 450.00,
                'image_url' => 'http://10.0.2.2:8000/image/0EE40873-80A4-4737-BBDE-7D003572F1EB.jpeg',
                'availability' => false  // Rented for testing
            ],
            [
                'name' => 'Caftan Takchita Rouge',
                'size' => 'XL',
                'price' => 800.00,
                'image_url' => 'http://10.0.2.2:8000/image/1A6011AD-9F8A-45DB-A23B-CBDF0618446B.jpeg',
                'availability' => true
            ],
            [
                'name' => 'Caftan Soie Vert Émeraude',
                'size' => 'M',
                'price' => 550.00,
                'image_url' => 'http://10.0.2.2:8000/image/26AA8C7D-7C18-42DB-9D5A-F0E678DA40BA.jpeg',
                'availability' => true
            ],
            [
                'name' => 'Caftan Blanc Perles',
                'size' => 'L',
                'price' => 700.00,
                'image_url' => 'http://10.0.2.2:8000/image/3382BD60-FDC1-4929-939A-5DEEE5A0B2AC.jpeg',
                'availability' => true
            ],
            [
                'name' => 'Caftan Doré Mariée',
                'size' => 'M',
                'price' => 900.00,
                'image_url' => 'http://10.0.2.2:8000/image/3E4D443D-E181-449A-ADDB-5274CF935AB2.jpeg',
                'availability' => true
            ],
            [
                'name' => 'Caftan Rose Poudré',
                'size' => 'S',
                'price' => 480.00,
                'image_url' => 'http://10.0.2.2:8000/image/42921FD8-5DA7-4D96-AB33-798E39A38590.jpeg',
                'availability' => true
            ],
            [
                'name' => 'Caftan Noir Diamants',
                'size' => 'L',
                'price' => 750.00,
                'image_url' => 'http://10.0.2.2:8000/image/430B995D-EBE4-4067-A6A4-2C879950A16A.jpeg',
                'availability' => false  // Rented for testing
            ],
            [
                'name' => 'Caftan Turquoise Traditionnel',
                'size' => 'M',
                'price' => 520.00,
                'image_url' => 'http://10.0.2.2:8000/image/529C48F7-06B2-4477-8704-F97B9A2B82BA.jpeg',
                'availability' => true
            ],
            [
                'name' => 'Caftan Argenté Luxe',
                'size' => 'L',
                'price' => 850.00,
                'image_url' => 'http://10.0.2.2:8000/image/54397209-2AD1-46F0-8FAB-E77BE0433C86.jpeg',
                'availability' => true
            ],
            [
                'name' => 'Caftan Bordeaux Velours',
                'size' => 'M',
                'price' => 620.00,
                'image_url' => 'http://10.0.2.2:8000/image/56BB3A9E-550F-4DA3-A5B6-C1E7260FDF45.jpeg',
                'availability' => true
            ],
            [
                'name' => 'Caftan Violet Princesse',
                'size' => 'S',
                'price' => 580.00,
                'image_url' => 'http://10.0.2.2:8000/image/6AC53B2B-E73F-45DE-A819-58FF9420230F.jpeg',
                'availability' => true
            ],
            [
                'name' => 'Caftan Bleu Ciel Broderie',
                'size' => 'XL',
                'price' => 720.00,
                'image_url' => 'http://10.0.2.2:8000/image/748B7BC9-D865-48F8-A0CF-3DB877984A4D.jpeg',
                'availability' => true
            ],
            [
                'name' => 'Caftan Orange Safran',
                'size' => 'M',
                'price' => 490.00,
                'image_url' => 'http://10.0.2.2:8000/image/791C3E2A-C39D-4747-836F-700C0AD07E00.jpeg',
                'availability' => true
            ],
            [
                'name' => 'Caftan Vert Jade',
                'size' => 'L',
                'price' => 680.00,
                'image_url' => 'http://10.0.2.2:8000/image/7D31A610-D7D4-4C5F-873C-A8EBB3F4CBE6.jpeg',
                'availability' => true
            ],
            [
                'name' => 'Caftan Champagne Élégance',
                'size' => 'M',
                'price' => 780.00,
                'image_url' => 'http://10.0.2.2:8000/image/97DE3056-D948-48D9-B04E-34220495154E.jpeg',
                'availability' => true
            ],
            [
                'name' => 'Caftan Corail Moderne',
                'size' => 'S',
                'price' => 540.00,
                'image_url' => 'http://10.0.2.2:8000/image/A0F3328B-A7EA-4AAA-8139-BA93A9AEC1B4.jpeg',
                'availability' => true
            ],
            [
                'name' => 'Caftan Prune Royal',
                'size' => 'L',
                'price' => 820.00,
                'image_url' => 'http://10.0.2.2:8000/image/CE080590-45E9-47CE-B681-EE721B710735.jpeg',
                'availability' => true
            ],
            [
                'name' => 'Caftan Beige Tradition',
                'size' => 'M',
                'price' => 560.00,
                'image_url' => 'http://10.0.2.2:8000/image/E635746A-ADA5-4733-A9C1-17198FD598D1.jpeg',
                'availability' => true
            ]
        ];

        // Insérer chaque caftan dans la base de données
        foreach ($caftans as $caftanData) {
            Caftan::create($caftanData);
        }

        // Message de confirmation dans la console
        echo "✅ 20 caftans marocains ont été ajoutés avec succès!\n";
    }
}
