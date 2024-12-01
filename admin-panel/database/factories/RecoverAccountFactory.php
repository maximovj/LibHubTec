<?php

namespace Database\Factories;

use App\Models\Account;
use App\Models\RecoverAccount;
use Illuminate\Database\Eloquent\Factories\Factory;

/**
 * @extends \Illuminate\Database\Eloquent\Factories\Factory<\App\Models\RecoverAccount>
 */
class RecoverAccountFactory extends Factory
{

    protected $model = RecoverAccount::class;

    /**
     * Define the model's default state.
     *
     * @return array<string, mixed>
     */
    public function definition(): array
    {
        $account_id = fake()->unique()->randomElement(Account::pluck('id')->toArray());
        $account = Account::find($account_id);

        return [
            'account_id' => $account->id,
            'name' => $account->name,
            'last_name' => $account->last_name,
            'email' => $account->email,
            'token' => null,
            'code' => fake()->numberBetween(10000, 99999),
            'active' => fake()->randomElement([true, false]),
            'created_at' => now(),
            'updated_at' => now(),
        ];
    }
}
