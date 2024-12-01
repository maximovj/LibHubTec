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
            'shift'=> 'Vespertino',
            'bio' => 'Me apasiona la tecnología, me gusta escuchar música y mis hobbies es la programación.',
            'username' => 'victor.maximo',
            'email' => 'victor.maximo@demo.com',
            'password' => Hash::make('victor.maximo@demo.com'),
        ]);

        Account::factory(10)->create();
    }
}
