<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration
{
    /**
     * Run the migrations.
     */
    public function up(): void
    {
        Schema::create('recover_accounts', function (Blueprint $table) {
            $table->id();

            // Create a foreign key called `account_id` between account and recover_passwords
            $table->foreignId('account_id')
            ->nullable()
            ->constrained('accounts', 'id')
            ->onDelete('cascade')
            ->onUpdate('cascade');

            $table->string('name')->nullable()->default('NA');
            $table->string('last_name')->nullable()->default('NA');
            $table->string('email')->nullable()->default('NA');
            $table->text('token')->nullable();
            $table->integer('code')->default(random_int(10000,99999));
            $table->boolean('active')->default(true);

            $table->unique(['account_id']);
            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('recover_accounts');
    }
};
