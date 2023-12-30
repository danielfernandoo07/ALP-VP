<?php

namespace App\Models;

use Carbon\Carbon;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Factories\HasFactory;

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

    protected $dates = [
        'created_at',
        'updated_at',
    ];

    public function getCreatedAtAttribute($value)
    {
        return Carbon::parse($value)->timezone('Asia/Jakarta')->format('Y-m-d H:i:s');
    }

    public function getUpdatedAtAttribute($value)
{
    return Carbon::parse($value)->timezone('Asia/Jakarta')->format('Y-m-d H:i:s');
}

}
