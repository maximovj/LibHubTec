<?php

namespace Database\Seeders;

use App\Models\Search;
use Illuminate\Database\Console\Seeds\WithoutModelEvents;
use Illuminate\Database\Seeder;

class SearchSeeder extends Seeder
{
    /**
     * Run the database seeds.
     */
    public function run(): void
    {
        Search::truncate();

        Search::factory(100)->create();
    }
}
