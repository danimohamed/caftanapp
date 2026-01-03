<?php

namespace App\Http\Controllers;

use App\Models\Caftan;
use Illuminate\Http\Request;

/**
 * Contrôleur pour gérer les caftans
 * 
 * Ce contrôleur gère toutes les opérations liées aux caftans :
 * - Afficher la liste des caftans
 * - Afficher les détails d'un caftan spécifique
 */
class CaftanController extends Controller
{
    /**
     * Afficher la liste de tous les caftans
     * 
     * Route: GET /api/caftans
     * 
     * Retourne tous les caftans avec leurs informations (nom, taille, prix, image, disponibilité)
     * 
     * @return \Illuminate\Http\JsonResponse
     */
    public function index()
    {
        // Récupérer tous les caftans de la base de données
        $caftans = Caftan::all();

        // Retourner les caftans en format JSON
        return response()->json([
            'success' => true,
            'message' => 'Liste des caftans récupérée avec succès',
            'data' => $caftans
        ], 200);
    }

    /**
     * Afficher les détails d'un caftan spécifique
     * 
     * Route: GET /api/caftans/{id}
     * 
     * Retourne les informations d'un caftan selon son ID
     * 
     * @param int $id L'ID du caftan à afficher
     * @return \Illuminate\Http\JsonResponse
     */
    public function show($id)
    {
        // Chercher le caftan par son ID
        $caftan = Caftan::find($id);

        // Si le caftan n'existe pas, retourner une erreur 404
        if (!$caftan) {
            return response()->json([
                'success' => false,
                'message' => 'Caftan non trouvé'
            ], 404);
        }

        // Retourner le caftan trouvé en format JSON
        return response()->json([
            'success' => true,
            'message' => 'Caftan récupéré avec succès',
            'data' => $caftan
        ], 200);
    }
}
