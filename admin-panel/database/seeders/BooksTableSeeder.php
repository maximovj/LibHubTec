<?php

namespace Database\Seeders;

use App\Models\Book;
use Illuminate\Database\Console\Seeds\WithoutModelEvents;
use Illuminate\Database\Seeder;

class BooksTableSeeder extends Seeder
{
    /**
     * Run the database seeds.
     */
    public function run(): void
    {
        Book::factory()->create([
            'thumbnail' => 'WLn9uTWsNZH8inuUYuOAFlxcKFt2CDPkrp5Z6VBy.jpg',
            'title' => 'Ciberactivismo',
            'author' => 'Mario Táscon y Yolanda Quintana',
            'summary' => 'La historia del internet, como hacer activismo desde la red.',
            'description' => 'Es un libro genial, tipo manual para las personas activistas.',
        ]);

        Book::factory()->create([
            'thumbnail' => 'otR1tQ94i5KSm6abH3qVIqFHzxCoO0OONKnImow7.jpg',
            'title' => 'El Aventurero',
            'author' => 'Santiago Gárcia-Clairac',
            'summary' => 'Relata la vida de Victor un adolescente, después de la segunda guerra mundial.',
            'description' => 'Es una gran libro, que toca temas sensibles para la época como la derrota y la homosexualidad.',
        ]);

        Book::factory()->create([
            'thumbnail' => 'TuQp59xazc7T6YhFEMa53V24sT71dBIGN9Zvslrh.jpg',
            'title' => 'El gato negro',
            'author' => 'Edgar Allan Poe',
            'summary' => 'Relato la vida de una mujer, a lado de su gato negro.',
            'description' => 'Es una historia corta, y sencilla.',
        ]);

        Book::factory()->create([
            'thumbnail' => 'irtzHyWUhwSpWmIgbgx3Jnxp7i9o4wtXomcXyiM3.jpg',
            'title' => 'El orfanato heiskinn',
            'author' => 'Javier Berzosa',
            'summary' => 'Trata de un orfanato de adolescentes, dónde ocurren crimines.',
            'description' => 'Es un libro aterrador con misterios para resolver.',
        ]);
    }
}
