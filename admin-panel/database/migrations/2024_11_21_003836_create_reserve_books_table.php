<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\DB;
use Illuminate\Support\Facades\Schema;

return new class extends Migration
{
    /**
     * Run the migrations.
     */
    public function up(): void
    {
        Schema::create('reserve_books', function (Blueprint $table) {
            $table->id();

            // Información (copia de seguridad)
            $table->string('account_username')->nullable()->default('NA');
            $table->string('account_email')->nullable()->default('NA');
            $table->string('account_name')->nullable()->default('NA');
            $table->string('account_last_name')->nullable()->default('NA');
            $table->string('book_title')->nullable()->default('NA');
            $table->string('book_author')->nullable()->default('NA');
            $table->unsignedInteger('book_count')->nullable()->default(1);
            $table->decimal('book_price', 18, 2)->nullable()->default(0.0);

            // Crear llave foránea `account_id`
            $table->foreignId('account_id')
            ->nullable()
            ->constrained('accounts', 'id')
            ->onDelete('cascade')
            ->onUpdate('cascade');

            // Crear llave foránea `book_id`
            $table->foreignId('book_id')
            ->nullable()
            ->constrained('books', 'id')
            ->onDelete('cascade')
            ->onUpdate('cascade');

            // Control de reservación
            $table->timestamp('date_from', 0)->nullable()->default(DB::raw('CURRENT_TIMESTAMP')); // fecha de inicio (recoger)
            $table->timestamp('date_to', 0)->nullable()->default(DB::raw('CURRENT_TIMESTAMP')); // fecha final (entregar)
            $table->string('status')->nullable()->default('pendiente'); // estado: pendiente, procesando, pagado, adeudo
            $table->boolean('active')->nullable()->default(true);
            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('reserve_books');
    }
};
