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

        NotificationAccount::factory(100)->create();
    }
}
