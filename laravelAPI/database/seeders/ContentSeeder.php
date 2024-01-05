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
            'headline' => "Open Recruitment NPLC 2023",
            'content_text' => "Halo semua, kami dari NPLC 2023 mengadakan open recruitment untuk kalian yang ingin bergabung dengan kami. Silahkan klik link dibawah atau scan QR poster untuk mendaftar ya, Terima kasih.
            https://docs.google.com/forms/d/e/1FAIpQLSfMNEtKc5JXWAnIEloRXOLqbgzNy8kNZXOhAWObZyzgZce4zA/viewform",
            'image' => 'https://alpvp.shop/images/database2.jpg',
            'category_id' => 2,
            'user_id' => 2
        ]);
        Content::create([
            'headline' => "Macet",
            'content_text' => "Jangan lewat jalan lontar ya, macet parah",
            'category_id' => 2,
            'user_id' => 2
        ]);

        Content::create([
            'headline' => "Seminar Karir: Sukses Meniti Karir di Industri Teknologi",
            'content_text' => "Segera hadiri seminar karir kami yang akan membahas langkah-langkah sukses dalam membangun karir di industri teknologi.",
            'image' => 'https://alpvp.shop/images/database1.jpeg',
            'category_id' => 2,
            'user_id' => 2
        ]);

        Content::create([
            'headline' => "Call for Papers: Lomba Poster Ilmiah Nasional 2023",
            'content_text' => "Kami membuka peluang pengiriman abstrak untuk lomba poster ilmiah nasional. Jadilah bagian dari acara prestisius ini!",
            'image' => 'https://alpvp.shop/images/database3.jpeg',
            'category_id' => 2,
            'user_id' => 3
        ]);

        Content::create([
            'headline' => "Workshop Kewirausahaan: Pelajari Kiat Sukses Berbisnis",
            'content_text' => "Segera daftarkan diri untuk workshop kewirausahaan kami yang akan membahas strategi dan kiat sukses dalam berbisnis.",
            'image' => '',
            'category_id' => 2,
            'user_id' => 4
        ]);

        Content::create([
            'headline' => "Seminar Kesehatan Mental: Pentingnya Kesehatan Jiwa",
            'content_text' => "Bergabunglah dengan kami dalam seminar penting mengenai kesehatan mental dan dukung gerakan kesehatan jiwa.",
            'image' => '',
            'category_id' => 2,
            'user_id' => 5
        ]);

        Content::create([
            'headline' => "Penelitian: Hubungan Antara Pola Makan dan Produktivitas",
            'content_text' => "Studi baru menunjukkan bahwa pola makan yang sehat berhubungan langsung dengan tingkat produktivitas. Temukan lebih lanjut di sini.",
            'image' => 'https://alpvp.shop/images/database4.jpeg',
            'category_id' => 1,
            'user_id' => 3
        ]);

        Content::create([
            'headline' => "Peran Teknologi: Transformasi Pendidikan dalam Era Digital",
            'content_text' => "Bagaimana teknologi mengubah wajah pendidikan tinggi? Baca lebih lanjut untuk mengetahui peranannya dalam era digital.",
            'image' => 'https://alpvp.shop/images/database5.jpeg',
            'category_id' => 1,
            'user_id' => 4
        ]);

        Content::create([
            'headline' => "Metode Pembelajaran Jarak Jauh yang Efektif",
            'content_text' => "Universitas-universitas terkemuka kini mengembangkan metode pembelajaran jarak jauh yang lebih efektif. Apa rahasianya?",
            'image' => '',
            'category_id' => 1,
            'user_id' => 3
        ]);

        Content::create([
            'headline' => "Tantangan Keuangan Mahasiswa: Studi Kasus dan Solusi",
            'content_text' => "Studi mengenai tantangan keuangan yang dihadapi mahasiswa terus berkembang. Temukan solusi untuk masalah ini.",
            'image' => '',
            'category_id' => 1,
            'user_id' => 4
        ]);

        Content::create([
            'headline' => "Kesehatan Mental di Perguruan Tinggi",
            'content_text' => "Fokus pada kesehatan mental mahasiswa menjadi perhatian utama. Temukan upaya-upaya universitas untuk menciptakan lingkungan yang sehat secara mental.",
            'image' => '',
            'category_id' => 1,
            'user_id' => 5
        ]);

    }
}
