<?php

namespace Database\Factories;

use App\Models\Account;
use Illuminate\Database\Eloquent\Factories\Factory;
use Illuminate\Support\Arr;
use MoonShine\Models\MoonshineUser;

/**
 * @extends \Illuminate\Database\Eloquent\Factories\Factory<\App\Models\NotificationAccount>
 */
class NotificationAccountFactory extends Factory
{
    /**
     * Define the model's default state.
     *
     * @return array<string, mixed>
     */
    public function definition(): array
    {
        $moonshine_user = MoonshineUser::inRandomOrder()->first();
        $account = Account::inRandomOrder()->first();
        $tags = ['Test', 'Prueba', 'Urgente', 'Aviso', 'Recomendación', 'Cita'];

        return [
            'moonshine_user_id' => $moonshine_user->id,
            'account_id' => $account->id,
            'subject' => fake()->paragraph(1),
            'content' => fake()->paragraph(6),
            'attach' => null,
            'signature'  =>  null,
            'status' => Arr::random(['pending','send','resend','read']),
            'send_email' => Arr::random([true, false]),
            'tags' => Arr::random($tags, 3),
            'created_at' => now(),
            'updated_at' => now(),
        ];
    }
}
