<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Relations\HasMany;

class Message extends Model
{
    use HasFactory;

    protected $fillable = [
        'user1_id',
        'user2_id',
        'message_text'
    ];

    public function users_1() : HasMany
    {
        return $this->hasMany(User::class, 'user1_id', 'id');
    }

    public function users_2() : HasMany
    {
        return $this->hasMany(User::class, 'user2_id', 'id');
    }


}
