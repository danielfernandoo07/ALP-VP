<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration {
    /**
     * Run the migrations.
     */
    public function up(): void
    {
        Schema::create('users', function (Blueprint $table) {
            $table->id();
            $table->string('name');
            $table->string('email')->unique();
            $table->timestamp('email_verified_at')->nullable();
            $table->string('password');
            $table->string('photo')->nullable();
            $table->string('nim');
            $table->string('bio')->nullable();
            $table->unsignedBigInteger('prodi_id');
            $table->unsignedBigInteger('friend_id')->nullable();
            $table->rememberToken();
            $table->timestamps();

            $table->foreign('prodi_id')
                    ->references('id')
                    ->on('prodis')
                    ->onDelete('cascade');

            $table->foreign('friend_id')
                    ->references('id')
                    ->on('users');

        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('users');
    }
};
