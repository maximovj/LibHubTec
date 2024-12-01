<?php

namespace Database\Factories;

use App\Models\Account;
use App\Models\Book;
use App\Models\Search;
use Illuminate\Database\Eloquent\Factories\Factory;
use Illuminate\Support\Arr;

/**
 * @extends \Illuminate\Database\Eloquent\Factories\Factory<\App\Models\Search>
 */
class SearchFactory extends Factory
{

    protected $model = Search::class;

    /**
     * Define the model's default state.
     *
     * @return array<string, mixed>
     */
    public function definition(): array
    {
        $account = Account::inRandomOrder()->first();
        $search = Arr::random(['Orfanato', 'Victor', 'Activismo', 'El gato negro', 'de la vida', 'quaerat', 'tempora', 'Doloribus']);
        $search_urlencode = urlencode($search);
        $result = Book::where(function($query) use ($search){
            return $query
                ->where('title', 'like', "%$search%")
                ->orWhere('author', 'like', "%$search%")
                ->orWhere('summary', 'like', "%$search%");
        })->count();

        return [
            'account_id' => $account->id,
            'query' => 'q',
            'search' => "$search",
            'base_url' => "/books/list?q=$search_urlencode",
            'result' => $result,
            'created_at' => now(),
            'updated_at' => now(),
        ];
    }
}
