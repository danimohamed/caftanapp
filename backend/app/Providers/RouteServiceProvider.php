<?php

namespace App\Providers;

use Illuminate\Cache\RateLimiting\Limit;
use Illuminate\Foundation\Support\Providers\RouteServiceProvider as ServiceProvider;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\RateLimiter;
use Illuminate\Support\Facades\Route;

/**
 * Service Provider pour les routes de l'application
 * Configure comment les routes sont chargées
 */
class RouteServiceProvider extends ServiceProvider
{
    /**
     * Le path vers le "home" de votre application
     *
     * @var string
     */
    public const HOME = '/home';

    /**
     * Définir les routes de l'application
     */
    public function boot(): void
    {
        RateLimiter::for('api', function (Request $request) {
            return Limit::perMinute(60)->by($request->user()?->id ?: $request->ip());
        });

        $this->routes(function () {
            // Routes API
            // Toutes les routes dans api.php auront le préfixe /api
            Route::prefix('api')
                ->middleware('api')
                ->group(base_path('routes/api.php'));
        });
    }
}
