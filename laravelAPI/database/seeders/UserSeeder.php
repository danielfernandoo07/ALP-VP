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
            'email' => 'admin@ciputra.ac.id',
            'password' => bcrypt('123'),
            'nim' => '0000000000001',
            'prodi_id' => 1,
        ]);
        User::create([
            'name' => 'Steven',
            'email' => 'sbudiman@student.ciputra.ac.id',
            'password' => bcrypt('123'),
            'photo' => 'https://yourteachingmentor.com/wp-content/uploads/2020/12/istockphoto-1223671392-612x612-1.jpg',
            'nim' => '0706012210012',
            'prodi_id' => 9,
        ]);
        User::create([
            'name' => 'Daniel',
            'email' => 'dfernando@student.ciputra.ac.id',
            'password' => bcrypt('123'),
            'photo' => 'https://yourteachingmentor.com/wp-content/uploads/2020/12/istockphoto-1223671392-612x612-1.jpg',
            'nim' => '0706012210004',
            'prodi_id' => 9,
        ]);
        User::create([
            'name' => 'Nathan',
            'email' => 'ndarrell@student.ciputra.ac.id',
            'password' => bcrypt('123'),
            'photo' => 'https://yourteachingmentor.com/wp-content/uploads/2020/12/istockphoto-1223671392-612x612-1.jpg',
            'nim' => '0706012210034',
            'prodi_id' => 9,
        ]);
        User::create([
            'name' => 'Landy',
            'email' => 'ferriogent@student.ciputra.ac.id',
            'password' => bcrypt('123'),
            'photo' => 'https://yourteachingmentor.com/wp-content/uploads/2020/12/istockphoto-1223671392-612x612-1.jpg',
            'nim' => '0706012210062',
            'prodi_id' => 9,
        ]);


    }
}
