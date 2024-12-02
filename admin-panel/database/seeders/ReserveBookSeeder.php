<?php

namespace Database\Seeders;

use App\Models\Account;
use App\Models\Book;
use App\Models\ReserveBook;
use Carbon\Carbon;
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

        $account = Account::find(1);
        $bookIds = [1,4,6];

        foreach($bookIds as $item ) {
            $book = Book::find($item);
            ReserveBook::factory()->create([
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
                'date_to' => Carbon::now()->addDays(7),
                'status' => 'pending',
                'active' => true,
                'created_at' => now(),
                'updated_at' => now(),
            ]);
        }

        //$stock = Book::stock();
        //ReserveBook::factory($stock)->create();
    }
}
