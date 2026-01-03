<?php

namespace App\Http\Controllers;

use Illuminate\Foundation\Auth\Access\AuthorizesRequests;
use Illuminate\Foundation\Validation\ValidatesRequests;
use Illuminate\Routing\Controller as BaseController;

/**
 * Contrôleur de base
 * 
 * Tous les autres contrôleurs héritent de cette classe
 */
class Controller extends BaseController
{
    use AuthorizesRequests, ValidatesRequests;
}
