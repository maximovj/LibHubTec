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
        Schema::create('notification_accounts', function (Blueprint $table) {
            $table->id();

            // Crear una llave foránea `moonshine_user_id`
            // Este es el remitente
            $table->foreignId("moonshine_user_id")
            ->default(auth()->id())
            ->constrained("moonshine_users", "id")
            ->onDelete("cascade")
            ->onUpdate("cascade");

            // Crear una llave foránea `account_id`
            // Este es el destinatario
            $table->foreignId("account_id")
            ->nullable()
            ->constrained("accounts", "id")
            ->onDelete("cascade")
            ->onUpdate("cascade");

            $table->string("subject")->nullable()->default("Sin asunto");
            $table->text("content")->nullable();
            $table->string("attach")->nullable();
            $table->string("signature")->nullable();
            $table->enum("status", ["pendiente", "enviado","reenviado", "leído"])->default("pendiente");
            $table->boolean("send_email")->nullable()->default(false);
            $table->json("tags")->nullable();
            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('notification_accounts');
    }
};
