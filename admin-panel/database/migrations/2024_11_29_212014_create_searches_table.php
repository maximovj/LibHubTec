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
        Schema::create('searches', function (Blueprint $table) {
            $table->id();

            // Crear una llave foránea `account_id`
            $table->foreignId('account_id')
            ->nullable()
            ->constrained('accounts', 'id')
            ->onDelete('cascade')
            ->onUpdate('cascade');

            $table->string('query')->nullable()->default('q');
            $table->string('base_url')->nullable();
            $table->string('search')->nullable();
            $table->unsignedInteger('result')->nullable()->default(0);
            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('searches');
    }
};
