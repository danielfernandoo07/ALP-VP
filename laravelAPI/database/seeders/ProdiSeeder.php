<?php

namespace Database\Seeders;

use App\Models\Prodi;
use Illuminate\Database\Seeder;
use Illuminate\Database\Console\Seeds\WithoutModelEvents;

class ProdiSeeder extends Seeder
{
    /**
     * Run the database seeds.
     */
    public function run(): void
    {
        Prodi::create([
            'name' => 'IBM-RC'
        ]);
        Prodi::create([
            'name' => 'IBM-IC'
        ]);
        Prodi::create([
            'name' => 'ACC'
        ]);
        Prodi::create([
            'name' => 'MED'
        ]);
        Prodi::create([
            'name' => 'FIKOM'
        ]);
        Prodi::create([
            'name' => 'INA'
        ]);
        Prodi::create([
            'name' => 'FPD'
        ]);
        Prodi::create([
            'name' => 'VCD'
        ]);
        Prodi::create([
            'name' => 'IMT'
        ]);
        Prodi::create([
            'name' => 'ISB'
        ]);
        Prodi::create([
            'name' => 'HTB'
        ]);
        Prodi::create([
            'name' => 'CB'
        ]);
        Prodi::create([
            'name' => 'PSY'
        ]);
        Prodi::create([
            'name' => 'FTP'
        ]);
    }
}
