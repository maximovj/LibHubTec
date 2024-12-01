<?php

namespace Database\Seeders;

use App\Models\Book;
use App\Models\ReserveBook;
use Illuminate\Database\Console\Seeds\WithoutModelEvents;
use Illuminate\Database\Seeder;

class ReserveBookSeeder extends Seeder
{
    /**
     * Run the database seeds.
     */
    public function run(): void
    {
        ReserveBook::truncate();

        $stock = Book::stock();

        ReserveBook::factory($stock)->create();
    }
}
