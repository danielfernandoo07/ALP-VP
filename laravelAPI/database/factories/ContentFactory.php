<?php

namespace Database\Factories;

use Illuminate\Database\Eloquent\Factories\Factory;

/**
 * @extends \Illuminate\Database\Eloquent\Factories\Factory<\App\Models\Content>
 */
class ContentFactory extends Factory
{
    /**
     * Define the model's default state.
     *
     * @return array<string, mixed>
     */
    public function definition(): array
    {
        return [
            'headline' => fake()->sentence(6),
            'image' => fake()->imageUrl(),
            'content_text' => fake()->paragraphs(3, true),
            'category_id' => '1', // Adjust category IDs to your needs
            'user_id' => '1', // Adjust user IDs to your users
        ];
    }
}
