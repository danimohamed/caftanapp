<?php

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;
use App\Http\Controllers\CaftanController;
use App\Http\Controllers\RentalController;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider and all of them will
| be assigned to the "api" middleware group. Make something great!
|
*/

// Route pour récupérer l'utilisateur authentifié (si vous utilisez l'authentification)
Route::middleware('auth:sanctum')->get('/user', function (Request $request) {
    return $request->user();
});

/*
|--------------------------------------------------------------------------
| Caftan Routes - Gestion des caftans
|--------------------------------------------------------------------------
*/

// GET /api/caftans - Récupérer tous les caftans
Route::get('/caftans', [CaftanController::class, 'index']);

// GET /api/caftans/{id} - Récupérer un caftan spécifique
Route::get('/caftans/{id}', [CaftanController::class, 'show']);

/*
|--------------------------------------------------------------------------
| Rental Routes - Gestion des locations
|--------------------------------------------------------------------------
*/

// POST /api/rentals - Créer une nouvelle location
Route::post('/rentals', [RentalController::class, 'store']);

// GET /api/rentals - Récupérer toutes les locations
Route::get('/rentals', [RentalController::class, 'index']);

// DELETE /api/rentals/{id} - Supprimer une location
Route::delete('/rentals/{id}', [RentalController::class, 'destroy']);

// GET /api/rentals/{id} - Récupérer une location spécifique (optionnel)
// Route::get('/rentals/{id}', [RentalController::class, 'show']);

