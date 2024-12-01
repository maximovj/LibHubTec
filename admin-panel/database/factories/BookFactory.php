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
            'cover' => null,
            'title' => fake()->sentence(),
            'author' => fake()->name(),
            'summary' => fake()->text(),
            'description' => fake()->paragraph(),
            'stock' => fake()->numberBetween(1,40),
            'price' => fake()->randomFloat(2,5,399),
        ];
    }
}
