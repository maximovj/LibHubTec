<?php

namespace Database\Seeders;

use App\Models\Account;
use App\Models\RecoverAccount;
use Illuminate\Database\Console\Seeds\WithoutModelEvents;
use Illuminate\Database\Seeder;

class RecoverAccountSeeder extends Seeder
{
    /**
     * Run the database seeds.
     */
    public function run(): void
    {
        RecoverAccount::truncate();

        $accounts =  Account::count();

        RecoverAccount::factory($accounts)->create();
    }
}
