<?php

namespace Database\Factories;

use Illuminate\Support\Str;
use App\Models\Book;
use Illuminate\Database\Eloquent\Factories\Factory;

class BookFactory extends Factory
{
    protected $model = Book::class;

    public function definition()
    {
        return [
            'thumbnail' => null,
            'title' => $this->faker->sentence,
            'author' => $this->faker->name,
            'summary' => $this->faker->text,
            'description' => $this->faker->paragraph,
        ];
    }
}
