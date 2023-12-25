<?php

namespace App\Http\Resources;

use Illuminate\Contracts\Encryption\DecryptException;
use Illuminate\Http\Request;
use Illuminate\Http\Resources\Json\JsonResource;
use Illuminate\Support\Facades\Crypt;

class UserResource extends JsonResource
{
    /**
     * Transform the resource into an array.
     *
     * @return array<string, mixed>
     */
    public function toArray(Request $request): array
    {
        return [
            'id' => $this->id,
            'name' => $this->name,
            'email' => $this->email,
            'password' => $this->password,
            'nim' => $this->nim,
            'photo' => $this->photo,
            'bio' => $this->bio,
            'prodi_id' => $this->prodi_id,
            'friend_id' => $this->friend_id
        ];
    }
}
