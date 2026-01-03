<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

/**
 * Modèle Caftan
 *
 * Représente un caftan dans l'application
 * Ce modèle gère les opérations sur la table 'caftans'
 */
class Caftan extends Model
{
    use HasFactory;

    /**
     * Le nom de la table associée au modèle
     *
     * @var string
     */
    protected $table = 'caftans';

    /**
     * Les attributs qui peuvent être assignés en masse
     * Ces champs peuvent être remplis via un formulaire ou une requête API
     *
     * @var array
     */
    protected $fillable = [
        'name',         // Nom du caftan
        'size',         // Taille (S, M, L, XL)
        'price',        // Prix par jour
        'image_url',    // URL de l'image
        'availability'  // Disponibilité (true = disponible, false = loué)
    ];

    /**
     * Les attributs qui doivent être castés vers des types natifs
     *
     * @var array
     */
    protected $casts = [
        'price' => 'decimal:2',      // Prix avec 2 décimales
        'availability' => 'boolean'  // Disponibilité en booléen
    ];

    /**
     * Relation avec les locations
     * Un caftan peut avoir plusieurs locations
     *
     * @return \Illuminate\Database\Eloquent\Relations\HasMany
     */
    public function rentals()
    {
        return $this->hasMany(Rental::class);
    }

    /**
     * Vérifier si le caftan est disponible
     *
     * @return bool
     */
    public function isAvailable()
    {
        return $this->availability === true;
    }

    /**
     * Marquer le caftan comme loué
     *
     * @return void
     */
    public function markAsRented()
    {
        $this->availability = false;
        $this->save();
    }

    /**
     * Marquer le caftan comme disponible
     *
     * @return void
     */
    public function markAsAvailable()
    {
        $this->availability = true;
        $this->save();
    }
}

