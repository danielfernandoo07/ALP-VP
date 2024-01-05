<?php

namespace Database\Seeders;

use App\Models\Comment;
use Illuminate\Database\Seeder;
use Illuminate\Database\Console\Seeds\WithoutModelEvents;

class CommentSeeder extends Seeder
{
    /**
     * Run the database seeds.
     */
    public function run(): void
    {

        Comment::create([
            'content_id' => 1,
            'user_id' => 3,
            'comment' => 'Open Recruitment NPLC 2023, sangat bagus untuk memperluas partisipasi anggota baru!'
        ]);
        Comment::create([
            'content_id' => 2,
            'user_id' => 4,
            'comment' => 'Diharapkan tidak ada lagi info macet, terima kasih.'
        ]);
        Comment::create([
            'content_id' => 3,
            'user_id' => 1,
            'comment' => 'Seminar karir di industri teknologi akan sangat bermanfaat!'
        ]);
        Comment::create([
            'content_id' => 4,
            'user_id' => 5,
            'comment' => 'Keren! Workshop kewirausahaan pasti menarik banyak peserta.'
        ]);
        Comment::create([
            'content_id' => 5,
            'user_id' => 2,
            'comment' => 'Kesehatan mental sangat penting, semoga seminar ini sukses!'
        ]);
        Comment::create([
            'content_id' => 6,
            'user_id' => 4,
            'comment' => 'Studi yang menarik, hubungan antara pola makan dan produktivitas sangat menarik untuk dijelajahi.'
        ]);
        Comment::create([
            'content_id' => 7,
            'user_id' => 3,
            'comment' => 'Penting untuk memahami transformasi pendidikan oleh teknologi.'
        ]);
        Comment::create([
            'content_id' => 8,
            'user_id' => 5,
            'comment' => 'Metode pembelajaran jarak jauh adalah masa depan.'
        ]);
        Comment::create([
            'content_id' => 9,
            'user_id' => 1,
            'comment' => 'Studi yang sangat relevan dengan kehidupan mahasiswa saat ini.'
        ]);
        Comment::create([
            'content_id' => 10,
            'user_id' => 2,
            'comment' => 'Pentingnya fokus pada kesehatan mental di lingkungan akademik.'
        ]);

    }
}
