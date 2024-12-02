<?php

namespace Database\Seeders;

use App\Models\Book;
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

        $searches = ['Un mundo peor', 'Pasado', 'Activismo', 'Victor', 'en el ', 'Orfanato'];

        foreach($searches as $search) {
            $search_urlencode = urlencode($search);
            $result = Book::resultSearch($search);

            Search::factory()->create([
                'account_id' => 1,
                'query' => 'q',
                'search' => "$search",
                'base_url' => "/books/list?q=$search_urlencode",
                'result' => $result,
                'created_at' => now(),
                'updated_at' => now(),
            ]);
        }

        //Search::factory(100)->create();
    }
}
