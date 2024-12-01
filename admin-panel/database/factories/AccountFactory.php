<?php

namespace Database\Factories;

use App\Models\Account;
use Illuminate\Database\Eloquent\Factories\Factory;
use Illuminate\Support\Facades\Hash;
use Illuminate\Support\Str;

/**
 * @extends \Illuminate\Database\Eloquent\Factories\Factory<\App\Models\Account>
 */
class AccountFactory extends Factory
{

    protected $model = Account::class;

    protected static ?string $password;

    /**
     * Define the model's default state.
     *
     * @return array<string, mixed>
     */
    public function definition(): array
    {
        return [
            'name' => fake()->name(),
            'last_name' => fake()->lastName(),
            'control_number' => fake()->numberBetween(10001000,99999999),
            'sex' => fake()->randomElement(['mujer','hombre', 'binario', 'otro']),
            'age' => fake()->numberBetween(18,190),
            'grade' => fake()->numberBetween(1,20),
            'shift'=> fake()->randomElement(['matutino','vespertino']),
            'bio' => Str::substr(fake()->paragraph(), 0, 60),
            'username' => fake()->userName(),
            'email' => fake()->unique()->safeEmail(),
            'password' => Hash::make('password'),
        ];
    }
}
