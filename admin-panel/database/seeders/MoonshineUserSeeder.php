<?php

namespace Database\Seeders;

use Illuminate\Database\Console\Seeds\WithoutModelEvents;
use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\Hash;
use MoonShine\Models\MoonshineUser;

class MoonshineUserSeeder extends Seeder
{
    /**
     * Run the database seeds.
     */
    public function run(): void
    {
        MoonshineUser::truncate();

        MoonshineUser::factory()->create([
            'moonshine_user_role_id' => 1,
            'email' => 'admin@admin.com',
            'password' => Hash::make('password'),
            'name' => 'Admin',
            'avatar' => 'moonshine_users/bu9VJlTqn9FEW4EPED8lEkCFOSGd3rE4XH2pSJeD.jpg',
            'remember_token' => null,
            'created_at' => now(),
            'updated_at' => now(),
        ]);

        //MoonshineUser::factory(10)->create();
    }
}
