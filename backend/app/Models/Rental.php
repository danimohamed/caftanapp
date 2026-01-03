<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use Carbon\Carbon;

/**
 * Modèle Rental (Location)
 * 
 * Représente une location de caftan dans l'application
 * Ce modèle gère les opérations sur la table 'rentals'
 */
class Rental extends Model
{
    use HasFactory;

    /**
     * Le nom de la table associée au modèle
     * 
     * @var string
     */
    protected $table = 'rentals';

    /**
     * Les attributs qui peuvent être assignés en masse
     * Ces champs peuvent être remplis via un formulaire ou une requête API
     * 
     * @var array
     */
    protected $fillable = [
        'customer_name',  // Nom du client
        'caftan_id',      // ID du caftan loué
        'start_date',     // Date de début
        'end_date',       // Date de fin
        'total_price'     // Prix total
    ];

    /**
     * Les attributs qui doivent être castés vers des types natifs
     * 
     * @var array
     */
    protected $casts = [
        'start_date' => 'date',        // Date de début en objet Date
        'end_date' => 'date',          // Date de fin en objet Date
        'total_price' => 'decimal:2',  // Prix avec 2 décimales
    ];

    /**
     * Les attributs supplémentaires à ajouter au JSON
     *
     * @var array
     */
    protected $appends = [
        'start_date_formatted',
        'end_date_formatted',
        'duration_days'
    ];

    /**
     * Obtenir la date de début formatée
     *
     * @return string
     */
    public function getStartDateFormattedAttribute()
    {
        return $this->start_date ? $this->start_date->format('Y-m-d') : '';
    }

    /**
     * Obtenir la date de fin formatée
     *
     * @return string
     */
    public function getEndDateFormattedAttribute()
    {
        return $this->end_date ? $this->end_date->format('Y-m-d') : '';
    }

    /**
     * Obtenir la durée en jours
     *
     * @return int
     */
    public function getDurationDaysAttribute()
    {
        return $this->getDurationInDays();
    }

    /**
     * Relation avec le caftan
     * Une location appartient à un caftan
     * 
     * @return \Illuminate\Database\Eloquent\Relations\BelongsTo
     */
    public function caftan()
    {
        return $this->belongsTo(Caftan::class);
    }

    /**
     * Calculer le nombre de jours de location
     * 
     * @return int
     */
    public function getDurationInDays()
    {
        // Calcule la différence en jours entre start_date et end_date
        return $this->start_date->diffInDays($this->end_date) + 1;
    }

    /**
     * Calculer le prix total de la location
     * Basé sur le prix par jour du caftan et le nombre de jours
     * 
     * @param float $pricePerDay Prix par jour du caftan
     * @return float
     */
    public function calculateTotalPrice($pricePerDay)
    {
        $days = $this->getDurationInDays();
        return $days * $pricePerDay;
    }
}
