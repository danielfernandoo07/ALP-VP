<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Comment extends Model
{
    use HasFactory;

    protected $fillable=[
        'comment_text',
        'content_id',
        'user_id'
    ];

    public function content(){
        return $this->belongsTo(Content::class);
    }

    public function user(){
        return $this->belongsTo(User::class);
    }

}
