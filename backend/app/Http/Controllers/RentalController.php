<?php

namespace App\Http\Controllers;

use App\Models\Rental;
use App\Models\Caftan;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Validator;

/**
 * Contrôleur pour gérer les locations de caftans
 *
 * Ce contrôleur gère la création et consultation des locations
 * UNLIMITED RENTALS: Aucune restriction de disponibilité ou de dates
 */
class RentalController extends Controller
{
    /**
     * Récupérer toutes les locations
     *
     * Route: GET /api/rentals
     *
     * Retourne toutes les locations avec les informations du caftan associé
     *
     * @return \Illuminate\Http\JsonResponse
     */
    public function index()
    {
        // Récupérer toutes les locations avec les caftans associés
        $rentals = Rental::with('caftan')->orderBy('created_at', 'desc')->get();

        // Retourner les locations en format JSON
        return response()->json([
            'success' => true,
            'message' => 'Liste des locations récupérée avec succès',
            'data' => $rentals
        ], 200);
    }

    /**
     * Créer une nouvelle location
     *
     * Route: POST /api/rentals
     *
     * Permet à un client de louer un caftan pour une période donnée
     * UNLIMITED: Aucune vérification de disponibilité - locations illimitées
     *
     * @param Request $request La requête HTTP contenant les données de location
     * @return \Illuminate\Http\JsonResponse
     */
    public function store(Request $request)
    {
        // Étape 1 : Valider les données envoyées par le client
        $validator = Validator::make($request->all(), [
            // Nom du client : requis, texte, max 255 caractères
            'customer_name' => 'required|string|max:255',

            // ID du caftan : requis, doit exister dans la table caftans
            'caftan_id' => 'required|exists:caftans,id',

            // Date de début : requise, doit être une date, pas avant aujourd'hui
            'start_date' => 'required|date|after_or_equal:today',

            // Date de fin : requise, doit être une date, après la date de début
            'end_date' => 'required|date|after:start_date',
        ]);

        // Si la validation échoue, retourner les erreurs
        if ($validator->fails()) {
            return response()->json([
                'success' => false,
                'message' => 'Erreur de validation',
                'errors' => $validator->errors()
            ], 422);
        }

        // Étape 2 : Récupérer le caftan pour le calcul du prix
        $caftan = Caftan::find($request->caftan_id);

        // Étape 3 : Créer la location (UNLIMITED - pas de vérification de disponibilité)
        $rental = Rental::create([
            'customer_name' => $request->customer_name,
            'caftan_id' => $request->caftan_id,
            'start_date' => $request->start_date,
            'end_date' => $request->end_date,
            'total_price' => 0 // Will be calculated after
        ]);

        // Calculer le prix total (nombre de jours × prix par jour)
        $rental->total_price = $rental->calculateTotalPrice($caftan->price);

        // Sauvegarder la location dans la base de données
        $rental->save();

        // Étape 4 : Retourner la réponse de succès
        // Note: La disponibilité du caftan n'est PAS modifiée
        // Permet des locations illimitées et concurrentes
        return response()->json([
            'success' => true,
            'message' => 'Location créée avec succès',
            'data' => [
                'rental' => $rental,
                'caftan' => $caftan
            ]
        ], 201);
    }

    /**
     * Supprimer une location
     *
     * Route: DELETE /api/rentals/{id}
     *
     * Supprime une location
     * Note: La disponibilité du caftan n'est PAS modifiée
     *
     * @param int $id L'ID de la location à supprimer
     * @return \Illuminate\Http\JsonResponse
     */
    public function destroy($id)
    {
        // Trouver la location
        $rental = Rental::find($id);

        // Vérifier si la location existe
        if (!$rental) {
            return response()->json([
                'success' => false,
                'message' => 'Location introuvable'
            ], 404);
        }

        // Supprimer la location
        $rental->delete();

        // Retourner la réponse de succès
        // Note: La disponibilité du caftan n'est PAS modifiée
        return response()->json([
            'success' => true,
            'message' => 'Location supprimée avec succès',
            'data' => [
                'rental_id' => $id
            ]
        ], 200);
    }
}

