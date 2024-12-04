<?php

namespace App\Console\Commands;

use Illuminate\Console\Command;
use Illuminate\Support\Facades\Http;
use ZipArchive;

class PublishStorage extends Command
{
    /**
     * The name and signature of the console command.
     *
     * @var string
     */
    protected $signature = 'app:publish-storage';

    /**
     * The console command description.
     *
     * @var string
     */
    protected $description = 'Descargar el storage del proyecto LibHubTec';

    /**
     * Execute the console command.
     */
    public function handle()
    {
        $url = 'https://github.com/maximovj/LibHubTec/releases/download/v1.0Release/storage_public.zip';

        // Definir la ruta dónde se descomprime el archivo zip
        $destinationPath = storage_path("app/public");

        // Paso 1: Asegurar que la carpea exista
        if (!is_dir($destinationPath)) {
            $this->info("Creando la carpeta: {$destinationPath}");
            mkdir($destinationPath, 0755, true);
        }

        // Paso 2 : Descargar el archivo ZIP
        $this->info('Descargando el archivo comprimido (zip)...');
        $zipPath = storage_path('temp.zip');
        $response = Http::get($url);

        if (!$response->successful()) {
            $this->error('Oops fallo al descargar el archivo ZIP.');
            return 1;
        }

        file_put_contents($zipPath, $response->body());
        $this->info('Descarga completada.');

        // Paso 3: Extraer el archivo zip
        $this->info('Extrayendo el archivo comprimido (zip)...');
        $zip = new ZipArchive();

        if ($zip->open($zipPath) === true) {
            $zip->extractTo($destinationPath);
            $zip->close();
            $this->info("Extracción completada. Extraído en: {$destinationPath}");
        } else {
            $this->error('Oops falló al extraer el archivo comprimido (zip).');
            return 1;
        }

        // Paso 4: Eliminar el archivo ZIP
        unlink($zipPath);
        $this->info('Eliminado residuos.');

        return 0;
    }
}
