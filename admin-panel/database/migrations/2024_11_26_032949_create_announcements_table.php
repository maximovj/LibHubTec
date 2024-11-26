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
        Schema::create('announcements', function (Blueprint $table) {
            $table->id();

            // Crear una llave foránea `moonshine_user_id`
            $table->foreignId('moonshine_user_id')
            ->nullable()
            ->default(auth()->id())
            ->constrained('moonshine_users', 'id')
            ->onDelete('cascade')
            ->onUpdate('cascade');

            $table->string('title')->nullable();
            $table->text('content')->nullable();
            $table->json('pictures')->nullable();
            $table->string('link')->nullable();
            $table->string('tags')->nullable();
            $table->boolean('deleted')->default(false);
            $table->softDeletes();
            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('announcements');
    }
};
