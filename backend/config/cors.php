<?php

return [

    /*
    |--------------------------------------------------------------------------
    | Cross-Origin Resource Sharing (CORS) Configuration
    |--------------------------------------------------------------------------
    |
    | Configuration pour permettre aux applications mobiles et web
    | d'accéder à l'API depuis différents domaines
    |
    */

    // Chemins où CORS est appliqué
    'paths' => ['api/*'],

    // Méthodes HTTP autorisées
    'allowed_methods' => ['*'],

    // Origines autorisées (tous les domaines pour le développement)
    'allowed_origins' => ['*'],

    // Patterns d'origines autorisées
    'allowed_origins_patterns' => [],

    // En-têtes autorisés
    'allowed_headers' => ['*'],

    // En-têtes exposés
    'exposed_headers' => [],

    // Durée de cache de la réponse preflight
    'max_age' => 0,

    // Permettre les cookies
    'supports_credentials' => false,

];
