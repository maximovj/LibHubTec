<?php

namespace Database\Seeders;

use App\Models\Account;
use Illuminate\Database\Console\Seeds\WithoutModelEvents;
use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\Hash;

class AccountSeeder extends Seeder
{
    /**
     * Run the database seeds.
     */
    public function run(): void
    {
        Account::truncate();

        Account::factory()->create([
            'name' => 'Victor',
            'last_name' => 'Máximo',
            'control_number' => '19022441',
            'sex' => 'hombre',
            'age' => 25,
            'grade' => 9,
            'shift'=> 'vespertino',
            'photo' => 'accounts/pwqEtB563biL5dDONAQDmCC2DkfMkmm0M2ppqn21.png',
            'bio' => 'Soy un apasionado por la tecnología, me encanta escuchar música y mi hobby es la programación.',
            'username' => 'victor.maximo',
            'email' => 'victor.maximo@example.com',
            'password' => Hash::make('password'),
        ]);

        Account::factory()->create([
            'name' => 'User',
            'last_name' => 'Demo',
            'control_number' => '1800404',
            'sex' => 'otro',
            'age' => 18,
            'grade' => 1,
            'shift'=> 'matutino',
            'photo' => 'accounts/CXkWgq7nlcri0bm2EmLLHGjuOjxpSeNG6AJ0pW2e.png',
            'bio' => 'Soy un usuario de pruebas.',
            'username' => 'user.demo',
            'email' => 'use.demo@example.com',
            'password' => Hash::make('password'),
        ]);

        //Account::factory(10)->create();
    }
}
