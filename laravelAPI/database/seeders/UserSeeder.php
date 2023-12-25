<?php

namespace Database\Seeders;

use App\Models\User;
use Illuminate\Database\Seeder;
use Illuminate\Database\Console\Seeds\WithoutModelEvents;

class UserSeeder extends Seeder
{
    /**
     * Run the database seeds.
     */
    public function run(): void
    {
        User::create([
            'name' => 'Admin',
            'email' => 'admin@example.com',
            'password' => bcrypt('123'),
            'nim' => '0706012210004',
            'prodi_id' => 1,
        ]);
        User::create([
            'name' => 'user1',
            'email' => 'user1@example.com',
            'password' => bcrypt('123'),
            'nim' => '0706012210005',
            'prodi_id' => 1,
        ]);
        User::create([
            'name' => 'user2',
            'email' => 'user2@example.com',
            'password' => bcrypt('123'),
            'nim' => '0706012210006',
            'prodi_id' => 1,
        ]);
    }
}
