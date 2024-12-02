<?php

namespace Database\Factories;

use App\Models\Account;
use App\Models\Book;
use App\Models\ReserveBook;
use Carbon\Carbon;
use Illuminate\Database\Eloquent\Factories\Factory;

/**
 * @extends \Illuminate\Database\Eloquent\Factories\Factory<\App\Models\ReserveBook>
 */
class ReserveBookFactory extends Factory
{
    /**
     * Define the model's default state.
     *
     * @return array<string, mixed>
     */
    public function definition(): array
    {
        $book = Book::inRandomOrder()->first();
        $account = Account::inRandomOrder()->first();

        if($book->stock <= 0) {
            return [];
        }

        if(ReserveBook::isAlreadyExists($account, $book)) {
            return [];
        }


        $book->stock = $book->stock - 1;
        $book->save();

        return [
            'account_id' => $account->id,
            'account_username' => $account->username,
            'account_email' => $account->email,
            'account_name' => $account->name,
            'account_last_name' => $account->last_name,
            'book_id' => $book->id,
            'book_title' => $book->title,
            'book_author' => $book->author,
            'book_count' => 1,
            'book_price' => $book->price,
            'date_from' => Carbon::now(),
            'date_to' => Carbon::now()->subDays(30),
            'status' => 'pending',
            'active' => true,
        ];
    }
}
