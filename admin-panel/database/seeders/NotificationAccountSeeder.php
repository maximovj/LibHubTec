<?php

namespace Database\Seeders;

use App\Models\NotificationAccount;
use Illuminate\Database\Console\Seeds\WithoutModelEvents;
use Illuminate\Database\Seeder;

class NotificationAccountSeeder extends Seeder
{
    /**
     * Run the database seeds.
     */
    public function run(): void
    {
        NotificationAccount::truncate();

        NotificationAccount::factory()->create([
            'moonshine_user_id' => 1,
            'account_id' => 1,
            'subject' => '6 nuevos libros',
            'content' => 'Hola, hemos agregado 6 nuevos libros a nuestro catálogo',
            'attach' => null,
            'signature'  =>  null,
            'status' => 'send',
            'send_email' => false,
            'tags' => ["Nuevo libros"],
            'created_at' => now(),
            'updated_at' => now(),
        ]);

        NotificationAccount::factory()->create([
            'moonshine_user_id' => 1,
            'account_id' => 2,
            'subject' => '6 nuevos libros',
            'content' => 'Hola, hemos agregado 6 nuevos libros a nuestro catálogo',
            'attach' => null,
            'signature'  =>  null,
            'status' => 'send',
            'send_email' => false,
            'tags' => ["Nuevo libros"],
            'created_at' => now(),
            'updated_at' => now(),
        ]);

        //NotificationAccount::factory(100)->create();
    }
}
