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
        Schema::create('books', function (Blueprint $table) {
            $table->id();
            $table->string('cover')->nullable();
            $table->string('title')->nullable();
            $table->string('author')->nullable();
            $table->text('summary')->nullable();
            $table->text('description')->nullable();
            $table->unsignedInteger('stock')->nullable()->default(0);
            $table->decimal('price', 18, 2)->nullable()->default(0.0);
            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('books');
    }
};
