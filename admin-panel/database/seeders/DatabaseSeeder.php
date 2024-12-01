<?php

namespace Database\Seeders;

use App\Models\User;
// use Illuminate\Database\Console\Seeds\WithoutModelEvents;
use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\DB;

class DatabaseSeeder extends Seeder
{
    /**
     * Seed the application's database.
     */
    public function run(): void
    {
        DB::statement('SET FOREIGN_KEY_CHECKS=0;');

        $this->call([
            MoonshineUserSeeder::class,
            BookSeeder::class,
            AccountSeeder::class,
            ReserveBookSeeder::class,
            NotificationAccountSeeder::class,
            RecoverAccountSeeder::class,
            AnnouncementSeeder::class,
            SearchSeeder::class,
        ]);

        DB::statement('SET FOREIGN_KEY_CHECKS=1;');
    }
}
