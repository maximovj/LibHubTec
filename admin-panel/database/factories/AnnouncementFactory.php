<?php

namespace Database\Factories;

use Illuminate\Database\Eloquent\Factories\Factory;
use Illuminate\Support\Arr;
use MoonShine\Models\MoonshineUser;

/**
 * @extends \Illuminate\Database\Eloquent\Factories\Factory<\App\Models\Announcement>
 */
class AnnouncementFactory extends Factory
{
    /**
     * Define the model's default state.
     *
     * @return array<string, mixed>
     */
    public function definition(): array
    {
        $moonshine_user = MoonshineUser::inRandomOrder()->first();

        return [
            'moonshine_user_id' => $moonshine_user->id,
            'title' => fake()->paragraph(1),
            'content' => fake()->paragraph(8),
            'pictures' => null,
            'link' =>  fake()->url(),
            'tags' => 'test,anuncio,urgente',
            'is_published' => Arr::random([true, false]),
            'created_at' => now(),
            'updated_at' => now(),
        ];
    }
}
