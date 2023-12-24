<?php

namespace Database\Seeders;

use App\Models\Content;
use Illuminate\Database\Seeder;
use Illuminate\Database\Console\Seeds\WithoutModelEvents;

class ContentSeeder extends Seeder
{
    /**
     * Run the database seeds.
     */
    public function run(): void
    {
        Content::create([
            'headline' => "Headline 1",
            'image' => "https://picsum.photos/200/300",
            'content_text' => "Content Text 1",
            'category_id' => 1,
            'user_id' => 1
        ]);
    }
}
